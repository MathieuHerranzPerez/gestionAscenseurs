package ascenseur.traitement.exception;

public class IncoherenceEtatAscenseurRequete extends Exception{
	private static final long serialVersionUID = 1L;
	private int etageRequete;
	private int etageAscenseur;
	private String etat;
	
	public IncoherenceEtatAscenseurRequete(int etageRequete, int etageAscenseur, String etat){
		this.etageRequete = etageRequete;
		this.etageAscenseur = etageAscenseur;
		this.etat = etat;
	}
	public void afficherIncoherence(){
		System.out.println("incoherence => etage requete en tete de liste: " + etageRequete + ", etage de l'ascenseur: " 
				+ etageAscenseur + " etat: " + etat);
	}
}
