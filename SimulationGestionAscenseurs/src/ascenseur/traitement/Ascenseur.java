package ascenseur.traitement;

import java.util.ArrayList;

import ascenseur.affichage.VueAscenseur;
import ascenseur.traitement.exception.IncoherenceEtatAscenseurRequete;
import ascenseur.traitement.exception.MauvaiseConstanteChoisi;

/**
 * Classe principale, le sujet etant de simuler une gestion d'ascenseur
 */
public class Ascenseur implements IAscenseur{
	/**
	 * L'etat de l'ascenseur : valeur: IMMOBILE_OUVERT; IMMOBILE_FERME; MONTANT; DESCENDANT
						   de la classe Constantes
	 *@see Constantes
	 */
	private String etat;
	
	/**
	 * Si l'ascenseur est bloqué ou non
	 * @see Ascenseur#bloquer()
	 * @see Ascenseur#debloquer()
	 * @see Ascenseur#estBloquer()
	 */
	private boolean bloque;
	
	/**
	 * L'etage courant de l'ascenseur
	 * @see Ascenseur#etage()
	 * @see Ascenseur#action()
	 */
	private int etage;
	
	/**
	 * La liste des requetes triee selon le parcours de l'ascenseur
	 * @see Ascenseur#ajouterRequete(Requete)
	 */
	private ArrayList<Requete> listeRequetes;
	
	/**
	 * La liste des requetes <b>internes</b> de l'ascenseur (les boutons appuyes dans l'ascenseur)
	 * @see Ascenseur#creerRequeteInterne(int)
	 */
	private ArrayList<RequeteInterne> listeRequetesInternes;
	
	/**
	 * Strategie utilisee pour ajouter une requete
	 * @see StrategieAjoutRequete
	 */
	private StrategieAjoutRequete strategieAjoutRequete;
	
	/**
	 * L'etat de l'ascenseur avant d'avoir ete bloque, permet de remettre l'etat de l'ascenseur avant le blocage
	 * @see Ascenseur#bloquer()
	 * @see Ascenseur#debloquer()
	 * @see Ascenseur#estBloquer()
	 */
	private String sauvegardeEtat;
	
	private VueAscenseur vueAscenseur;
	
	/**
	 * Constructeur d'ascenseur
	 * @param strategieAjoutRequete
	 * @see Ascenseur#bloque
	 * @see Ascenseur#etage
	 * @see Ascenseur#etat
	 * @see Ascenseur#listeRequetes
	 * @see Ascenseur#listeRequetesInternes
	 * @see Ascenseur#strategieAjoutRequete 
	 * @see Ascenseur#vueAscenseur
	 */
	public Ascenseur(StrategieAjoutRequete strategieAjoutRequete){
		etat = Constantes.IMMOBILE_FERME;
		bloque = false;
		etage = 0;
		listeRequetes = new ArrayList<Requete>();
		listeRequetesInternes = new ArrayList<RequeteInterne>();
		this.strategieAjoutRequete = strategieAjoutRequete;
		vueAscenseur = new VueAscenseur();
	}
	
	/**
	 * Change l'etat de l'ascenseur
	 * @see ascenseur.traitement.IAscenseur#changerEtat(java.lang.String)
	 */
	public void changerEtat(String etat) throws MauvaiseConstanteChoisi{
		if(!(etat.equals(Constantes.IMMOBILE_FERME) || etat.equals(Constantes.IMMOBILE_OUVERT)
		|| etat.equals(Constantes.MONTANT) || etat.equals(Constantes.DESCENDANT))){
			throw new MauvaiseConstanteChoisi(etat);
		}
		this.etat = etat;
	}
	
	/**
	 * Detruit la premiere requete de la liste des requetes de l'ascenseur
	 * @see Ascenseur#listeRequetes
	 * @see Ascenseur#listeRequetesInternes
	 */
	public void detruireRequete(){
		listeRequetesInternes.remove(listeRequetes.get(0));
		listeRequetes.remove(0);
	}
	
	/**
	 * Bloque l'ascenseur le rendant provisoirement inutilisable, tout en gardant son ancien etat
	 * @see Ascenseur#bloque
	 * @see AScenseur#sauvegardeEtat
	 */
	public void bloquer(){
		bloque = true;
		sauvegardeEtat = etat;
		try {
			changerEtat(Constantes.IMMOBILE_FERME);
		} catch (MauvaiseConstanteChoisi e) {
			e.printStackTrace();
			e.afficherMauvaiseConstante();
		}
	}
	
