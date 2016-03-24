package ascenseur.affichage;

import ascenseur.traitement.Controleur;

/**
 * Observateur du controleur
 * classe filles:
 * @see VueBoutonsSysteme
 * @see VueImmeuble
 * @see VueRequetes
 */
public interface ObservateurControleur {
	public void mettreAJour(Controleur controleur);
}
