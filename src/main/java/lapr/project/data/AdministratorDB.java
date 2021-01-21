/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import lapr.project.model.Administrator;

/**
 *
 * @author Diogo
 */
public class AdministratorDB {
    
    public Administrator newAdministrator(String name, String email, String pwd){
        return new Administrator(name, email, pwd);
    }
}
