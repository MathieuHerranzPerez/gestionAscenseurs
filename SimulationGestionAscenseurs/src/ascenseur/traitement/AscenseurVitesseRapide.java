package ascenseur.traitement;

import java.util.ArrayList;

import ascenseur.traitement.exception.IncoherenceEtatAscenseurRequete;
import ascenseur.traitement.exception.MauvaiseConstanteChoisi;

/**
 * Classe qui va permettre d'ajouter une fonctionnalité <b>musique</b> aux ascenseurs
 */
public class AscenseurVitesseRapide extends AscenseurOption{

	public AscenseurVitesseRapide(IAscenseur ascenseur) {
		this.ascenseur = ascenseur;
	}
	
	/**
	 * Permet d'afficher que l'ascenseur avance rapidement quand action() est appele sur un ascenseur equipe de cette option
	 * @see Ascenseur#action()
	 */
	public void action() throws IncoherenceEtatAscenseurRequete {
		System.out.println("Cet ascenseur avance rapidement");
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
