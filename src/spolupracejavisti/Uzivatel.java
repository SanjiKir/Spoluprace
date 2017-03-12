/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spolupracejavisti;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Třída pro uživatele, kteří se mohou přihlásit do systému. Jelikož každý
 * uživatel je zároveň i osoba, tak tato třída dědí od třídy Osoba.
 *
 * @author wooow
 */
public final class Uzivatel {

    public enum TypUzivatele {
        SPRAVCE, UZIVATEL
    };

    public enum Opravneni {
        PRIDAT_FIRMU, EDITOVAT_FIRMU, ZOBRAZIT_FIRMU, SMAZAT_FIRMU,
        PRIDAT_SPOLUPRACI, EDITOVAT_SPOLUPRACI, ZOBRAZIT_SPOLUPRACI, SMAZAT_SPOLUPRACI,
        PRIDAT_TYP_SPOLUPRACE, EDITOVAT_TYP_SPOLUPRACE, ZOBRAZIT_TYP_SPOLUPRACE, SMAZAT_TYP_SPOLUPRACE,
        PRIDAT_OSOBU, EDITOVAT_OSOBU, ZOBRAZIT_OSOBU, SMAZAT_OSOBU

    };

    private Osoba osoba;
    private String login;
    private String heslo;
    private TypUzivatele typUzivatele;

    public Uzivatel(Osoba osoba, String login, String heslo, TypUzivatele typUzivatele) {
        this(osoba, login, heslo, typUzivatele, true);
    }

    public Uzivatel(Osoba osoba, String login, String heslo, TypUzivatele typUzivatele, boolean hashovatHeslo) {
        setOsoba(osoba);
        setLogin(login);
        if (hashovatHeslo) {
            setHeslo(heslo);
        } else {
            this.heslo = heslo;
        }
        setTypUzivatele(typUzivatele);
    }

    public Osoba getOsoba() {
        return osoba;
    }

    public void setOsoba(Osoba osoba) {
        this.osoba = osoba;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHeslo() {
        return heslo;
    }

    public void setHeslo(String heslo) {
        this.heslo = zahashujHeslo(heslo);
    }

    public TypUzivatele getTypUzivatele() {
        return typUzivatele;
    }

    public void setTypUzivatele(TypUzivatele typUzivatele) {
        this.typUzivatele = typUzivatele;
    }

    /**
     * Zahashuje heslo pro uložení v databázi. Je použit algoritmus SHA-256.
     *
     * @param heslo Heslo v textové podobě
     * @return Heslo zahashované pomocí algoritmu SHA-256
     */
    private String zahashujHeslo(String heslo) {
        String zahashovaneHeslo = null;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(heslo.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            zahashovaneHeslo = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return zahashovaneHeslo;
    }

    /**
     * Ověří, zda je zadané heslo správné.
     *
     * @param heslo
     * @return True pokud je zadané heslo správné, false pokud je špatné.
     */
    public boolean zkontrolujHeslo(String heslo) {
        return zahashujHeslo(heslo).equals(this.heslo);
    }

    /**
     * Ověří, zda uživatel může vykonat danou činnost.
     *
     * @param opravneni Činnost, u které chceme zjistit, zda ji může uživatel
     * vykonat.
     * @return True, pokud uživatel může činnost vykonat, jinak false.
     */
    public boolean maOpravneni(Opravneni opravneni) {
        if (typUzivatele == TypUzivatele.SPRAVCE) {
            return true;
        } else if (typUzivatele == TypUzivatele.UZIVATEL) {
            if (opravneni == Opravneni.ZOBRAZIT_FIRMU
                    || opravneni == Opravneni.ZOBRAZIT_OSOBU
                    || opravneni == Opravneni.ZOBRAZIT_SPOLUPRACI
                    || opravneni == Opravneni.ZOBRAZIT_TYP_SPOLUPRACE) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
