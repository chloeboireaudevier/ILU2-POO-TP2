package frontiere;

import controleur.ControlLibererEtal;

public class BoundaryLibererEtal {
	private ControlLibererEtal controlLibererEtal;

	public BoundaryLibererEtal(ControlLibererEtal controlLibererEtal) {
		this.controlLibererEtal = controlLibererEtal;
	}

	public void libererEtal(String nomVendeur) {
		boolean vendeurReconnu = this.controlLibererEtal.isVendeur(nomVendeur);
		if(!vendeurReconnu) {
			System.out.println("Mais vous n'êtes pas inscrit sur notre marché aujourd'hui !");
		} else {
			String[] donneesEtal = this.controlLibererEtal.libererEtal(nomVendeur);
			String etalOccupe = donneesEtal[0];
			if(etalOccupe == "true") {
				StringBuilder chaine = new StringBuilder();
				String produit = donneesEtal[2];
				String quantiteInitial = donneesEtal[3];
				String quantiteVendu = donneesEtal[4];
				chaine.append("Vous avez vendu "+quantiteVendu+" sur "+quantiteInitial+" "+produit+".");
				chaine.append("\nAu revoir "+nomVendeur+", passez une bonne journée.");
				System.out.println(chaine);
			}
		}
		
	}

}
