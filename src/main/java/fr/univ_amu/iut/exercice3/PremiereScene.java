package fr.univ_amu.iut.exercice3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Exercice 3 - Première Scene avec un Label.
 *
 * <p>Objectif : au-delà de la fenêtre vide, afficher du contenu. On construit une Scene qui
 * contient un conteneur (BorderPane) dans lequel on place un Label avec un message.
 *
 * <p>Concepts : {@link javafx.scene.Scene}, {@link javafx.scene.layout.BorderPane}, {@link
 * javafx.scene.control.Label}.
 */
public class PremiereScene extends Application {

  @Override
  public void start(Stage primaryStage) {
    BorderPane root = new BorderPane();
    Label label = new Label("Bonjour, JavaFX !");
    root.setCenter(label);
    Scene scene = new Scene(root);
    primaryStage.setScene(scene);
    primaryStage.show();
    // TODO exercice 3 : afficher un Label au centre d'un BorderPane.
    //
    // 1. Créer un BorderPane (le "squelette" de la scène).
    // 2. Créer un Label avec le texte "Bonjour, JavaFX !".
    // 3. Placer le label au CENTRE du BorderPane (méthode setCenter).
    // 4. Construire une Scene à partir du BorderPane.
    // 5. Attacher la Scene au Stage.
    // 6. Afficher le Stage.
    //
    // Les imports nécessaires sont déjà dans la Javadoc ci-dessus.
  }

  public static void main(String[] args) {
    launch(args);
  }
}
