/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spolupracejavisti;

import java.io.Serializable;

/**
 *
 * @author wooow
 */
public class Adresa implements Serializable { //osoba a firma, na kterou odkazuje adresa
    
    Osoba osoba; 
    Firma firma;
    
    private int id;
    private String stat;
    private String mesto;
    private int zip;
    private String ulice;
    private int cp;
    
    public Adresa(int id, String stat,String mesto, int zip, String ulice, int cp){
        this.id = id;
        this.stat = stat;
        this.mesto = mesto;
        this.zip = zip;
        this.ulice = ulice;
        this.cp = cp;   
}
     public Osoba getOsoba() {
        return osoba;
    }
 
    public void setOsoba(Osoba osoba) {
        this.osoba = osoba;
    }
     public Firma getFirma() {
        return firma;
    }
 
    public void setFirma(Firma firma) {
        this.firma = firma;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }
    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }
    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }
    public String getUlice() {
        return ulice;
    }

    public void setUlice(String ulice) {
        this.ulice = ulice;
    }
    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }   
}

