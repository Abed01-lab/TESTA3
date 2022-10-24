package dto;

import java.sql.Date;
import java.sql.Time;

public class BookingCreation {
    public final int customerId, employeeId;
    public final Time startTime, endTime;
    public final Date bookingDate;

    public BookingCreation(int customerId, int employeeId, Time startTime, Time endTime, Date bookingDate) {
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.bookingDate = bookingDate;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public Date getBookingDate() {
        return bookingDate;
    }
}
