/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Model.Users;
import Model.UsersDAO;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Eithel
 */
public class CtrlUsers {
     private UsersDAO dao;
    private int id; // Para mantener el ID del usuario seleccionado

    public CtrlUsers(UsersDAO dao) {
        this.dao = dao;
    }

    public void loadDataUsers(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<TableModel> order = new TableRowSorter<TableModel>(model);
        table.setRowSorter(order);
        model.setRowCount(0);
        List<Users> users = dao.read();
        for (Users user : users) {
            Object[] row = {user.getId(), user.getId_number(), user.getName(),
                user.getAge(), user.getTelephone(), user.getKey(), user.getRol_id()};
            model.addRow(row);
        }
    }

    public void addUser(JTextField IDNumber, JTextField name, JTextField age, JTextField telephone, JTextField key, JTextField rol_id) {
        try {
            this.dao.create(new Users(IDNumber.getText(), name.getText(), Integer.parseInt(age.getText()), Integer.parseInt(telephone.getText()), Integer.parseInt(key.getText()), Integer.parseInt(rol_id.getText())));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al agregar el usuario: " + e.toString());
        }
    }

    public void updateUser(JTextField IDNumber, JTextField name, JTextField age, JTextField telephone, JTextField key, JTextField rol_id) {
        try {
            this.dao.update(new Users(this.id, IDNumber.getText(), name.getText(), Integer.parseInt(age.getText()), Integer.parseInt(telephone.getText()), Integer.parseInt(key.getText()), Integer.parseInt(rol_id.getText())));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el usuario: " + e.toString());
        }
    }

    public void deleteUser() {
        this.dao.delete(this.id);
    }

    public void selectedRow(JTable table, JTextField IDNumber, JTextField name, JTextField age, JTextField telephone, JTextField key, JTextField rol_id) {
        try {
            int row = table.getSelectedRow();
            if (row >= 0) {
                this.id = Integer.parseInt(table.getValueAt(row, 0).toString());
                IDNumber.setText(table.getValueAt(row, 1).toString());
                name.setText(table.getValueAt(row, 2).toString());
                age.setText(table.getValueAt(row, 3).toString());
                telephone.setText(table.getValueAt(row, 4).toString());
                key.setText(table.getValueAt(row, 5).toString());
                rol_id.setText(table.getValueAt(row, 6).toString());
            } else {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de selección, error: " + e.toString());
        }
    }

    public void clearFields(JTextField IDNumber, JTextField name, JTextField age, JTextField telephone, JTextField key, JTextField rol_id) {
        IDNumber.setText("");
        name.setText("");
        age.setText("");
        telephone.setText("");
        key.setText("");
        rol_id.setText("");
    }
}

