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
import spolupracejavisti.TypSpoluprace;

/**
 *
 * @author wooow
 */
public class TypSpolupraceTest {
    
    private TypSpoluprace typSpoluprace;
    
   
    @Before
    public void setUp() {
        
        typSpoluprace = new TypSpoluprace(
            11111,
            "Test"   
        );
       
       
        
    }
    
    

    /**
     * Test of getId method, of class TypSpoluprace.
     */
    @Test
    public void testGetId() {
        TypSpoluprace typSpoluprace2 = new TypSpoluprace(
            11111,
            "Test"
       );
        
        TypSpoluprace typSpoluprace3 = new TypSpoluprace(
            33333,
            "Test3"
       );
        
        assertNotSame(typSpoluprace.typSpolupraceId, typSpoluprace3.typSpolupraceId);
        
        assertEquals(typSpoluprace.typSpolupraceId, typSpoluprace2.typSpolupraceId);
    }

    /**
     * Test of setId method, of class TypSpoluprace.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int typSpolupraceId = 0;
        TypSpoluprace instance = typSpoluprace;
        instance.setId(typSpolupraceId);
        
    }

    /**
     * Test of getNazev method, of class TypSpoluprace.
     */
    @Test
    public void testGetNazev() {
        TypSpoluprace typSpoluprace2 = new TypSpoluprace(
            11111,
            "Test"
       );
        
        TypSpoluprace typSpoluprace3 = new TypSpoluprace(
            33333,
            "Test3"
       );
        
        assertNotSame(typSpoluprace.nazev, typSpoluprace3.nazev);
        
        assertEquals(typSpoluprace.nazev, typSpoluprace2.nazev);
    }

    /**
     * Test of setNazev method, of class TypSpoluprace.
     */
    @Test
    public void testSetNazev() {
        System.out.println("setNazev");
        String nazev = "";
        TypSpoluprace instance = typSpoluprace;
        instance.setNazev(nazev);
        
    }
    
}
