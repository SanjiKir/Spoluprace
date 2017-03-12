/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spolupracejavisti;

/**
 *
 * @author wooow
 */
public class Spoluprace {

    public int id;
    public String datumOd;
    public String datumDo;
    public TypSpoluprace typSpolupraceId;
    public Osoba organizujiciOsoba;
    public Osoba realizujiciOsoba;

    public Spoluprace(int id, String datumOd, String datumDo, TypSpoluprace typSpolupraceId, Osoba organizujiciOsoba, Osoba realizujiciOsoba) {

        this.id = id;
        this.datumOd = datumOd;
        this.datumDo = datumDo;

        this.typSpolupraceId = typSpolupraceId;
        this.organizujiciOsoba = organizujiciOsoba;
        this.realizujiciOsoba = realizujiciOsoba;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(String datumOd) {
        this.datumOd = datumOd;
    }

    public String getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(String datumDo) {
        this.datumDo = datumDo;
    }

    public TypSpoluprace getTypSpolupraceId() {
        return typSpolupraceId;
    }

    public void setTypSpolupraceId(TypSpoluprace typSpolupraceId) {
        this.typSpolupraceId = typSpolupraceId;
    }

    public Osoba getOrganizujiciOsoba() {
        return organizujiciOsoba;
    }

    public void setOrganizujiciOsoba(Osoba organizujiciOsoba) {
        this.organizujiciOsoba = organizujiciOsoba;
    }

    public Osoba getRealizujiciOsoba() {
        return realizujiciOsoba;
    }

    public void setRealizujiciOsoba(Osoba realizujiciOsoba) {
        this.realizujiciOsoba = realizujiciOsoba;
    }

}
