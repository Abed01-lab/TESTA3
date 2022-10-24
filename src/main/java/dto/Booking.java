package dto;

import java.sql.Date;
import java.sql.Time;

public class Booking {

    private int id;

    private int employeeId;
    private int customerId;
    private Date date;
    private Time start;
    private Time end;

    public Booking(int id, int employeeId, int customerId, Date date, Time start, Time end) {
        this.id = id;
        this.employeeId = employeeId;
        this.customerId = customerId;
        this.date = date;
        this.start = start;
        this.end = end;
    }

    public Booking(int employeeId, int customerId, Date date, Time start, Time end) {
        this.employeeId = employeeId;
        this.customerId = customerId;
        this.date = date;
        this.start = start;
        this.end = end;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getStart() {
        return start;
    }

    public void setStart(Time start) {
        this.start = start;
    }

    public Time getEnd() {
        return end;
    }

    public void setEnd(Time end) {
        this.end = end;
    }
}
