package Converters;

import Enums.TimeFactor;

import java.time.Duration;

import static Enums.TimeFactor.*;

public class TimePeriods {

    public static TimeFactor timeFactorToString(){

    }

    /**
     * Did not implement one for month, as a month is not a constant length
     * @param timePeriod
     * @return
     */
    public static Duration timeFactorToDuration(TimeFactor timePeriod){

        switch (timePeriod) {
            case m1:
                return Duration.ofMinutes(1L);
            case m3:
                return Duration.ofMinutes(3L);
            case m5:
                return Duration.ofMinutes(5L);
            case m15:
                return Duration.ofMinutes(15L);
            case m30:
                return Duration.ofMinutes(30L);
            case m45:
                return Duration.ofMinutes(45L);
            case h1:
                return Duration.ofHours(1L);
            case h2:
                return Duration.ofHours(2L);
            case h3:
                return Duration.ofHours(3L);
            case h4:
                return Duration.ofHours(4L);
            case d1:
                return Duration.ofDays(1L);
            case w1:
                return Duration.ofDays(7L);
        }
        return null;
    }
}
