/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spolupracejavisti;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author wooow
 */
public class UzivatelTest {
    Uzivatel uzivatel;
    
    @Before
    public void setUp() {
        Osoba osoba = new Osoba(
            "950101/1234",
            "Testovací Uživatel",
            "+420777888999",
            "testovaci@uzivatel.cz",
            new Adresa(1, "CZ", "Praha", 70000, "Wilsonova", 1)
        );
        uzivatel = new Uzivatel(
                osoba,
                "testovaci",
                "testovaciHeslo",
                Uzivatel.TypUzivatele.UZIVATEL);
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    /**
     * Testuje správnou funkci hashování a kontrolování hesla.
     */
    @Test
    public void testHesla()
    {        
        Assert.assertTrue(uzivatel.zkontrolujHeslo("testovaciHeslo"));
        Assert.assertFalse(uzivatel.zkontrolujHeslo("testovaciheslo"));
        
        uzivatel.setHeslo("test");
        
        Assert.assertTrue(uzivatel.zkontrolujHeslo("test"));
        Assert.assertFalse(uzivatel.zkontrolujHeslo("spatneHeslo"));
        
        uzivatel.setHeslo("spravneHeslo");
        
        Assert.assertFalse(uzivatel.zkontrolujHeslo("test"));
        Assert.assertTrue(uzivatel.zkontrolujHeslo("spravneHeslo"));        
    }
    
    @Test
    public void testOpravneni()
    {
        assertTrue(uzivatel.maOpravneni(Uzivatel.Opravneni.ZOBRAZIT_OSOBU));
        assertFalse(uzivatel.maOpravneni(Uzivatel.Opravneni.EDITOVAT_OSOBU));
        
        uzivatel.setTypUzivatele(Uzivatel.TypUzivatele.SPRAVCE);
        
        assertTrue(uzivatel.maOpravneni(Uzivatel.Opravneni.PRIDAT_FIRMU));
        
        uzivatel.setTypUzivatele(null);
        
        assertFalse(uzivatel.maOpravneni(Uzivatel.Opravneni.ZOBRAZIT_FIRMU));
    }
}
