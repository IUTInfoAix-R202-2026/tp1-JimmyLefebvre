package fr.univ_amu.iut.bonus8;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

/**
 * Le Pacman : un cercle jaune avec une bouche (trait) dont l'orientation change selon la direction.
 */
public class Pacman extends Personnage {

  private final Circle corps;
  private final Line bouche;

  public Pacman() {
    corps = new Circle(10, 10, LARGEUR_MOITIE_PERSONNAGE, Color.YELLOW);
    bouche =
        new Line(
            LARGEUR_MOITIE_PERSONNAGE,
            LARGEUR_MOITIE_PERSONNAGE,
            LARGEUR_MOITIE_PERSONNAGE * 2,
            LARGEUR_MOITIE_PERSONNAGE);
    bouche.setStroke(Color.BLACK);
    direction = "droite";

    getChildren().addAll(corps, bouche);
  }

  @Override
  public void directionGauche() {
    super.directionGauche();
    bouche.setEndX(bouche.getStartX() - LARGEUR_MOITIE_PERSONNAGE);
    bouche.setEndY(bouche.getStartY());
  }

  @Override
  public void directionDroite(double largeurJeu) {
    super.directionDroite(largeurJeu);
    bouche.setEndX(bouche.getStartX() + LARGEUR_MOITIE_PERSONNAGE);
    bouche.setEndY(bouche.getStartY());
  }

  @Override
  public void directionBas(double hauteurJeu) {
    // TODO exercice 8 : orienter la bouche vers le bas après le déplacement.
    //
    // Appeler super.directionBas(hauteurJeu) puis ajuster bouche.setEndX/setEndY
    // pour que la bouche pointe vers le bas.
  }

  @Override
  public void directionHaut() {
    // TODO exercice 8 : orienter la bouche vers le haut après le déplacement.
    //
    // Appeler super.directionHaut() puis ajuster bouche.setEndX/setEndY
    // pour que la bouche pointe vers le haut.
  }
}
