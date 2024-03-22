package frontiere;

import java.util.Scanner;

import controleur.ControlAcheterProduit;
import personnages.Gaulois;
import villagegaulois.Etal;

public class BoundaryAcheterProduit {
	private Scanner scan = new Scanner(System.in);
	private ControlAcheterProduit controlAcheterProduit;

	public BoundaryAcheterProduit(ControlAcheterProduit controlAcheterProduit) {
		this.controlAcheterProduit = controlAcheterProduit;
	}

	public void acheterProduit(String nomAcheteur) {
		if(!controlAcheterProduit.verifierIdentite(nomAcheteur)) {
			System.out.println("Je suis désolée "+nomAcheteur+" mais il faut être un habitant de notre village pour commercer ici");
		} else {
			String nomProduit = Clavier.entrerChaine("Quel produit voulez-vous acheter ?");
			Gaulois[] listeVendeurs = controlAcheterProduit.rechercherProduit(nomProduit);
			if( listeVendeurs.length == 0) {
				System.out.println("Désolé, personne ne vend ce produit au marché.");
			} else {
				for (int i = 0; i<listeVendeurs.length;i++) {
					System.out.println("- "+(i+1)+" "+listeVendeurs[i].getNom());
				}
				int choixUtilisateur = Clavier.entrerEntier("Chez quel commerçant voulez-vous acheter des "+nomProduit+"?");
				while (choixUtilisateur < 1 && choixUtilisateur > listeVendeurs.length) {
					choixUtilisateur = Clavier.entrerEntier("Chez quel commerçant voulez-vous acheter des "+nomProduit+"?");
					
				}
				
				int choixQuantite = Clavier.entrerEntier("Combien de "+nomProduit+" voulez-vous acheter ?");
				Etal etalAchat = controlAcheterProduit.getEtal(listeVendeurs[choixUtilisateur-1]);
				int quantiteRestante = etalAchat.getQuantite();
				if(quantiteRestante == 0) {
					System.out.println(nomAcheteur+" veut acheter des "+nomProduit+", malheureusement il n'y en a plus !");
				} else if (quantiteRestante < choixQuantite) {
					System.out.println(nomAcheteur+" veut acheter "+choixQuantite+" "+nomProduit+", malheureusement "+etalAchat.getVendeur().getNom()+
							"n'en a plus que "+quantiteRestante+". "+nomAcheteur+" achète tout le stock à "+etalAchat.getVendeur().getNom()+".");
					controlAcheterProduit.acheterProduit(etalAchat, choixQuantite);
				} else {
					System.out.println(nomAcheteur+" achète "+choixQuantite+" "+nomProduit+" à "+etalAchat.getVendeur().getNom());
					controlAcheterProduit.acheterProduit(etalAchat, choixQuantite);
				}
			}
			
		}
	}
}
