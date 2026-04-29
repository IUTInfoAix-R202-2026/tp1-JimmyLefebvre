/**
 * Module JavaFX pour le TP1 - Bases JavaFX.
 *
 * <p>Chaque exercice vit dans son propre sous-paquet ; un export par sous-paquet permet à JavaFX de
 * réfléchir sur les classes depuis son loader.
 */
open module tp1.javafx {
  // JavaFX dependencies
  requires transitive javafx.base;
  requires transitive javafx.controls;
  requires transitive javafx.graphics;
  requires transitive javafx.fxml;

  // Paquet racine : App (launcher) + AppTest
  exports fr.univ_amu.iut;

  // Exercices du TP1
  exports fr.univ_amu.iut.exercice1;
  exports fr.univ_amu.iut.exercice2;
  exports fr.univ_amu.iut.exercice3;
  exports fr.univ_amu.iut.exercice4;
  exports fr.univ_amu.iut.exercice5;
  exports fr.univ_amu.iut.exercice6;
  exports fr.univ_amu.iut.bonus7;
  exports fr.univ_amu.iut.bonus8;
}
