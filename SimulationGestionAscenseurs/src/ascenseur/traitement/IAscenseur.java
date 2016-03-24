package ascenseur.traitement;

import java.util.ArrayList;

import ascenseur.traitement.exception.IncoherenceEtatAscenseurRequete;
import ascenseur.traitement.exception.MauvaiseConstanteChoisi;
/**
 * @see Ascenseur
 * @see AscenseurOption
 */
public interface IAscenseur {
	public void action() throws IncoherenceEtatAscenseurRequete;
	public void changerEtat(String etat) throws MauvaiseConstanteChoisi;
	public void detruireRequete();
	public void bloquer();
	public void debloquer();
	public boolean estBloque();
	public void ajouterRequete(Requete requete);
	public void creerRequeteInterne(int etage);
	public ArrayList<Requete> requetes();
	public String etat();
	public int etage();
	public ArrayList<RequeteInterne> requetesInternes();
	public void actualiserVue();
}
