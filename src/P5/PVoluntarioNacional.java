/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package P5;

/**
 *
 * @author Josep
 */
public class PVoluntarioNacional extends PVoluntario {
    public PVoluntarioNacional(String pNombre, String pApellidos, String pDNI){
        this.setApellidos(pApellidos);
        this.setNombre(pNombre);
        this.setDNI(pDNI);
    }
}
