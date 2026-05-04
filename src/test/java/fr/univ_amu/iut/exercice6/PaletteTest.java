package fr.univ_amu.iut.exercice6;

import static org.assertj.core.api.Assertions.assertThat;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

/** Tests de l'exercice 6 - Palette de couleurs (capstone). */
@ExtendWith(ApplicationExtension.class)
class PaletteTest {

  private Stage stage;

  @Start
  void start(Stage stage) throws Exception {
    stage.setScene(null); // évite la fuite de Scene entre tests (TestFX réutilise le Stage)
    this.stage = stage;
    new Palette().start(stage);
  }

  // --- Étape 1 : afficher la fenêtre ---

  // @Disabled("Retire cette annotation pour activer le test")
  @Test
  void laFenetreEstVisible(FxRobot robot) {
    assertThat(stage.isShowing())
        .as("le Stage doit être affiché - appelle show() à la fin de start()")
        .isTrue();
  }

  // --- Étape 2 : créer une Scene ---

  // @Disabled("Retire cette annotation pour activer le test")
  @Test
  void laSceneExiste(FxRobot robot) {
    assertThat(stage.getScene())
        .as("le Stage doit avoir une Scene attachée via setScene()")
        .isNotNull();
  }

  // @Disabled("Retire cette annotation pour activer le test")
  @Test
  void laRacineEstUnBorderPane(FxRobot robot) {
    assertThat(stage.getScene().getRoot())
        .as("la racine de la Scene doit être un BorderPane (top/center/bottom)")
        .isInstanceOf(BorderPane.class);
  }

  // --- Étape 3 : ajouter les trois boutons ---

  // @Disabled("Retire cette annotation pour activer le test")
  @Test
  void lesTroisBoutonsExistent(FxRobot robot) {
    Button btnRouge = robot.lookup("#btn-rouge").queryAs(Button.class);
    Button btnVert = robot.lookup("#btn-vert").queryAs(Button.class);
    Button btnBleu = robot.lookup("#btn-bleu").queryAs(Button.class);
    assertThat(btnRouge).as("un Button avec l'id 'btn-rouge' doit être présent").isNotNull();
    assertThat(btnRouge.getText())
        .as("le bouton #btn-rouge doit afficher 'Rouge'")
        .isEqualTo("Rouge");
    assertThat(btnVert).as("un Button avec l'id 'btn-vert' doit être présent").isNotNull();
    assertThat(btnVert.getText()).as("le bouton #btn-vert doit afficher 'Vert'").isEqualTo("Vert");
    assertThat(btnBleu).as("un Button avec l'id 'btn-bleu' doit être présent").isNotNull();
    assertThat(btnBleu.getText()).as("le bouton #btn-bleu doit afficher 'Bleu'").isEqualTo("Bleu");
  }

  // @Disabled("Retire cette annotation pour activer le test")
  @Test
  void leHBoxDesBoutonsEstEnHaut(FxRobot robot) {
    BorderPane root = (BorderPane) stage.getScene().getRoot();
    assertThat(root.getTop())
        .as("la zone top du BorderPane doit contenir un HBox (les 3 boutons de couleur)")
        .isInstanceOf(HBox.class);
    HBox hbox = (HBox) root.getTop();
    assertThat(hbox.getChildren())
        .as("le HBox du haut doit contenir les 3 boutons #btn-rouge, #btn-vert, #btn-bleu")
        .extracting(n -> n instanceof Button ? n.getId() : null)
        .contains("btn-rouge", "btn-vert", "btn-bleu");
  }

  // --- Étape 4 : ajouter la zone de couleur ---

  // @Disabled("Retire cette annotation pour activer le test")
  @Test
  void laZoneDeCouleurExiste(FxRobot robot) {
    Pane zone = robot.lookup("#zone").queryAs(Pane.class);
    assertThat(zone).as("un Pane avec l'id 'zone' doit être présent au centre").isNotNull();
  }

  // --- Étape 5 : ajouter le label des compteurs ---

  // @Disabled("Retire cette annotation pour activer le test")
  @Test
  void leLabelCompteursExiste(FxRobot robot) {
    Label compteurs = robot.lookup("#compteurs").queryAs(Label.class);
    assertThat(compteurs).as("un Label avec l'id 'compteurs' doit être présent en bas").isNotNull();
  }

