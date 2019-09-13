import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class SoundTest {

    @Test
    void connectTest() {
        try {

            Toolkit.getDefaultToolkit().beep();
            TimeUnit.SECONDS.sleep(1);

            Toolkit.getDefaultToolkit().beep();
            TimeUnit.SECONDS.sleep(1);

            Toolkit.getDefaultToolkit().beep();
            TimeUnit.SECONDS.sleep(1);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}