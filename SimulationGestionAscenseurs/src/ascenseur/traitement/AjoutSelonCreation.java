package ascenseur.traitement;

import java.util.ArrayList;
import java.util.Collections;

/**
 * ajoute les requetes dans la liste des requetes de l'ascenseur dans l'ordre de leur creation, 
 * seulement les requetes internes sont prioritaires
 */
public class AjoutSelonCreation implements StrategieAjoutRequete{

	/**
	 * ajoute les requetes dans la liste des requetes de l'ascenseur dans l'ordre de leur creation, 
	 * seulement les requetes internes sont prioritaires
	 * @param listeRequetes
	 * @param requete
	 * @param etageAscenseur
	 * @return listeRequetes avec la requete ajoutee
	 */
	public ArrayList<Requete> ajouterRequete(ArrayList<Requete> listeRequetes, Requete requete, int etageAscenseur) {
		//On ajoute la requete en fin de liste
		listeRequetes.add(requete);
		//Si la requete est Interne et s'il y a au moins 2 ascenseurs
		if(requete.getClass() == RequeteInterne.class && listeRequetes.size() > 1)
			//Tant qu'il y a une requete externe avant on swap avec la requete interne
			for(int i = listeRequetes.size() - 1; i > 0 &&
			  listeRequetes.get(i-1).getClass() != requete.getClass(); --i)
				Collections.swap(listeRequetes, i, i-1);
		return listeRequetes;
	}

}
