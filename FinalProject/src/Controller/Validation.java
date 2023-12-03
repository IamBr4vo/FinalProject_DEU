/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author Bravo
 */
public class Validation {

    public static boolean validateNumbers(String value) {
        Pattern patron = Pattern.compile("[0-9]*");
        Matcher m = patron.matcher(value);
        return m.matches();
    }

    public static boolean validateIdNumber(String value) {
        Pattern patron = Pattern.compile("^[1-7][0-9]{8}$");
        Matcher m = patron.matcher(value);
        return m.matches();
    }

    public static boolean validateLyrics(String value) {
        Pattern patron = Pattern.compile("^[a-zA-ZñÑáéíóúÁÉÍÓÚ ]{1,50}$");
        Matcher m = patron.matcher(value);
        return m.matches();
    }

    public static boolean validateEmail(String value) {
        String emailRegex = "^[A-Za-z0-9_.+-]+@[A-Za-z0-9-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    public static boolean verificateIdNumberExistingForUsers(String cedula) {
        DBConnection db = new DBConnection();
        String consultaSQL = "SELECT COUNT(*) FROM users WHERE id_number = ?";

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
            ps.setString(1, cedula);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0; //si es mayor a 0 la cédula ya existe dentro de la base de datos
            }
        } catch (SQLException e) {
        } finally {
            db.disconnect();
        }
        return false; // la cédula no existe dentro de la base de datos
    }
    
    public static boolean verificateIdNumberExistingForCandidates(String cedula) {
        DBConnection db = new DBConnection();
        String consultaSQL = "SELECT COUNT(*) FROM candidates WHERE id_number = ?";

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
            ps.setString(1, cedula);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0; //si es mayor a 0 la cédula ya existe dentro de la base de datos
            }
        } catch (SQLException e) {
        } finally {
            db.disconnect();
        }
        return false; // la cédula no existe dentro de la base de datos
    }
}
