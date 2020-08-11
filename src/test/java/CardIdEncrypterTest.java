import com.alfarosoft.hotelbooking.HotelBookingApp;
import com.alfarosoft.hotelbooking.utils.CardIdEncrypter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = HotelBookingApp.class)
public class CardIdEncrypterTest {

    private static CardIdEncrypter cardIdEncrypter;

    @BeforeAll
    public static void buildEncrypter(){
        cardIdEncrypter = new CardIdEncrypter();
    }

    @Test
    public void testCardIdEncryption(){
        String cardIdWithoutEncryption = "1234123445676788";
        String cardIdEncrypted = cardIdEncrypter.encryptCardNumber(cardIdWithoutEncryption);
        assertNotEquals(cardIdWithoutEncryption, cardIdEncrypted);
        assertTrue(cardIdEncrypted.contains("/"));
        assertTrue(cardIdEncrypted.contains("%"));
    }
}
