package servicelayer.booking;

import datalayer.booking.BookingStorage;
import dto.Booking;
import dto.BookingCreation;

import java.sql.SQLException;
import java.util.List;

public class BookingServiceImpl implements BookingService{

    private BookingStorage bookingStorage;

    public BookingServiceImpl(BookingStorage bookingStorage) {
        this.bookingStorage = bookingStorage;
    }

    @Override
    public int createBooking(BookingCreation bookingCreation) throws BookingServiceException {
        try {
            return bookingStorage.createBooking(new Booking(bookingCreation.employeeId, bookingCreation.customerId, bookingCreation.bookingDate, bookingCreation.startTime, bookingCreation.endTime));
        } catch (SQLException e) {
            throw new BookingServiceException(e.getMessage());
        }
    }

    @Override
    public List<Booking> getBookingsForCustomer(int customerId) throws BookingServiceException {
        try {
            return bookingStorage.getBookingsForCustomer(customerId);
        } catch (SQLException e) {
            throw new BookingServiceException(e.getMessage());
        }
    }

    @Override
    public List<Booking> getBookingsForEmployee(int employeeId) throws BookingServiceException {
        try {
            return bookingStorage.getBookingForEmployee(employeeId);
        } catch (SQLException e) {
            throw new BookingServiceException(e.getMessage());
        }
    }
}
