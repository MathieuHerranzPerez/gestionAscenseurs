package ascenseur.traitement;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.ListView;

import ascenseur.affichage.ObservateurControleur;
import ascenseur.affichage.VueBoutonsSysteme;
import ascenseur.affichage.VueImmeuble;
import ascenseur.affichage.VueRequetes;
import ascenseur.traitement.exception.IncoherenceEtatAscenseurRequete;
import ascenseur.traitement.exception.IncoherenceParametre;
import ascenseur.traitement.exception.MauvaisParametre;
import ascenseur.traitement.exception.StrategieNonDefinie;

/**
 * <p>Singleton Controleur qui fait le lien entre les requetes externes faites par les utilisateurs et les ascenseurs.
 * Il se caracterise par une collection memorisant les requetes externes existantes et une collection incluant
 * les ascenseurs dont il assure le fonctionnement</p>
 */
public class Controleur {
	
	private static Controleur singletonInstance;
	
	/**
	 * liste des requetes externes attribuees aux ascenseur, en attente de satisfaction
	 * @see Controleur#requetesExternes()
	 * @see Controleur#creerRequeteExterne(int, String)
	 * @see Controleur#requeteExterneDejaExistante(RequeteExterne)
    */
	private List<RequeteExterne> listeRequetesExternes;
	
	/**
	 * liste des ascenseurs geres par le controleur
	 * @see Controleur#ajouterAscenseur(IAscenseur)
	 */
	private List<IAscenseur> listeAscenseurs;
	
	/**
	 * Strategie choisie pour l'attribution des requetes externes (par defaut: Attribution Equilibree())
	 * @see AttributionEquilibree
	 */
	private StrategieAttribution stratAttrib;
	
	/**
	 * Nombre d'etage a gerer (par defaut 5)
	 */
	private int nombreEtage;
	
	/**
	 * Liste des differentes vues
	 * @see ascenseur.affichage.VueAscenseur
	 * @see ascenseur.affichage.VueBoutonsSysteme
	 * @see ascenseur.affichage.VueImmeuble
	 * @see ascenseur.affichage.VueRequetes
	 */
	private List<ObservateurControleur> listeVues;
	
	/**
	 * Constructeur du singleton
	 * @see Controleur#listeAscenseurs
	 * @see Controleur#listeRequetesExternes
	 * @see Controleur#listeVues
	 * @see Controleur#nombreEtage
	 * @see Controleur#stratAttrib
	 */
 	private Controleur () {
		listeRequetesExternes = new ArrayList<RequeteExterne>();
		listeAscenseurs = new ArrayList<IAscenseur>();
		stratAttrib = null;
		nombreEtage = 5;
		listeVues = new ArrayList<ObservateurControleur>();
		listeVues.add(new VueImmeuble());
		listeVues.add(new VueBoutonsSysteme());
		listeVues.add(new VueRequetes());
		stratAttrib = new AttributionOptimale();
	}
	
 	/**
 	 * Pour savoir si la requete existe deja ou non, pour eviter les doublons
 	 * @param nouvelleRequete
 	 * @return true si la requete existe deja, false sinon
 	 */
 	private boolean requeteExterneDejaExistante(RequeteExterne nouvelleRequete){
 		boolean trouve = false;
 		for(RequeteExterne requete : listeRequetesExternes){
 			if(nouvelleRequete.etage() == requete.etage() && nouvelleRequete.demande() == requete.demande())
 				trouve = true;
 		}
 		return trouve;
 	}
 	
 	/**
 	 * Permet l'acces au singleton Controleur
 	 * @return l'instance de Controleur
 	 */
	public static Controleur getInstance() {
		if (null == singletonInstance) {
			singletonInstance = new Controleur();
		}
		return singletonInstance;
	}//getInstance()
	
	/**
	 * Change la strategie a adopter pour l'attribution des requetes externes
	 * @param typeStrat, la nouvelle strategie a adopter
	 * @throws MauvaisParametre si on tente de mettre une strategie incorecte
	 * @see AttributionEquilibree
	 * @see AttributionOptimal
	 */
	public void changerStrategieAttribution(StrategieAttribution typeStrat) throws MauvaisParametre{
		if(typeStrat == null) throw new MauvaisParametre("typeStrat");
		stratAttrib = typeStrat;
	}//changerStrategieAttribution()
	
