import com.alfarosoft.hotelbooking.HotelBookingApp;
import com.alfarosoft.hotelbooking.service.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(classes = HotelBookingApp.class)
@AutoConfigureMockMvc
public class HotelBookingControllerTest {
    @MockBean
    private BookingService bookingService;

    @MockBean
    private RoomService roomService;

    @MockBean
    private CardEncrypterService cardEncrypterService;

    @MockBean
    private CustomerService customerService;

    @MockBean
    private PaymentAccountService paymentAccountService;

    @Autowired
    private MockMvc mvc;
}
