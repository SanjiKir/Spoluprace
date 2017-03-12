/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spolupracejavisti;

/**
 * Třída spravující instance osob.
 *
 * @author wooow
 */
public class Osoba {
    public String rodneCislo;
    public String jmeno;
    public String telefon;
    public String email;
    public Adresa adresa;
    
    public Osoba(String rodneCislo, String jmeno, String telefon, String email, Adresa adresa)
    {
        setRodneCislo(rodneCislo);
        setJmeno(jmeno);
        setTelefon(telefon);
        setEmail(email);
        setAdresa(adresa);
    }
    
    public String getRodneCislo()
    {
        return rodneCislo;
    }
    
    public void setRodneCislo(String rodneCislo)
    {
        this.rodneCislo = rodneCislo;
    }
    
    public String getJmeno()
    {
        return jmeno;
    }
    
    public void setJmeno(String jmeno)
    {
        this.jmeno = jmeno;
    }
    
    public String getTelefon()
    {
        return telefon;
    }
    
    public void setTelefon(String telefon)
    {
        this.telefon = telefon;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public Adresa getAdresa()
    {
        return adresa;
    }
    
    public void setAdresa(Adresa adresa)
    {
        this.adresa = adresa;
    }
}
