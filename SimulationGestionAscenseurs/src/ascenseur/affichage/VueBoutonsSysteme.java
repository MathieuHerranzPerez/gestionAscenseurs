package ascenseur.affichage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ascenseur.traitement.Constantes;
import ascenseur.traitement.Controleur;
import ascenseur.traitement.IAscenseur;
import ascenseur.traitement.exception.IncoherenceParametre;
import ascenseur.traitement.exception.StrategieNonDefinie;

/**
 * la vue des boutons internes de chaque ascenseur
 * Pour chaque etage demande a l'utilisateur de creer ou pas des requetes externes
 * @see ascenseur.traitement.Controleur
 * @see ascenseur.traitement.Ascenseur#actualiserVue()
 */
public class VueBoutonsSysteme implements ObservateurControleur{
	
	/**
	 * demande a l'utilisateur de saisir l'action associee a la requete externe (monter ou descendre)
	 * @return demande
	 */
	public String saisirDemandeRequeteExterne(){
		System.out.println("Appeler un ascenseur pour: (saisir m (monter) ou d (descendre); saisir i (ignorer))");
		BufferedReader bR = new BufferedReader(new InputStreamReader(System.in));
		try {
			String demande = bR.readLine();
			if(!(demande.toLowerCase().equals("m") || demande.toLowerCase().equals("d") 
			  || demande.toLowerCase().equals("i")))
				throw new Exception();
			return demande;
		}catch (Exception e) {
			System.out.println("Mauvaise saisie...");
			return saisirDemandeRequeteExterne();
		}
	}
	

	/**
	 * actualise la vue
	 */
	public void mettreAJour(Controleur controleur) {
		try{
			BufferedReader bR = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Voulez-vous creer des requetes? ('o' pour oui, sinon considere comme non)");
			String str = bR.readLine();
			if(str.equals("o")){
				System.out.println("\n--------------------------------------\n");
				System.out.println("Vue boutons internes des ascenseurs:\n");
				
				
				System.out.println("Voulez-vous creer des requetes INTERNES? (saisir 'o' pour en creer)");
				str = bR.readLine();
				if(str.equals("o")){
					int numeroAscenseur = 0;
					for(IAscenseur ascenseur : controleur.ascenseurs()){
						++numeroAscenseur;
						System.out.print("Ascenseur " + numeroAscenseur +": ");
						ascenseur.actualiserVue();
					}
				}
				
				System.out.println("\n\nCreation de requetes externes:\n");
				for(int etage = 0; etage < Controleur.getInstance().nombreEtage() + 1; ++etage){
					System.out.println("Etage " + etage);
					for(String demande = saisirDemandeRequeteExterne();!demande.toLowerCase().equals("i")
						; demande = saisirDemandeRequeteExterne()){
						while(etage == 0 && demande.equals("d")){
							System.out.println("Mauvaise saisie");
							demande = saisirDemandeRequeteExterne();
						}
						while(etage == controleur.nombreEtage() && demande.equals("m")){
							System.out.println("Mauvaise saisie");
							demande = saisirDemandeRequeteExterne();
						}
						if(demande.toLowerCase().equals("m")){
							Controleur.getInstance().creerRequeteExterne(etage, Constantes.BOUTON_MONTER);
						}
						else if (demande.toLowerCase().equals("d")){
							Controleur.getInstance().creerRequeteExterne(etage, Constantes.BOUTON_DESCENDRE);
						}
					}
				}
			}
		} catch (StrategieNonDefinie e) {
			e.afficherStrategieNonDefinie();
			e.printStackTrace();
		} catch (IncoherenceParametre e) {
			e.afficherParametres();
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
