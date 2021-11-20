package office;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * User: sameer
 * Date: 16/05/2013
 * Time: 14:09
 */
public class MeetingSchedulePrinterTest {

    private MeetingSchedulePrinter printer;

    @Before
    public void setUp() throws Exception {

        printer = new MeetingSchedulePrinter(new MeetingScheduler());

    }

    @Test
    public void shouldPrintMeetingSchedule() {
        //given
        String meetingRequest =
                "0900 1730\n" +
                        "2011-03-17 10:17:06 EMP001\n" +
                        "2011-03-21 09:00 2\n" +
                        "2011-03-16 12:34:56 EMP002\n" +
                        "2011-03-21 09:00 2\n" +
                        "2011-03-16 09:28:23 EMP003\n" +
                        "2011-03-22 14:00 2\n" +
                        "2011-03-17 10:17:06 EMP004\n" +
                        "2011-03-22 16:00 1\n" +
                        "2011-03-15 17:29:12 EMP005\n" +
                        "2011-03-21 16:00 3\n";

        //when
        String actualPrint = printer.print(meetingRequest);

        //then
        String expectedPrint = "2011-03-21\n" +
                "09:00 11:00 EMP002\n" +
                "2011-03-22\n" +
                "14:00 16:00 EMP003\n" +
                "16:00 17:00 EMP004\n";

        assertEquals(expectedPrint, actualPrint);

    }
}
