package ascenseur.traitement;

import java.util.List;

/**
 * Interface pour choisir une strategie d'attribution des requetes externes entre les ascenseurs
 * @see AttributionEquilibree
 * @see AttributionOptimale
 */
public interface StrategieAttribution {
	
	public int choisirAscenseur(List<IAscenseur> listeAscenseurs, int etage);
}
