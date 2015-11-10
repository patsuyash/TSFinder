package com.suyash586;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suyash on 10-11-2015.
 */
public class Finder {

    private InputObject data;

    public List<TimeSlot> getFoundTimeSlots() {
        return foundTimeSlots;
    }

    private List<TimeSlot> foundTimeSlots;

    public Finder(InputObject data) {
        this.data = data;
    }

    public void find() {
        if (data == null) {
            throw new RuntimeException("Empty data");
        }
        int interval = data.getMeeting().getDuration();

        List<LocalDateTime> period = this.getPeriod(interval);
        // finding
        foundTimeSlots = new ArrayList<TimeSlot>();

        int i = 0;


        for (LocalDateTime dateTime : period) {
            List<Attendee> canAttend = new ArrayList<Attendee>();
            for (Attendee attendee : this.data.getAttendees()) {
                if (attendee.canAttend(dateTime)) {
                    canAttend.add(attendee);
                }
            }
            if (canAttend.size() == this.data.getAttendees().size()) {

                foundTimeSlots.add(TimeSlot.createFromDatetime(dateTime, interval));
                i++;
                if (i >= this.data.getNoOfSlotsExpected()) {
                    break;
                }
            }
        }

        if(getFoundTimeSlots().isEmpty()) {
            throw new RuntimeException("Sorry could not find any slot empty for you, please try with passing lesser Attendees ");
        }

    }

    private List<LocalDateTime> getPeriod(int interval) {
        List<LocalDateTime> result = new ArrayList<LocalDateTime>();
        LocalDateTime startDateForPeriod = this.calculateStartDateForPeriod();
        LocalDateTime endDateForPeriod = this.calculateEndDateForPeriod();
        result.add(startDateForPeriod);
        LocalDateTime cDate=startDateForPeriod.plusMinutes(interval);
        do {

            result.add(cDate);
            cDate = cDate.plusMinutes(interval);
        }
        while (cDate.isBefore(endDateForPeriod));
        return result;
    }



    private LocalDateTime calculateStartDateForPeriod() {
        List<Attendee> attendees = this.data.getAttendees();
        if (attendees.size() == 1) {
            return attendees.get(0).getWorkingHours().getStartDateTime();
        }
        LocalDateTime result = attendees.get(0).getWorkingHours().getStartDateTime();
        for (Attendee at : attendees) {
            LocalDateTime dt = at.getWorkingHours().getStartDateTime();
            if (dt.isAfter(result)) {
                result = dt;
            }
        }
        return result;
    }

    private LocalDateTime calculateEndDateForPeriod() {
        List<Attendee> attendees = this.data.getAttendees();
        if (attendees.size() == 1) {
            return attendees.get(0).getWorkingHours().getEndDateTime();
        }
        LocalDateTime result = attendees.get(0).getWorkingHours().getEndDateTime();
        for (Attendee at : attendees) {
            LocalDateTime dt = at.getWorkingHours().getEndDateTime();
            if (dt.isBefore(result)) {
                result = dt;
            }
        }
        return result;
    }

}
