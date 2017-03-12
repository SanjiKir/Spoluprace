package spolupracejavisti;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import spolupracejavisti.Adresa;
import spolupracejavisti.Osoba;
import spolupracejavisti.Spoluprace;
import spolupracejavisti.TypSpoluprace;

/**
 *
 * @author wooow
 */
public class SpolupraceTest {
    
   private Spoluprace spoluprace;
    
    @Before
    public void setUp() {
        
         spoluprace = new Spoluprace(
            11111,
            "1.1.2017",
            "3.1.2017",
            new TypSpoluprace(1, "test"),
            new Osoba("test", "test", "test", "test2", new Adresa(1, "CZ", "Praha", 70000, "Wilsonova", 1)),
            new Osoba("test2", "test2", "test2", "test2", new Adresa(2, "CZ", "Praha", 70001, "Wilsonovaa", 2))
        );
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class Spoluprace.
     */
    @Test
    public void testGetId() {
        Spoluprace spoluprace2 = new Spoluprace(
            11111,
            "1.1.2017",
            "3.1.2017",
            new TypSpoluprace(1, "test"),
            new Osoba("test", "test", "test", "test2", new Adresa(1, "CZ", "Praha", 70000, "Wilsonova", 1)),
            new Osoba("test2", "test2", "test2", "test2", new Adresa(2, "CZ", "Praha", 70001, "Wilsonovaa", 2))
        );
        
        Spoluprace spoluprace3 = new Spoluprace(
            2222,
            "2.1.2017",
            "15.1.2017",
            new TypSpoluprace(1, "test3"),
            new Osoba("test3", "test3", "test3", "test23", new Adresa(13, "CZ", "Praha", 70003, "Wilsonovaaaa", 13)),
            new Osoba("test22", "test22", "test22", "test22", new Adresa(22, "CZ", "Praha", 70021, "Wilsonovaa2", 22))
        );
        
        
        assertNotSame(spoluprace.id, spoluprace3.id);
        
        assertEquals(spoluprace.id, spoluprace2.id);
    }

    /**
     * Test of setId method, of class Spoluprace.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int id = 0;
        Spoluprace instance = spoluprace;
        instance.setId(id);
     
    }

    /**
     * Test of getDatumOd method, of class Spoluprace.
     */
    @Test
    public void testGetDatumOd() {
        
        Spoluprace spoluprace2 = new Spoluprace(
            11111,
            "1.1.2017",
            "4.1.2017",
            new TypSpoluprace(1, "test"),
            new Osoba("test", "test", "test", "test2", new Adresa(1, "CZ", "Praha", 70000, "Wilsonova", 1)),
            new Osoba("test2", "test2", "test2", "test2", new Adresa(2, "CZ", "Praha", 70001, "Wilsonovaa", 2))
        );
        
         Spoluprace spoluprace3 = new Spoluprace(
            2222,
            "2.1.2017",
            "15.1.2017",
            new TypSpoluprace(1, "test3"),
            new Osoba("test3", "test3", "test3", "test23", new Adresa(13, "CZ", "Praha", 70003, "Wilsonovaaaa", 13)),
            new Osoba("test22", "test22", "test22", "test22", new Adresa(22, "CZ", "Praha", 70021, "Wilsonovaa2", 22))
        );
        
        assertEquals(spoluprace.datumOd, spoluprace2.datumOd);
        assertNotSame(spoluprace.datumOd, spoluprace3.datumOd);
    }

    /**
     * Test of setDatumOd method, of class Spoluprace.
     */
    @Test
    public void testSetDatumOd() {
        System.out.println("setDatumOd");
        String datumOd = "";
        Spoluprace instance = spoluprace;
        instance.setDatumOd(datumOd);
     
    }

    /**
     * Test of getDatumDo method, of class Spoluprace.
     */
    @Test
    public void testGetDatumDo() {
        Spoluprace spoluprace2 = new Spoluprace(
            11111,
            "1.1.2017",
            "3.1.2017",
            new TypSpoluprace(1, "test"),
            new Osoba("test", "test", "test", "test2", new Adresa(1, "CZ", "Praha", 70000, "Wilsonova", 1)),
            new Osoba("test2", "test2", "test2", "test2", new Adresa(2, "CZ", "Praha", 70001, "Wilsonovaa", 2))
        );
        
        Spoluprace spoluprace3 = new Spoluprace(
            2222,
            "2.1.2017",
            "15.1.2017",
            new TypSpoluprace(1, "test3"),
            new Osoba("test3", "test3", "test3", "test23", new Adresa(13, "CZ", "Praha", 70003, "Wilsonovaaaa", 13)),
            new Osoba("test22", "test22", "test22", "test22", new Adresa(22, "CZ", "Praha", 70021, "Wilsonovaa2", 22))
        );
        
        
        assertNotSame(spoluprace.datumDo, spoluprace3.datumDo);
        
        assertEquals(spoluprace.datumDo, spoluprace2.datumDo);
    }

    /**
     * Test of setDatumDo method, of class Spoluprace.
     */
    @Test
    public void testSetDatumDo() {
        System.out.println("setDatumDo");
        String datumDo = "";
        Spoluprace instance = spoluprace;
        instance.setDatumDo(datumDo);
  
    }

    /**
     * Test of getTypSpolupraceId method, of class Spoluprace.
     */
    @Test
    public void testGetTypSpolupraceId() {
        Spoluprace spoluprace2 = new Spoluprace(
            11111,
            "1.1.2017",
            "3.1.2017",
            new TypSpoluprace(1, "test"),
            new Osoba("test", "test", "test", "test2", new Adresa(1, "CZ", "Praha", 70000, "Wilsonova", 1)),
            new Osoba("test2", "test2", "test2", "test2", new Adresa(2, "CZ", "Praha", 70001, "Wilsonovaa", 2))
        );
        
        Spoluprace spoluprace3 = new Spoluprace(
            2222,
            "2.1.2017",
            "15.1.2017",
            new TypSpoluprace(1, "test3"),
            new Osoba("test3", "test3", "test3", "test23", new Adresa(13, "CZ", "Praha", 70003, "Wilsonovaaaa", 13)),
            new Osoba("test22", "test22", "test22", "test22", new Adresa(22, "CZ", "Praha", 70021, "Wilsonovaa2", 22))
        );
        
        
        assertNotSame(spoluprace.typSpolupraceId, spoluprace3.typSpolupraceId);
        
        assertEquals(spoluprace.typSpolupraceId.getId(), spoluprace2.typSpolupraceId.getId());
    }

    /**
     * Test of setTypSpolupraceId method, of class Spoluprace.
     */
    @Test
    public void testSetTypSpolupraceId() {
        System.out.println("setTypSpolupraceId");
        TypSpoluprace typSpolupraceId = new TypSpoluprace(0,"nazev");
        Spoluprace instance = spoluprace;
        instance.setTypSpolupraceId(typSpolupraceId);
    
    }

    /**
     * Test of getOrganizujiciOsoba method, of class Spoluprace.
     */
    @Test
    public void testGetOrganizujiciOsoba() {
         Spoluprace spoluprace2 = new Spoluprace(
            11111,
            "1.1.2017",
            "3.1.2017",
            new TypSpoluprace(1, "test"),
            new Osoba("test", "test", "test", "test2", new Adresa(1, "CZ", "Praha", 70000, "Wilsonova", 1)),
            new Osoba("test2", "test2", "test2", "test2", new Adresa(2, "CZ", "Praha", 70001, "Wilsonovaa", 2))
        );
        
        Spoluprace spoluprace3 = new Spoluprace(
            2222,
            "2.1.2017",
            "15.1.2017",
            new TypSpoluprace(1, "test3"),
            new Osoba("test3", "test3", "test3", "test23", new Adresa(13, "CZ", "Praha", 70003, "Wilsonovaaaa", 13)),
            new Osoba("test22", "test22", "test22", "test22", new Adresa(22, "CZ", "Praha", 70021, "Wilsonovaa2", 22))
        );
        
        
        assertNotSame(spoluprace.organizujiciOsoba, spoluprace3.organizujiciOsoba);
        
        assertEquals(spoluprace.organizujiciOsoba.getRodneCislo(), spoluprace2.organizujiciOsoba.getRodneCislo());
    }

    /**
     * Test of setOrganizujiciOsoba method, of class Spoluprace.
     */
    @Test
    public void testSetOrganizujiciOsoba() {
        System.out.println("setOrganizujiciOsoba");
        Osoba organizujiciOsoba = new Osoba("asd", "asd", "asd", "asd", new Adresa(0, "stat", "mesto", 0, "ulice", 0));;
        Spoluprace instance = spoluprace;
        instance.setOrganizujiciOsoba(organizujiciOsoba);
      
    }

    /**
     * Test of getRealizujiciOsoba method, of class Spoluprace.
     */
    @Test
    public void testGetRealizujiciOsoba() {
         Spoluprace spoluprace2 = new Spoluprace(
            11111,
            "1.1.2017",
            "3.1.2017",
            new TypSpoluprace(1, "test"),
            new Osoba("test", "test", "test", "test2", new Adresa(1, "CZ", "Praha", 70000, "Wilsonova", 1)),
            new Osoba("test2", "test2", "test2", "test2", new Adresa(2, "CZ", "Praha", 70001, "Wilsonovaa", 2))
        );
        
        Spoluprace spoluprace3 = new Spoluprace(
            2222,
            "2.1.2017",
            "15.1.2017",
            new TypSpoluprace(1, "test3"),
            new Osoba("test3", "test3", "test3", "test23", new Adresa(13, "CZ", "Praha", 70003, "Wilsonovaaaa", 13)),
            new Osoba("test22", "test22", "test22", "test22", new Adresa(22, "CZ", "Praha", 70021, "Wilsonovaa2", 22))
        );
        
        
        assertNotSame(spoluprace.realizujiciOsoba, spoluprace3.realizujiciOsoba);
        
        assertEquals(spoluprace.realizujiciOsoba.getRodneCislo(), spoluprace2.realizujiciOsoba.getRodneCislo());
    }

    /**
     * Test of setRealizujiciOsoba method, of class Spoluprace.
     */
    @Test
    public void testSetRealizujiciOsoba() {
        System.out.println("setRealizujiciOsoba");
        Osoba realizujiciOsoba = new Osoba("asd", "asd", "asd", "asd", new Adresa(0, "stat", "mesto", 0, "ulice", 0));
        Spoluprace instance = spoluprace;
        instance.setRealizujiciOsoba(realizujiciOsoba);
       
    }
    
}
