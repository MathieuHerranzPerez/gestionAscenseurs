package ascenseur.traitement;

import java.util.ArrayList;

import ascenseur.traitement.exception.IncoherenceEtatAscenseurRequete;
import ascenseur.traitement.exception.MauvaiseConstanteChoisi;

/**
 * Classe qui va permettre d'ajouter une fonctionnalite d'avance rapide aux ascenseurs
 */
public class AscenseurMusique extends AscenseurOption{

	public AscenseurMusique(IAscenseur ascenseur) {
		this.ascenseur = ascenseur;
	}
	
	/**
	 * Permet d'afficher qu'il y a de la musique quand action est appele sur un ascenseur equipe de cette option
	 * @see Ascenseur#action()
	 */
	public void action() throws IncoherenceEtatAscenseurRequete{
		System.out.println("Il y a de la musique dans cet ascenseur");
		ascenseur.action();
	}	
	
	public void changerEtat(String etat) throws MauvaiseConstanteChoisi {
		ascenseur.changerEtat(etat);
	}
	
	public void detruireRequete() {
		ascenseur.detruireRequete();
	}
	
	public void bloquer() {
		ascenseur.bloquer();
	}
	
	public void debloquer() {
		ascenseur.debloquer();
	}
	
	public void ajouterRequete(Requete requete) {
		ascenseur.ajouterRequete(requete);
	}
	
	public void creerRequeteInterne(int etage) {
		ascenseur.creerRequeteInterne(etage);
	}
	
	public ArrayList<Requete> requetes() {
		return ascenseur.requetes();
	}
	
	public String etat() {
		return ascenseur.etat();
	}
	
	public int etage() {
		return ascenseur.etage();
	}

	public boolean estBloque() {
		return ascenseur.estBloque();
	}

	public ArrayList<RequeteInterne> requetesInternes() {
		return ascenseur.requetesInternes();
	}

	public void actualiserVue() {
		ascenseur.actualiserVue();
	}
}
