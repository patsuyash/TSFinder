package com.suyash586;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suyash on 08-11-2015.
 */
public class Attendee {

    private TimeSlot workingHours;

    private String name;

    public Attendee(TimeSlot workingHours, String name, List<TimeSlot> timeSlotsBooked) {
        this.workingHours = workingHours;
        this.name = name;
        this.timeSlotsBooked = timeSlotsBooked;
    }

    private List<TimeSlot> timeSlotsBooked;

    public Boolean canAttend(LocalDateTime dateTime) {
        if (workingHours.getStartDateTime().isBefore(dateTime) && workingHours.getEndDateTime().isAfter(dateTime)) {

            if (!this.isDatetimeIsBooked(dateTime)) {
                return Boolean.TRUE;
            }
        }

        return Boolean.FALSE;
    }

    private Boolean isDatetimeIsBooked(LocalDateTime dateTime) {
        for (TimeSlot tS : timeSlotsBooked) {
            if (tS.contains(dateTime)) {
                return true;
            }
        }
        return false;
    }


    //TODO
    public List<Duration> getEmptySlots() {

        return new ArrayList<Duration>();
    }

    public TimeSlot getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(TimeSlot workingHours) {
        this.workingHours = workingHours;
    }

    public List<TimeSlot> getTimeSlotsBooked() {
        return timeSlotsBooked;
    }

    public void setTimeSlotsBooked(List<TimeSlot> timeSlotsBooked) {
        this.timeSlotsBooked = timeSlotsBooked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
