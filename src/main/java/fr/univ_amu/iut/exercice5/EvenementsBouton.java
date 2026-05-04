package fr.univ_amu.iut.exercice5;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Exercice 5 - Réagir à un clic.
 *
 * <p>Objectif : afficher un bouton "Clique-moi" et un label qui affiche le nombre de clics. Chaque
 * clic incrémente le compteur.
 *
 * <p>L'intérêt pédagogique est de voir qu'un même effet peut s'écrire avec trois styles différents
 * d'écouteur (voir les commentaires dans {@link #start(Stage)}).
 */
public class EvenementsBouton extends Application {

  @Override
  public void start(Stage primaryStage) {
    VBox root = new VBox();

    Button button = new Button();
    button.setId("bouton-clique-moi");
    root.getChildren().add(button);
    button.setText("Clique-moi");

    Label labelCompteur = new Label();
    labelCompteur.setId("compteur");
    labelCompteur.setText("0 clics");
    root.getChildren().add(labelCompteur);
    Scene scene = new Scene(root);

    Compteur compteur = new Compteur();

    button.setOnAction(
        e -> {
          compteur.incrementer();
          labelCompteur.setText(compteur.getValeur() + " clics");
        });

    primaryStage.setScene(scene);
    primaryStage.show();
    // TODO exercice 5 : construire l'IHM et brancher un écouteur.
    //
    // L'IHM attendue :
    // - un Button "Clique-moi" avec l'id "bouton-clique-moi"
    // - un Label qui affichera "N clics" avec l'id "compteur"
    // - le tout dans un VBox, dans une Scene, dans le Stage
    //
    // Pour l'écouteur du bouton, TU AS LE CHOIX entre 3 styles équivalents.
    // Les trois font exactement la même chose ; choisis-en un et laisse les
    // autres en commentaire pour pouvoir les comparer visuellement.
    //
    // Compteur compteur = new Compteur();
    // Label labelCompteur = new Label("0 clics");
    // Button bouton = new Button("Clique-moi");
    //
    // // --- Style 1 : classe nommée (style "historique") ---------------
    // // bouton.setOnAction(new EcouteurClasseNommee(compteur));
    // // Note : avec ce style, il te faut quand même un mécanisme pour
    // // mettre à jour labelCompteur après chaque incrément.
    //
    // // --- Style 2 : classe anonyme (intermédiaire) -------------------
    // // bouton.setOnAction(new EventHandler<ActionEvent>() {
    // // @Override
    // // public void handle(ActionEvent e) {
    // // compteur.incrementer();
    // // labelCompteur.setText(compteur.getValeur() + " clics");
    // // }
    // // });
    //
    // // --- Style 3 : lambda (moderne, recommandé) ---------------------
    // // bouton.setOnAction(e -> {
    // // compteur.incrementer();
    // // labelCompteur.setText(compteur.getValeur() + " clics");
    // // });
  }

  public static void main(String[] args) {
    launch(args);
  }
}
