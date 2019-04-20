package com.adventium.produit;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.adventium.produit.categorie.CatalogueProduit;
import com.adventium.produit.categorie.CategorieProduit;
import com.adventium.tva.CalculTVA;

/**
 * Cette classe represente le produit et ses attributs
 * @author soffiane
 *
 */
public class Produit {

	private String nom;
	private BigDecimal prixHT;
	private BigDecimal tauxTVA;
	private int quantite;
	private boolean estImporte;
	private CategorieProduit categorie;
	
	public Produit(String nom, BigDecimal prixHT, int quantite, boolean estImporte) {
		this.nom = nom;
		this.prixHT = prixHT;
		this.quantite = quantite;
		this.estImporte = estImporte;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
    public String toString() {
        return 
            quantite + " " +
            nom +
            estImporte() +
            " à "+
            this.prixHT+"€ : "+
            getProduitsAvecTVA()+
            "€ TTC\n";
    }

    /**
     * Cette méthode permet de determiner à quelle categorie appartient un produit
     * @param catalogueProduits
     * @return on retourne le produit apres avoir renseigné sa categorie
     */
    public Produit determinerCategorieProduit(CatalogueProduit catalogueProduits) {
    	categorie = catalogueProduits.getCategorieProduitByName(nom);
    	return this;
    }

    /**
     * Calcul du taux de TVA d'un produit
     * @param tauxTva
     * @return on retourne le produit apres avoir renseigné son taux de TVA
     */
    public Produit appliquerTauxTVA(CalculTVA tauxTva) {
    	tauxTVA = tauxTva.calculTva(categorie, estImporte);
    	return this;
    }

    /**
     * Calcule le prix TTC d'un produit et fait l'arrondi au 0.05 supérieur
     * @return le prix TTC du produit
     */
    public BigDecimal getProduitsAvecTVA() {
        return getPrixAvecTva()
            .multiply(BigDecimal.valueOf(quantite))
            .divide(BigDecimal.valueOf(0.05), 0, RoundingMode.UP)
            .multiply(BigDecimal.valueOf(0.05))
            .setScale(2);
    }

    /**
     * Calcule la somme des montants de TVA pour le produit
     * @return la TVA ajoutée au prix des produits
     */
    public BigDecimal getMontantDesTaxes() {
        return getMontantTVA()
            .multiply(BigDecimal.valueOf(quantite));
    }

    /**
     * @return
     */
    private BigDecimal getPrixAvecTva() {
        return prixHT
            .add(getMontantTVA())
            .setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

    /**
     * @return
     */
    private BigDecimal getMontantTVA() {
        BigDecimal montantTva = prixHT.multiply(tauxTVA);
        return arrondirAuCinqCentimesPres(montantTva);
    }

	/**
	 * Calcul de l'arrondi du prix TTC
	 * @param montantTva
	 * @return le prix TTC arrondi au 0.05 superieur
	 */
	private BigDecimal arrondirAuCinqCentimesPres(BigDecimal montantTva) {
        return montantTva
            .divide(BigDecimal.valueOf(0.05), 0, RoundingMode.UP)
            .multiply(BigDecimal.valueOf(0.05))
            .setScale(2);
    }

    private String estImporte() {
        return estImporte ? " importé" : "";
    }
}
