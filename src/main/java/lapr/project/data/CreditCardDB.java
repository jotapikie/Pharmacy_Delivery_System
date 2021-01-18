/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import lapr.project.model.CreditCard;

/**
 *
 * @author Diogo
 */
public class CreditCardDB {

    public CreditCard newCreditCard(long number, String validityDate, int ccv) {
        return new CreditCard(number, validityDate, ccv);
    }
    
}
