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
public class Firma {
    private String nazev;
    private int ICO;
    private Adresa adresa;
    private Osoba osoba;

    public Firma(int ICO, String nazev, Adresa adresa, Osoba osoba ){
        this.nazev = nazev;
        this.ICO = ICO;
        this.adresa = adresa;
        this.osoba = osoba;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public int getICO() {
        return ICO;
    }

    public void setICO(int ICO) {
        this.ICO = ICO;
    }

    public Adresa getAdresa() {
        return adresa;
    }

    public void setAdresa(Adresa adresa) {
        this.adresa = adresa;
    }

    public Osoba getOsoba() {
        return osoba;
    }

    public void setOsoba(Osoba osoba) {
        this.osoba = osoba;
    }

  
}
