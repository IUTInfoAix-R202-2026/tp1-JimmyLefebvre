package fr.univ_amu.iut.exercice5;

/**
 * Petit compteur partagé entre l'application et son écouteur.
 *
 * <p>Volontairement minimaliste : un entier, un getter, une méthode pour l'incrémenter. Entièrement
 * fourni - tu n'as rien à modifier ici.
 */
public class Compteur {

  private int valeur;

  public int getValeur() {
    return valeur;
  }

  public void incrementer() {
    valeur++;
  }
}
