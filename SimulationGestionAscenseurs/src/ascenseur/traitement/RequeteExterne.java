package ascenseur.traitement;

/**
 * Classe difinissant ce qu'est une requete externe
 */
public class RequeteExterne implements Requete{
	
	/**
	 * demande de monte ou descente d'un utilisateur
	 * @see Constantes#BOUTON_MONTER
	 * @see Constantes#BOUTON_DESCENDRE
	 */
	private String demande;
						   
	/**
	 * etage ou la demande a ete faite
	 */
	private int etage;
	
	/**
	 * Constructeur de requetes externes
	 * @param etage, ou l'on veut appeler un ascenseur
	 * @param demande, si on veut monter ou descendre
	 * @see RequeteExterne#demande
	 * @see RequeteExterne#etage
	 */
	public RequeteExterne(int etage, String demande){
		this.etage = etage;
		this.demande = demande;
	}
	
	/**
	 * acces a etage
	 * @return etage
	 * @see RequeteExterne#etage
	 */
	public int etage(){
		return etage;
	}
	
	/**
	 * acces a la demande
	 * @return demande
	 * @see RequeteExterne#demande
	 */
	public String demande(){
		return demande;
	}

	/**
	 * permet un bel affichage d'une requete externe
	 */
	public String toString(){
		return "[" + etage + ", " + demande + "]";
	}
}
