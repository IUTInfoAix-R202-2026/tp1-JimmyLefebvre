package fr.univ_amu.iut.bonus8;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;

/** Tests unitaires pour les déplacements des personnages (Personnage, Pacman, Fantome). */
@ExtendWith(ApplicationExtension.class)
class PersonnageTest {

  // ========== Personnage : déplacement vertical ==========

  // --- Étape 1 : directionBas déplace vers le bas ---

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  void directionBasDeplaceLePacmanVersLeBas() {
    Pacman pacman = new Pacman();
    pacman.setLayoutY(0);
    pacman.directionBas(480);
    assertThat(pacman.getLayoutY()).as("directionBas doit avancer de 20 pixels").isEqualTo(20.0);
  }

  // --- Étape 2 : directionHaut déplace vers le haut ---

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  void directionHautDeplaceLePacmanVersLeHaut() {
    Pacman pacman = new Pacman();
    pacman.setLayoutY(40);
    pacman.directionHaut();
    assertThat(pacman.getLayoutY()).as("directionHaut doit reculer de 20 pixels").isEqualTo(20.0);
  }

  // --- Étape 3 : ne pas dépasser le bord supérieur ---

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  void directionHautNeDepassePasLeBordSuperieur() {
    Pacman pacman = new Pacman();
    pacman.setLayoutY(0);
    pacman.directionHaut();
    assertThat(pacman.getLayoutY())
        .as("directionHaut ne doit pas sortir du plateau")
        .isGreaterThanOrEqualTo(0);
  }

  // --- Étape 4 : ne pas dépasser le bord inférieur ---

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  void directionBasNeDepassePasLeBordInferieur() {
    Pacman pacman = new Pacman();
    pacman.setLayoutY(460);
    pacman.directionBas(480);
    assertThat(pacman.getLayoutY())
        .as("directionBas ne doit pas sortir du plateau")
        .isLessThanOrEqualTo(460);
  }

  // ========== Pacman : orientation de la bouche ==========

  // --- Étape 5 : la bouche pointe vers le bas ---

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  void pacmanBouchePointeVersLeBas() {
    Pacman pacman = new Pacman();
    pacman.directionBas(480);
    javafx.scene.shape.Line bouche = (javafx.scene.shape.Line) pacman.getChildren().get(1);
    assertThat(bouche.getEndY())
        .as("après directionBas, la bouche doit pointer vers le bas (endY > startY)")
        .isGreaterThan(bouche.getStartY());
  }

  // --- Étape 6 : la bouche pointe vers le haut ---

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  void pacmanBouchePointeVersLeHaut() {
    Pacman pacman = new Pacman();
    pacman.setLayoutY(40);
    pacman.directionHaut();
    javafx.scene.shape.Line bouche = (javafx.scene.shape.Line) pacman.getChildren().get(1);
    assertThat(bouche.getEndY())
        .as("après directionHaut, la bouche doit pointer vers le haut (endY < startY)")
        .isLessThan(bouche.getStartY());
  }

  // ========== Fantome : orientation des yeux ==========

  // --- Étape 7 : les yeux regardent à gauche ---

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  void fantomeYeuxVersLaGauche() {
    Fantome fantome = new Fantome();
    fantome.directionGauche();
    javafx.scene.shape.Circle oeilGauche = (javafx.scene.shape.Circle) fantome.getChildren().get(2);
    javafx.scene.shape.Circle retineGauche =
        (javafx.scene.shape.Circle) fantome.getChildren().get(3);
    assertThat(retineGauche.getCenterX())
        .as("après directionGauche, la rétine doit être à gauche de l'œil")
        .isLessThan(oeilGauche.getCenterX());
  }

  // --- Étape 8 : les yeux regardent à droite ---

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  void fantomeYeuxVersLaDroite() {
    Fantome fantome = new Fantome();
    fantome.directionGauche(); // d'abord à gauche...
    fantome.directionDroite(640); // puis à droite
    javafx.scene.shape.Circle oeilDroit = (javafx.scene.shape.Circle) fantome.getChildren().get(4);
    javafx.scene.shape.Circle retineDroite =
        (javafx.scene.shape.Circle) fantome.getChildren().get(5);
    assertThat(retineDroite.getCenterX())
        .as("après directionDroite, la rétine doit être à droite de l'œil")
        .isGreaterThan(oeilDroit.getCenterX());
  }

  // --- Étape 9 : les yeux regardent vers le bas ---

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  void fantomeYeuxVersLeBas() {
    Fantome fantome = new Fantome();
    fantome.directionBas(480);
    javafx.scene.shape.Circle oeilGauche = (javafx.scene.shape.Circle) fantome.getChildren().get(2);
    javafx.scene.shape.Circle retineGauche =
        (javafx.scene.shape.Circle) fantome.getChildren().get(3);
    assertThat(retineGauche.getCenterY())
        .as("après directionBas, la rétine doit être en dessous de l'œil")
        .isGreaterThan(oeilGauche.getCenterY());
  }

  // --- Étape 10 : les yeux regardent vers le haut ---

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  void fantomeYeuxVersLeHaut() {
    Fantome fantome = new Fantome();
    fantome.setLayoutY(40);
    fantome.directionHaut();
    javafx.scene.shape.Circle oeilGauche = (javafx.scene.shape.Circle) fantome.getChildren().get(2);
    javafx.scene.shape.Circle retineGauche =
        (javafx.scene.shape.Circle) fantome.getChildren().get(3);
    assertThat(retineGauche.getCenterY())
        .as("après directionHaut, la rétine doit être au-dessus de l'œil")
        .isLessThan(oeilGauche.getCenterY());
  }

  // ========== Collision ==========

  // --- Étape 11 : collision quand superposés ---

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  void collisionDetecteeQuandSuperposition() {
    Pacman pacman = new Pacman();
    Fantome fantome = new Fantome();
    pacman.setLayoutX(0);
    fantome.setLayoutX(0);
    assertThat(pacman.collision(fantome))
        .as("deux personnages au même endroit doivent être en collision")
        .isTrue();
  }

  // --- Étape 12 : pas de collision quand éloignés ---

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  void pasDeCollisionQuandEloignes() {
    Pacman pacman = new Pacman();
    Fantome fantome = new Fantome();
    pacman.setLayoutX(0);
    fantome.setLayoutX(200);
    assertThat(pacman.collision(fantome))
        .as("deux personnages éloignés ne doivent pas être en collision")
        .isFalse();
  }
}
