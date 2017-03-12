/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spolupracejavisti;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Třída pro manipulaci s databází.
 *
 * @author wooow
 */
public final class Databaze {

    Connection connection;

    HashMap<Integer, Spoluprace> spoluprace;
    HashMap<Integer, TypSpoluprace> typySpoluprace;
    HashMap<String, Osoba> osoby;
    HashMap<String, Uzivatel> uzivatele;
    HashMap<Integer, Firma> firmy;
    HashMap<Integer, Adresa> adresy;

    /**
     * Připojí se k databázovému serveru. Pokud je připojení úspěšné, načte data
     * do příslušných atributů.
     */
    public Databaze() {
        MysqlDataSource dataSource = new MysqlDataSource();
        spoluprace = new HashMap<>();
        typySpoluprace = new HashMap<>();
        osoby = new HashMap<>();
        uzivatele = new HashMap<>();
        firmy = new HashMap<>();
        adresy = new HashMap<>();
        dataSource.setServerName("Your Server here");
        dataSource.setUser("java");
        dataSource.setPassword("password");
        dataSource.setDatabaseName("java");

        try {
            connection = dataSource.getConnection();
            nactiEntity();
        } catch (SQLException e) {
            System.out.println("Chyba při připojení k databázi!");
            System.out.print(e);
        }
    }

    /**
     * Načte data z databáze do příslušných tříd, které jsou vloženy do HashMap.
     */
    public void nactiEntity() {
        try {
            Statement stmt = connection.createStatement();

            // Adresy
            ResultSet adresyResultSet = stmt.executeQuery(
                    "SELECT adresa_id, adresa_stat, adresa_mesto, adresa_zip, adresa_ulice, adresa_cp "
                    + "FROM adresa");
            while (adresyResultSet.next()) {
                Adresa data = new Adresa(
                        adresyResultSet.getInt(1),
                        adresyResultSet.getString(2),
                        adresyResultSet.getString(3),
                        adresyResultSet.getInt(4),
                        adresyResultSet.getString(5),
                        adresyResultSet.getInt(6)
                );
                adresy.put(data.getId(), data);
            }

            // Osoby
            ResultSet osobyResultSet = stmt.executeQuery(
                    "SELECT osoba_rodne_cislo, osoba_jmeno, osoba_telefon, osoba_email, adresa_id "
                    + "FROM osoba"
            );
            while (osobyResultSet.next()) {
                Osoba data = new Osoba(
                        osobyResultSet.getString(1),
                        osobyResultSet.getString(2),
                        osobyResultSet.getString(3),
                        osobyResultSet.getString(4),
                        adresy.get(osobyResultSet.getInt(5))
                );
                osoby.put(data.getRodneCislo(), data);
            }

            // Uživatelé
            ResultSet uzivateleResultSet = stmt.executeQuery(
                    "SELECT osoba_rodne_cislo, uzivatel_login, uzivatel_heslo, uzivatel_opravneni "
                    + "FROM uzivatel"
            );
            while (uzivateleResultSet.next()) {
                Uzivatel data = new Uzivatel(
                        osoby.get(uzivateleResultSet.getString(1)),
                        uzivateleResultSet.getString(2),
                        uzivateleResultSet.getString(3),
                        Uzivatel.TypUzivatele.valueOf(uzivateleResultSet.getString(4)),
                        false
                );
                uzivatele.put(data.getLogin(), data);
            }

            // Typy spolupráce
            ResultSet typySpolupraceResultSet = stmt.executeQuery(
                    "SELECT typ_spoluprace_id, typ_spoluprace_nazev "
                    + "FROM typ_spoluprace"
            );
            while (typySpolupraceResultSet.next()) {
                TypSpoluprace data = new TypSpoluprace(
                        typySpolupraceResultSet.getInt(1),
                        typySpolupraceResultSet.getString(2)
                );
                typySpoluprace.put(data.getId(), data);
                System.out.println(data.getNazev() + " " + data.getId());
            }

            // Spolupráce
            ResultSet spolupraceResultSet = stmt.executeQuery(
                    "SELECT spoluprace_id, spoluprace_od, spoluprace_do, typ_spoluprace_id, organizujici_osoba, realizujici_osoba "
                    + "FROM spoluprace"
            );
            while (spolupraceResultSet.next()) {
                Spoluprace data = new Spoluprace(
                        spolupraceResultSet.getInt(1),
                        spolupraceResultSet.getString(2),
                        spolupraceResultSet.getString(3),
                        typySpoluprace.get(spolupraceResultSet.getInt(4)),
                        osoby.get(spolupraceResultSet.getString(5)),
                        osoby.get(spolupraceResultSet.getString(6))
                );
                spoluprace.put(data.getId(), data);
            }

            // Firmy
            ResultSet firmyResultSet = stmt.executeQuery(
                    "SELECT firma_ico, firma_nazev, adresa_id, kontaktni_osoba "
                    + "FROM firma"
            );
            while (firmyResultSet.next()) {
                Firma data = new Firma(
                        firmyResultSet.getInt(1),
                        firmyResultSet.getString(2),
                        adresy.get(firmyResultSet.getInt(3)),
                        osoby.get(firmyResultSet.getString(4))
                );
                firmy.put(data.getICO(), data);
            }

        } catch (SQLException e) {
            System.out.println("Chyba při přístupu k databázi!");
            System.out.print(e);
        }
    }

