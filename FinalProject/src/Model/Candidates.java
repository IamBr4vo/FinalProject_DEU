/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Bravo
 */
public class Candidates {
    private int id;
    private String id_number;
    private String name;
    private String politicParty;
    private String image;

    public Candidates(String id_number, String name, String politicParty, String image) {
        this.id_number = id_number;
        this.name = name;
        this.politicParty = politicParty;
        this.image = image;
    }

    public Candidates(int id, String id_number, String name, String politicParty, String image) {
        this.id = id;
        this.id_number = id_number;
        this.name = name;
        this.politicParty = politicParty;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_number() {
        return id_number;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoliticParty() {
        return politicParty;
    }

    public void setPoliticParty(String politicParty) {
        this.politicParty = politicParty;
    }

    public String getImage() {
        return image;
    }

    public void setImagen(String image) {
        this.image = image;
    }
    
}
