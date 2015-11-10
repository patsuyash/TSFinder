package com.suyash586;

import java.time.LocalDateTime;

/**
 * Created by Suyash on 09-11-2015.
 */
public class TimeSlot {
    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public TimeSlot(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
        this.startDateTime = startDateTime;
    }

    public static TimeSlot createFromDatetime(LocalDateTime startDateTime, Integer interval) {
        LocalDateTime endDateTime = startDateTime.plusMinutes(interval);
        return new TimeSlot(startDateTime, endDateTime);

    }

    public Boolean contains(LocalDateTime dateTime) {
        return (dateTime.isEqual(startDateTime) ||dateTime.isAfter(startDateTime)) && dateTime.isBefore(endDateTime);
    }

    @Override
    public String toString() {
        return String.format("%s - %s", startDateTime.toString(), endDateTime.toString());
    }

}
