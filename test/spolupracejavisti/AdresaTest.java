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
public class AdresaTest {
    private Adresa adresa;
    public AdresaTest() {
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
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getOsoba method, of class Adresa.
     */
    @Test
    public void testGetOsoba() {
        System.out.println("getOsoba");
        Adresa instance = adresa;
        Osoba expResult = null;
        Osoba result = instance.getOsoba();
        assertEquals(expResult, result);
        expResult = new Osoba("123", "jmeno", "asdas", "email", adresa);
        adresa.setOsoba(expResult);
        result = instance.getOsoba();
        assertEquals(expResult, result);
    }

    /**
     * Test of setOsoba method, of class Adresa.
     */
    @Test
    public void testSetOsoba() {
         System.out.println("getOsoba");
        Adresa instance = adresa;
        Osoba expResult = null;
        Osoba result = instance.getOsoba();
        assertEquals(expResult, result);
        expResult = new Osoba("123", "jmeno", "asdas", "email", adresa);
        instance.setOsoba(expResult);
        result = instance.getOsoba();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFirma method, of class Adresa.
     */
    @Test
    public void testGetFirma() {
        System.out.println("getFirma");
        Adresa instance = adresa;
        Firma expResult = null;
        Firma result = instance.getFirma();
        assertEquals(expResult, result);
        expResult = new Firma(0, "nazev", adresa, new Osoba("123", "jmeno", "asdas", "email", adresa));
        instance.setFirma(expResult);
        result = instance.getFirma();
        assertEquals(expResult, result);
    }

    /**
     * Test of setFirma method, of class Adresa.
     */
    @Test
    public void testSetFirma() {
        System.out.println("getFirma");
        Adresa instance = adresa;
        Firma expResult = null;
        Firma result = instance.getFirma();
        assertEquals(expResult, result);
        expResult = new Firma(0, "nazev", adresa, new Osoba("123", "jmeno", "asdas", "email", adresa));
        instance.setFirma(expResult);
        result = instance.getFirma();
        assertEquals(expResult, result);
      
    }

    /**
     * Test of getId method, of class Adresa.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Adresa instance = adresa;
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setId method, of class Adresa.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int id = 1;
        Adresa instance = adresa;
        instance.setId(id);
         assertEquals(id, instance.getId());
      
    }

    /**
     * Test of getStat method, of class Adresa.
     */
    @Test
    public void testGetStat() {
        System.out.println("getStat");
        Adresa instance = adresa;
        String expResult = "stat";
        String result = instance.getStat();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setStat method, of class Adresa.
     */
    @Test
    public void testSetStat() {
        System.out.println("setStat");
        String stat = "stat1";
        Adresa instance = adresa;
        instance.setStat(stat);
         String result = instance.getStat();
        assertEquals(stat, result);
       
    }

    /**
     * Test of getMesto method, of class Adresa.
     */
    @Test
    public void testGetMesto() {
        System.out.println("getMesto");
        Adresa instance = adresa;
        String expResult = "mesto";
        String result = instance.getMesto();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setMesto method, of class Adresa.
     */
    @Test
    public void testSetMesto() {
        System.out.println("setMesto");
        String mesto = "mesto1";
        Adresa instance = adresa;
        instance.setMesto(mesto);
     String result = instance.getMesto();
        assertEquals(mesto, result);
    }

    /**
     * Test of getZip method, of class Adresa.
     */
    @Test
    public void testGetZip() {
        System.out.println("getZip");
        Adresa instance = adresa;
        int expResult = 0;
        int result = instance.getZip();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setZip method, of class Adresa.
     */
    @Test
    public void testSetZip() {
        System.out.println("setZip");
        int zip = 1;
        Adresa instance = adresa;
        instance.setZip(zip);
        int result = instance.getZip();
        assertEquals(zip, result);
    }

    /**
     * Test of getUlice method, of class Adresa.
     */
    @Test
    public void testGetUlice() {
        System.out.println("getUlice");
        Adresa instance = adresa;
        String expResult = "ulice";
        String result = instance.getUlice();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setUlice method, of class Adresa.
     */
    @Test
    public void testSetUlice() {
        System.out.println("setUlice");
        String ulice = "ulice1";
        Adresa instance = adresa;
        instance.setUlice(ulice);
         String result = instance.getUlice();
        assertEquals(ulice, result);
    }

    /**
     * Test of getCp method, of class Adresa.
     */
    @Test
    public void testGetCp() {
        System.out.println("getCp");
        Adresa instance = adresa;
        int expResult = 0;
        int result = instance.getCp();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setCp method, of class Adresa.
     */
    @Test
    public void testSetCp() {
        System.out.println("setCp");
        int cp = 1;
        Adresa instance = adresa;
        instance.setCp(cp);
        int result = instance.getCp();
        assertEquals(cp, result);
    }
    
}
