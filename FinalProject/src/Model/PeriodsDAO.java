/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Eithel
 */
public class PeriodsDAO {
    
    // Method to create a new period 
    public void create(Periods periods) {
        DBConnection db = new DBConnection();
        String consultaSQL = "INSERT INTO periods (start_date, finish_date, status) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
            ps.setDate(1, new java.sql.Date(periods.getStart_date().getTime()));
            ps.setDate(2, new java.sql.Date(periods.getFinish_date().getTime()));
            ps.setString(3, periods.getStatus());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Se insertó correctamente el periodo");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No Se insertó correctamente el estudiante, error: " + e.toString());
        } finally {
            db.disconnect();
        }
    }

    // Method to read and retrieve a list of periods
    public List<Periods> read() {

        DBConnection db = new DBConnection();
        List<Periods> periods = new ArrayList<>();
        String sql = "SELECT * FROM periods";

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                Date startDate = resultSet.getDate("start_date");
                Date finishDate = resultSet.getDate("finish_date");
                String status = resultSet.getString("status");
                periods.add(new Periods(id, startDate, finishDate, status)); //Create the new periods object
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return periods;
    }

    // Method to update an existing periods record
    public void update(Periods periods) {

        DBConnection db = new DBConnection();

        String consultaSQL = "UPDATE periods SET start_date=?, finish_date=?, status=? WHERE id=?";

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
            ps.setDate(1, new java.sql.Date(periods.getStart_date().getTime()));
            ps.setDate(2, new java.sql.Date(periods.getFinish_date().getTime()));
            ps.setString(3, periods.getStatus());
            ps.setInt(4, periods.getId());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Modificación Exitosa");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se modificó, error:" + e.toString());
        } finally {
            db.disconnect();
        }
    }

    // Method to delete a periods record by ID
    public void delete(int id) {
        DBConnection db = new DBConnection();
        String consultaSQL = "DELETE FROM periods WHERE id=?";
        try {
            PreparedStatement preparedStatement = db.getConnection().prepareStatement(consultaSQL);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            JOptionPane.showMessageDialog(null, "Se eliminó correctamente el período");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar, error: " + e.toString());
        } finally {
            db.disconnect();
        }
    }
     // Method to obtain active dates
    public Date[] getActivePeriodDates() {
        DBConnection db = new DBConnection();
        Date[] activeDates = new Date[2]; // Array to store start and end dates

        String activePeriodsSQL = "SELECT start_date, finish_date FROM periods WHERE status = 'Activo'";

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(activePeriodsSQL);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                activeDates[0] = resultSet.getDate("start_date");
                activeDates[1] = resultSet.getDate("finish_date");
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            db.disconnect();
        }

        return activeDates;
    }
    public int getIdActivePeriod() {
        DBConnection db = new DBConnection();
        int id = 0;

        String activePeriodsSQL = "SELECT id FROM periods WHERE status = 'Activo'";

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(activePeriodsSQL);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                id = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            db.disconnect();
        }

        return id;
    }
}
