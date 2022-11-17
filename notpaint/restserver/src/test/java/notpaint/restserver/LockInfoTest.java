package notpaint.restserver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link LockInfo}.
 */
public class LockInfoTest {
    
    @Test
    public void testLockInfoCompare() {
        Date date1 = new GregorianCalendar(2000, 1, 1).getTime();
        Date date2 = new GregorianCalendar(2000, 1, 2).getTime();
        LockInfo lockInfo1 = new LockInfo(UUID.randomUUID(), date1);
        LockInfo lockInfo2 = new LockInfo(UUID.randomUUID(), date2);

        assertEquals(0, lockInfo1.compareTo(lockInfo1));
        assertTrue(lockInfo1.compareTo(lockInfo2) <  0);
        assertTrue(lockInfo2.compareTo(lockInfo1) >  0);
    }

}
