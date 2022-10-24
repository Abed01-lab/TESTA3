package datalayer.booking;

import dto.Booking;
import java.sql.SQLException;
import java.util.List;

public interface BookingStorage {
    public int createBooking(Booking booking) throws SQLException;
    public List<Booking> getBookings() throws SQLException;
    public Booking getBookingWithId(int id) throws SQLException;
}
