/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Bravo
 */
public class CandidateVotes {
    private int candidateId;
    private String candidateName;
    private String politicParty;
    private int votesCount;

    public CandidateVotes(int candidateId, String candidateName, int votesCount, String politicParty) {
        this.candidateId = candidateId;
        this.candidateName = candidateName;
        this.votesCount = votesCount;
        this.politicParty = politicParty;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public void setVotesCount(int votesCount) {
        this.votesCount = votesCount;
    }

    public int getCandidateId() {
        return candidateId;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public int getVotesCount() {
        return votesCount;
    }

    public String getPoliticParty() {
        return politicParty;
    }

    public void setPoliticParty(String politicParty) {
        this.politicParty = politicParty;
    }
    
}
