package fr.univ_amu.iut.bonus7;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.TimeUnit;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.util.WaitForAsyncUtils;

/** Tests de l'exercice 7 (bonus) - Balle rebondissante. */
@ExtendWith(ApplicationExtension.class)
class BalleRebondissanteTest {

  private Stage stage;

  @Start
  void start(Stage stage) throws Exception {
    stage.setScene(null); // évite la fuite de Scene entre tests (TestFX réutilise le Stage)
    this.stage = stage;
    new BalleRebondissante().start(stage);
  }

  // --- Étape 1 : afficher la fenêtre ---

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  void laFenetreEstVisible(FxRobot robot) {
    assertThat(stage.isShowing()).as("le Stage doit être affiché").isTrue();
  }

  // --- Étape 2 : la scène existe ---

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  void laSceneExiste(FxRobot robot) {
    assertThat(stage.getScene()).as("le Stage doit avoir une Scene").isNotNull();
  }

  // --- Étape 3 : le bouton Démarrer ---

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  void leBoutonDemarrerExiste(FxRobot robot) {
    Button btn = robot.lookup("#btn-start").queryAs(Button.class);
    assertThat(btn).as("un Button avec l'id 'btn-start' doit être présent").isNotNull();
  }

  // --- Étape 4 : le bouton Pause ---

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  void leBoutonPauseExiste(FxRobot robot) {
    Button btn = robot.lookup("#btn-pause").queryAs(Button.class);
    assertThat(btn).as("un Button avec l'id 'btn-pause' doit être présent").isNotNull();
  }

  // --- Étape 5 : le bouton Reprendre ---

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  void leBoutonReprendreExiste(FxRobot robot) {
    Button btn = robot.lookup("#btn-resume").queryAs(Button.class);
    assertThat(btn).as("un Button avec l'id 'btn-resume' doit être présent").isNotNull();
  }

  // --- Étape 6 : le bouton Stop ---

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  void leBoutonStopExiste(FxRobot robot) {
    Button btn = robot.lookup("#btn-stop").queryAs(Button.class);
    assertThat(btn).as("un Button avec l'id 'btn-stop' doit être présent").isNotNull();
  }

  // --- Étape 7 : le slider de vitesse ---

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  void leSliderExiste(FxRobot robot) {
    Slider slider = robot.lookup("#slider-vitesse").queryAs(Slider.class);
    assertThat(slider).as("un Slider avec l'id 'slider-vitesse' doit être présent").isNotNull();
  }

  // --- Étape 8 : le slider a les bonnes limites ---

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  void leSliderALesBonnesLimites(FxRobot robot) {
    Slider slider = robot.lookup("#slider-vitesse").queryAs(Slider.class);
    assertThat(slider.getMin()).as("le min du slider doit être 0.1").isEqualTo(0.1);
    assertThat(slider.getMax()).as("le max du slider doit être 5").isEqualTo(5.0);
  }

  // --- Étape 9 : la balle ---

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  void laBalleExiste(FxRobot robot) {
    Circle balle = robot.lookup("#balle").queryAs(Circle.class);
    assertThat(balle).as("un Circle avec l'id 'balle' doit être présent").isNotNull();
    assertThat(balle.getRadius()).as("le rayon de la balle doit être 15").isEqualTo(15.0);
  }

  // --- Étape 10 : la zone d'animation ---

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  void laZoneAnimationExiste(FxRobot robot) {
    Pane zone = robot.lookup("#zone-animation").queryAs(Pane.class);
    assertThat(zone).as("un Pane avec l'id 'zone-animation' doit être présent").isNotNull();
  }

  // --- Étape 11 : démarrer lance l'animation ---

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  void demarrerLanceAnimation(FxRobot robot) {
    Circle balle = robot.lookup("#balle").queryAs(Circle.class);
    Button btnStart = robot.lookup("#btn-start").queryAs(Button.class);
    robot.interact(btnStart::fire);
    WaitForAsyncUtils.sleep(200, TimeUnit.MILLISECONDS);

    assertThat(balle.getTranslateY())
        .as("après démarrage, la balle doit avoir bougé (translateY > 0)")
        .isGreaterThan(0);
  }

  // --- Étape 12 : pause arrête le mouvement ---

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  void pauseArreteLAnimation(FxRobot robot) {
    Button btnStart = robot.lookup("#btn-start").queryAs(Button.class);
    Button btnPause = robot.lookup("#btn-pause").queryAs(Button.class);
    robot.interact(btnStart::fire);
    WaitForAsyncUtils.sleep(100, TimeUnit.MILLISECONDS);
    robot.interact(btnPause::fire);

    Circle balle = robot.lookup("#balle").queryAs(Circle.class);
    double yApresPause = balle.getTranslateY();
    WaitForAsyncUtils.sleep(200, TimeUnit.MILLISECONDS);
    assertThat(balle.getTranslateY())
        .as("après pause, la balle ne doit plus bouger")
        .isEqualTo(yApresPause);
  }

  // --- Étape 13 : reprendre relance l'animation ---

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  void reprendreContinueLAnimation(FxRobot robot) {
    Button btnStart = robot.lookup("#btn-start").queryAs(Button.class);
    Button btnPause = robot.lookup("#btn-pause").queryAs(Button.class);
    Button btnResume = robot.lookup("#btn-resume").queryAs(Button.class);
    robot.interact(btnStart::fire);
    WaitForAsyncUtils.sleep(100, TimeUnit.MILLISECONDS);
    robot.interact(btnPause::fire);

    Circle balle = robot.lookup("#balle").queryAs(Circle.class);
    double yApresPause = balle.getTranslateY();

    robot.interact(btnResume::fire);
    WaitForAsyncUtils.sleep(200, TimeUnit.MILLISECONDS);
    assertThat(balle.getTranslateY())
        .as("après reprise, la balle doit avoir rebougé")
        .isNotEqualTo(yApresPause);
  }

  // --- Étape 14 : stop réinitialise l'animation ---

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  void stopArreteLAnimation(FxRobot robot) {
    Button btnStart = robot.lookup("#btn-start").queryAs(Button.class);
    Button btnStop = robot.lookup("#btn-stop").queryAs(Button.class);
    robot.interact(btnStart::fire);
    WaitForAsyncUtils.sleep(200, TimeUnit.MILLISECONDS);
    robot.interact(btnStop::fire);

    Circle balle = robot.lookup("#balle").queryAs(Circle.class);
    double yApresStop = balle.getTranslateY();
    WaitForAsyncUtils.sleep(200, TimeUnit.MILLISECONDS);
    assertThat(balle.getTranslateY())
        .as("après stop, la balle ne doit plus bouger")
        .isEqualTo(yApresStop);
  }
}
