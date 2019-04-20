package com.adventium.facture;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.stream.Collectors;

import com.adventium.produit.Produit;

public class FactureDetaillee {
	
	/**
	 * Cette méthode affiche tous les prix HT et TTC et le montant de TVA
	 * @param products
	 * @return la facture détaillée
	 */
	public String produireFactureDetaillee(Collection<Produit> products) {

        // Cette expression lambda permet de fabriquer un String avec la liste des produits 
		//en y appliquant la methode toString redefinie dans Produit et les concatenant dans une chaine unique
		String listeDesProduits = products.stream()
            .map(Produit::toString)
            .collect(Collectors.joining(""));

        //Cette expression lambda calcule tous les taux de TVA des produits de la liste 
		//et les additionne
		BigDecimal montantTotalDesTaxes = products.stream()
            .map(Produit::getMontantDesTaxes)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

		//Cette expression lambda calcule tous les prix produits de la liste TTC
		//et les additionne
		BigDecimal montantTotal = products.stream()
            .map(Produit::getProduitsAvecTVA)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        return String.format(
            "%s%nMontant des taxes : %s€%nTotal: %s€ TTC%n",
            listeDesProduits,
            montantTotalDesTaxes,
            montantTotal
        );
}
}
