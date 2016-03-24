package ascenseur.traitement;
import java.util.List;

/**
 * Cette classe va permettre d'associer une strategie d'attribution de requetes au controleur
 * Ici, une strategie pour associer une requete a l'ascenseur ayant le moins de requetes
 */
public class AttributionEquilibree implements StrategieAttribution { //implements StrategieAttribution
	
	/**
	 * return l'indice de l'ascenseur ayant le moins de requetes
	 * @param listeAscenseurs
	 * @param etage
	 * @return l'ascenseur ayant le moins de requetes
	 */
	public int choisirAscenseur(List<IAscenseur> listeAscenseurs, int etage) {
		
		int minimum = 0; // initialisation de l'ascenseur ayant le moins de requetes
		for(int indiceCourant = 1 ; indiceCourant < listeAscenseurs.size() ; ++indiceCourant) { 	//si l'ascenseur sur lequel on est en passant dans la boucle a
															//moins de requete que l'ascenseur "minimum, "minimum" prend sa valeur
			if(listeAscenseurs.get(indiceCourant).requetes().size() < listeAscenseurs.get(minimum).requetes().size())
				minimum = indiceCourant;
		}
		return minimum;
	}
}
