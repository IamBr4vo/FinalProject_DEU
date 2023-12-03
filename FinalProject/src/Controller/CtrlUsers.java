/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Users;
import Model.UsersDAO;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.util.Random;

/**
 *
 * @author Eithel
 */
public class CtrlUsers {

    UsersDAO dao = new UsersDAO();
    private int id; // Para mantener el ID del usuario seleccionado
    private static int rolId;

    public void loadDataUsers(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<TableModel> order = new TableRowSorter<TableModel>(model);
        table.setRowSorter(order);
        model.setRowCount(0);
        List<Users> users = dao.read();
        for (Users user : users) {
            Object[] row = {user.getId(), user.getId_number(), user.getName(), user.getEmail(),
                user.getAge(), user.getTelephone(), user.getKey(), dao.getNameRol(user.getRol_id())};
            model.addRow(row);
        }
    }

    public void addUserForAdmin(JTextField IDNumber, JTextField name, JTextField email, JTextField age, JTextField telephone, JTextField key) {
        // check if the ID already exists in the database
        if (Validation.verificateIdNumberExistingForUsers(IDNumber.getText())) {
            JOptionPane.showMessageDialog(null, "La cédula ya existe en la base de datos.");
        } else {
            try {
                // check the format of the ID and others
                if (!Validation.validateIdNumber(IDNumber.getText()) || !Validation.validateLyrics(name.getText()) || !Validation.validateEmail(email.getText()) || !Validation.validateNumbers(age.getText()) || !Validation.validateNumbers(telephone.getText())) {
                    JOptionPane.showMessageDialog(null, "Error en la cedula, nombre, email, edad o telefono, por favor revise y digite un formato válido.");
                } else {
                    this.dao.create(new Users(IDNumber.getText(), name.getText(), email.getText(), Integer.parseInt(age.getText()), Integer.parseInt(telephone.getText()), Integer.parseInt(key.getText()), rolId));
                    clearFields(IDNumber, name, email, age, telephone, key);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al agregar el usuario: " + e.toString());
            }
        }
    }

    public void addUserForVoters(JTextField IDNumber, JTextField name, JTextField email, JTextField age, JTextField telephone, JTextField key, JFrame currentFrame) {
        // check if the ID already exists in the database
        if (Validation.verificateIdNumberExistingForUsers(IDNumber.getText())) {
            JOptionPane.showMessageDialog(null, "La cédula ya existe en la base de datos.");
        } else {
            try {
                // check the format of the ID and the name
                if (!Validation.validateIdNumber(IDNumber.getText()) || !Validation.validateLyrics(name.getText()) || !Validation.validateEmail(email.getText()) || !Validation.validateNumbers(age.getText()) || !Validation.validateNumbers(telephone.getText())) {
                    JOptionPane.showMessageDialog(null, "Error en la cedula, nombre, email, edad o telefono, por favor revise y digite un formato válido.");
                } else {
                    this.dao.create(new Users(IDNumber.getText(), name.getText(), email.getText(), Integer.parseInt(age.getText()), Integer.parseInt(telephone.getText()), Integer.parseInt(key.getText()), rolId));
                    if (rolId == 2) {
                        currentFrame.dispose();
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al agregar el usuario: " + e.toString());
            }
        }
    }

    public void updateUser(JTable table, JTextField IDNumber, JTextField name, JTextField email, JTextField age, JTextField telephone, JTextField key) {
        try {
            int selectedRow = table.getSelectedRow();
            int userId = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
            // verificate if user is admin
            Users selectedUser = getUserById(userId);
            if (selectedUser != null && selectedUser.getRol_id() == 1) {
                JOptionPane.showMessageDialog(null, "Los administradores no pueden ser editados", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // check the format of the ID and the name
            if (!Validation.validateIdNumber(IDNumber.getText()) || !Validation.validateLyrics(name.getText()) || !Validation.validateEmail(email.getText()) || !Validation.validateNumbers(age.getText()) || !Validation.validateNumbers(telephone.getText())) {
                JOptionPane.showMessageDialog(null, "Error en la cedula, nombre, email, edad o telefono, por favor revise y digite un formato válido.");
            } else {
                this.dao.update(new Users(this.id, IDNumber.getText(), name.getText(), email.getText(), Integer.parseInt(age.getText()), Integer.parseInt(telephone.getText()), Integer.parseInt(key.getText()), rolId));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el usuario: " + e.toString());
        }
    }

    public void deleteUser() {
        this.dao.delete(this.id);
    }

    //Method to get a user by Id
    public Users getUserById(int userId) {
        List<Users> users = dao.read();
        for (Users user : users) {
            if (user.getId() == userId) {
                return user;
            }
        }
        return null;
    }

    public void selectedRow(JTable table, JTextField IDNumber, JTextField name, JTextField email, JTextField age, JTextField telephone, JTextField key) {
        try {
            int row = table.getSelectedRow();
            if (row >= 0) {
                this.id = Integer.parseInt(table.getValueAt(row, 0).toString());
                IDNumber.setText(table.getValueAt(row, 1).toString());
                name.setText(table.getValueAt(row, 2).toString());
                email.setText(table.getValueAt(row, 3).toString());
                age.setText(table.getValueAt(row, 4).toString());
                telephone.setText(table.getValueAt(row, 5).toString());
                key.setText(table.getValueAt(row, 6).toString());
            } else {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de selección, error: " + e.toString());
        }
    }

    //Method to generate 4 digit key
    public String generateRandomKey() {
        Random random = new Random();
        int key = 1000 + random.nextInt(9000); // generates a 4 digit random number
        return Integer.toString(key);
    }

    public void clearFields(JTextField IDNumber, JTextField name, JTextField email, JTextField age, JTextField telephone, JTextField key) {
        IDNumber.setText("");
        name.setText("");
        email.setText("");
        age.setText("");
        telephone.setText("");
        key.setText("");
    }

    public static void setRolId(int id) {
        rolId = id;
    }
}
