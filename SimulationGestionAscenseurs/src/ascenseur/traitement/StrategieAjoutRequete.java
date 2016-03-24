package ascenseur.traitement;

import java.util.ArrayList;

/**
 * Interface pour choisir une strategie d'organisation des requetes dans la liste des requetes de l'ascenseur
 * @see AjoutSelonCreation
 * @see AjoutOptimal
 */
public interface StrategieAjoutRequete {
	public ArrayList<Requete> ajouterRequete(ArrayList<Requete> listeRequetes, Requete requete, int etageAscenseur);
}
