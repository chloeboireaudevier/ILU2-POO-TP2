package controleur;

import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;

public class ControlAcheterProduit {
	private Village village;
	private ControlTrouverEtalVendeur controlTrouverEtalVendeur;
	private ControlVerifierIdentite controlVerifierIdentite;

	public ControlAcheterProduit(ControlVerifierIdentite controlVerifierIdentite,
			ControlTrouverEtalVendeur controlTrouverEtalVendeur,
			Village village) {
		this.village = village;
		this.controlVerifierIdentite = controlVerifierIdentite;
		this.controlTrouverEtalVendeur = controlTrouverEtalVendeur;
	}
	
	public boolean verifierIdentite(String nomVillageois) {
		return this.controlVerifierIdentite.verifierIdentite(nomVillageois);
	}
	
	
	public Gaulois[] rechercherProduit(String nomProduit) {
		return this.village.rechercherVendeursProduit(nomProduit);
	}
	
	public Etal getEtal(Gaulois gaulois) {
		return controlTrouverEtalVendeur.trouverEtalVendeur(gaulois.getNom());
	}

	public void acheterProduit(Etal etalAcheter, int quantite) {
		etalAcheter.acheterProduit(quantite);
	}
}
