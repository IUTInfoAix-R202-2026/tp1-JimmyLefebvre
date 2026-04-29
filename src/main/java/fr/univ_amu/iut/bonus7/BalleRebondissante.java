package fr.univ_amu.iut.bonus7;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

/**
 * Exercice 7 (bonus) - Balle rebondissante.
 *
 * <p>Objectif : découvrir les animations JavaFX. Une balle rebondit verticalement dans un panneau.
 * Quatre boutons permettent de démarrer, mettre en pause, reprendre et arrêter l'animation. Un
 * slider contrôle la vitesse en temps réel.
 *
 * <p>Concepts : {@link TranslateTransition}, {@link Animation}, {@link Slider}, binding de
 * propriétés.
 */
public class BalleRebondissante extends Application {

  @Override
  public void start(Stage primaryStage) {
    // TODO exercice 7 : animer une balle rebondissante.
    //
    // 1. Créer un VBox comme conteneur racine.
    //
    // 2. Créer un HBox avec 4 boutons :
    //    - "Démarrer" (id: btn-start)  → appelle transition.playFromStart()
    //    - "Pause"    (id: btn-pause)  → appelle transition.pause()
    //    - "Reprendre"(id: btn-resume) → appelle transition.play()
    //    - "Stop"     (id: btn-stop)   → appelle transition.stop()
    //
    // 3. Créer un Slider (id: slider-vitesse) avec min=0.1, max=5, valeur=1.
    //    Ajouter un listener sur sa propriété value pour ajuster
    //    transition.setRate(newValue).
    //
    // 4. Créer un Circle (id: balle) de rayon 15, couleur rouge.
    //    Le placer dans un Pane (id: zone-animation).
    //
    // 5. Créer un TranslateTransition :
    //    - durée 1000ms
    //    - noeud = le cercle
    //    - fromY = 10, toY = 400
    //    - autoReverse = true
    //    - cycleCount = INDEFINITE
    //
    // 6. Assembler : HBox + Slider + Pane dans le VBox.
    //    Créer une Scene, l'attacher au Stage, afficher.
  }

  public static void main(String[] args) {
    launch(args);
  }
}
