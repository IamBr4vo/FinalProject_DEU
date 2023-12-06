/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.CandidateVotes;
import Model.Candidates;
import Model.CandidatesDAO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;

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
        // check if the ID already exists in the database
        if (Validation.verificateIdNumberExistingForCandidates(IDNumber.getText())) {
            JOptionPane.showMessageDialog(null, "La cédula ya existe en la base de datos.");
        } else {
            try {
                // check the format of the ID and the name
                if (!Validation.validateIdNumber(IDNumber.getText()) || !Validation.validateLyrics(name.getText()) || !Validation.validateLyrics(politicParty.getText())) {
                    JOptionPane.showMessageDialog(null, "Error en la cedula, nombre o partido politico, por favor revise y digite un formato válido.");
                } else {
                    this.dao.create(new Candidates(IDNumber.getText(), name.getText(), politicParty.getText(), imagen.getText()));
                    this.clearFields(IDNumber, name, politicParty, imagen);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al agregar el candidato: " + e.toString());
            }
        }
    }

    public void updateCandidate(JTextField IDNumber, JTextField name, JTextField politicParty, JTextField imagen) {
        try {
            // check the format of the ID and the name
            if (!Validation.validateIdNumber(IDNumber.getText()) || !Validation.validateLyrics(name.getText()) || !Validation.validateLyrics(politicParty.getText())) {
                JOptionPane.showMessageDialog(null, "Error en la cedula, nombre o partido politico, por favor revise y digite un formato válido.");
            } else {
                this.dao.update(new Candidates(this.id, IDNumber.getText(), name.getText(), politicParty.getText(), imagen.getText()));
            }
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

    public void loadGraficCandidates(JPanel jPanel) {
        jPanel.removeAll();
        jPanel.repaint();

        CandidatesDAO candidateDAO = new CandidatesDAO();
        List<CandidateVotes> candidateVotesList = candidateDAO.getVotesCountPerCandidate();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (CandidateVotes candidateVotes : candidateVotesList) {
            String candidateName = candidateVotes.getCandidateName();
            String politicParty = candidateVotes.getPoliticParty();
            int votesCount = candidateVotes.getVotesCount();
            dataset.addValue(votesCount, politicParty, candidateName);
        }

        JFreeChart grafic = ChartFactory.createBarChart3D(
                "Cantidad De Votos de los Candidatos",
                "Votaciones 2026",
                "Votos",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        CategoryPlot plot = grafic.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setBarPainter(new StandardBarPainter());
        renderer.setDrawBarOutline(true);
        Paint[] colors = {Color.CYAN, Color.MAGENTA, Color.YELLOW, Color.GREEN, Color.RED, Color.BLUE};
        for (int i = 0; i < colors.length; i++) {
            renderer.setSeriesPaint(i, colors[i]);
        }

        ChartPanel chartPanel = new ChartPanel(grafic);
        chartPanel.setMouseWheelEnabled(true);
        chartPanel.setPreferredSize(new Dimension(440, 738));

        jPanel.setLayout(new BorderLayout());
        jPanel.add(chartPanel, BorderLayout.CENTER);
        jPanel.repaint();
    }
}