  // @Disabled("Retire cette annotation pour activer le test")
  @Test
  void leLabelCompteursEstEnBas(FxRobot robot) {
    BorderPane root = (BorderPane) stage.getScene().getRoot();
    Label compteurs = robot.lookup("#compteurs").queryAs(Label.class);
    assertThat(root.getBottom())
        .as("le Label #compteurs doit être placé dans la zone bottom du BorderPane")
        .isSameAs(compteurs);
  }

  // @Disabled("Retire cette annotation pour activer le test")
  @Test
  void leTexteInitialDesCompteurs(FxRobot robot) {
    Label compteurs = robot.lookup("#compteurs").queryAs(Label.class);
    assertThat(compteurs.getText())
        .as("avant tout clic, le label doit afficher les trois compteurs à 0")
        .contains("Rouge: 0")
        .contains("Vert: 0")
        .contains("Bleu: 0");
  }

  // --- Étape 6 : cliquer Rouge change la couleur de fond ---

  // @Disabled("Retire cette annotation pour activer le test")
  @Test
  void cliquerRougeMetLaZoneEnRouge(FxRobot robot) {
    Button btnRouge = robot.lookup("#btn-rouge").queryAs(Button.class);
    robot.interact(btnRouge::fire);
    Pane zone = robot.lookup("#zone").queryAs(Pane.class);
    assertThat(zone.getStyle())
        .as("la zone doit contenir une règle background-color rouge après un clic")
        .contains("red");
  }

  // --- Étape 7 : cliquer Vert change la couleur de fond ---

  // @Disabled("Retire cette annotation pour activer le test")
  @Test
  void cliquerVertMetLaZoneEnVert(FxRobot robot) {
    Button btnVert = robot.lookup("#btn-vert").queryAs(Button.class);
    robot.interact(btnVert::fire);
    Pane zone = robot.lookup("#zone").queryAs(Pane.class);
    assertThat(zone.getStyle())
        .as("la zone doit contenir une règle background-color verte après un clic")
        .contains("green");
  }

  // --- Étape 8 : cliquer Bleu change la couleur de fond ---

  // @Disabled("Retire cette annotation pour activer le test")
  @Test
  void cliquerBleuMetLaZoneEnBleu(FxRobot robot) {
    Button btnBleu = robot.lookup("#btn-bleu").queryAs(Button.class);
    robot.interact(btnBleu::fire);
    Pane zone = robot.lookup("#zone").queryAs(Pane.class);
    assertThat(zone.getStyle())
        .as("la zone doit contenir une règle background-color bleue après un clic")
        .contains("blue");
  }

  // --- Étape 9 : cliquer incrémente le compteur correspondant ---

  // @Disabled("Retire cette annotation pour activer le test")
  @Test
  void cliquerIncrementeLeCompteurCorrespondant(FxRobot robot) {
    Button btnVert = robot.lookup("#btn-vert").queryAs(Button.class);
    robot.interact(btnVert::fire);
    robot.interact(btnVert::fire);

    Label compteurs = robot.lookup("#compteurs").queryAs(Label.class);
    assertThat(compteurs.getText())
        .as("après 2 clics sur Vert, le label doit contenir 'Vert: 2'")
        .contains("Vert: 2");
  }

  // --- Étape 10 : les compteurs sont indépendants ---

  // @Disabled("Retire cette annotation pour activer le test")
  @Test
  void lesCompteursSontIndependants(FxRobot robot) {
    Button btnRouge = robot.lookup("#btn-rouge").queryAs(Button.class);
    Button btnBleu = robot.lookup("#btn-bleu").queryAs(Button.class);
    robot.interact(btnRouge::fire);
    robot.interact(btnRouge::fire);
    robot.interact(btnBleu::fire);

    Label compteurs = robot.lookup("#compteurs").queryAs(Label.class);
    assertThat(compteurs.getText()).as("Rouge doit valoir 2").contains("Rouge: 2");
    assertThat(compteurs.getText()).as("Vert doit valoir 0 (aucun clic)").contains("Vert: 0");
    assertThat(compteurs.getText()).as("Bleu doit valoir 1").contains("Bleu: 1");
  }
}
