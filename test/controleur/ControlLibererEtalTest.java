package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import villagegaulois.Etal;
import villagegaulois.Village;

class ControlLibererEtalTest {
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
	void testControlLibererEtal() {
		ControlTrouverEtalVendeur controlTrouverEtalVendeur = new ControlTrouverEtalVendeur(village);
		ControlLibererEtal controlLibererEtal = new ControlLibererEtal(controlTrouverEtalVendeur);
		assertNotNull(controlLibererEtal,"Constructeur ne renvoie pas nul");
	}

	@Test
	void testIsVendeur() {
		ControlVerifierIdentite controlVerifierIdentite = new ControlVerifierIdentite(village);
		ControlPrendreEtal controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite,village);
		ControlTrouverEtalVendeur controlTrouverEtalVendeur = new ControlTrouverEtalVendeur(village);
		ControlLibererEtal controlLibererEtal = new ControlLibererEtal(controlTrouverEtalVendeur);
		ControlEmmenager controlEmmenager = new ControlEmmenager(village);
		controlEmmenager.ajouterGaulois("Bonemine", 10);
		controlPrendreEtal.prendreEtal("Bonemine", "fleurs", 10);
		assertTrue(controlLibererEtal.isVendeur("Bonemine"));
		assertFalse(controlLibererEtal.isVendeur("Personne"));
		
	}

	@Test
	void testLibererEtal() {
		ControlVerifierIdentite controlVerifierIdentite = new ControlVerifierIdentite(village);
		ControlPrendreEtal controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite,village);
		ControlTrouverEtalVendeur controlTrouverEtalVendeur = new ControlTrouverEtalVendeur(village);
		ControlLibererEtal controlLibererEtal = new ControlLibererEtal(controlTrouverEtalVendeur);
		ControlEmmenager controlEmmenager = new ControlEmmenager(village);
		controlEmmenager.ajouterGaulois("Bonemine", 10);
		controlPrendreEtal.prendreEtal("Bonemine", "fleurs", 10);
		
		Etal etalLibere = controlTrouverEtalVendeur.trouverEtalVendeur("Bonemine");
		controlLibererEtal.libererEtal("Bonemine");
		
		assertFalse(etalLibere.isEtalOccupe());
		
	}

}
