/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spolupracejavisti;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author wooow
 */
public class OsobaTest {
    
    private Osoba osoba;
    
    @Before
    public void setUp() {
        
        Adresa adresa = new Adresa (1, "CZ", "Praha", 70000, "Rohlikova", 1);
        
        osoba = new Osoba (
        "111",
        "Honza Rohlik",
        "777777777",
        "rohlik@vse.cz",
        adresa
        
        );
    }
    
    
    /**
     * Test of getRodneCislo method, of class Osoba.
     */
    @Test
    public void testGetRodneCislo() {
        
        
        Osoba osoba2 = new Osoba (
        "111",
        "Honza Rohlik",
        "777777777",
        "rohlik@vse.cz",
        new Adresa(1, "CZ", "Praha", 70000, "Rohlikova", 1)
        
        );
        
        Osoba osoba3 = new Osoba (
        "333",
        "Honza Chleb",
        "777555555",
        "chleb@vse.cz",
        new Adresa(2, "CZ", "Praha", 70000, "Chlebova", 1)
        
        );
        
        assertNotSame(osoba.rodneCislo, osoba3.rodneCislo);
        
        assertEquals(osoba.rodneCislo, osoba2.rodneCislo);
    }

    /**
     * Test of setRodneCislo method, of class Osoba.
     */
    @Test
    public void testSetRodneCislo() {
        System.out.println("setRodneCislo");
        String rodneCislo = "";
        Osoba instance = osoba;
        instance.setRodneCislo(rodneCislo);
       
    }

    /**
     * Test of getJmeno method, of class Osoba.
     */
    @Test
    public void testGetJmeno() {
        Osoba osoba2 = new Osoba (
        "111",
        "Honza Rohlik",
        "777777777",
        "rohlik@vse.cz",
        new Adresa(1, "CZ", "Praha", 70000, "Rohlikova", 1)
        
        );
        
        Osoba osoba3 = new Osoba (
        "333",
        "Honza Chleb",
        "777555555",
        "chleb@vse.cz",
        new Adresa(2, "CZ", "Praha", 70000, "Chlebova", 1)
        
        );
        
        assertNotSame(osoba.jmeno, osoba3.jmeno);
        
        assertEquals(osoba.jmeno, osoba2.jmeno);
    }

    /**
     * Test of setJmeno method, of class Osoba.
     */
    @Test
    public void testSetJmeno() {
        System.out.println("setJmeno");
        String jmeno = "";
        Osoba instance = osoba;
        instance.setJmeno(jmeno);
        
    }

    /**
     * Test of getTelefon method, of class Osoba.
     */
    @Test
    public void testGetTelefon() {
        Osoba osoba2 = new Osoba (
        "111",
        "Honza Rohlik",
        "777777777",
        "rohlik@vse.cz",
        new Adresa(1, "CZ", "Praha", 70000, "Rohlikova", 1)
        
        );
        
        Osoba osoba3 = new Osoba (
        "333",
        "Honza Chleb",
        "777555555",
        "chleb@vse.cz",
        new Adresa(2, "CZ", "Praha", 70000, "Chlebova", 1)
        
        );
        
        assertNotSame(osoba.telefon, osoba3.telefon);
        
        assertEquals(osoba.telefon, osoba2.telefon);
    }

    /**
     * Test of setTelefon method, of class Osoba.
     */
    @Test
    public void testSetTelefon() {
        System.out.println("setTelefon");
        String telefon = "";
        Osoba instance = osoba;
        instance.setTelefon(telefon);
        
    }

    /**
     * Test of getEmail method, of class Osoba.
     */
    @Test
    public void testGetEmail() {
       Osoba osoba2 = new Osoba (
        "111",
        "Honza Rohlik",
        "777777777",
        "rohlik@vse.cz",
        new Adresa(1, "CZ", "Praha", 70000, "Rohlikova", 1)
        
        );
        
        Osoba osoba3 = new Osoba (
        "333",
        "Honza Chleb",
        "777555555",
        "chleb@vse.cz",
        new Adresa(2, "CZ", "Praha", 70000, "Chlebova", 1)
        
        );
        
        assertNotSame(osoba.email, osoba3.email);
        
        assertEquals(osoba.email, osoba2.email);
    }

    /**
     * Test of setEmail method, of class Osoba.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String email = "";
        Osoba instance = osoba;
        instance.setEmail(email);
        
    }

    /**
     * Test of getAdresa method, of class Osoba.
     */
    @Test
    public void testGetAdresa() {
        
        Adresa adresa1 = new Adresa (1, "CZ", "Praha", 70000, "Rohlikova", 1);
        Adresa adresa2 = new Adresa (2, "CZZ", "Prahaa", 70010, "Chlebova", 3);
        
       Osoba osoba2 = new Osoba (
        "111",
        "Honza Rohlik",
        "777777777",
        "rohlik@vse.cz",
        adresa1
        
        );
        
        Osoba osoba3 = new Osoba (
        "333",
        "Honza Chleb",
        "777555555",
        "chleb@vse.cz",
        adresa2
        
        );
        
        assertNotSame(osoba2.adresa, osoba3.adresa);
        
        
    }

    /**
     * Test of setAdresa method, of class Osoba.
     */
    @Test
    public void testSetAdresa() {
        System.out.println("setAdresa");
        Adresa adresa = null;
        Osoba instance = osoba;
        instance.setAdresa(adresa);
        
    }
    
}
