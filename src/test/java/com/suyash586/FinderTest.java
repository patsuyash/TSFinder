package com.suyash586;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FinderTest {
    private static final LocalDateTime NOV_20_1_30_AM = LocalDateTime.of(2015, 11, 20, 1, 30);

    private static final LocalDateTime NOV_20_3_30_AM = LocalDateTime.of(2015, 11, 20, 3, 30);

    private static final LocalDateTime NOV_20_4_30_AM = LocalDateTime.of(2015, 11, 20, 4, 30);

    private static final LocalDateTime NOV_20_6_00_AM = LocalDateTime.of(2015, 11, 20, 6, 00);

    private static final LocalDateTime NOV_20_7_30_AM = LocalDateTime.of(2015, 11, 20, 7, 30);

    private static final LocalDateTime NOV_20_9_30_AM = LocalDateTime.of(2015, 11, 20, 9, 30);

    private static final LocalDateTime NOV_20_11_30_AM = LocalDateTime.of(2015, 11, 20, 11, 30);

    private static final LocalDateTime NOV_20_12_30_AM = LocalDateTime.of(2015, 11, 20, 12, 30);

    private static final LocalDateTime NOV_20_13_00_AM = LocalDateTime.of(2015, 11, 20, 13, 00);

    private static final LocalDateTime NOV_20_2_00_PM = LocalDateTime.of(2015, 11, 20, 14, 00);

    private static final LocalDateTime NOV_20_4_00_PM = LocalDateTime.of(2015, 11, 20, 16, 00);

    private static final LocalDateTime NOV_20_6_30_PM = LocalDateTime.of(2015, 11, 20, 18, 30);

    private static final LocalDateTime NOV_20_8_00_PM = LocalDateTime.of(2015, 11, 20, 20, 00);

    private static final LocalDateTime NOV_20_9_30_PM = LocalDateTime.of(2015, 11, 20, 21, 30);

    private static final LocalDateTime NOV_20_20_00_PM = LocalDateTime.of(2015, 11, 20, 23, 00);


    private static final LocalDateTime NOV_21_00_30_PM = LocalDateTime.of(2015, 11, 21, 00, 30);

    @org.junit.Test
    public void testFindWithoutBooked() throws Exception {

        Meeting meeting = new Meeting();
        meeting.setDate(NOV_20_4_00_PM);
        meeting.setTitle("Budget Meet");
        meeting.setDuration(30);//30 min
        List<Attendee> attendees = new ArrayList<Attendee>();
        List<TimeSlot> timeSlotsBooked = new ArrayList<TimeSlot>();
        attendees.add(new Attendee(new TimeSlot(NOV_20_11_30_AM, NOV_20_9_30_PM),"suyash",timeSlotsBooked));
        attendees.add(new Attendee(new TimeSlot(NOV_20_1_30_AM, NOV_20_8_00_PM),"suyash",timeSlotsBooked));
        attendees.add(new Attendee(new TimeSlot(NOV_20_4_30_AM, NOV_20_9_30_PM),"suyash",timeSlotsBooked));
        InputObject obj = new InputObject();
        obj.setMeeting(meeting);
        obj.setAttendees(attendees);
        obj.setNoOfSlotsExpected(3);
        obj.setTimeFrame(new TimeSlot(NOV_20_1_30_AM, NOV_21_00_30_PM));

        Finder finder = new Finder(obj);

        finder.find();
        Assert.assertNotNull(finder.getFoundTimeSlots());
        System.out.println(finder.getFoundTimeSlots());
    }

    @org.junit.Test
    public void testFindWithBooked() throws Exception {

        Meeting meeting = new Meeting();
        meeting.setDate(NOV_20_4_00_PM);
        meeting.setTitle("Budget Meet");
        meeting.setDuration(30);//30 min
        List<Attendee> attendees = new ArrayList<Attendee>();
        List<TimeSlot> timeSlotsBooked = new ArrayList<TimeSlot>();
        timeSlotsBooked.add(new TimeSlot(NOV_20_12_30_AM,NOV_20_13_00_AM));
        List<TimeSlot> emptyTimeSlotsBooked = new ArrayList<TimeSlot>();
        attendees.add(new Attendee(new TimeSlot(NOV_20_11_30_AM, NOV_20_9_30_PM),"suyash",timeSlotsBooked));
        attendees.add(new Attendee(new TimeSlot(NOV_20_1_30_AM, NOV_20_8_00_PM),"suyash",emptyTimeSlotsBooked));
        attendees.add(new Attendee(new TimeSlot(NOV_20_4_30_AM, NOV_20_9_30_PM),"suyash",emptyTimeSlotsBooked));
        InputObject obj = new InputObject();
        obj.setMeeting(meeting);
        obj.setAttendees(attendees);
        obj.setNoOfSlotsExpected(3);
        obj.setTimeFrame(new TimeSlot(NOV_20_1_30_AM, NOV_21_00_30_PM));

        Finder finder = new Finder(obj);

        finder.find();

        Assert.assertNotNull(finder.getFoundTimeSlots());
        System.out.println(finder.getFoundTimeSlots());
    }

    @Test(expected=RuntimeException.class)
    public void testFindNoResult() {
        Meeting meeting = new Meeting();
        meeting.setDate(NOV_20_4_00_PM);
        meeting.setTitle("Budget Meet");
        meeting.setDuration(30);//30 min
        List<Attendee> attendees = new ArrayList<Attendee>();

        List<TimeSlot> emptyTimeSlotsBooked = new ArrayList<TimeSlot>();
        attendees.add(new Attendee(new TimeSlot(NOV_20_11_30_AM, NOV_20_9_30_PM),"suyash",emptyTimeSlotsBooked));
        attendees.add(new Attendee(new TimeSlot(NOV_20_1_30_AM, NOV_20_8_00_PM),"suyash",emptyTimeSlotsBooked));
        attendees.add(new Attendee(new TimeSlot(NOV_20_4_30_AM, NOV_20_7_30_AM),"suyash",emptyTimeSlotsBooked));
        InputObject obj = new InputObject();
        obj.setMeeting(meeting);
        obj.setAttendees(attendees);
        obj.setNoOfSlotsExpected(3);
        obj.setTimeFrame(new TimeSlot(NOV_20_1_30_AM, NOV_21_00_30_PM));

        Finder finder = new Finder(obj);

        finder.find();

        Assert.assertNotNull(finder.getFoundTimeSlots());
        System.out.println(finder.getFoundTimeSlots());
    }
}