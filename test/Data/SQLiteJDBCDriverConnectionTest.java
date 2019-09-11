package Data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SQLiteJDBCDriverConnectionTest {

    @Test
    void connectTest() {
        assertTrue(SQLiteJDBCDriverConnection.getInstance().connect());
    }
}