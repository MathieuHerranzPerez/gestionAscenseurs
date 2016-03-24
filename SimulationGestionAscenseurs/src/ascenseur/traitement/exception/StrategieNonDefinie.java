package ascenseur.traitement.exception;

public class StrategieNonDefinie extends Exception{
	private String strategie;
	
	public StrategieNonDefinie(String strategie) {
		this.strategie = strategie;
	}
	
	public void afficherStrategieNonDefinie(){
		System.out.println("Strategie non definie: " + strategie);
	}
}
