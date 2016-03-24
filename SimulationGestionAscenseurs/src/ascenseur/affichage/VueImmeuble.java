package ascenseur.affichage;

import java.util.List;

import ascenseur.traitement.Constantes;
import ascenseur.traitement.Controleur;
import ascenseur.traitement.IAscenseur;
import ascenseur.traitement.RequeteExterne;

/**
 * Affichage de l'immeuble (l'etage et l'etat des ascenseurs, et l'etat des boutons de chaque palier)
 * @see ascenseur.traitement.Controleur
 */
public class VueImmeuble implements ObservateurControleur{
	
	/**
	 * ajoute dans l'immeuble les ascenseurs (leur etat a l'etage ou ils se trouvent)
	 * @param immeuble
	 * @return immeuble mis a jour
	 */
	private String[][] initialiserImmeuble(String[][] immeuble){
		Controleur controleur = Controleur.getInstance();
		//initialise l'immeuble
		for(int etage = 0; etage < immeuble.length ; ++etage){
			for(int i = 0; i < immeuble[etage].length; ++i){
				immeuble[etage][i] = Constantes.ETAGE;
			}
		}
		//Ajoute dans l'immeuble les ascenseurs (leur etat a l'etage ou ils se trouvent)
		List<IAscenseur> listeAscenseurs = controleur.ascenseurs();
		for(int numeroAscenseur = 0
		  ; numeroAscenseur < listeAscenseurs.size(); ++ numeroAscenseur){
			IAscenseur ascenseur = listeAscenseurs.get(numeroAscenseur);
			immeuble[ascenseur.etage()][numeroAscenseur] = ascenseur.etat();
		}
		return immeuble;
	}//initialiserImmeuble(immeuble)
	
	/**
	 * initialise les boutons pour les requetes externes
	 * @param boutonsPaliers
	 * @return boutonsPaliers
	 */
	private String[][] initialiserBoutonsPaliers(String[][] boutonsPaliers){
		Controleur controleur = Controleur.getInstance();
		List<RequeteExterne> listeRequetesExternes = controleur.requetesExternes();
		//initialise les boutons
		for(int etage = 0; etage < boutonsPaliers.length ; ++etage){
			boutonsPaliers[etage][0] = "0";//boutonsPaliers[etage][0] => bouton monter
			boutonsPaliers[etage][1] = "0";//boutonsPaliers[etage][1] => bouton descendre
		}
		boutonsPaliers[0][1] = " ";
		boutonsPaliers[Controleur.getInstance().nombreEtage()][0] = " ";
		
		//actualise l'etat des boutons
		for(RequeteExterne requeteExterne : listeRequetesExternes){
			if(requeteExterne.demande() == Constantes.BOUTON_MONTER)
				boutonsPaliers[requeteExterne.etage()][0] = Constantes.BOUTON_MONTER;
			else
				boutonsPaliers[requeteExterne.etage()][1] = Constantes.BOUTON_DESCENDRE;
		}
		
		return boutonsPaliers;
	}//initialiserBoutonsPaliers(boutonsPaliers)
	
	/**
	 * Affiche chaque etage de l'immeuble (les etats de chaque ascenseur a leur etage courant)
	 * et les boutons monter(m) et descendre(d) de chaque etage
	 * @param immeuble
	 * @param boutonsPaliers
	 */
	private void afficherImmeuble(String[][] immeuble, String[][] boutonsPaliers){
		System.out.print("__");
		for (int i = 0; i < immeuble[0].length; ++i)
			System.out.print("___");
		System.out.println();
		for(int etage = immeuble.length - 1; etage >= 0; --etage){
			System.out.print("||");
			for(int i = 0; i < immeuble[etage].length; ++i)
				System.out.print(immeuble[etage][i] + "||");
			System.out.println(" " + boutonsPaliers[etage][0] + boutonsPaliers[etage][1]);
		}
	}
	
	/**
	 * raffraichit l'affichage
	 */
	public void mettreAJour(Controleur controleur) {
		int nombreEtage = controleur.nombreEtage();
		String[][] immeuble = new String[nombreEtage + 1][controleur.ascenseurs().size()];
		String[][] boutonsPaliers = new String[nombreEtage + 1][2];
		immeuble = initialiserImmeuble(immeuble);
		boutonsPaliers = initialiserBoutonsPaliers(boutonsPaliers);
		afficherImmeuble(immeuble, boutonsPaliers);		
	}

}
