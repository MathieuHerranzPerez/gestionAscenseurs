package ascenseur.traitement;

import java.util.ArrayList;
import java.util.List;

/**
 * Strategie pour trouver le meilleur asceseur par rapport au temps de parcours des requetes de chaque ascenseur
 * @see StrategieAttribution
 */
public class AttributionOptimale implements StrategieAttribution{

	/**
	 * renvoie la veleur absolue d'un nombre
	 * @param i nombre d'etage entre deux etages
	 * @return i, la valeur absolue
	 */
	private int abs(int i) {
		if (i < 0)
			return i*-1;
		else
			return i;
	}
	/**
	 * selectionne un ascenseur en fonction du temps qu'il met a effectuer ses requetes
	 * @param listeAscenseur
	 * @param etage
	 * @return numeroAscenseurSelectionne, l'ascenseur choisi
	 */
	public int choisirAscenseur(List<IAscenseur> listeAscenseurs, int etage) {
		
		int minimum = 9999999; // ascenseur qui met le plus petit temps pour reasliser ses requetes
		int numeroAscenseurSelectionne = 0; // numero de l'acsenseur dans la liste qui sera selectionne
		int temps;
		
		for(int i = 0 ; i < listeAscenseurs.size() ;  ++i) {
			
			ArrayList<Requete> reqAscenseur = listeAscenseurs.get(i).requetes(); //liste des requetes de chaque ascenseur
			temps = 0; 
			IAscenseur ascenseur = listeAscenseurs.get(i);
			int etageAscenseurDeplacement = ascenseur.etage();
			
			for(int j = 0 ; j < reqAscenseur.size() ;  ++j) {
				
				int etageRequeteAscenseur = ascenseur.requetes().get(j).etage();
				
				if((etageAscenseurDeplacement <= etage && etageRequeteAscenseur >= etage) || (etageAscenseurDeplacement >= etage && etageRequeteAscenseur <= etage)) {
					temps += abs(etage - etageAscenseurDeplacement);
					break; //Si l'ascenseur va dans la direction de l'étage courant, on sort de la boucle car l'acenseur passera par l'étage courant
				} 
				
				temps += abs(etageRequeteAscenseur - etageAscenseurDeplacement) + 2; //rajout dans la variable "temps" du temps effectuer pour accomplir chaque requete
				
				
				if(j == reqAscenseur.size() -1)
					temps += abs(etage - etageAscenseurDeplacement);
					//temps += abs(etageRequeteAscenseur - etage);
					
				
				etageAscenseurDeplacement = etageRequeteAscenseur;
			}
			
			if(temps < minimum) { //on selectionne l'ascenseur qui met le temps le plus court
				minimum = temps;
				numeroAscenseurSelectionne = i;
			}			
		}
		
		return numeroAscenseurSelectionne;
	}	
}


