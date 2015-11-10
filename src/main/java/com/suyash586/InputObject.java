package com.suyash586;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suyash on 08-11-2015.
 */
public class InputObject {

    private List<Attendee> attendees;

    private Meeting meeting;

    private Integer noOfSlotsExpected;

    private TimeSlot timeFrame;

    public List<Attendee> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<Attendee> attendees) {
        this.attendees = attendees;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    public Integer getNoOfSlotsExpected() {
        return noOfSlotsExpected;
    }

    public void setNoOfSlotsExpected(Integer noOfSlotsExpected) {
        this.noOfSlotsExpected = noOfSlotsExpected;
    }

    public TimeSlot getTimeFrame() {
        return timeFrame;
    }

    public void setTimeFrame(TimeSlot timeFrame) {
        this.timeFrame = timeFrame;
    }

    public InputObject() {
        attendees = new ArrayList<Attendee>();
    }

}
