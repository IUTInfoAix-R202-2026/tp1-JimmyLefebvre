#!/usr/bin/env bash
#
# Regenere les captures "Resultat attendu" des apps GUI du TP1 (branche
# solution) dans .github/assets/apercu-*.png.
#
# Outil de MAINTENANCE (enseignant) : a relancer apres modification des
# exercices, pour que les visuels du README restent a jour. Les etudiants
# n'en ont pas besoin.
#
# Prerequis : xvfb (xvfb-run) + ImageMagick (import, convert). Tout tourne
# en headless ; chaque app est lancee via `./mvnw javafx:run` en surchargeant
# le mainClass, capturee, puis arretee.
#
# Usage (depuis la racine du TP) :
#   ./.github/assets/capture-screenshots.sh            # toutes les captures
#   ./.github/assets/capture-screenshots.sh ex5        # une seule
#   ./.github/assets/capture-screenshots.sh ex5 ex6    # plusieurs
#
set -uo pipefail

ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/../.." && pwd)"
cd "$ROOT"
ASSETS=.github/assets
mkdir -p "$ASSETS"
SCREEN="-screen 0 1366x900x24"
WAIT=24   # secondes : demarrage de Maven + JavaFX avant la capture

# cle -> "mainClass|fichier de sortie (sans .png)"
declare -A APP=(
  [lanceur]="tp1.javafx/fr.univ_amu.iut.App|apercu-lanceur"
  [ex1]="tp1.javafx/fr.univ_amu.iut.exercice1.PremiereFenetre|apercu-ex1-premiere-fenetre"
  [ex2]="tp1.javafx/fr.univ_amu.iut.exercice2.StagePersonnalise|apercu-ex2-stage-personnalise"
  [ex3]="tp1.javafx/fr.univ_amu.iut.exercice3.PremiereScene|apercu-ex3-premiere-scene"
  [ex4]="tp1.javafx/fr.univ_amu.iut.exercice4.MiseEnPage|apercu-ex4-mise-en-page"
  [ex5]="tp1.javafx/fr.univ_amu.iut.exercice5.EvenementsBouton|apercu-ex5-evenements-bouton"
  [ex6]="tp1.javafx/fr.univ_amu.iut.exercice6.Palette|apercu-ex6-palette"
  [bonus7]="tp1.javafx/fr.univ_amu.iut.bonus7.BalleRebondissante|apercu-bonus7-balle"
  [bonus8]="tp1.javafx/fr.univ_amu.iut.bonus8.JeuPacman|apercu-bonus8-pacman"
)
ORDER=(lanceur ex1 ex2 ex3 ex4 ex5 ex6 bonus7 bonus8)

# Lance une app et capture sa fenetre. $1 = mainClass, $2 = nom de sortie, $3 = cle.
# Certaines apps sont "cliquees" via xdotool avant la capture, pour montrer un
# etat actif conforme a la maquette (ex6 : palette deja coloree).
# Les variables sont passees par l'environnement -> bash -c en quotes simples,
# pour eviter tout probleme d'echappement.
capture() {
  local main="$1" out="$2" key="${3:-}"
  MAIN="$main" OUT="$out" KEY="$key" WAITS="$WAIT" \
    xvfb-run -a --server-args="$SCREEN" bash -c '
      ./mvnw -q -Djavafx.mainClass="$MAIN" javafx:run >"/tmp/cap-$OUT.log" 2>&1 &
      M=$!
      sleep "$WAITS"
      # Etat actif (clics reels) pour coller a la maquette de certains exercices.
      if [ "$KEY" = ex6 ] && command -v xdotool >/dev/null 2>&1; then
        # Palette : Rouge, Bleu, Rouge -> zone rouge + "Rouge: 2  Vert: 0  Bleu: 1".
        eval "$(xdotool search --class iut.exercice6.Palette getwindowgeometry --shell 2>/dev/null | grep -E "^(X|Y)=")"
        if [ -n "${X:-}" ]; then
          xdotool mousemove $((X + 51)) $((Y + 30)) click 1; sleep 0.4
          xdotool mousemove $((X + 224)) $((Y + 30)) click 1; sleep 0.4
          xdotool mousemove $((X + 51)) $((Y + 30)) click 1; sleep 0.6
        fi
      fi
      import -window root "/tmp/$OUT-raw.png" 2>/dev/null
      ap=$(pgrep -f "enable-native-access=javafx.graphics --module-path" | head -1)
      [ -n "$ap" ] && kill -9 "$ap" 2>/dev/null
      kill -9 "$M" 2>/dev/null
      exit 0
    '
  convert "/tmp/$out-raw.png" -trim +repage "$ASSETS/$out.png"
  echo "  $out.png  ($(identify -format '%wx%h' "$ASSETS/$out.png" 2>/dev/null))"
}

echo "Pre-compilation..."
./mvnw -q compile

if [ "$#" -ge 1 ]; then
  echo "Captures demandees : $* -> $ASSETS/"
  for k in "$@"; do
    entry="${APP[$k]:-}"
    if [ -z "$entry" ]; then echo "Cle inconnue : $k (attendu : ${ORDER[*]})" >&2; continue; fi
    capture "${entry%%|*}" "${entry##*|}" "$k"
  done
else
  echo "Toutes les captures -> $ASSETS/"
  for k in "${ORDER[@]}"; do
    entry="${APP[$k]}"
    capture "${entry%%|*}" "${entry##*|}" "$k"
  done
fi

# Filet : tuer un eventuel process d'app residuel.
pgrep -f "enable-native-access=javafx.graphics --module-path" | xargs -r kill -9 2>/dev/null
echo "Termine."
