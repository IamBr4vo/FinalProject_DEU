/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Bravo
 */
public class CandidatesDAO {

    // Method to create a new candidate
    public void create(Candidates candidate) {
        DBConnection db = new DBConnection();
        String consultaSQL = "INSERT INTO candidates (id_number, name, politic_party, image) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
            ps.setString(1, candidate.getId_number());
            ps.setString(2, candidate.getName());
            ps.setString(3, candidate.getPoliticParty());
            ps.setString(4, candidate.getImage());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Se insertó correctamente el candidato");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo insertar el candidato, error: " + e.toString());
        } finally {
            db.disconnect();
        }
    }

    // Method to read and retrieve a list of candidates
    public List<Candidates> read() {
        DBConnection db = new DBConnection();
        List<Candidates> candidatesList = new ArrayList<>();
        String sql = "SELECT * FROM candidates";

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String id_number = resultSet.getString("id_number");
                String name = resultSet.getString("name");
                String politicParty = resultSet.getString("politic_party");
                String image = resultSet.getString("image");
                candidatesList.add(new Candidates(id, id_number, name, politicParty, image)); // Create the new candidate object
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return candidatesList;
    }

    // Method to update an existing candidate record
    public void update(Candidates candidate) {
        DBConnection db = new DBConnection();
        String consultaSQL = "UPDATE candidates SET id_number=?, name=?, politic_party=?, image=? WHERE id=?";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
            ps.setString(1, candidate.getId_number());
            ps.setString(2, candidate.getName());
            ps.setString(3, candidate.getPoliticParty());
            ps.setString(4, candidate.getImage());
            ps.setInt(5, candidate.getId());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Modificación Exitosa");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo modificar el candidato, error: " + e.toString());
        } finally {
            db.disconnect();
        }
    }

    // Method to delete a candidate record by ID
    public void delete(int id) {
        DBConnection db = new DBConnection();
        String consultaSQL = "DELETE FROM candidates WHERE id=?";
        try {
            PreparedStatement preparedStatement = db.getConnection().prepareStatement(consultaSQL);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            JOptionPane.showMessageDialog(null, "Se eliminó correctamente el candidato");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar el candidato, error: " + e.toString());
        } finally {
            db.disconnect();
        }
    }

    public String getNameCandidate(int id) {
        String name = "";
        DBConnection db = new DBConnection();
        String sql = "SELECT name FROM candidates WHERE id = ?";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                name = resultSet.getString("name");
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return name;
    }

    public void registVote(int idVotante, int idCandidato, int periodId) {
        DBConnection db = new DBConnection();
        String query = "INSERT INTO results (voter_id, candidate_id, period_id) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(query);
            ps.setInt(1, idVotante);
            ps.setInt(2, idCandidato);
            ps.setInt(3, periodId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean haVotadoEnPeriodo(int idVoter, int periodId) {
        DBConnection db = new DBConnection();
        boolean hasVoted = false;

        String checkVoteSQL = "SELECT COUNT(*) AS count FROM results WHERE voter_id = ? AND period_id = ?";

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(checkVoteSQL);
            ps.setInt(1, idVoter);
            ps.setInt(2, periodId);

            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                hasVoted = (count > 0);
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            db.disconnect();
        }

        return hasVoted;
    }

    public List<CandidateVotes> getVotesCountPerCandidate() {
        List<CandidateVotes> candidateVotesList = new ArrayList<>();

        String query = "SELECT c.id, c.name AS candidateName, c.politic_party AS politicParty, COUNT(r.id) AS votesCount FROM candidates c LEFT JOIN results r ON c.id = r.candidate_id GROUP BY c.id, c.name, c.politic_party";
        DBConnection db = new DBConnection();

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int candidateId = resultSet.getInt("id");
                String candidateName = resultSet.getString("candidateName");
                String politicParty = resultSet.getString("politicParty");
                int votesCount = resultSet.getInt("votesCount");
                CandidateVotes candidateVotes = new CandidateVotes(candidateId, candidateName, votesCount, politicParty);
                candidateVotesList.add(candidateVotes);
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            db.disconnect();
        }

        return candidateVotesList;
    }
}
