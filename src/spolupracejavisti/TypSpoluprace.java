/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spolupracejavisti;

/**
 *
 * @author wooow
 */
public class TypSpoluprace {
    
    public int typSpolupraceId;
     public String nazev;
     
     public TypSpoluprace(int typSpolupraceId, String nazev){
         
         this.typSpolupraceId=typSpolupraceId;
         this.nazev = nazev;
     
     }
     
     public int getId() {
        return typSpolupraceId;
    }

    public void setId(int typSpolupraceId) {
        this.typSpolupraceId = typSpolupraceId;
    }
    
     public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }
    
    
}
