package fr.univ_amu.iut.bonus8;

import javafx.scene.Group;

/**
 * Classe de base pour les personnages du jeu.
 *
 * <p>Un personnage est un {@link Group} de formes géométriques qui peut se déplacer dans les 4
 * directions sur un plateau de jeu. Chaque déplacement avance de {@link #LARGEUR_PERSONNAGE} pixels
 * (une case de la grille 20×20).
 */
public class Personnage extends Group {

  protected static final double LARGEUR_MOITIE_PERSONNAGE = 10;
  protected static final double LARGEUR_PERSONNAGE = LARGEUR_MOITIE_PERSONNAGE * 2;
  protected String direction;

  public void directionGauche() {
    if (getLayoutX() >= LARGEUR_PERSONNAGE) {
      setLayoutX(getLayoutX() - LARGEUR_PERSONNAGE);
    }
    if (!direction.equals("gauche")) {
      direction = "gauche";
    }
  }

  public void directionDroite(double largeurJeu) {
    if (getLayoutX() < largeurJeu - LARGEUR_PERSONNAGE) {
      setLayoutX(getLayoutX() + LARGEUR_PERSONNAGE);
    }
    if (!direction.equals("droite")) {
      direction = "droite";
    }
  }

  public void directionBas(double hauteurJeu) {
    // TODO exercice 8 : déplacement vers le bas.
    //
    // Même logique que directionGauche/directionDroite mais sur l'axe Y.
    // Vérifier que le personnage ne sort pas du plateau (layoutY < hauteurJeu -
    // LARGEUR_PERSONNAGE).
  }

  public void directionHaut() {
    // TODO exercice 8 : déplacement vers le haut.
    //
    // Vérifier que le personnage ne sort pas du plateau (layoutY >= LARGEUR_PERSONNAGE).
  }

  /**
   * Détecte une collision entre ce personnage et un autre.
   *
   * @param autre l'autre personnage
   * @return true si les deux personnages se chevauchent
   */
  public boolean collision(Personnage autre) {
    return getBoundsInParent().contains(autre.getBoundsInParent())
        || autre.getBoundsInParent().contains(getBoundsInParent());
  }
}
