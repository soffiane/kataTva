package com.adventium.caisse;

import java.util.ArrayList;
import java.util.List;

import com.adventium.facture.FactureDetaillee;
import com.adventium.produit.Produit;
import com.adventium.produit.ProduitParser;
import com.adventium.produit.categorie.CatalogueProduit;
import com.adventium.tva.CalculTVA;

/**
 * Cet objet va centraliser la liste de produits et effectuer tous les traitements necessaires 
 * pour le calcul de prix et de TVA
 * @author soffiane
 *
 */
public class Caisse {
	private FactureDetaillee facture;
    private ProduitParser parser;
    private CalculTVA tva;
    private CatalogueProduit catalogueProduits;

    private List<Produit> produitsScannes;

    public Caisse(FactureDetaillee facture, ProduitParser parser, CalculTVA tva, CatalogueProduit catalogueProduits) {
        this.facture = facture;
        this.parser = parser;
        this.tva = tva;
        this.catalogueProduits = catalogueProduits;

        this.produitsScannes = new ArrayList<>();
    }

    /**
     * Cette methode produit la facture detaillée à partir de la liste de produits de la commande
     * @return la facture détaillée
     */
    public String editerFacture() {
        return facture.produireFactureDetaillee(produitsScannes);
    }

    /**
     * Cette methode permet de verifier et de formatter la commande afin d'en extraire les principales valeurs
     * (prix, tva, categorie, importé ou non)
     * @param nomProduit : le produit ajouté à la commande
     */
    public void ajouterEtVerifierNomProduit(String nomProduit) {
        for(String produit : parser.parserNomProduit(nomProduit)) {
            ajouterProduitAuPanier(produit);
        }
    }

    /**
     * Cette methode permet d'ajouter le produit à la liste de produit de la commande
     * puis d'en determiner la categorie (medical, nourriture, autres...) et le taux de TVA correspondant
     * @param produit : le produit que l'on a crée dans la methode ajouterEtVerifierNomProduit
     */
    public void ajouterProduitAuPanier(String produit) {
        Produit p = parser
            .creerProduitParNom(produit)
            .determinerCategorieProduit(catalogueProduits)
            .appliquerTauxTVA(tva);

        this.produitsScannes.add(p);
    }
    
    /**
     * On vide la liste de produits
     */
    public void viderPanier() {
    	this.produitsScannes.clear();
    }
}
