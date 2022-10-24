package unit.servicelayer.booking;

import datalayer.booking.BookingStorage;
import datalayer.booking.BookingStorageImpl;
import dto.BookingCreation;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import servicelayer.booking.BookingService;
import servicelayer.booking.BookingServiceException;
import servicelayer.booking.BookingServiceImpl;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("unit")
public class CreateBookingTest {

    private BookingService bookingService;
    private BookingStorage storage;

    @BeforeAll
    public void mustCallStorageWhenCreatingBooking(){
        storage = mock(BookingStorage.class);
        bookingService = new BookingServiceImpl(storage);
    }

    @Test
    public void mustCallBookingStorageWhenCreatingBooking() throws BookingServiceException, SQLException {
        var employeeId = 1;
        var customerId = 2;
        var startTime = Time.valueOf("14:00:00");
        var endTime = Time.valueOf("15:00:00");
        var bookingDate = Date.valueOf("2022-10-24");

        bookingService.createBooking(new BookingCreation(customerId, employeeId, startTime, endTime, bookingDate));

        verify(storage).createBooking(argThat( x ->
                        x.getEmployeeId() == 1 &&
                        x.getCustomerId() == 2 &&
                        x.getStart().equals(startTime) &&
                        x.getEnd().equals(endTime) &&
                        x.getDate().equals(bookingDate)));

    }

    @Test
    public void getBookingsForCustomer() throws BookingServiceException, SQLException {
        int id = 1;

        bookingService.getBookingsForCustomer(id);

        verify(storage).getBookingsForCustomer(id);
    }

    @Test
    public void getBookingsForEmployee() throws BookingServiceException, SQLException {
        int id = 23;

        bookingService.getBookingsForEmployee(id);

        verify(storage).getBookingForEmployee(id);
    }

}
