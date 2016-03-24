package ascenseur.traitement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Elle choisie l'ascenseur le plus proche de la requete
 * @see StrategieAttribution
 */
public class AttributionAscenseurProche implements StrategieAttribution {
 
	int ascenseurSelectionne;

	/**
	 * 
	 * @param i nombre d'etage entre deux ascenbseurs
	 * @return le nombre d'etage entre deux ascenseurs
	 */
	private int abs(int i) { 
			if(i < 0)
				return i*-1;
			else
				return i;
		}
		
	/**
	 * 
	 * @param ascenseurCompatible
	 * @param etageCourant
	 * @return retourne un treemap contenant comme clee un ascenseur et comme valeur la difference d'etage entre l'etage courant et un ascenseur
	 */
	private Map<IAscenseur, Integer> differenceEtage(List<IAscenseur> ascenseurCompatible, int etageCourant) {
		Map<IAscenseur, Integer> differenceEtage = new TreeMap<IAscenseur, Integer>();
			for(int i = 0 ; i <= ascenseurCompatible.size() ; ++i) {
				differenceEtage.put(ascenseurCompatible.get(i), (abs(etageCourant - ascenseurCompatible.get(i).etage()))); //a tester
			}
			return differenceEtage;
		}
		
	/**
	 * 
	 * @param ascenseurCompatible
	 * @param map, l'ascenseur le plus proche de l'etage ou la requete a ete demandee
	 */
	private void obtenirAscenseurProche(List<IAscenseur> ascenseurCompatible, Map<IAscenseur, Integer> map) {
			if(!(ascenseurCompatible.isEmpty())) {
				int minimum =   map.get(ascenseurCompatible.get(0));
				ascenseurSelectionne = 0;
				for (int i = 0 ; i <= ascenseurCompatible.size() ; ++i) {
					if(map.get(ascenseurCompatible.get(i)) < minimum) { //listAscenseur.get(i)).getDifferenceEtage()
						minimum = map.get(ascenseurCompatible.get(i));
						ascenseurSelectionne = i; 
					}
				}
			}
		}
	/**
	 * va retourner l'indice de l'ascenseur le plus proche
	 * @param listeAscenseur
	 * @param etageCourant
	 * @return ascenseurSelectionne
	 */
	public int choisirAscenseur(List<IAscenseur> listeAscenseur, int etageCourant) {
		
		List<IAscenseur> ascenseurDispo = new ArrayList<IAscenseur>();
		
		for (int i = 0 ; i <= listeAscenseur.size() ; ++i) { //tous les ascenseurs non utilisés
			//Ascenseur a = listeAscenseur.get(i);
			//if (a.etat() != "MONTANT" || a.etat() != "DESCENDANT" ) { 
			if (listeAscenseur.get(i).requetes().isEmpty()) {
				ascenseurDispo.add(i, listeAscenseur.get(i));
			}
		}
		
		if((ascenseurDispo.isEmpty())){ // Si pas d'ascenseur dispo : tous les ascenseurs qui montent ou descendent en direction de l'etage courant
			List<IAscenseur> ascenseurCompatible = new ArrayList<IAscenseur>();
			for (int i = 0 ; i <= listeAscenseur.size() ; ++i) {
				IAscenseur a = listeAscenseur.get(i);
				/*if ((a.etat() == "MONTANT" && (a.etage() <= etageCourant)) || (a.etat() == "DESCENDANT" && (a.etage() >= etageCourant)))
					ascenseurCompatible.add(i, a); */
				ArrayList<Requete> listeRequetes = a.requetes();
				if((listeRequetes.get(0).etage() > a.etage() && (a.etage() < etageCourant)) || (listeRequetes.get(0).etage() < a.etage() && (a.etage() > etageCourant)))
					ascenseurCompatible.add(i, a); 
			}
			
			if (ascenseurCompatible.isEmpty()) { // Si pas d'ascenseur qui arrivent dans le bon sens, retourne l'ascenseur dont l'etage de sa derniere requete est le plus proche de l'étage courant
				for (int i = 0 ; i <= listeAscenseur.size() ; ++i) {
					IAscenseur a = listeAscenseur.get(i);
					int minimum = 99999;
					if(abs((a.requetes().get(a.requetes().size()-1).etage() - etageCourant)) < minimum) 
						ascenseurSelectionne = i;
					}
					return ascenseurSelectionne;
				}
			
			
			else { //Si ascenseurs dans le bon sens
				//Map<Ascenseur, Integer> differenceEtage = differenceEtage(ascenseurCompatible, etageCourant); stocker dans variable puis utiliser variable dans "obtenirAscenseurProche" ?
				obtenirAscenseurProche(ascenseurCompatible, differenceEtage(ascenseurCompatible, etageCourant));
				return ascenseurSelectionne;
			}
		}
		else { // Si ascenseurs dispo
		
			Map<IAscenseur, Integer> differenceEtage = differenceEtage(ascenseurDispo, etageCourant);
			obtenirAscenseurProche(ascenseurDispo, differenceEtage);
		
			return ascenseurSelectionne;
		}
	}
}