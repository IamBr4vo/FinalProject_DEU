/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author Eithel
 */

public class Periods {
    private int id;
    private Date start_date;
    private Date finish_date;
    private String status;

    public Periods() {
        
    }

    public Periods(int id, Date start_date, Date finish_date, String status) {
        this.id = id;
        this.start_date = start_date;
        this.finish_date = finish_date;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getFinish_date() {
        return finish_date;
    }

    public void setFinish_date(Date finish_date) {
        this.finish_date = finish_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Periods(Date start_date, Date finish_date, String status) {
        this.start_date = start_date;
        this.finish_date = finish_date;
        this.status = status;
    }
    
}
    
