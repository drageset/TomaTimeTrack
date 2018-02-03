package Data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SQLiteJDBCDriverConnectionTest {

    @Test
    void connectTest() {
        assertEquals(true, SQLiteJDBCDriverConnection.getInstance().connect());
    }
}