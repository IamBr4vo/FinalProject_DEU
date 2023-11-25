/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Periods;
import Model.PeriodsDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class CtrlPeriods {

    PeriodsDAO dao = new PeriodsDAO();
    private int id; // To maintain the ID of the selected period
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


    public void loadDataPeriods(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<TableModel> order = new TableRowSorter<TableModel>(model);
        table.setRowSorter(order);
        model.setRowCount(0);
        List<Periods> periods = dao.read();
        for (Periods period : periods) {
            Object[] row = {period.getId(), period.getStart_date(), period.getFinish_date(), period.getStatus()};
            model.addRow(row);
        }
    }

    public void addPeriod(JTextField startDate, JTextField finishDate, JTextField status) {
        try {
            Date start_date = dateFormat.parse(startDate.getText());
            Date finish_date = dateFormat.parse(finishDate.getText());
            this.dao.create(new Periods(start_date, finish_date, status.getText()));
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Error de formato, el indicado es año-mes-día");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al agregar el período: " + e.toString());
        }
    }

    public void updatePeriod(JTextField startDate, JTextField finishDate, JTextField status) {
        try {
            Date start_date = dateFormat.parse(startDate.getText());
            Date finish_date = dateFormat.parse(finishDate.getText());
            this.dao.update(new Periods(this.id, start_date, finish_date, status.getText()));
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Error de formato, el indicado es año-mes-día");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el período: " + e.toString());
        }
    }

    public void deletePeriod() {
        this.dao.delete(this.id);
    }

    public void selectedRow(JTable table, JTextField startDate, JTextField finishDate, JTextField status) {
        try {
            int row = table.getSelectedRow();
            if (row >= 0) {
                this.id = Integer.parseInt(table.getValueAt(row, 0).toString());
                startDate.setText(table.getValueAt(row, 1).toString());
                finishDate.setText(table.getValueAt(row, 2).toString());
                status.setText(table.getValueAt(row, 3).toString());
            } else {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de selección, error: " + e.toString());
        }
    }

    public void clearFields(JTextField startDate, JTextField finishDate, JTextField status) {
        startDate.setText("");
        finishDate.setText("");
        status.setText("");
    }
    public Date[] getActivePeriodDates() {
        PeriodsDAO periodsDAO = new PeriodsDAO();
        return periodsDAO.getActivePeriodDates();
    }
    
    public Date[] getActivePeriods(JTextField fechaLimite){
        // Get active dates directly in the constructor
        Date[] activePeriodDates = getActivePeriodDates();

        // Check if there are active dates and show them in the txt
        if (activePeriodDates != null && activePeriodDates.length == 2) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            //Check if dates are not null before formatting them
            if (activePeriodDates[0] != null && activePeriodDates[1] != null) {
                String startDateStr = dateFormat.format(activePeriodDates[0]);
                String finishDateStr = dateFormat.format(activePeriodDates[1]);
                fechaLimite.setText("Inicio: " + startDateStr + ", Fin: " + finishDateStr);
            } else {
                fechaLimite.setText("No hay período activo");
            }
        } else {
            fechaLimite.setText("No hay período activo");
        }
        return activePeriodDates;
    }
}

