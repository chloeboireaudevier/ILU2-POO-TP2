package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;

class ControlAcheterProduitTest {
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
	void testControlAcheterProduit() {
		ControlVerifierIdentite controlVerifierIdentite = new ControlVerifierIdentite(village);
		ControlTrouverEtalVendeur controlTrouverEtalVendeur = new ControlTrouverEtalVendeur(village);
		ControlAcheterProduit controlAcheterProduit = new ControlAcheterProduit(controlVerifierIdentite,controlTrouverEtalVendeur,village);
		assertNotNull(controlAcheterProduit,"Constructeur ne renvoie pas nul");
	}

	@Test
	void testVerifierIdentite() {
		ControlVerifierIdentite controlVerifierIdentite = new ControlVerifierIdentite(village);
		ControlTrouverEtalVendeur controlTrouverEtalVendeur = new ControlTrouverEtalVendeur(village);
		ControlAcheterProduit controlAcheterProduit = new ControlAcheterProduit(controlVerifierIdentite,controlTrouverEtalVendeur,village);
		ControlEmmenager controlEmmenager = new ControlEmmenager(village);
		controlEmmenager.ajouterGaulois("Bonemine", 10);
		assertTrue(controlAcheterProduit.verifierIdentite("Bonemine"));
		assertFalse(controlAcheterProduit.verifierIdentite("Inexsistant"));
	}

	@Test
	void testRechercherProduit() {
		ControlVerifierIdentite controlVerifierIdentite = new ControlVerifierIdentite(village);
		ControlTrouverEtalVendeur controlTrouverEtalVendeur = new ControlTrouverEtalVendeur(village);
		ControlAcheterProduit controlAcheterProduit = new ControlAcheterProduit(controlVerifierIdentite,controlTrouverEtalVendeur,village);
		ControlPrendreEtal controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite,village);
		ControlEmmenager controlEmmenager = new ControlEmmenager(village);
		controlEmmenager.ajouterGaulois("Bonemine", 10);
		controlPrendreEtal.prendreEtal("Bonemine", "fleurs", 10);
		
		assertEquals(controlAcheterProduit.rechercherProduit("fleurs")[0].getNom(),"Bonemine");
	}

	@Test
	void testGetEtal() {
		ControlVerifierIdentite controlVerifierIdentite = new ControlVerifierIdentite(village);
		ControlTrouverEtalVendeur controlTrouverEtalVendeur = new ControlTrouverEtalVendeur(village);
		ControlAcheterProduit controlAcheterProduit = new ControlAcheterProduit(controlVerifierIdentite,controlTrouverEtalVendeur,village);
		ControlPrendreEtal controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite,village);
		ControlEmmenager controlEmmenager = new ControlEmmenager(village);
		controlEmmenager.ajouterGaulois("Bonemine", 10);
		Gaulois bonemine = village.trouverHabitant("Bonemine");
		controlPrendreEtal.prendreEtal("Bonemine", "fleurs", 10);
		
		assertEquals(controlAcheterProduit.getEtal(bonemine),village.rechercherEtal(bonemine));
	}

	@Test
	void testAcheterProduit() {
		ControlVerifierIdentite controlVerifierIdentite = new ControlVerifierIdentite(village);
		ControlTrouverEtalVendeur controlTrouverEtalVendeur = new ControlTrouverEtalVendeur(village);
		ControlAcheterProduit controlAcheterProduit = new ControlAcheterProduit(controlVerifierIdentite,controlTrouverEtalVendeur,village);
		ControlPrendreEtal controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite,village);
		ControlEmmenager controlEmmenager = new ControlEmmenager(village);
		controlEmmenager.ajouterGaulois("Bonemine", 10);
		Gaulois bonemine = village.trouverHabitant("Bonemine");
		controlPrendreEtal.prendreEtal("Bonemine", "fleurs", 10);
		Etal etal = village.rechercherEtal(bonemine);
		
		controlAcheterProduit.acheterProduit(etal, 5);
		
		assertEquals(etal.getQuantite(),5);
	}

}
