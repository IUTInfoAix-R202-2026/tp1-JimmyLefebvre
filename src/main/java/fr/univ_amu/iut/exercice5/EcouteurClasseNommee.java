package fr.univ_amu.iut.exercice5;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Premier style d'écouteur : une classe dédiée qui implémente {@link EventHandler}.
 *
 * <p>C'est le style "historique" (avant Java 8), le plus verbeux mais aussi le plus explicite. La
 * classe encapsule son propre état (ici, une référence vers un {@link Compteur}) et expose sa
 * logique via la méthode {@link #handle(ActionEvent)}.
 */
public class EcouteurClasseNommee implements EventHandler<ActionEvent> {

  private final Compteur compteur;

  public EcouteurClasseNommee(Compteur compteur) {
    this.compteur = compteur;
  }

  @Override
  public void handle(ActionEvent event) {
    // TODO exercice 5 : incrémenter le compteur à chaque appel.
    //
    // Une seule ligne suffit. Utilise la méthode incrementer() de Compteur.
  }
}
