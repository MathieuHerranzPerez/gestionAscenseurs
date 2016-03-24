package ascenseur.traitement.exception;

public class MauvaisParametre extends Exception{
	String nomParametre;
	public MauvaisParametre(String nomParametre) {
		this.nomParametre = nomParametre;
	}
	public void afficherParametre(){
		System.out.println("Mauvaise valeur choisie pour " + nomParametre);
	}
}
