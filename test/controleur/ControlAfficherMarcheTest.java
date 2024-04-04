package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import villagegaulois.Etal;
import villagegaulois.Village;

class ControlAfficherMarcheTest {
	private Village village;
	private Chef abraracourcix;

	@BeforeEach
	public void initialiserSituation() {
		System.out.println("Initialisation...");
		village = new Village("le village des irréductibles",10,5);
		abraracourcix = new Chef("Arabracourix",10,village);
		village.setChef(abraracourcix);
	}

	@Test
	void testControlAfficherMarche() {
		ControlAfficherMarche controlAfficherMarche = new ControlAfficherMarche(village);
		assertNotNull(controlAfficherMarche,"Constructeur ne renvoie pas nul");
	}

	@Test
	void testDonnerEtatMarche() {
		ControlAfficherMarche controlAfficherMarche = new ControlAfficherMarche(village);
		ControlVerifierIdentite controlVerifierIdentite = new ControlVerifierIdentite(village);
		ControlPrendreEtal controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite,village);
		ControlTrouverEtalVendeur controlTrouverEtalVendeur = new ControlTrouverEtalVendeur(village);
		ControlLibererEtal controlLibererEtal = new ControlLibererEtal(controlTrouverEtalVendeur);
		ControlEmmenager controlEmmenager = new ControlEmmenager(village);
		controlEmmenager.ajouterGaulois("Bonemine", 10);
		controlPrendreEtal.prendreEtal("Bonemine", "fleurs", 10);
		
		Etal etal = controlTrouverEtalVendeur.trouverEtalVendeur("Bonemine");
		String[] test = new String[3];
		
		test[0]= etal.getVendeur().getNom();
		test[1]=String.valueOf(etal.getQuantite());
		test[2]=etal.getProduit();
		
		String[] etat = controlAfficherMarche.donnerEtatMarche();
		assertEquals(etat[0],test[0]);
		assertEquals(etat[1],test[1]);
		assertEquals(etat[2],test[2]);
	}

}
