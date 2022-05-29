package Enums;

import java.time.Duration;

public enum TimeFactor {
    m1,
    m3,
    m5,
    m15,
    m30,
    m45,
    h1,
    h2,
    h3,
    h4,
    d1,
    w1;
    public static TimeFactor fromString(String str){
        return switch (str) {
            case "1m" -> m1;
            case "3m" -> m3;
            case "5m" -> m5;
            case "15m" -> m15;
            case "30m" -> m30;
            case "45m" -> m45;
            case "1h" -> h1;
            case "2h" -> h2;
            case "3h" -> h3;
            case "4h" -> h4;
            case "1d" -> d1;
            case "1w" -> w1;
            default -> null;
        };
    }

    @Override
    public String toString(){
        return switch (this) {
            case m1 -> "1m";
            case m3 -> "3m";
            case m5 -> "5m";
            case m15 -> "15m";
            case m30 -> "30m";
            case m45 -> "45m";
            case h1 -> "1h";
            case h2 -> "2h";
            case h3 -> "3h";
            case h4 -> "4h";
            case d1 -> "1d";
            case w1 -> "1w";
        };
    }

    public Duration toDuration() throws ClassCastException {
        return switch (this){
            case m1 -> Duration.ofMinutes(1L);
            case m3 -> Duration.ofMinutes(3L);
            case m5 -> Duration.ofMinutes(5L);
            case m15 -> Duration.ofMinutes(15L);
            case m30 -> Duration.ofMinutes(30L);
            case m45 -> Duration.ofMinutes(45L);
            case h1 -> Duration.ofHours(1L);
            case h2 -> Duration.ofHours(2L);
            case h3 -> Duration.ofHours(3L);
            case h4 -> Duration.ofHours(4L);
            case d1 -> Duration.ofDays(1L);
            case w1 -> Duration.ofDays(7L);
        };
    }
}
