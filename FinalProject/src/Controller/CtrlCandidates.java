/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Candidates;
import Model.CandidatesDAO;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Bravo
 */
public class CtrlCandidates {
   CandidatesDAO dao = new CandidatesDAO();
    private int id; // To maintain the ID of the selected candidate

    public void loadDataCandidates(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<TableModel> order = new TableRowSorter<TableModel>(model);
        table.setRowSorter(order);
        model.setRowCount(0);
        List<Candidates> candidates = dao.read();
        for (Candidates candidate : candidates) {
            Object[] row = {candidate.getId(), candidate.getId_number(), candidate.getName(),
                candidate.getPoliticParty(), candidate.getImage()};
            model.addRow(row);
        }
    }

    public void addCandidate(JTextField IDNumber, JTextField name, JTextField politicParty, JTextField imagen) {
        try {
            this.dao.create(new Candidates(IDNumber.getText(), name.getText(), politicParty.getText(), imagen.getText()));
            this.clearFields(IDNumber, name, politicParty, imagen);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al agregar el candidato: " + e.toString());
        }
    }

    public void updateCandidate(JTextField IDNumber, JTextField name, JTextField politicParty, JTextField imagen) {
        try {
            this.dao.update(new Candidates(this.id, IDNumber.getText(), name.getText(), politicParty.getText(), imagen.getText()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el candidato: " + e.toString());
        }
    }

    public void deleteCandidate() {
        this.dao.delete(this.id);
    }

    public void selectedRow(JTable table, JTextField IDNumber, JTextField name, JTextField politicParty, JTextField imagen) {
        try {
            int row = table.getSelectedRow();
            if (row >= 0) {
                this.id = Integer.parseInt(table.getValueAt(row, 0).toString());
                IDNumber.setText(table.getValueAt(row, 1).toString());
                name.setText(table.getValueAt(row, 2).toString());
                politicParty.setText(table.getValueAt(row, 3).toString());
                imagen.setText(table.getValueAt(row, 4).toString());
            } else {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de selección, error: " + e.toString());
        }
    }

    public void clearFields(JTextField IDNumber, JTextField name, JTextField politicParty, JTextField imagen) {
        IDNumber.setText("");
        name.setText("");
        politicParty.setText("");
        imagen.setText("");
    }
}
