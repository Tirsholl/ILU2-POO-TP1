package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum, int nbEtals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		this.marche = new Marche(nbEtals);
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les lÃ©gendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
		StringBuilder chercheEtal = new StringBuilder();
		chercheEtal.append(vendeur);
		chercheEtal.append(" cherche un endroit pour vendre ");
		chercheEtal.append(nbProduit);
		chercheEtal.append(" ");
		chercheEtal.append(produit);
		chercheEtal.append("\n");
	
		int i = marche.trouverEtalLibre();
		if(i == -1) {
			chercheEtal.append("Tous les étals sont occupés le vendeur reviendra demain \n");
		}
		else {
			marche.utiliserEtal(i, vendeur, produit, nbProduit);
			Etal etals = marche.trouverVendeur(vendeur);
			chercheEtal.append(etals.afficherEtal());
			chercheEtal.append("\n");
		}
		return chercheEtal.toString();
	}
	
	public String rechercherVendeursProduit(String produit) {
		StringBuilder vendeurProduit = new StringBuilder(); 
		Etal[] etals = marche.trouverEtals(produit);
		if(etals.length == 0) {
			vendeurProduit.append("Il n'a pas de vendeur qui propose ");
			vendeurProduit.append(produit);
			vendeurProduit.append("\n");
		}
		if(etals.length == 1) {
			vendeurProduit.append("Seul le vendeur ");
			vendeurProduit.append(etals[0].getVendeur());
			vendeurProduit.append(" propose ");
			vendeurProduit.append(produit);
			vendeurProduit.append("\n");
		}
		else {
			vendeurProduit.append("les vendeurs qui porposent des fleurs sont ");
			for(int i = 0; i < etals.length; i++) {
				vendeurProduit.append(b)
			}
		}
		
	}
	
	public static class Marche{
		private Etal[] etals;
	
	private Marche(int quantitéEtals) {
		this.etals = new Etal[quantitéEtals];
		}
	
	private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
		etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
	}
	
	private int trouverEtalLibre() {
		int indice = 0;
		for(int i = 0; i < etals.length && etals[indice].isEtalOccupe(); i++) {
			return indice;
			}
		return -1;
		}
	
	private Etal[] trouverEtals(String produit) {
		int nbEtals = 0;
		for(int i = 0; i < etals.length; i++) {
			if (etals[i].contientProduit(produit)){
				nbEtals+=1;
			}
		}
			Etal[] listeEtals = new Etal[nbEtals];
		for(int i = 0; i < etals.length; i++) {
			if (etals[i].contientProduit(produit)) {
				listeEtals[i] = etals[i];
				}
			}
		return listeEtals;
		}
	
	private Etal trouverVendeur(Gaulois gaulois) {
		for(int i = 0; i < etals.length; i++) {
			if (etals[i].getVendeur() == gaulois) {
				return etals[i];
				}
			}
		return null;
		}
	
	private String afficherMarche() {
		StringBuilder EtalOccupe = new StringBuilder();
		int nbEtalVide = 0;
		for(int i =0; i < etals.length; i++) {
			if (etals[i].isEtalOccupe()) {
				EtalOccupe.append(etals[i].afficherEtal());
			}
			else {
				nbEtalVide+=1;
			}
		}
		if(nbEtalVide > 0) {
			EtalOccupe.append("Il reste ");
			EtalOccupe.append(nbEtalVide);
			EtalOccupe.append(" étals non utilisés dans le marché. \n");
		}
		return EtalOccupe.toString();
	}
	
	
	}
}
