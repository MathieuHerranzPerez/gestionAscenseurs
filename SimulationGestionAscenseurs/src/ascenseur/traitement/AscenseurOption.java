package ascenseur.traitement;

import ascenseur.traitement.exception.IncoherenceEtatAscenseurRequete;
/**
 * Decorateur de la classe Ascenseur
 * @see Ascenseur
 * classes filles:
 * @see AscenseurMusique
 * @see AscenseurVitesseRapide
 */
public abstract class AscenseurOption implements IAscenseur{
	IAscenseur ascenseur;
	public abstract void action() throws IncoherenceEtatAscenseurRequete;
}
