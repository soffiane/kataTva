package com.adventium.produit.categorie;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CatalogueProduit {

	protected Map<CategorieProduit, Set<String>> catalogue;
	
	public CatalogueProduit() {
		 catalogue = new HashMap<>();
		 fillCategoryMapper();
	}

	public CategorieProduit getCategorieProduitByName(String nomProduit) {
		for (Map.Entry<CategorieProduit, Set<String>> entry : catalogue.entrySet()) {
			CategorieProduit categories = entry.getKey();
			Set<String> noms = entry.getValue();

			if (noms.contains(nomProduit)) {
				return categories;
			}
		}

		return CategorieProduit.AUTRES;
	}

	protected final void fillCategoryMapper() {
		Set<String> produitsLivre = new HashSet<>();
		produitsLivre.add("livre");
		produitsLivre.add("livres");
		catalogue.put(CategorieProduit.LIVRE, produitsLivre);

		Set<String> produitsNourriture = new HashSet<>();
		produitsNourriture.add("barre de chocolat");
		produitsNourriture.add("boîte de chocolat");
		produitsNourriture.add("barres de chocolat");
		produitsNourriture.add("boîtes de chocolat");
		catalogue.put(CategorieProduit.NOURRITURE, produitsNourriture);

		Set<String> produitsMedicaux = new HashSet<>();
		produitsMedicaux.add("boîte de pilules contre la migraine");
		produitsMedicaux.add("boîtes de pilules contre la migraine");
		catalogue.put(CategorieProduit.MEDICAL, produitsMedicaux);

		Set<String> produitsAutres = new HashSet<>();
		produitsAutres.add("cd musical");
		produitsAutres.add("flacon de parfum");
		produitsAutres.add("cd musicaux");
		produitsAutres.add("flacons de parfum");
		catalogue.put(CategorieProduit.AUTRES, produitsAutres);
	}
}
