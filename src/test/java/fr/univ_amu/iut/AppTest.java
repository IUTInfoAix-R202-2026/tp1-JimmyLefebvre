package fr.univ_amu.iut;

import static org.assertj.core.api.Assertions.assertThat;

import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

/**
 * Smoke test du lanceur : vérifie que JavaFX rend bien la scène de {@link App} et que le titre du
 * lanceur est visible. Les tests spécifiques à chaque exercice sont dans les sous-paquets {@code
 * exerciceN}.
 */
@ExtendWith(ApplicationExtension.class)
class AppTest {

  @Start
  void start(Stage stage) {
    stage.setScene(null); // évite la fuite de Scene entre tests (TestFX réutilise le Stage)
    new App().start(stage);
  }

  @Test
  void leTitreDuLanceurEstAffiche(FxRobot robot) {
    Label titre = robot.lookup("#titre-tp1").queryAs(Label.class);
    assertThat(titre).isNotNull();
    assertThat(titre.getText()).isEqualTo("TP1 - Bases JavaFX");
  }
}
