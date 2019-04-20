package com.adventium.produit;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Cette classe permet de verifier que les commandes passées le sont dans le format attendu
 * exemple : X nomProduit "importé" à 0.00€
 * @author soffiane
 *
 */
public class ProduitParser {
    
	public static final Pattern labelImportePattern = Pattern.compile("importé.?");
    private static final Pattern produitPattern = Pattern.compile("([0-9]+) (.*?) à ([0-9]+\\.[0-9]{2})");


    /**
     * Cette methode decompose la commande pour en extraire les informations essentielles
     * @param product la chaine de caractere saisie dans le cadre de la commande
     * @return la liste des mots de la commande : quantité, nom, prix
     */
    public static List<String> parserNomProduit(String product) {
        List<String> result = new ArrayList<>();

        Matcher matcher = produitPattern
            .matcher(product);

        while(matcher.find()) {
            result.add(matcher.group());
        }

        return result;
    }

    /**
     * Cette methode recupere les parametres de la commande pour créer un objet Produit
     * @param product le nom de produit
     * @return le produit nouvellement crée
     */
    public static Produit creerProduitParNom(String product) {
        Matcher matcher = produitPattern
            .matcher(product);

        matcher.matches();

        int quantite        = Integer.parseInt(matcher.group(1));
        String nom         = recupererNomSansImporte(matcher.group(2));
        BigDecimal prixHT    = new BigDecimal(matcher.group(3));
        boolean estImporte  = labelImportePattern.matcher(matcher.group(2)).find();

        return new Produit(nom, prixHT, quantite ,estImporte);
    }

    
    /**
     * On enleve la mention "importé" du nom du produit pour la suite du process
     * @param name nom complet du produit
     * @return le nom du produit sans la mention importé
     */
    private static String recupererNomSansImporte(String nom) {
        return nom.replaceAll(labelImportePattern.pattern(), "").trim();
    }
}
