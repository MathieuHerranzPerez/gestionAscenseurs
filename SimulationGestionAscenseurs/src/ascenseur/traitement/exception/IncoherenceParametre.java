package ascenseur.traitement.exception;

import java.util.ArrayList;

public class IncoherenceParametre extends Exception{
	String[] parametres;
	
	public IncoherenceParametre(String[] parametres) {
		this.parametres = parametres;
	}
	
	public void afficherParametres(){
		String parametresStr = "";
		for(String parametre : parametres){
			parametresStr += "; " + parametre; 
		}
		System.out.println("Parametres Incoherents: " + parametresStr);
	}
}
