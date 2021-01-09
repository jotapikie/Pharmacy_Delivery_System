/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

/**
 *
 * @author ivoal
 */
public class Invoice {
    
    private int id;
    private int nif;
    
    public Invoice(int id, int nif){
        this.id=id;
        this.nif=nif;
    }

    

    public int getId() {
        return id;
    }

    public int getNif() {
        return nif;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    @Override
    public String toString() {
        return "Invoice{" + "id=" + id + ", nif=" + nif + "}";
    }
    
    
}
