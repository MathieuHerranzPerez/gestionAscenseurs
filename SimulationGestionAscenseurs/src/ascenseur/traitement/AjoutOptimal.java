package ascenseur.traitement;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Classe permettant l'ajoute de requetes dans la liste en fonction de: 
 * - La proximite de l'ascenseur par rapport à l'etage des requetes
 * - Le sens de parcours de l'ascenseur
 */
public class AjoutOptimal implements StrategieAjoutRequete{
	

	/**
	 * Si la liste des requetes est vide on ajoute la requete normalement
	 * 
	 * On distingue deux suites qui peuvent exister dans la liste :
	 * suite montante    (pour tout i, listeRequetes.get(i).etage() >= etageAscenseur)
	 * suite descandante (pour tout i, listeRequetes.get(i).etage() < etageAscenseur)
	 *
	 * il ne peut y avoir au maximum que deux suites dans la liste et elles sont forcement differentes
	 * @see ascenseur.traitement.StrategieAjoutRequete#ajouterRequete(java.util.ArrayList, ascenseur.traitement.Requete, int)
	 * @param listeRequetes
	 * @param requete
	 * @param etageAscenseur
	 * @return listeRequetes
	 */
	public ArrayList<Requete> ajouterRequete(ArrayList<Requete> listeRequetes, Requete requete, int etageAscenseur) {

		if(listeRequetes.isEmpty()){
			listeRequetes.add(requete);
			return listeRequetes;
		}

		
		int i = 0; // indice de la liste ou l'on ajoutera la requete
		
		// si l'ascenseur devra monter
		if(etageAscenseur <= requete.etage()){
			
			//si la liste commence par une
			//suite MONTANTE
			if(listeRequetes.get(0).etage() >= etageAscenseur){
				//on cherche ou dans la suite on ajoutera la requete
				for(;listeRequetes.size() > i
						&& listeRequetes.get(i).etage() <= requete.etage(); ++i);
			}
			
			//si la liste commence par une
			//suite DESCENDANTE
			else if (listeRequetes.get(0).etage() < etageAscenseur){
				//on cherche une suite montante;
				for(;listeRequetes.size() > i 
						&& listeRequetes.get(i).etage() < etageAscenseur; ++i);
				//on cherche ou dans la suite on ajoutera la requete
				for(;listeRequetes.size() > i
						&& listeRequetes.get(i).etage() <= requete.etage(); ++i);
			}
		}
		//si l'ascenseur devra descendre
		else if(etageAscenseur > requete.etage()){
			
			//si la liste commence par une
			//suite MONTANTE
			if(listeRequetes.get(0).etage() >= etageAscenseur){
				//on cherche une suite descendante;
				for(;listeRequetes.size() > i
						&& listeRequetes.get(i).etage() >= etageAscenseur; ++i);
				//on cherche ou dans la suite on ajoutera la requete
				for(;listeRequetes.size() > i
						&& listeRequetes.get(i).etage() >= requete.etage(); ++i);
			}
			
			//si la liste commence par une
			//suite DESCENDANTE
			else if (listeRequetes.get(0).etage() < etageAscenseur){
				//on cherche ou dans la suite on ajoutera la requete
				for(;listeRequetes.size() > i
						&& listeRequetes.get(i).etage() >= requete.etage(); ++i);
			}
		}		//ajouter la requete a l'indice i de la liste
		listeRequetes.add(requete);
		if(i < listeRequetes.size()){
			for(int j = listeRequetes.size() - 1; j > 0 && i < j ; --j)
				Collections.swap(listeRequetes, j, j - 1);
		}
			
		return listeRequetes;
	}

}
