package fr.univ_amu.iut.bonus8;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * Le Fantôme : un cercle bleu sur un rectangle, avec deux yeux dont les rétines suivent la
 * direction du déplacement.
 */
public class Fantome extends Personnage {

  private final Circle oeilGauche;
  private final Circle retineGauche;
  private final Circle oeilDroit;
  private final Circle retineDroite;

  public Fantome() {
    Rectangle basCorps = new Rectangle(0, 10, 20, 10);
    basCorps.setFill(Color.BLUE);
    Circle corps = new Circle(10, 10, LARGEUR_MOITIE_PERSONNAGE, Color.BLUE);

    oeilGauche = new Circle(6, 6, 2, Color.WHITE);
    retineGauche = new Circle(oeilGauche.getCenterX() + 1, 6, 1, Color.BLACK);
    oeilDroit = new Circle(14, 6, 2, Color.WHITE);
    retineDroite = new Circle(oeilDroit.getCenterX() + 1, 6, 1, Color.BLACK);

    direction = "droite";

    getChildren().addAll(basCorps, corps, oeilGauche, retineGauche, oeilDroit, retineDroite);
  }

  @Override
  public void directionGauche() {
    // TODO exercice 8 : déplacer le fantôme à gauche et orienter les rétines.
    //
    // Appeler super.directionGauche() puis positionner les rétines à gauche
    // des yeux : retine.setCenterX(oeil.getCenterX() - 1).
  }

  @Override
  public void directionDroite(double largeurJeu) {
    // TODO exercice 8 : déplacer le fantôme à droite et orienter les rétines.
  }

  @Override
  public void directionBas(double hauteurJeu) {
    // TODO exercice 8 : déplacer le fantôme vers le bas et orienter les rétines.
  }

  @Override
  public void directionHaut() {
    // TODO exercice 8 : déplacer le fantôme vers le haut et orienter les rétines.
  }
}
