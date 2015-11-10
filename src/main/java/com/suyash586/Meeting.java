package com.suyash586;

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.time.LocalDateTime;

/**
 * Created by Suyash on 10-11-2015.
 */
public class Meeting {

    private static int MIN_DURATION = 30;

    private static int MAX_DURATION = 240;

    private Integer duration;

    private String title;

    private LocalDateTime date;

    public void setDuration(Integer duration)
    {
        if (duration < MIN_DURATION || duration > MAX_DURATION) {
            throw new RuntimeException(String.format("Duration of meeting must be %d-%d minutes", MIN_DURATION, MAX_DURATION));
        }
        this.duration = duration;
    }


    public Integer getDuration()
    {
        return this.duration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
