package ascenseur.affichage;

import java.util.List;

import ascenseur.traitement.Controleur;
import ascenseur.traitement.IAscenseur;
import ascenseur.traitement.Requete;

/**
 * Pour afficher toutes les requetes du systeme
 * @see ascenseur.traitement.Requete
 */
public class VueRequetes implements ObservateurControleur{

	/**
	 * Met a jour l'affichage
	 * @param controleur, le singleton controleur
	 */
	public void mettreAJour(Controleur controleur) {
		System.out.println("\n\nREQUETES A SATISFAIRE\n");
		List<IAscenseur> listeAscenseurs = controleur.ascenseurs();
		for(int numeroAscenseur = 0; 
		 numeroAscenseur < listeAscenseurs.size(); ++numeroAscenseur){
			System.out.print("Ascenseur " + (numeroAscenseur + 1) + ": ");
			for(Requete requete : listeAscenseurs.get(numeroAscenseur).requetes())
				System.out.print(requete.toString() + "    ");
			System.out.println();
		}
	}

}
