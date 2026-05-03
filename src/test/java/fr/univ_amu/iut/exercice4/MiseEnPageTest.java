package fr.univ_amu.iut.exercice4;

import static org.assertj.core.api.Assertions.assertThat;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

/** Tests de l'exercice 4 - Mise en page d'un formulaire. */
@ExtendWith(ApplicationExtension.class)
class MiseEnPageTest {

  private Stage stage;

  @Start
  void start(Stage stage) throws Exception {
    stage.setScene(null); // évite la fuite de Scene entre tests (TestFX réutilise le Stage)
    this.stage = stage;
    new MiseEnPage().start(stage);
  }

  // --- Étape 1 : afficher la fenêtre ---

  // @Disabled("Retire cette annotation pour activer le test")
  @Test
  void laFenetreEstVisible(FxRobot robot) {
    assertThat(stage.isShowing())
        .as("le Stage doit être affiché - appelle show() à la fin de start()")
        .isTrue();
  }

  // --- Étape 2 : créer le squelette BorderPane + Scene ---

  // @Disabled("Retire cette annotation pour activer le test")
  @Test
  void leRootEstUnBorderPane(FxRobot robot) {
    assertThat(stage.getScene().getRoot())
        .as("la racine de la Scene doit être un BorderPane")
        .isInstanceOf(BorderPane.class);
  }

  // --- Étape 3 : ajouter un MenuBar dans la zone top ---

  // @Disabled("Retire cette annotation pour activer le test")
  @Test
  void leMenuBarEstEnHaut(FxRobot robot) {
    BorderPane root = (BorderPane) stage.getScene().getRoot();
    assertThat(root.getTop())
        .as("la zone top du BorderPane doit contenir un MenuBar")
        .isInstanceOf(MenuBar.class);
  }

  // --- Étape 4 : ajouter deux Menu au MenuBar ---

  // @Disabled("Retire cette annotation pour activer le test")
  @Test
  void leMenuBarContientDeuxMenus(FxRobot robot) {
    BorderPane root = (BorderPane) stage.getScene().getRoot();
    MenuBar menuBar = (MenuBar) root.getTop();
    assertThat(menuBar.getMenus())
        .as("le MenuBar doit contenir au moins 2 menus")
        .hasSizeGreaterThanOrEqualTo(2);
  }

  // @Disabled("Retire cette annotation pour activer le test")
  @Test
  void lesMenusOntLesBonsNoms(FxRobot robot) {
    BorderPane root = (BorderPane) stage.getScene().getRoot();
    MenuBar menuBar = (MenuBar) root.getTop();
    assertThat(menuBar.getMenus().get(0).getText())
        .as("le premier menu doit s'appeler 'Fichier'")
        .isEqualTo("Fichier");
    assertThat(menuBar.getMenus().get(1).getText())
        .as("le deuxième menu doit s'appeler 'Aide'")
        .isEqualTo("Aide");
  }

  // --- Étape 5 : ajouter un GridPane dans la zone center ---

  // @Disabled("Retire cette annotation pour activer le test")
  @Test
  void leGridPaneEstAuCentre(FxRobot robot) {
    BorderPane root = (BorderPane) stage.getScene().getRoot();
    assertThat(root.getCenter())
        .as("la zone center du BorderPane doit contenir un GridPane")
        .isInstanceOf(GridPane.class);
  }

  // --- Étape 6 : ajouter les labels dans le GridPane ---

  // @Disabled("Retire cette annotation pour activer le test")
  @Test
  void lesLabelsNomEtEmailExistent(FxRobot robot) {
    BorderPane root = (BorderPane) stage.getScene().getRoot();
    GridPane grid = (GridPane) root.getCenter();
    Label nom = labelAt(grid, 0, 0);
    Label email = labelAt(grid, 0, 1);
    assertThat(nom)
        .as("un Label doit être placé dans le GridPane à la colonne 0, ligne 0")
        .isNotNull();
    assertThat(nom.getText()).as("le Label (0,0) doit afficher 'Nom :'").isEqualTo("Nom :");
    assertThat(email)
        .as("un Label doit être placé dans le GridPane à la colonne 0, ligne 1")
        .isNotNull();
    assertThat(email.getText()).as("le Label (0,1) doit afficher 'Email :'").isEqualTo("Email :");
  }

  // --- Étape 7 : ajouter les TextField dans le GridPane ---

  // @Disabled("Retire cette annotation pour activer le test")
  @Test
  void lesDeuxChampsDeSaisieExistent(FxRobot robot) {
    BorderPane root = (BorderPane) stage.getScene().getRoot();
    GridPane grid = (GridPane) root.getCenter();
    assertThat(nodeAt(grid, 1, 0, TextField.class))
        .as("un TextField doit être placé dans le GridPane à la colonne 1, ligne 0")
        .isNotNull();
    assertThat(nodeAt(grid, 1, 1, TextField.class))
        .as("un TextField doit être placé dans le GridPane à la colonne 1, ligne 1")
        .isNotNull();
  }

  // --- Étape 8 : ajouter un HBox dans la zone bottom ---

  // @Disabled("Retire cette annotation pour activer le test")
  @Test
  void leHBoxEstEnBas(FxRobot robot) {
    BorderPane root = (BorderPane) stage.getScene().getRoot();
    assertThat(root.getBottom())
        .as("la zone bottom du BorderPane doit contenir un HBox")
        .isInstanceOf(HBox.class);
  }

  // --- Étape 9 : ajouter le bouton Valider ---

  // @Disabled("Retire cette annotation pour activer le test")
  @Test
  void leBoutonValiderExiste(FxRobot robot) {
    HBox hbox = hboxEnBas();
    boolean valider =
        hbox.getChildren().stream()
            .anyMatch(n -> n instanceof Button && "Valider".equals(((Button) n).getText()));
    assertThat(valider)
        .as("un Button 'Valider' doit être placé dans le HBox du bas (pas ailleurs)")
        .isTrue();
  }

  // --- Étape 10 : ajouter le bouton Annuler ---

  // @Disabled("Retire cette annotation pour activer le test")
  @Test
  void leBoutonAnnulerExiste(FxRobot robot) {
    HBox hbox = hboxEnBas();
    boolean annuler =
        hbox.getChildren().stream()
            .anyMatch(n -> n instanceof Button && "Annuler".equals(((Button) n).getText()));
    assertThat(annuler)
        .as("un Button 'Annuler' doit être placé dans le HBox du bas (pas ailleurs)")
        .isTrue();
  }

  // --- Helpers ---

  private HBox hboxEnBas() {
    BorderPane root = (BorderPane) stage.getScene().getRoot();
    return (HBox) root.getBottom();
  }

  private Label labelAt(GridPane grid, int col, int row) {
    return (Label) nodeAt(grid, col, row, Label.class);
  }

  private <T> T nodeAt(GridPane grid, int col, int row, Class<T> type) {
    return grid.getChildren().stream()
        .filter(type::isInstance)
        .filter(n -> col == colOf(n) && row == rowOf(n))
        .map(type::cast)
        .findFirst()
        .orElse(null);
  }

  private int colOf(javafx.scene.Node n) {
    Integer c = GridPane.getColumnIndex(n);
    return c == null ? 0 : c;
  }

  private int rowOf(javafx.scene.Node n) {
    Integer r = GridPane.getRowIndex(n);
    return r == null ? 0 : r;
  }
}