    public boolean signInAttempt(String login, String password) {
        Uzivatel uzivatel = uzivatele.get(login);
        if (uzivatel != null) {
            return uzivatel.zkontrolujHeslo(password);
        } else {
            return false;
        }
    }

    public boolean signUpAttempt(Uzivatel uzivatel) {
        if (uzivatele.get(uzivatel.getLogin()) != null) {
            return false;
        }
        if (uzivatel.getOsoba() == null) {
            return false;
        }
        String sqlQuery = "INSERT INTO uzivatel (osoba_rodne_cislo, uzivatel_login, "
                + "uzivatel_heslo, uzivatel_opravneni) VALUES (" + uzivatel.getOsoba().getRodneCislo()
                + ", '" + uzivatel.getLogin() + "','" + uzivatel.getHeslo() + "', '"
                + uzivatel.getTypUzivatele() + "')";
        System.out.println(sqlQuery);
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlQuery);
            uzivatele.put(uzivatel.getLogin(), uzivatel);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean insertNewTypSpoluprace(String name, int ID) {
        String sqlQuery = "insert into typ_spoluprace (typ_spoluprace_id, typ_spoluprace_nazev) values (" + ID + ", '" + name + "')";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlQuery);
            TypSpoluprace typSpoluprace = new TypSpoluprace(ID, name);
            typySpoluprace.put(ID, typSpoluprace);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateTypSpoluprace(String name, int ID) {
        String sqlQuery = "update typ_spoluprace set "
                + "typ_spoluprace_nazev = '" + name + "' "
                + "where typ_spoluprace_id = " + ID;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlQuery);
            TypSpoluprace typSpoluprace = typySpoluprace.get(ID);
            typSpoluprace.setNazev(name);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean insertNewOsoba(String RC, String jmeno, String telefon, String email, int adresaID) {
        String sqlQuery = "insert into osoba (osoba_rodne_cislo, osoba_jmeno, osoba_telefon, osoba_email, adresa_id) values ('" + RC + "', '" + jmeno + "','" + telefon + "','" + email + "'," + adresaID + ")";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlQuery);
            Osoba osoba = new Osoba(RC, jmeno, telefon, email, adresy.get(adresaID));
            osoby.put(RC, osoba);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateOsoba(String RC, String jmeno, String telefon, String email, int adresaID) {
        String sqlQuery = "update osoba set "
                + "osoba_rodne_cislo = '" + RC + "', "
                + "osoba_jmeno = '" + jmeno + "', "
                + "osoba_telefon = '" + telefon + "', "
                + "osoba_email = '" + email + "', "
                + "adresa_id = " + adresaID + " "
                + "where osoba_rodne_cislo = '" + RC + "'";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlQuery);
            Osoba osoba = osoby.get(RC);
            osoba.setJmeno(jmeno);
            osoba.setTelefon(telefon);
            osoba.setEmail(email);
            osoba.setAdresa(adresy.get(adresaID));
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteOsoba(String RC) {
        String sqlQuery = "delete from osoba where osoba_rodne_cislo = " + RC;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlQuery);
            osoby.remove(RC);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //SELECT spoluprace_id, spoluprace_od, spoluprace_do, typ_spoluprace_id, organizujici_osoba, realizujici_osoba
    public boolean insertNewSpoluprace(int ID, String datumOd, String datumDo, int typSpolupraceId, String organizujiciOsoba, String realizujiciOsoba) {
        String sqlQuery = "insert into spoluprace (spoluprace_id, spoluprace_od, spoluprace_do, typ_spoluprace_id, organizujici_osoba, realizujici_osoba) values (" + ID + ", '" + datumOd + "','" + datumDo + "'," + typSpolupraceId + ",'" + organizujiciOsoba + "', '" + realizujiciOsoba + "') ";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlQuery);
            Spoluprace novaSpoluprace = new Spoluprace(ID, datumOd, datumDo, typySpoluprace.get(typSpolupraceId), osoby.get(organizujiciOsoba), osoby.get(realizujiciOsoba));
            spoluprace.put(ID, novaSpoluprace);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateSpoluprace(int ID, String datumOd, String datumDo, int typSpolupraceId, String organizujiciOsoba, String realizujiciOsoba) {
        String sqlQuery = "update spoluprace set "
                + "spoluprace_id = " + ID + ", "
                + "spoluprace_od = '" + datumOd + "', "
                + "spoluprace_do = '" + datumDo + "', "
                + "typ_spoluprace_id = " + typSpolupraceId + ", "
                + "organizujici_osoba = '" + organizujiciOsoba + "', "
                + "realizujici_osoba = '" + realizujiciOsoba + "' "
                + "where spoluprace_id = " + ID;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlQuery);
            Spoluprace upravovanaSpoluprace = spoluprace.get(ID);
            upravovanaSpoluprace.setDatumOd(datumOd);
            upravovanaSpoluprace.setDatumDo(datumDo);
            upravovanaSpoluprace.setTypSpolupraceId(typySpoluprace.get(typSpolupraceId));
            upravovanaSpoluprace.setOrganizujiciOsoba(osoby.get(organizujiciOsoba));
            upravovanaSpoluprace.setRealizujiciOsoba(osoby.get(realizujiciOsoba));
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteSpoluprace(int ID) {
        String sqlQuery = "delete from spoluprace where spoluprace_id = " + ID;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlQuery);
            spoluprace.remove(ID);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean insertNewFirma(int adresaID, int firmaICO, String firmaNazev, String kontaktniOsoba) {
        String sqlQuery = "insert into firma (adresa_id, firma_ico, firma_nazev, kontaktni_osoba) values (" + adresaID + ", '" + firmaICO + "','" + firmaNazev + "', '" + kontaktniOsoba + "') ";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlQuery);
            Firma firma = new Firma(firmaICO, firmaNazev, adresy.get(adresaID), osoby.get(kontaktniOsoba));
            firmy.put(firmaICO, firma);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateFirma(int adresaID, int firmaICO, String firmaNazev, String kontaktniOsoba) {
        String sqlQuery = "update firma set "
                + "adresa_id = " + adresaID + ", "
                + "firma_nazev = '" + firmaNazev + "', "
                + "kontaktni_osoba = '" + kontaktniOsoba + "' "
                + "where firma_ico = " + firmaICO;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlQuery);
            Firma firma = firmy.get(firmaICO);
            firma.setAdresa(adresy.get(adresaID));
            firma.setNazev(firmaNazev);
            firma.setOsoba(osoby.get(kontaktniOsoba));
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteFirma(int ID) {
        String sqlQuery = "delete from firma where firma_ico = " + ID;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlQuery);
            firmy.remove(ID);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public HashMap<Integer, Spoluprace> getSpoluprace() {
        return spoluprace;
    }

    public Spoluprace getSpolupraci(Integer id) {
        return spoluprace.get(id);
    }

    public HashMap<Integer, TypSpoluprace> getTypySpoluprace() {
        return typySpoluprace;
    }

    public TypSpoluprace getTypSpoluprace(Integer id) {
        return typySpoluprace.get(id);
    }

    public HashMap<String, Osoba> getOsoby() {
        return osoby;
    }

    public Osoba getOsoba(String rodneCislo) {
        return osoby.get(rodneCislo);
    }

    public HashMap<Integer, Firma> getFirmy() {
        return firmy;
    }

    public Firma getFirma(Integer id) {
        return firmy.get(id);
    }

    public HashMap<Integer, Adresa> getAdresy() {
        return adresy;
    }

    public Adresa getAdresa(Integer id) {
        return adresy.get(id);
    }

    public HashMap<String, Uzivatel> getUzivatele() {
        return uzivatele;
    }

    public Uzivatel getUzivatel(String login) {
        return uzivatele.get(login);
    }
}
