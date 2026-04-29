package fr.univ_amu.iut;

import fr.univ_amu.iut.bonus7.BalleRebondissante;
import fr.univ_amu.iut.bonus8.JeuPacman;
import fr.univ_amu.iut.exercice1.PremiereFenetre;
import fr.univ_amu.iut.exercice2.StagePersonnalise;
import fr.univ_amu.iut.exercice3.PremiereScene;
import fr.univ_amu.iut.exercice4.MiseEnPage;
import fr.univ_amu.iut.exercice5.EvenementsBouton;
import fr.univ_amu.iut.exercice6.Palette;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.function.Supplier;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * Lanceur du TP1. Affiche un menu avec un bouton par exercice ; cliquer lance l'exercice
 * correspondant dans une nouvelle fenêtre.
 *
 * <p>Point d'entrée par défaut de {@code ./mvnw javafx:run}. Les étudiants peuvent aussi lancer
 * chaque exercice directement depuis leur IDE en faisant clic droit → Run sur la classe de
 * l'exercice concerné.
 */
public class App extends Application {

  @Override
  public void start(Stage primaryStage) {
    Label titre = new Label("TP1 - Bases JavaFX");
    titre.setId("titre-tp1");
    titre.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

    VBox root = new VBox(10);
    root.setPadding(new Insets(20));
    root.setAlignment(Pos.CENTER);
    root.getChildren().add(titre);
    root.getChildren().add(bouton("Exercice 1 - Première fenêtre", PremiereFenetre::new));
    root.getChildren().add(bouton("Exercice 2 - Stage personnalisé", StagePersonnalise::new));
    root.getChildren().add(bouton("Exercice 3 - Première Scene", PremiereScene::new));
    root.getChildren().add(bouton("Exercice 4 - Mise en page", MiseEnPage::new));
    root.getChildren().add(bouton("Exercice 5 - Événements bouton", EvenementsBouton::new));
    root.getChildren().add(bouton("Exercice 6 - Palette", Palette::new));
    root.getChildren().add(bouton("Bonus 7 - Balle rebondissante", BalleRebondissante::new));
    root.getChildren().add(bouton("Bonus 8 - Pacman", JeuPacman::new));

    primaryStage.setTitle("TP1 - Bases JavaFX - Lanceur");
    primaryStage.setScene(new Scene(root, 420, 420));
    primaryStage.show();
  }

  private Button bouton(String libelle, Supplier<Application> fabrique) {
    Button btn = new Button(libelle);
    btn.setMaxWidth(Double.MAX_VALUE);
    btn.setOnAction(e -> lancerExercice(libelle, fabrique));
    return btn;
  }

  /**
   * Lance l'exercice. Si la méthode {@code start()} de l'exercice est vide (TODO non encore
   * implémenté par l'étudiant) OU lève une exception, on affiche une fenêtre d'aide explicite au
   * lieu de laisser le bouton sans effet apparent.
   */
  private void lancerExercice(String libelle, Supplier<Application> fabrique) {
    int fenetresAvant = Window.getWindows().size();
    try {
      fabrique.get().start(new Stage());
    } catch (Exception ex) {
      StringWriter sw = new StringWriter();
      ex.printStackTrace(new PrintWriter(sw));
      afficherAlerte(
          AlertType.ERROR,
          "Exception pendant le lancement de " + libelle,
          "L'exercice a levé une exception. Extrait :\n\n" + sw.toString().split("\n")[0],
          sw.toString());
      return;
    }
    int fenetresApres = Window.getWindows().size();
    if (fenetresApres == fenetresAvant) {
      afficherAlerte(
          AlertType.INFORMATION,
          libelle + " - rien à afficher",
          "La méthode start() de cet exercice ne produit aucune fenêtre.",
          "C'est normal si tu n'as pas encore implémenté l'exercice.\n\n"
              + "Prochaines étapes :\n"
              + "  1. Ouvrir la classe de l'exercice dans src/main/java/...\n"
              + "  2. Remplacer le TODO par l'implémentation attendue\n"
              + "  3. Activer le(s) test(s) correspondant(s) en retirant @Disabled\n"
              + "  4. Vérifier que le test passe avec ./mvnw test\n"
              + "  5. Relancer l'application : le bouton ouvrira maintenant une fenêtre");
    }
  }

  private void afficherAlerte(AlertType type, String titre, String header, String content) {
    Alert alert = new Alert(type);
    alert.setTitle(titre);
    alert.setHeaderText(header);
    alert.setContentText(content);
    alert.setResizable(true);
    alert.getDialogPane().setPrefWidth(520);
    alert.showAndWait();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
