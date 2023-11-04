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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getRol_id() {
        return rol_id;
    }

    public void setRol_id(int rol_id) {
        this.rol_id = rol_id;
    }
    
}
