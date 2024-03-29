package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Village;

class ControlAfficherVillageTest {
	private Village village;
	private Chef abraracourcix;
	
	@BeforeEach
	public void initialiserSituation() {
		System.out.println("Initialisation...");
		village = new Village("le village des irréductibles",10,5);
		abraracourcix = new Chef("Arabracourix",10,village);
		village.setChef(abraracourcix);
		Gaulois bonemine = new Gaulois("Bonemine",5);
		village.ajouterHabitant(bonemine);
		Druide panoramix = new Druide("Panoramix",5,3,5);
		village.ajouterHabitant(panoramix);
	}
	
	
	@Test
	void testControlAfficherVillage() {
		ControlAfficherVillage controlAfficherVillage = new ControlAfficherVillage(village);
		assertNotNull(controlAfficherVillage,"Constructeur ne renvoie pas nul");
	}

	@Test
	void testDonnerNomsVillageois() {
		ControlAfficherVillage controlAfficherVillage = new ControlAfficherVillage(village);
		String[] listeHabitants = controlAfficherVillage.donnerNomsVillageois();
		assertTrue(listeHabitants[0].equals(abraracourcix.getNom()));
		assertTrue(listeHabitants[1].equals("Bonemine"));
		assertTrue(listeHabitants[2].equals("le druide Panoramix"));
	}

	@Test
	void testDonnerNomVillage() {
		ControlAfficherVillage controlAfficherVillage = new ControlAfficherVillage(village);
		String nomVillage = controlAfficherVillage.donnerNomVillage();
		assertTrue(nomVillage.equals("le village des irréductibles"));
	}

	@Test
	void testDonnerNbEtals() {
		ControlAfficherVillage controlAfficherVillage = new ControlAfficherVillage(village);
		int nbEtals = controlAfficherVillage.donnerNbEtals();
		assertEquals(nbEtals,village.donnerNbEtal());
	}

}
