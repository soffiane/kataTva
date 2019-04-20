package com.adventium.simulation;

import com.adventium.caisse.Caisse;
import com.adventium.facture.FactureDetaillee;
import com.adventium.produit.ProduitParser;
import com.adventium.produit.categorie.CatalogueProduit;
import com.adventium.tva.CalculTVA;

/**
 * Classe pour le test
 * 
 * @author soffiane
 *
 */
public class Simulation {

	public static void main(String[] args) {
		// l'objet caisse est l'objet qui va nous permettre d'ajouter les produits au
		// panier tout en verifiant la syntaxe
		// et va calculer le prix TTC
		Caisse caisse = new Caisse(new FactureDetaillee(), new ProduitParser(), new CalculTVA(),
				new CatalogueProduit());
		// on ajoute au panier un produit dont on va analyser si la syntaxe est correct
		// avant l'ajout
		// et determiner de quel type de produit il s'agit et appliquer le taux de TVA
		// correspondant
		caisse.ajouterEtVerifierNomProduit("2 livres à 12.49€");
		caisse.ajouterEtVerifierNomProduit("1 cd musical à 14.99€");
		caisse.ajouterEtVerifierNomProduit("3 barres de chocolat à 0.85€");
		System.out.println(caisse.editerFacture());
		caisse.viderPanier();

		caisse.ajouterEtVerifierNomProduit("2 boîtes de chocolat importé à 10.00€");
		caisse.ajouterEtVerifierNomProduit("3 flacons de parfum importé à 47.50€");
		System.out.println(caisse.editerFacture());
		caisse.viderPanier();

		caisse.ajouterEtVerifierNomProduit("2 flacons de parfum importé à 27.99€");
		caisse.ajouterEtVerifierNomProduit("1 flacon de parfum à 18.99€");
		caisse.ajouterEtVerifierNomProduit("3 boîtes de pilules contre la migraine à 9.75€");
		caisse.ajouterEtVerifierNomProduit("2 boîtes de chocolat importés à 11.25€");
		System.out.println(caisse.editerFacture());
		caisse.viderPanier();
	}
}
