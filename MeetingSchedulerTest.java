package office;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * User: sameer
 * Date: 15/05/2013
 * Time: 15:18
 */
public class MeetingSchedulerTest {

    private String meetingRequest;

    private MeetingScheduler meetingScheduler;

    private DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");
    private DateTimeFormatter timeFormatter = DateTimeFormat.forPattern("HH:mm");

    @org.junit.Before
    public void setUp() throws Exception {
        meetingScheduler = new MeetingScheduler();
    }

	/*
	  @Test 
	  public void shouldParseOfficeHours() { 
		  //given 
		  meetingRequest =
	  
	  "0900 1730\n";
	  
	  System.out.println(" The request we got " + meetingRequest);
	  
	  //when 
	  MeetingsSchedule bookings = meetingScheduler.schedule(meetingRequest);
	 
	  System.out.println(" what are we getting for " );
	  
	  
	  //then 
	  assertEquals(bookings.getOfficeStartTime().getHourOfDay(),9);
	  assertEquals(bookings.getOfficeStartTime().getMinuteOfHour(),0);
	  assertEquals(bookings.getOfficeFinishTime().getHourOfDay(),17);
	  assertEquals(bookings.getOfficeFinishTime().getMinuteOfHour(),30); }


	
	
	  @Test 
	  public void shouldParseMeetingRequest() { //given 
		  meetingRequest =
	  "0900 1730\n" + "2011-03-17 10:17:06 EMP001\n" + "2011-03-21 09:00 2\n";
	  
	  //when 
		  MeetingsSchedule bookings = meetingScheduler.schedule(meetingRequest);
	  
	  //then 
		  LocalDate meetingDate = new LocalDate(2011,3,21);
	  
	  System.out.println("bookings size " +
	  bookings.getMeetings().get(meetingDate).size()); assertEquals(1,
	  bookings.getMeetings().get(meetingDate).size());
	  
	  Meeting meeting = bookings.getMeetings().get(meetingDate).toArray(new
	  Meeting[0])[0];
	  
	  assertEquals("EMP001", meeting.getEmployeeId()); assertEquals(9,
	  meeting.getStartTime().getHourOfDay()); assertEquals(0,
	  meeting.getStartTime().getMinuteOfHour());
	  assertEquals(11,meeting.getFinishTime().getHourOfDay()); assertEquals(0,
	  meeting.getFinishTime().getMinuteOfHour());
	  
	  }
	 
	 

	
	
	  @Test 
	  public void shouldNotBookMeetingRoomOutsideOfficeHours() { //given
	  meetingRequest = "0900 1730\n" + "2011-03-15 17:29:12 EMP005\n" +
	  "2011-03-21 16:00 3\n"; //when 
	  MeetingsSchedule bookings =
	  meetingScheduler.schedule(meetingRequest);
	  
	  //then 
	  assertEquals(0, bookings.getMeetings().size()); }
	 */
	 

	
	
	
	  @Test 
	  public void shouldProcessMeetingsInChronologicalOrderOfSubmission() {
	  //given 
		  meetingRequest = "0900 1730\n" + "2011-03-17 10:17:06 EMP001\n" +
	  "2011-03-21 09:00 2\n" + "2011-03-16 12:34:56 EMP002\n" +
	  "2011-03-21 09:00 2\n";
	  
	  //when 
		  MeetingsSchedule bookings = meetingScheduler.schedule(meetingRequest);
	  
	  //then 
		  LocalDate meetingDate = new LocalDate(2011,3,21);
	  
	  assertEquals(1, bookings.getMeetings().get(meetingDate).size()); Meeting
	  meeting = bookings.getMeetings().get(meetingDate).toArray(new Meeting[0])[0];
	  assertEquals("EMP002", meeting.getEmployeeId()); assertEquals(9,
	  meeting.getStartTime().getHourOfDay()); assertEquals(0,
	  meeting.getStartTime().getMinuteOfHour()); assertEquals(11,
	  meeting.getFinishTime().getHourOfDay()); assertEquals(0,
	  meeting.getFinishTime().getMinuteOfHour()); }
	 
	 
	 

	
	
	
	/*
	  @Test 
	  public void shouldGroupMeetingsChronologically() { //given
	  meetingRequest = "0900 1730\n" + "2011-03-17 10:17:06 EMP004\n" +
	  "2011-03-22 16:00 1\n" + "2011-03-16 09:28:23 EMP003\n" +
	  "2011-03-22 14:00 2\n";
	  
	  //when 
	  MeetingsSchedule bookings = meetingScheduler.schedule(meetingRequest);
	  
	  //then 
	  LocalDate meetingDate = new LocalDate(2011,3,22);
	  
	  assertEquals(1, bookings.getMeetings().size()); assertEquals(2,
	  bookings.getMeetings().get(meetingDate).size()); Meeting[] meetings =
	  bookings.getMeetings().get(meetingDate).toArray(new Meeting[0]);
	  
	  assertEquals("EMP003", meetings[0].getEmployeeId()); assertEquals(14,
	  meetings[0].getStartTime().getHourOfDay()); assertEquals(0,
	  meetings[0].getStartTime().getMinuteOfHour()); assertEquals(16,
	  meetings[0].getFinishTime().getHourOfDay()); assertEquals(0,
	  meetings[0].getFinishTime().getMinuteOfHour());
	  
	  assertEquals("EMP004", meetings[1].getEmployeeId()); assertEquals(16,
	  meetings[1].getStartTime().getHourOfDay()); assertEquals(0,
	  meetings[1].getStartTime().getMinuteOfHour()); assertEquals(17,
	  meetings[1].getFinishTime().getHourOfDay()); assertEquals(0,
	  meetings[1].getFinishTime().getMinuteOfHour()); }
	 */
	 
	 
	 
    
	
	
	  @Test 
	  public void shouldNotHaveOverlappingMeetings() { //given 
		  meetingRequest
	  = "0900 1730\n" + "2011-03-17 10:17:06 EMP001\n" + "2011-03-21 09:00 2\n" +
	  "2011-03-16 12:34:56 EMP002\n" + "2011-03-21 10:00 1\n";
	  
	  //when 
		  MeetingsSchedule bookings = meetingScheduler.schedule(meetingRequest);
	  
	  //then 
		  LocalDate meetingDate = new LocalDate(2011,3,21);
	  
	  assertEquals(1, bookings.getMeetings().size()); assertEquals(1,
	  bookings.getMeetings().get(meetingDate).size()); Meeting[] meetings =
	  bookings.getMeetings().get(meetingDate).toArray(new Meeting[0]);
	  assertEquals("EMP002", meetings[0].getEmployeeId()); assertEquals(10,
	  meetings[0].getStartTime().getHourOfDay()); assertEquals(0,
	  meetings[0].getStartTime().getMinuteOfHour()); assertEquals(11,
	  meetings[0].getFinishTime().getHourOfDay()); assertEquals(0,
	  meetings[0].getFinishTime().getMinuteOfHour()); }
	 
	 

}