	/**
	 * Change le nombre d'etage de l'immeuble
	 * @param nombreEtage
	 * @throws MauvaisParametre
	 */
	public void changerNombreEtage(int nombreEtage) throws MauvaisParametre{
		if(nombreEtage <= 0) throw new MauvaisParametre("nombreEtage");
		this.nombreEtage = nombreEtage;
	}//changerNombreEtage(nombreEtage)
	
	/**
	 * Met a jour la liste des requetes externes en fonction de la requete courante, de l'etage puis
	 * appelle la methode action() de chaque ascenseur pour mettre a jour leurs etats
	 * @see Ascenseur
	 * @throws IncoherenceEtatAscenseurRequete
	 */
	public void action() throws IncoherenceEtatAscenseurRequete{
		//met a jour la liste des requetes externes en fonction de la requete courante, de l'etage
		//puis
		//appelle la methode action() de chaque ascenseur pour mettre a jour leurs etats
		for(IAscenseur ascenseur : listeAscenseurs){
			//mettre a jour listeRequeteExternes
			ArrayList<Integer> indicesRequetesADetruire = new ArrayList<Integer>();
			for(int i = 0; i < listeRequetesExternes.size(); ++i){
				//Si la premiere requete de l'ascenseur correspond a cette requete 
				//et l'etage de l'ascenceur correspond a l'etage de la requete
				if(ascenseur.requetes().size() > 0 && listeRequetesExternes.get(i) == ascenseur.requetes().get(0) 
				 && listeRequetesExternes.get(i).etage() == ascenseur.etage()){
					indicesRequetesADetruire.add(i);
				}					
			}
			for(Integer indice : indicesRequetesADetruire){
				listeRequetesExternes.remove(indice);
			}
			
			ascenseur.action();
		}
	}//action()
	
	/**
	 * Creer une requete externe
	 * @param etage etage d'ou provient la requete
	 * @param demande de monte ou de descendre
	 * @throws StrategieNonDefinie
	 * @throws IncoherenceParametre
	 */
	public void creerRequeteExterne(int etage, String demande)throws StrategieNonDefinie, IncoherenceParametre {
		if(stratAttrib == null) throw new StrategieNonDefinie("StrategieAttribution");
		if((etage == 0 && demande.equals(Constantes.BOUTON_DESCENDRE)) 
		 || (etage == nombreEtage && demande.equals(Constantes.BOUTON_MONTER))){
			String[] params = {"etage", "demande"};
			throw new IncoherenceParametre(params);
		}
		
		RequeteExterne requete = new RequeteExterne(etage, demande);
		if(!requeteExterneDejaExistante(requete)){
			int indiceAscenseurChoisi = stratAttrib.choisirAscenseur(listeAscenseurs, etage);
			listeAscenseurs.get(indiceAscenseurChoisi).ajouterRequete(requete);
			listeRequetesExternes.add(requete);
		}
	}//creerRequeteExterne()
	
	/**
	 * Ajoute un ascenseur a la liste des ascenseurs a gerer
	 * @see Ascenseur
	 * @param ascenseur
	 * @throws MauvaisParametre
	 */
	public void ajouterAscenseur(IAscenseur ascenseur) throws MauvaisParametre{
		if(ascenseur == null)throw new MauvaisParametre("ascenseur");
		listeAscenseurs.add(ascenseur);
	}//ajouterAscenseur

	/**
	 * acces a la liste des ascenseurs a gerer
	 * @return la liste des ascenseurs a gerer
	 */
	public List<IAscenseur> ascenseurs(){
		return listeAscenseurs;
	}//ascenseurs()
	
	/**
	 * acces au nombre d'etage
	 * @return le nombre d'etage
	 */
	public int nombreEtage(){
		return nombreEtage;
	}

	/**
	 * acces a la liste des requetes externes
	 * @return la liste des equetes externes
	 */
	public List<RequeteExterne> requetesExternes(){
		return listeRequetesExternes;
	}

	/**
	 * met a jour les vues du controleur
	 */
	public void actualiserVues(){
		for(ObservateurControleur vue : listeVues)
			vue.mettreAJour(this);
	}
}