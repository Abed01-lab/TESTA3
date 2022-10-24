package integration.datalayer.Booking;

import datalayer.booking.BookingStorage;
import datalayer.booking.BookingStorageImpl;
import datalayer.customer.CustomerStorage;
import datalayer.customer.CustomerStorageImpl;
import datalayer.employee.EmployeeStorage;
import datalayer.employee.EmployeeStorageImpl;
import dto.Booking;
import dto.CustomerCreation;
import dto.Employee;
import dto.EmployeeCreation;
import integration.ContainerizedDbIntegrationTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("integration")
public class CreateBookingTest extends ContainerizedDbIntegrationTest {

    private BookingStorage bookingStorage;
    private EmployeeStorage employeeStorage;
    private CustomerStorage customerStorage;

    @BeforeAll
    public void Setup() {
        runMigration(3);
        bookingStorage = new BookingStorageImpl(getConnectionString(), "root", getDbPassword());
        employeeStorage = new EmployeeStorageImpl(getConnectionString(), "root", getDbPassword());
        customerStorage = new CustomerStorageImpl(getConnectionString(), "root", getDbPassword());
    }

    @Test
    public void createBookingTest() throws SQLException {
        int employeeId = employeeStorage.createEmployee(new EmployeeCreation("Abed", "Hariri"));
        int customerId = customerStorage.createCustomer(new CustomerCreation("John", "Doe", Date.valueOf("1997-07-14")));
        bookingStorage.createBooking(new Booking(employeeId, customerId, Date.valueOf("2022-03-24"), Time.valueOf("14:00:00"), Time.valueOf("15:00:00")));

        var bookings = bookingStorage.getBookings();
        assertTrue(bookings.stream().anyMatch(x -> x.getCustomerId() == customerId &&
                x.getEmployeeId() == employeeId &&
                x.getDate().equals(Date.valueOf("2022-03-24")) &&
                x.getStart().equals(Time.valueOf("14:00:00")) &&
                x.getEnd().equals(Time.valueOf("15:00:00"))));
    }

    @Test
    public void getBookingForCustomerWithId() throws SQLException {
        int employeeId = employeeStorage.createEmployee(new EmployeeCreation("Abed", "Hariri"));
        int customerId = customerStorage.createCustomer(new CustomerCreation("John", "Doe", Date.valueOf("1997-07-14")));
        int bookingId = bookingStorage.createBooking(new Booking(employeeId, customerId, Date.valueOf("2022-03-24"), Time.valueOf("14:00:00"), Time.valueOf("15:00:00")));

        var b = bookingStorage.getBookingsForCustomer(bookingId);

        assertTrue(b.stream().allMatch(x -> x.getCustomerId() == customerId));
    }
}
