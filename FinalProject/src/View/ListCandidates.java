
package View;

import Model.*;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JPanel;


public class ListCandidates extends javax.swing.JPanel {
    private List<Candidates> candidate;
    private votesFrame parent;

    public ListCandidates(List<Candidates> candidate, votesFrame parent) {
        initComponents();
        this.candidate = candidate;
        this.parent = parent;
        this.imprintCandidates();
    }

    private void imprintCandidates() {
        JPanel panel;
        setLayout(new GridLayout(0, 3, 8, 8));

        for (int i = 0; i < candidate.size(); i++) {
            Candidates candidates = candidate.get(i);
            panel = new CandidatesPanel(candidates, parent);
            add(panel);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 258, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 280, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
