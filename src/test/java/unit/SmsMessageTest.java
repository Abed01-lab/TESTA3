package unit;

import dto.SmsMessage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("unit")
public class SmsMessageTest {

    private SmsMessage smsMessage;

    @BeforeAll
    public void setUp() {
        smsMessage = mock(SmsMessage.class);
    }

    @Test
    public void sendSmsParameters(){
        var phone = 12345678;
        var message = "You have been booked to a meeting!";
        smsMessage.sendSMS(phone, message);

        verify(smsMessage).sendSMS(phone, message);
    }

    @Test
    public void phoneNumberLessThanEightDigit () {
        var phone = 1234567;
        var message = "Hello";

        var sms = smsMessage.sendSMS(phone, message);

        assertTrue(!sms);
    }

    @Test
    public void phoneNumberLargerThanEightDigit () {
        var phone = 123456789;
        var message = "Hello";

        var sms = smsMessage.sendSMS(phone, message);

        assertTrue(!sms);
    }

    @Test
    public void messageIsEmpty () {
        var phone = 12345678;
        var message = "";

        var sms = smsMessage.sendSMS(phone, message);
        assertTrue(!sms);
    }
}
