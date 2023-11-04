/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.sql.ResultSet;

/**
 *
 * @author Eithel
 */
public class UsersDAO {
    // Method to create a new user

    public void create(Users user) {
        DBConnection db = new DBConnection();
        String consultaSQL = "INSERT INTO users (id_number, name, age, telephone, key, rol_id) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
            ps.setString(1, user.getId_number());
            ps.setString(2, user.getName());
            ps.setInt(3, user.getAge());
            ps.setInt(4, user.getTelephone());
            ps.setInt(5, user.getKey());
            ps.setInt(6, user.getRol_id());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Se insertó correctamente el usuario");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No Se insertó correctamente el usuario, error: " + e.toString());
        } finally {
            db.disconnect();
        }
    }

    // Method to read and retrieve a list of users
    public List<Users> read() {

        DBConnection db = new DBConnection();
        List<Users> usersList = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String id_number = resultSet.getString("id_number");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                int telephone = resultSet.getInt("telephone");
                int key = resultSet.getInt("key");
                int rol_id = resultSet.getInt("rol_id");
                usersList.add(new Users(id, id_number, name, age, telephone, key, rol_id)); // Create the new users object
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return usersList;
    }

    // Method to update an existing user record
    public void update(Users user) {

        DBConnection db = new DBConnection();

        String consultaSQL = "UPDATE users SET id_number=?, name=?, age=?, telephone=?, key=?, rol_id=? WHERE id=?";

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
            ps.setString(1, user.getId_number());
            ps.setString(2, user.getName());
            ps.setInt(3, user.getAge());
            ps.setInt(4, user.getTelephone());
            ps.setInt(5, user.getKey());
            ps.setInt(6, user.getRol_id());
            ps.setInt(7, user.getId());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Modificación Exitosa");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se modificó, error:" + e.toString());
        } finally {
            db.disconnect();
        }
    }
    // Method to delete a user record by ID

    public void delete(int id) {
        DBConnection db = new DBConnection();
        String consultaSQL = "DELETE FROM users WHERE id=?";
        try {
            PreparedStatement preparedStatement = db.getConnection().prepareStatement(consultaSQL);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            JOptionPane.showMessageDialog(null, "Se eliminó correctamente el usuario");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar, error: " + e.toString());
        } finally {
            db.disconnect();
        }
    }
}
