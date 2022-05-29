package Enums;

import java.util.ArrayList;
import java.util.HashMap;

public enum BarField {
    OPENTIME,
    CLOSETIME,
    TIMEPERIOD,
    OPENPRICE,
    HIGHPRICE,
    LOWPRICE,
    CLOSEPRICE,
    VOLUME;

    private static HashMap<String, String> dictionary;


    private static void init(){
        if(dictionary == null){
            dictionary = new HashMap<>();
            fillMap();
        }
        return;
    }

    private static void fillMap(){
        addVariations(  OpenTimeVariations(), "Open Time");
        addVariations(  CloseTimeVariations(), "Close Time");
        addVariations(  TimePeriodVariations(), "Time Period");
        addVariations(  OpenPriceVariations(), "Open Price");
        addVariations(  HighPriceVariations(), "High Price");
        addVariations(  LowPriceVariations(), "Low Price");
        addVariations(  ClosePriceVariations(), "Close Price");
        addVariations(  VolumeVariations(), "Volume");
    }


    private static void addVariations(ArrayList<String> strings, String type){
        for(String str : strings){
            dictionary.put(str, type);
        }
    }

    public static BarField fromString(String str){
        init();
        if(!dictionary.containsKey(str)){
            return null;
        }
        return switch (dictionary.get(str)) {
            case "Open Time" -> OPENTIME;
            case "Close Time" -> CLOSETIME;
            case "Time Period" -> TIMEPERIOD;
            case "Open Price" -> OPENPRICE;
            case "High Price" -> HIGHPRICE;
            case "Low Price" -> LOWPRICE;
            case "Close Price" -> CLOSEPRICE;
            case "Volume" -> VOLUME;
            default -> null;
        };
    }

    public static boolean isBarField(String str){
        BarField bf = fromString(str);
        return bf != null;
    }

    @Override
    public String toString(){
        return switch(this) {
            case OPENTIME-> "Open Time";
            case CLOSETIME-> "Close Time";
            case TIMEPERIOD-> "Time Period";
            case OPENPRICE-> "Open Price";
            case HIGHPRICE-> "High Price";
            case LOWPRICE-> "Low Price";
            case CLOSEPRICE-> "Close Price";
            case VOLUME-> "Volume";
        };
    }

    private static ArrayList<String> OpenTimeVariations(){
        ArrayList<String> openTimeVariations = new ArrayList<>();

        openTimeVariations.add("OpenTime");
        openTimeVariations.add("opentime");
        openTimeVariations.add("Opentime");
        openTimeVariations.add("OPENTIME");

        openTimeVariations.add("Open Time");
        openTimeVariations.add("open time");
        openTimeVariations.add("Open time");
        openTimeVariations.add("OPEN TIME");
        openTimeVariations.add("ot");
        openTimeVariations.add("OT");
        return openTimeVariations;
    }

    private static ArrayList<String> OpenPriceVariations(){
        ArrayList<String> openPrice = new ArrayList<>();

        openPrice.add("OpenPrice");
        openPrice.add("Openprice");
        openPrice.add("openprice");
        openPrice.add("OPENPRICE");

        openPrice.add("Open Price");
        openPrice.add("Open price");
        openPrice.add("open price");
        openPrice.add("OPEN PRICE");
        openPrice.add(" Open");// binance notation

        openPrice.add("OP");
        openPrice.add("op");

        return openPrice;
    }

    private static ArrayList<String> HighPriceVariations(){
        ArrayList<String> highPrice = new ArrayList<>();

        highPrice.add("HighPrice");
        highPrice.add("Highprice");
        highPrice.add("highprice");
        highPrice.add("HIGHPRICE");

        highPrice.add("High Price");
        highPrice.add("High price");
        highPrice.add("high price");
        highPrice.add("HIGH PRICE");
        highPrice.add(" High");


        highPrice.add("HP");
        highPrice.add("hp");

        return highPrice;
    }

    private static ArrayList<String> LowPriceVariations(){

        ArrayList<String> lowPrice = new ArrayList<>();

        lowPrice.add("LowPrice");
        lowPrice.add("Lowprice");
        lowPrice.add("lowprice");
        lowPrice.add("LOWPRICE");

        lowPrice.add("Low Price");
        lowPrice.add("Low price");
        lowPrice.add("low price");
        lowPrice.add("LOW PRICE");
        lowPrice.add(" Low");

        lowPrice.add("LP");
        lowPrice.add("lp");

        return lowPrice;

    }

    private static ArrayList<String> ClosePriceVariations(){

        ArrayList<String> closePrice = new ArrayList<>();

        closePrice.add("ClosePrice");
        closePrice.add("Closeprice");
        closePrice.add("closeprice");
        closePrice.add("CLOSEPRICE");

        closePrice.add("Close Price");
        closePrice.add("Close price");
        closePrice.add("close price");
        closePrice.add("CLOSE PRICE");
        closePrice.add(" Close");// binance

        closePrice.add("CP");
        closePrice.add("cp");

        return closePrice;
    }

    private static ArrayList<String> VolumeVariations(){
        ArrayList<String> volume = new ArrayList<>();

        volume.add("Volume");
        volume.add("volume");
        volume.add("VOLUME");
        volume.add(" Volume"); // binance

        volume.add("v");
        volume.add("V");

        return volume;
    }

    private static ArrayList<String> CloseTimeVariations(){
        ArrayList<String> CloseTimeVariations = new ArrayList<>();

        CloseTimeVariations.add("CloseTime");
        CloseTimeVariations.add("Closetime");
        CloseTimeVariations.add("closetime");
        CloseTimeVariations.add("CLOSETIME");

        CloseTimeVariations.add("Close Time");
        CloseTimeVariations.add("Close time");
        CloseTimeVariations.add("close time");
        CloseTimeVariations.add("CLOSE TIME");
        CloseTimeVariations.add(" Close time"); // binance

        CloseTimeVariations.add("CT");
        CloseTimeVariations.add("ct");

        return CloseTimeVariations;
    }

    private static ArrayList<String> TimePeriodVariations(){
        ArrayList<String> TimePeriodVariations = new ArrayList<>();

        TimePeriodVariations.add("TimePeriod");
        TimePeriodVariations.add("Timeperiod");
        TimePeriodVariations.add("timeperiod");
        TimePeriodVariations.add("TIMEPERIOD");

        TimePeriodVariations.add("Time Period");
        TimePeriodVariations.add("Time period");
        TimePeriodVariations.add("time period");
        TimePeriodVariations.add("TIME PERIOD");

        TimePeriodVariations.add("TP");
        TimePeriodVariations.add("tp");

        return TimePeriodVariations;
    }

}


