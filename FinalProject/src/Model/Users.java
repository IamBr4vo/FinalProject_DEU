/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Eithel
 */
public class Users {
    private int id;
    private String id_number;
    private String name;
    private int age;
    private int telephone;
    private int key;
    private int rol_id;

    public Users() {
    }

    public Users(int id, String id_number, String name, int age, int telephone, int key, int rol_id) {
        this.id = id;
        this.id_number = id_number;
        this.name = name;
        this.age = age;
        this.telephone = telephone;
        this.key = key;
        this.rol_id = rol_id;
    }

    public Users(String id_number, String name, int age, int telephone, int key, int rol_id) {
        this.id_number = id_number;
        this.name = name;
        this.age = age;
        this.telephone = telephone;
        this.key = key;
        this.rol_id = rol_id;
    }
    
}
