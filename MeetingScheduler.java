package office;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.*;
import java.util.Map.Entry;

import static java.lang.Integer.parseInt;

/**
 * User: sameer
 * Date: 15/05/2013
 * Time: 15:12
 */
public class MeetingScheduler{

	private DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");
    private DateTimeFormatter separatedTimeFormatter = DateTimeFormat.forPattern("HH:mm");
    
    public MeetingsSchedule test;
    /**
     *
     * @param meetingRequest
     * @return
     */
    public MeetingsSchedule schedule(String meetingRequest) {
        String[] requestLines = meetingRequest.split("\n");

        System.out.println("What does request Lines have " + Arrays.toString(requestLines));
        
        String[] officeHoursTokens = requestLines[0].split(" ");
        System.out.println("What does officeHoursTokens have " + Arrays.toString(officeHoursTokens));

        LocalTime officeStartTime =  new 
        		LocalTime(parseInt(officeHoursTokens[0].substring(0, 2)),
                parseInt(officeHoursTokens[0].substring(2, 4)));
        
        System.out.println("Office start time " + officeStartTime);
        
        LocalTime officeFinishTime =  new 
        		LocalTime(parseInt(officeHoursTokens[1].substring(0, 2)),
                parseInt(officeHoursTokens[1].substring(2, 4)));

        System.out.println("Office finish time " + officeFinishTime);

        Map<LocalDate, Set<Meeting>> meetings = new HashMap<LocalDate, Set<Meeting>>();
        
        String employeeId = "";
        // here we are capturing request submission time and employee id
        for(int i=1;i<requestLines.length;i=i+2){

        	// break into submission dates, hours, employeeID
            String[] meetingSlotRequest = requestLines[i].split(" ");
            
            System.out.println("Index " + i);
            employeeId = meetingSlotRequest[2];
            System.out.println("Request submission " + Arrays.toString(meetingSlotRequest) + " Employee ID "
            		+ employeeId);
            
        	// break it into meeting start time and duration
            String[] meetingSlotRequest1 = requestLines[i+1].split(" ");
            LocalDate meetingDate = dateFormatter.parseLocalDate(meetingSlotRequest1[0]);

            System.out.println("What's in the line? " + Arrays.toString(meetingSlotRequest) );
            System.out.println("What's in the line? " + (meetingSlotRequest1[0]) );

            
            Meeting meeting = extractMeeting(employeeId, requestLines[i], 
            		officeStartTime, officeFinishTime, meetingSlotRequest1);
            
            

            
            if(meetings.containsKey(meetingDate)){           	

            	// if the order overlaps
                for (Map.Entry<LocalDate, Set<Meeting>> meetingEntry : meetings.entrySet()) {
            		if (meetingDate == meetingEntry.getKey())
            		{
            			Set<Meeting> setOfMeeting = meetingEntry.getValue();
            			for (Meeting m : setOfMeeting) {
            				
            			}
            		}
            		
                }
            	
            	// if the order doesn't
            	
                if (meetings.get(meetingDate) != null) 
                	//shouldNotHaveOverlappingMeetings
                {                            	
                	System.out.println("HERES?"); 
                   	
                    meetings.remove(meetingDate);
                    Set<Meeting> meetingsForDay = new HashSet<Meeting>();
                    meetingsForDay.add(meeting);
                    meetings.put(meetingDate, meetingsForDay);
                }  else
                {
                	System.out.println("HERES2?"); 
                	meetings.get(meetingDate).add(meeting);
                }
                
                }else if (meeting != null){
                	// if meeting doens't have meetingDate then create a new HashMap with date & Meeting
            	System.out.println("HERES3?");
                Set<Meeting> meetingsForDay = new HashSet<Meeting>();
                meetingsForDay.add(meeting);
                meetings.put(meetingDate, meetingsForDay);
            }
            
        }
        
        Collection<Set<Meeting>> insideMeeting = meetings.values();
        System.out.println("meetings and their dates " + Arrays.asList(meetings));      
        System.out.println("How many meetings are there for date? " + insideMeeting);  
                
        return new MeetingsSchedule(officeStartTime, officeFinishTime, meetings);
    }
    
    
	private Meeting extractMeeting(String employeeID, String requestLine, LocalTime officeStartTime, 
			LocalTime officeFinishTime, String[] meetingSlotRequest) {
        
        System.out.println("Employee id " + employeeID);
        
        LocalTime meetingStartTime =  separatedTimeFormatter.parseLocalTime(meetingSlotRequest[1]);
        LocalTime meetingFinishTime = new LocalTime(meetingStartTime.getHourOfDay(), meetingStartTime.getMinuteOfHour())
                .plusHours(parseInt(meetingSlotRequest[2]));

        System.out.println("Employee's meeting start time " + meetingStartTime + " end time " + meetingFinishTime);

        
        if(meetingTimeOutsideOfficeHours(officeStartTime, officeFinishTime, meetingStartTime, meetingFinishTime)){
            System.out.println("EmployeeId:"+employeeID+" has requested booking which is outside office hour.");
            return null;
        }else{
            return new Meeting(employeeID, meetingStartTime, meetingFinishTime);

        }
    }

    private boolean meetingTimeOutsideOfficeHours(LocalTime officeStartTime, LocalTime officeFinishTime, LocalTime meetingStartTime, LocalTime meetingFinishTime) {
        return meetingStartTime.isBefore(officeStartTime)
                || meetingStartTime.isAfter(officeFinishTime)
                || meetingFinishTime.isAfter(officeFinishTime)
                || meetingFinishTime.isBefore(officeStartTime);
    }
}
