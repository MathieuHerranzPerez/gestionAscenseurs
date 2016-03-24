package ascenseur.traitement;

/**
 * Classe difinissant ce qu'est une requete externe
 */
public class RequeteInterne implements Requete{
	
	/**
	 * etage ou l'on veut aller
	 */
	private int etage;
	
	/**
	 * Constructeur de requetes internes
	 * @param etage ou on veut aller
	 * @see RequeteInterne#etage
	 */
	public RequeteInterne(int etage){
		this.etage = etage;
	}
	
	/**
	 * acces a etage
	 * @return etage
	 * @see RequeteInterne#etage
	 */
	public int etage(){
		return etage;
	}

	/**
	 * permet un bel affichage d'une requete interne
	 */
	public String toString(){
		return "[" + etage + "]";
	}
}
