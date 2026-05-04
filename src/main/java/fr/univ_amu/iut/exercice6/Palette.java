package fr.univ_amu.iut.exercice6;

import fr.univ_amu.iut.exercice5.Compteur;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Exercice 6 - Palette de couleurs (capstone).
 *
 * <p>Dernier exercice du TP : synthèse des concepts vus jusqu'ici (layout, boutons, événements,
 * mise à jour d'un label) sur une petite application autonome.
 *
 * <h3>Comportement attendu</h3>
 *
 * <pre>
 * ┌──────────────────────────────┐
 * │ [Rouge] [Vert] [Bleu]        │  ← HBox de 3 boutons
 * ├──────────────────────────────┤
 * │                              │
 * │     (zone de couleur)        │  ← Pane "#zone" dont le fond change
 * │                              │
 * ├──────────────────────────────┤
 * │ Rouge: 0  Vert: 0  Bleu: 0   │  ← Label "#compteurs"
 * └──────────────────────────────┘
 * </pre>
 *
 * <p>Chaque clic sur un bouton :
 *
 * <ul>
 *   <li>change la couleur de fond de la zone centrale ;
 *   <li>incrémente le compteur correspondant dans le label du bas.
 * </ul>
 *
 * <p>Les trois compteurs sont indépendants : cliquer "Rouge" n'affecte pas les compteurs "Vert" et
 * "Bleu".
 */
public class Palette extends Application {

  @Override
  public void start(Stage primaryStage) {

    BorderPane root = new BorderPane();

    HBox hBox = new HBox();

    Button rouge = new Button("Rouge");
    rouge.setId("btn-rouge");

    Button vert = new Button("Vert");
    vert.setId("btn-vert");

    Button bleu = new Button("Bleu");
    bleu.setId("btn-bleu");

    hBox.getChildren().addAll(rouge, vert, bleu);

    root.setTop(hBox);

    Pane zone = new Pane();
    zone.setId("zone");
    root.setCenter(zone);
    zone.setMinSize(300, 200);

    Compteur compteurRouge = new Compteur();
    Compteur compteurVert = new Compteur();
    Compteur compteurBleu = new Compteur();

    Label compteurs = new Label();
    compteurs.setId("compteurs");
    compteurs.setText("Rouge: 0 Vert: 0 Bleu: 0");
    root.setBottom(compteurs);

    rouge.setOnAction(
        e -> {
          compteurRouge.incrementer();
          zone.setStyle("-fx-background-color: red;");
          compteurs.setText(
              "Rouge: "
                  + compteurRouge.getValeur()
                  + " Vert: "
                  + compteurVert.getValeur()
                  + " Bleu: "
                  + compteurBleu.getValeur());
        });

    vert.setOnAction(
        e -> {
          compteurVert.incrementer();
          zone.setStyle("-fx-background-color: green;");
          compteurs.setText(
              "Rouge: "
                  + compteurRouge.getValeur()
                  + " Vert: "
                  + compteurVert.getValeur()
                  + " Bleu: "
                  + compteurBleu.getValeur());
        });

    bleu.setOnAction(
        e -> {
          compteurBleu.incrementer();
          zone.setStyle("-fx-background-color: blue;");
          compteurs.setText(
              "Rouge: "
                  + compteurRouge.getValeur()
                  + " Vert: "
                  + compteurVert.getValeur()
                  + " Bleu: "
                  + compteurBleu.getValeur());
        });

    Scene scene = new Scene(root);

    primaryStage.setScene(scene);
    primaryStage.show();
    // TODO exercice 6 : implémenter la palette décrite dans la Javadoc.
    //
    // Stratégie conseillée :
    //
    // 1. Créer un BorderPane comme racine.
    //
    // 2. Top : un HBox avec trois boutons "Rouge", "Vert", "Bleu".
    // Donne-leur les ids "btn-rouge", "btn-vert", "btn-bleu" - les tests
    // les retrouvent via robot.lookup("#btn-rouge") etc.
    //
    // 3. Center : un Pane avec l'id "zone", taille minimale 300×200.
    // Change sa couleur via setStyle("-fx-background-color: red;") etc.
    //
    // 4. Bottom : un Label avec l'id "compteurs", texte initial
    // "Rouge: 0 Vert: 0 Bleu: 0".
    //
    // 5. Trois entiers compteur_rouge, compteur_vert, compteur_bleu
    // (ou trois variables d'instance). Chaque clic incrémente le bon
    // compteur et reformate le texte du label.
    //
    // 6. Attention au format du texte du label : les tests vérifient la
    // présence exacte des substrings "Rouge: 2", "Vert: 0", "Bleu: 1"
    // après une séquence de clics.
  }

  public static void main(String[] args) {
    launch(args);
  }
}
