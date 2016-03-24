package ascenseur.traitement;

/**
 * Les constantes qui servent pour l'affichage et qui qualifient l'etat des ascenseurs
 */
public class Constantes {
	private Constantes(){}
	
	/**
	 * Constante pour l'etat d'un ascenseur immobile ouvert
	 * @see Ascenseur#etat()
	 * @see ascenseur.affichage.VueAscenseur#miseAjour(IAscenseur)
	 */
	public static final String IMMOBILE_OUVERT = "O";
	
	/**
	 * Constante pour l'etat d'un ascenseur immobile ferme
	 * @see Ascenseur#etat()
	 * @see ascenseur.affichage.VueAscenseur#miseAjour(IAscenseur)
	 */
	public static final String IMMOBILE_FERME = "F";
	
	/**
	 * Constante pour l'etat d'un ascenseur qui est en train de monter
	 * @see Ascenseur#etat()
	 * @see ascenseur.affichage.VueAscenseur#miseAjour(IAscenseur)
	 */
	public static final String MONTANT = "M";
	
	/**
	 * Constante pour l'etat d'un ascenseur qui est en train de descendre
	 * @see Ascenseur#etat()
	 * @see ascenseur.affichage.VueAscenseur#miseAjour(IAscenseur)
	 */
	public static final String DESCENDANT = "D";
	
	/**
	 * Constante pour requete externe pour monter
	 * @see ascenseur.affichage.VueBoutonsSysteme#mettreAJour(Controleur)
	 */
	public static final String BOUTON_MONTER = "m";
	
	/**
	 * Constante pour requete externe pour descendre
	 * @see ascenseur.affichage.VueBoutonsSysteme#mettreAJour(Controleur)
	 */
	public static final String BOUTON_DESCENDRE = "d";
	
	/**
	 * Constante pour l'absence de requete externe
	 * @see ascenseur.affichage.VueBoutonsSysteme#mettreAJour(Controleur)
	 */
	public static final String BOUTON_DEFAUT = "0";
	
	/**
	 * Constante pour l'affichage des étages
	 * @see ascenseur.affichage.VueImmeuble#mettreAJour(Controleur)
	 */
	public static final String ETAGE = " ";
}
