package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import villagegaulois.Village;

class ControlPrendreEtalTest {
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
	void testControlPrendreEtal() {
		ControlVerifierIdentite controlVerifierIdentite = new ControlVerifierIdentite(village);
		ControlPrendreEtal controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite,village);
		assertNotNull(controlPrendreEtal,"Constructeur ne renvoie pas nul");
	}

	@Test
	void testResteEtals() {
		ControlVerifierIdentite controlVerifierIdentite = new ControlVerifierIdentite(village);
		ControlPrendreEtal controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite,village);
		assertTrue(controlPrendreEtal.resteEtals());
		village = new Village("le village sans etal",0,0);
		controlVerifierIdentite = new ControlVerifierIdentite(village);
		controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite,village);
		assertFalse(controlPrendreEtal.resteEtals());
	}

	@Test
	void testPrendreEtal() {
		ControlVerifierIdentite controlVerifierIdentite = new ControlVerifierIdentite(village);
		ControlPrendreEtal controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite,village);
		ControlEmmenager controlEmmenager = new ControlEmmenager(village);
		controlEmmenager.ajouterGaulois("Bonemine", 10);
		assertEquals(controlPrendreEtal.prendreEtal("Bonemine", "fleurs", 10),0);
	}

	@Test
	void testVerifierIdentite() {
		ControlVerifierIdentite controlVerifierIdentite = new ControlVerifierIdentite(village);
		ControlPrendreEtal controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite,village);
		ControlEmmenager controlEmmenager = new ControlEmmenager(village);
		controlEmmenager.ajouterGaulois("Bonemine", 10);
		assertTrue(controlPrendreEtal.verifierIdentite("Bonemine"));
		assertFalse(controlPrendreEtal.verifierIdentite("Inexsistant"));
	}

}
