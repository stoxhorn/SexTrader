package Enums;

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

    @Override
    public String toString(){
        switch (this) {
            case m1:
                return "1m";
            case m3:
                return "3m";
            case m5:
                return "5m";
            case m15:
                return "15m";
            case m30:
                return "30m";
            case m45:
                return "45m";
            case h1:
                return "1h";
            case h2:
                return "2h";
            case h3:
                return "3h";
            case h4:
                return "4h";
            case d1:
                return "1d";
            case w1:
                return "1w";
        }
        return null;
    }
}
