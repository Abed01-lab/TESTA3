package datalayer.booking;

import dto.Booking;
import java.sql.SQLException;
import java.util.List;

public interface BookingStorage {
    public int createBooking(Booking booking) throws SQLException;
    public List<Booking> getBookings() throws SQLException;
    public List<Booking> getBookingsForCustomer(int customerId) throws SQLException;
    public List<Booking> getBookingForEmployee(int employeeId) throws SQLException;

}
