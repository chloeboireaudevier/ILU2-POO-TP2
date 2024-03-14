package frontiere;

import controleur.ControlEmmenager;

public class BoundaryEmmenager {
	private ControlEmmenager controlEmmenager;

	public BoundaryEmmenager(ControlEmmenager controlEmmenager) {
		this.controlEmmenager = controlEmmenager;
	}

	public void emmenager(String nomVisiteur) {
		if (controlEmmenager.isHabitant(nomVisiteur)) {
			System.out.println(
					"Mais vous êtes déjà un habitant du village !");
		} else {
			StringBuilder question = new StringBuilder();
			question.append("Êtes-vous :\n");
			question.append("1 - un druide.\n");
			question.append("2 - un gaulois.\n");
			int choixUtilisateur = -1;
			do {
				choixUtilisateur = Clavier.entrerEntier(question.toString());
				switch (choixUtilisateur) {
				case 1:
					emmenagerDruide(nomVisiteur);
					break;

				case 2:
					System.out.println("Bienvenue villageois "+nomVisiteur);
					StringBuilder question2 = new StringBuilder();
					question2.append("Quelle est votre force ?");
					int force = Clavier.entrerEntier(question2.toString());
					controlEmmenager.ajouterGaulois(nomVisiteur, force);
					break;

				default:
					System.out
							.println("Vous devez choisir le chiffre 1 ou 2 !");
					break;
				}
			} while (choixUtilisateur != 1 && choixUtilisateur != 2);
		}
	}

	private void emmenagerDruide(String nomVisiteur) {
		System.out.println("Bienvenue druide "+nomVisiteur);
		StringBuilder question = new StringBuilder();
		question.append("Quelle est votre force ?");
		int force = Clavier.entrerEntier(question.toString());
		StringBuilder questionMin = new StringBuilder();
		questionMin.append("Quelle est la force de potion la plus faible que vous produisez ?");
		StringBuilder questionMax = new StringBuilder();
		questionMax.append("Quelle est la force de potion la plus forte que vous produisez ?");
		int effetPotionMin = Clavier.entrerEntier(questionMin.toString());
		int effetPotionMax = Clavier.entrerEntier(questionMax.toString());
		while (effetPotionMax < effetPotionMin){
			if(effetPotionMax < effetPotionMin) {
				System.out.println("Attention druide, vous vous êtes trompé entre le minimum et le maximum");
			}
			effetPotionMin = Clavier.entrerEntier(questionMin.toString());
			effetPotionMax = Clavier.entrerEntier(questionMax.toString());
			
		}
		controlEmmenager.ajouterDruide(nomVisiteur, force, effetPotionMin, effetPotionMax);
	}
}
