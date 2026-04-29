package fr.univ_amu.iut.bonus8;

import static org.assertj.core.api.Assertions.assertThat;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

/** Tests de l'exercice 8 (bonus) - Mini-jeu Pacman. */
@ExtendWith(ApplicationExtension.class)
class JeuPacmanTest {

  private Stage stage;

  @Start
  void start(Stage stage) throws Exception {
    stage.setScene(null); // évite la fuite de Scene entre tests (TestFX réutilise le Stage)
    this.stage = stage;
    new JeuPacman().start(stage);
  }

  // --- Étape 1 : afficher la fenêtre ---

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  void laFenetreEstVisible(FxRobot robot) {
    assertThat(stage.isShowing()).as("le Stage doit être affiché").isTrue();
  }

  // --- Étape 2 : le plateau de jeu ---

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  void lePlateauExiste(FxRobot robot) {
    Pane plateau = robot.lookup("#plateau").queryAs(Pane.class);
    assertThat(plateau).as("un Pane avec l'id 'plateau' doit être présent").isNotNull();
  }

  // --- Étape 3 : les deux personnages ---

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  void lePacmanExiste(FxRobot robot) {
    Pacman pacman = robot.lookup("#pacman").queryAs(Pacman.class);
    assertThat(pacman).as("un Pacman avec l'id 'pacman' doit être présent").isNotNull();
  }

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  void leFantomeExiste(FxRobot robot) {
    Fantome fantome = robot.lookup("#fantome").queryAs(Fantome.class);
    assertThat(fantome).as("un Fantome avec l'id 'fantome' doit être présent").isNotNull();
  }
}