	/**
	 * debloque l'ascenseur bloque en lui rendant son ancien etat
	 * @see Ascenseur#bloque
	 * @see AScenseur#sauvegardeEtat
	 */
	public void debloquer(){
		bloque = false;
		etat = sauvegardeEtat;
	}
	
	/**
	 * Verifie si l'ascenseur est bloque ou non
	 * @return true si bloque, false sinon
	 */
	public boolean estBloque(){
		return bloque;
	}
	
	/**
	 * Ajoute une requete à la liste des requetes de l'ascenseur
	 * @see Ascenseur#listeRequete
	 */
	public void ajouterRequete(Requete requete){
		listeRequetes = strategieAjoutRequete.ajouterRequete(listeRequetes, requete, etage);
	}
	
	/**
	 * Creer un requete interne pour l'ascenseur
	 * @param l'etage de la requete interne
	 * @see Ascenseur#listeRequetesInternes
	 */
	public void creerRequeteInterne(int etage){
		RequeteInterne requete = new RequeteInterne(etage);
		ajouterRequete(requete);
		listeRequetesInternes.add(requete);
	}
	
	/**
	 * elle determine l'etat de l'ascenseur à l'iteration n en fonction de son etat a n-1
	 */
	public void action() throws IncoherenceEtatAscenseurRequete{
		try{
			if(!bloque && !listeRequetes.isEmpty()){
				Requete requete = listeRequetes.get(0);
				//cas IMMOBILE_FERME
				if(etat == Constantes.IMMOBILE_FERME){
					//si l'etage destination est superieur a l'etage de l'ascenseur
					if (requete.etage() > etage){
						changerEtat(Constantes.MONTANT);
					}
					//si l'etage destination est inferieur a l'etage de l'ascenseur
					else if (requete.etage() < etage){
						changerEtat(Constantes.DESCENDANT);
					}
					//sinon on ouvre l'ascenseur
					else{
						changerEtat(Constantes.IMMOBILE_OUVERT);
						detruireRequete();
					}
				}
				//cas IMMOBILE_OUVERT
				else if(etat == Constantes.IMMOBILE_OUVERT){
					if(requete.etage() != etage){
						changerEtat(Constantes.IMMOBILE_FERME);
					}
					else
						detruireRequete();
				}
				//cas MONTANT
				else if(etat == Constantes.MONTANT){
					if(requete.etage() < etage)
						throw new IncoherenceEtatAscenseurRequete(requete.etage(), etage, etat);
					if(requete.etage() == etage){
						changerEtat(Constantes.IMMOBILE_OUVERT);
						detruireRequete();
					}
					else
						++etage;
				}
				//cas DESCENDANT
				else if(etat == Constantes.DESCENDANT){
					if(requete.etage() > etage)
						throw new IncoherenceEtatAscenseurRequete(requete.etage(), etage, etat);//TODO exception levee quand Descendant & etage = 0 & nouvelle requete > 0
					if(requete.etage() == etage){
						changerEtat(Constantes.IMMOBILE_OUVERT);
						detruireRequete();
					}
					else
						--etage;
				}
			}else if(etat == Constantes.IMMOBILE_OUVERT && listeRequetes.isEmpty()){
				changerEtat(Constantes.IMMOBILE_FERME);
			}
		}catch(MauvaiseConstanteChoisi excpetion){
			excpetion.afficherMauvaiseConstante();
		}
	}//action()
	
	/**
	 * @return La liste des requetes de l'ascenseur
	 * @see Ascenseur#listeRequetes
	 */
	public ArrayList<Requete> requetes(){
		return listeRequetes;
	}
	
	/**
	 * @return La liste des requetes internes de l'ascenseur
	 * @see Ascenseur#listeRequetesInternes
	 */
	public ArrayList<RequeteInterne> requetesInternes(){
		return listeRequetesInternes;
	}
	
	/**
	 * @return l'etat courant de l'ascenseur
	 * @see Ascenseur#etat
	 */
	public String etat(){
		return etat;
	}
	
	/**
	 * @return l'etage courant de l'ascenseur
	 * @see Ascenseur#etage
	 */
	public int etage(){
		return etage;
	}
	
	public void actualiserVue(){
		vueAscenseur.miseAjour(this);
	}
}
