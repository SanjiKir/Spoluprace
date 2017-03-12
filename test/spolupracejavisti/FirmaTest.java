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
public class FirmaTest {

    private Firma firma;
    private Adresa adresa;
    private Osoba osoba;

    public FirmaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        adresa = new Adresa(0, "stat", "mesto", 0, "ulice", 0);
        osoba = new Osoba(
                "111",
                "Honza Rohlik",
                "777777777",
                "rohlik@vse.cz",
                adresa
        );
        firma = new Firma(0, "nazev", adresa, osoba);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getNazev method, of class Firma.
     */
    @Test
    public void testGetNazev() {
        System.out.println("getNazev");
        Firma instance = firma;
        String expResult = "nazev";
        String result = instance.getNazev();
        assertEquals(expResult, result);

    }

    /**
     * Test of setNazev method, of class Firma.
     */
    @Test
    public void testSetNazev() {
        System.out.println("setNazev");
        String nazev = "nazev1";
        Firma instance = firma;
        instance.setNazev(nazev);

    }

    /**
     * Test of getICO method, of class Firma.
     */
    @Test
    public void testGetICO() {
        System.out.println("getICO");
        Firma instance = firma;
        int expResult = 0;
        int result = instance.getICO();
        assertEquals(expResult, result);

    }

    /**
     * Test of setICO method, of class Firma.
     */
    @Test
    public void testSetICO() {
        System.out.println("setICO");
        int ICO = 1;
        Firma instance = firma;
        instance.setICO(ICO);
        int result = instance.getICO();
        assertEquals(ICO, result);
    }

    /**
     * Test of getAdresa method, of class Firma.
     */
    @Test
    public void testGetAdresa() {
        System.out.println("getAdresa");
        Firma instance = firma;
        Adresa expResult = adresa;
        Adresa result = instance.getAdresa();
        assertEquals(expResult, result);

    }

    /**
     * Test of setAdresa method, of class Firma.
     */
    @Test
    public void testSetAdresa() {
        System.out.println("setAdresa");
        Adresa adresa1 = new Adresa(0, "stat", "mesto", 0, "ulice", 0);
        Firma instance = firma;
        instance.setAdresa(adresa1);
        Adresa result = instance.getAdresa();
        assertEquals(adresa1, result);
    }

    /**
     * Test of getOsoba method, of class Firma.
     */
    @Test
    public void testGetOsoba() {
        System.out.println("getOsoba");
        Firma instance = firma;
        Osoba expResult = osoba;
        Osoba result = instance.getOsoba();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setOsoba method, of class Firma.
     */
    @Test
    public void testSetOsoba() {
        System.out.println("setOsoba");
        
        Osoba osoba1 = new Osoba(
                "111",
                "Honza Rohlik",
                "777777777",
                "rohlik@vse.cz",
                adresa
        );;
        Firma instance = firma;
        instance.setOsoba(osoba1);
         Osoba result = instance.getOsoba();
        assertEquals(osoba1, result);
        
    }

}
