package ascenseur.affichage;


import java.util.Scanner;

import ascenseur.traitement.AjoutOptimal;
import ascenseur.traitement.Ascenseur;
import ascenseur.traitement.AscenseurMusique;
import ascenseur.traitement.AscenseurVitesseRapide;
import ascenseur.traitement.AttributionEquilibree;
import ascenseur.traitement.Controleur;
import ascenseur.traitement.exception.IncoherenceEtatAscenseurRequete;
import ascenseur.traitement.exception.MauvaisParametre;

/**
 * class principale du systeme (main)
 */
public class Gestionnaire {
	
	/**
	 * demande a l'utilisateur de saisir le nombre d'etage de l'immeuble
	 *  et change le nombre d'etage de l'immeuble seulon cette saisie
	 */
	public static void saisirNombreEtage() {
		System.out.println("Nombre d'etage :");
		Scanner sc = new Scanner(System.in);
		try {
			Controleur.getInstance().changerNombreEtage(sc.nextInt());
		}catch (MauvaisParametre e) {
			System.out.println("Mauvaise saisie...");
			saisirNombreEtage();
		}
	}
	
	/**
	 * demande a l'utilisateur de saisir le nombre d'ascenseur a creer
	 * @return nombreAscenseur
	 */
	public static int saisirNombreAscenseur(){
		System.out.println("Nombre d'ascenseur:");
		Scanner sc = new Scanner(System.in);
		try {
			int nombreAscenseur = sc.nextInt();
			if(!(nombreAscenseur > 0)) throw new Exception();
			return nombreAscenseur;
		}catch (Exception e) {
			System.out.println("Mauvaise saisie...");
			e.printStackTrace();
			return saisirNombreAscenseur();
		}
	}
	
	/**
	 * main
	 * @param args
	 */
	public static void main(String[] args){
		try{
			Controleur controleur = Controleur.getInstance();
			controleur.changerStrategieAttribution(new AttributionEquilibree());
			saisirNombreEtage();
			int nombreAscenseur = saisirNombreAscenseur();
			for(int n = 0; n < nombreAscenseur; ++n){
				controleur.ajouterAscenseur(new AscenseurMusique (new AscenseurVitesseRapide(new Ascenseur(new AjoutOptimal()))));
			}
			
			for(;;){
				System.out.println("\n\n########################################\n"
								  	  +"#          NOUVELLE ITERATION          #\n"
								  	  +"########################################\n\n");
				
				controleur.actualiserVues();
				
				System.out.println("\n\n------------------------------\n"
						  +"Action:\n");
				controleur.action();
				
			}
		}catch(MauvaisParametre e){
			e.printStackTrace();
			e.afficherParametre();
		}catch (IncoherenceEtatAscenseurRequete e) {
			e.printStackTrace();
		}
	}
}
