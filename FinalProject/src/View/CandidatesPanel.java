package View;

import Model.*;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class CandidatesPanel extends javax.swing.JPanel {

    private Candidates candidate;
    private votesFrame parent;

    public CandidatesPanel(Candidates candidate, votesFrame parent) {
        initComponents();
        this.candidate = candidate;
        lblName.setText(candidate.getName());
        lblIdNumber.setText(candidate.getId_number());
        lblPoliticParty.setText(candidate.getPoliticParty());

        try {
            lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource(candidate.getImage())));
        } catch (Exception e) {
            try {
                File imageFile = new File(candidate.getImage());

                if (imageFile.exists() && !imageFile.isDirectory()) {
                    ImageIcon imageIcon = new ImageIcon(candidate.getImage());
                    lblImage.setIcon(imageIcon);
                } else {
                    System.out.println("Error al cargar la imagen: " + candidate.getImage());
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        this.parent = parent;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSpinner1 = new javax.swing.JSpinner();
        lblImage = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        lblIdNumber = new javax.swing.JLabel();
        lblPoliticParty = new javax.swing.JLabel();
        btnVotar = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Images/PAC.png"))); // NOI18N

        lblName.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblName.setText("Nombre");

        lblIdNumber.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblIdNumber.setText("Cedula");

        lblPoliticParty.setBackground(new java.awt.Color(0, 0, 0));
        lblPoliticParty.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lblPoliticParty.setText("Partido");

        btnVotar.setText("Votar");
        btnVotar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVotarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPoliticParty, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblName)
                            .addComponent(lblImage)
                            .addComponent(lblIdNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnVotar, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(lblIdNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPoliticParty, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVotar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnVotarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVotarActionPerformed
        if (parent != null) {
            int idVotante = parent.getIdVotante();
            int idCandidato = candidate.getId();
            CandidatesDAO candidateDAO = new CandidatesDAO();
            PeriodsDAO periodsDAO = new PeriodsDAO();
            int periodId = periodsDAO.getIdActivePeriod();
            if (!candidateDAO.haVotadoEnPeriodo(idVotante, periodId)) {
                candidateDAO.registVote(idVotante, idCandidato, periodId);
            } else {
                JOptionPane.showMessageDialog(null, "Ya ha votado en este periodo.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnVotarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVotar;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JLabel lblIdNumber;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblPoliticParty;
    // End of variables declaration//GEN-END:variables
}
