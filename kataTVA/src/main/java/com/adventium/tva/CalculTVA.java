package com.adventium.tva;

import java.math.BigDecimal;

import com.adventium.produit.categorie.CategorieProduit;

public class CalculTVA {
	
	private static final BigDecimal TVA_LIVRE = BigDecimal.valueOf(0.10);
	private static final BigDecimal TVA_AUTRES = BigDecimal.valueOf(0.20);
    private static final BigDecimal TVA_IMPORT = BigDecimal.valueOf(0.05);


    public BigDecimal calculTva(CategorieProduit categorie, boolean estImporte) {
        return BigDecimal.ZERO
            .add(categorie == CategorieProduit.LIVRE ? TVA_LIVRE : BigDecimal.ZERO)
            .add(categorie == CategorieProduit.AUTRES ? TVA_AUTRES : BigDecimal.ZERO)
            .add(estImporte ? TVA_IMPORT : BigDecimal.ZERO);
    }

}
