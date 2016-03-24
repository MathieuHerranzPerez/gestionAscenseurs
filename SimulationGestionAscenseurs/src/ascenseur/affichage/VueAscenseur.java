package ascenseur.affichage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

import ascenseur.traitement.Controleur;
import ascenseur.traitement.IAscenseur;
import ascenseur.traitement.RequeteInterne;

/**
 * Affiche tous les boutons interne de l'ascenseur et leur etat
 * @see ascenseur.traitement.Ascenseur
 * demande a l'utilisateur de creer des requetes internes
 */
public class VueAscenseur {
	
	/**
	 * demande la saisie d'un etage
	 * @return etage
	 */
	public int saisirEtageRequete(){
		System.out.println("Etage:   (saisir " + (Controleur.getInstance().nombreEtage() + 1) + " pour sortir)");
		Scanner sc = new Scanner(System.in);
		try {
			int etage = sc.nextInt();
			if(!(etage > 0 && etage <= Controleur.getInstance().nombreEtage() + 1))
				throw new Exception();
			return etage;
		}catch (Exception e) {
			System.out.println("Mauvaise saisie...");
			return saisirEtageRequete();
		}
	}	
	
	/**
	 * demande a l'utilisateur s'il veut boquer ou debloquer l'ascenseur
	 * @return demande
	 */
	public boolean demanderBloquerDebloquer(){
		System.out.println("Bloquer/Debloquer: (saisir oui ou non)");
		BufferedReader bR = new BufferedReader(new InputStreamReader(System.in));
		try {
			String demande = bR.readLine();
			if(!(demande.toLowerCase().equals("oui") || demande.toLowerCase().equals("non")))
				throw new Exception();
			return (demande.toLowerCase().equals("oui"))?true:false;
		}catch (Exception e) {
			System.out.println("Mauvaise saisie...");
			return demanderBloquerDebloquer();
		}
	}
	
	/**
	 * actualise la vue
	 * @param ascenseur
	 */
	public void miseAjour(IAscenseur ascenseur){
		int nombreEtage = Controleur.getInstance().nombreEtage();
		String[] boutons = new String[nombreEtage + 1];//liste des boutons a l'interieur de l'ascenseur
		
		//Affiche l'etat du bouton de blocage de l'ascenseur
		System.out.print("[bloque => " + (ascenseur.estBloque()?"on":"off") + "]\t");
		
		//On initialise tous les boutons a off
		for(int etage = 0; etage < boutons.length; ++etage){
			boutons[etage] = "off";
		}
		
		//Chaque bouton associe a une requete interne est mis a on
		for(RequeteInterne requete : ascenseur.requetesInternes()){
			boutons[requete.etage()] = "on";
		}
		
		//On affiche les boutons de l'ascenseur et leur etat
		for(int etage = boutons.length - 1; etage >= 0; --etage){
			System.out.print("[" + etage + " => " + boutons[etage] + "]    ");
		}
		System.out.println('\n');
		
		
		//########################
		//Demande de l'utilisateur
		//########################
		
		int etage = 0;
		while(true){
			etage = saisirEtageRequete();
			if(etage == Controleur.getInstance().nombreEtage() + 1) break;
			ascenseur.creerRequeteInterne(etage);
		}
		if(demanderBloquerDebloquer()){
			if(ascenseur.estBloque())
				ascenseur.debloquer();
			else
				ascenseur.bloquer();
		}
		
	}//miseAJour(IAscenseur ascenseur)
}
