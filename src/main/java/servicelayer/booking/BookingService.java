package servicelayer.booking;

import dto.Booking;
import dto.BookingCreation;

import java.util.List;

public interface BookingService {
    public int createBooking(BookingCreation bookingCreation) throws BookingServiceException;
    public List<Booking> getBookingsForCustomer(int customerId) throws BookingServiceException;
    public List<Booking> getBookingsForEmployee(int employeeId) throws BookingServiceException;
}
