package ascenseur.traitement.exception;

public class MauvaiseConstanteChoisi extends Exception{
	private static final long serialVersionUID = 1L;
	private String mauvaiseConstante;
	public MauvaiseConstanteChoisi(String mauvaiseConstante){
		super();
		this.mauvaiseConstante = mauvaiseConstante;
	}
	
	public void afficherMauvaiseConstante(){
		System.out.println("Mauvaise constante: " + mauvaiseConstante);
	}
}
