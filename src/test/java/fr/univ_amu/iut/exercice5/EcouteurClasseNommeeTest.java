package fr.univ_amu.iut.exercice5;

import static org.assertj.core.api.Assertions.assertThat;

import javafx.event.ActionEvent;
import org.junit.jupiter.api.Test;

/**
 * Test unitaire (pas besoin de TestFX) pour valider l'implémentation de la méthode {@link
 * EcouteurClasseNommee#handle(ActionEvent)}.
 */
class EcouteurClasseNommeeTest {

  // sss@Disabled("Retire cette annotation pour activer le test")
  @Test
  void handleIncrementeLeCompteur() {
    Compteur compteur = new Compteur();
    EcouteurClasseNommee ecouteur = new EcouteurClasseNommee(compteur);

    ecouteur.handle(new ActionEvent());
    ecouteur.handle(new ActionEvent());
    ecouteur.handle(new ActionEvent());

    assertThat(compteur.getValeur())
        .as("handle() doit appeler compteur.incrementer() à chaque invocation")
        .isEqualTo(3);
  }
}
