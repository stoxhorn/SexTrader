package Services;

import org.ta4j.core.*;

import java.time.Duration;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Analyzer {

    BarSeries bs;
    public Analyzer(){

    }

    public void createBarSeries(String name, String timePeriod, String klines){
        BaseBarSeriesBuilder bbsb = new BaseBarSeriesBuilder();
        createBarsFromCSV(timePeriod, klines);

        bbsb.withName(name);
    }


    private ArrayList<Bar> createBarsFromCSV(String timePeriod, String csv){
    /*
     CSV from reading binance coloumns, first row is the relevant ones
     Open time, Open, High, Low, Close, Volume, Close time,
     Quote asset volume, Number of trades, Taker buy base asset volume, Taker buy quote asset volume, Ignore
     */

        ArrayList<String[]> rows = new ArrayList<>();

        for(String l : List.of(  csv.split("\n")  )){
            rows.add(  l.split(",") );
        }



        String[] coloumns = rows.get(0);
        rows.remove(0);

        // 0 Open time, 1 Open, 2 High, 3 Low, 4 Close, 5 Volume, 6 Close time
        System.out.println(Arrays.toString(coloumns));
        System.out.println(Arrays.toString(rows.get(0)));

        System.out.println(rows.get(0)[0]);

        System.out.println(  Duration.ofSeconds(  Long.parseLong(  rows.get(0)[0]  )));


        ArrayList<Bar> bars = new ArrayList<>();

        for(String[] row : rows){

        }

        return null;
    }

    private String[] getBarData(String barData, String coloumns){
        String[] arr = barData.split(",");
        String[] cols = coloumns.split(",");

        HashMap<String, String> hm = new HashMap<>();
        int index = 0;
        for(String col : cols){
            if(col.equals("Open time")){
                hm.put("Open time", arr[index]);}

            else if(col.equals("Open")){
                hm.put("Open","");}
            else if(col.equals("High")){
                hm.put("High","");}
            else if(col.equals("Low")){
                hm.put("Low","");}
            else if(col.equals("Close")){
                hm.put("Close","");}
            else if(col.equals("Volume")){
                hm.put("Volume","");}
            else if(col.equals("Close time")){
                hm.put("Close time","");}

            index += 1;
        }
        return null;
    }

    private Bar createBar(HashMap<String, String> csvLine){
        long openTime = Long.getLong(csvLine.get("Open time"));
        long closeTime = Long.getLong(csvLine.get("Close time"));

        Duration timePeriod = Duration.ofSeconds(closeTime-openTime);

        ZonedDateTime endTime = ZonedDateTime.from(
                Instant.ofEpochSecond(closeTime));

        double openPrice = Double.parseDouble(csvLine.get("Open"));

        double highPrice = Double.parseDouble(csvLine.get("High"));
        double lowPrice = Double.parseDouble(csvLine.get("Low"));
        double closePrice = Double.parseDouble(csvLine.get("Close"));
        double volume = Double.parseDouble(csvLine.get("Volume"));

        return new BaseBar(timePeriod, endTime, openPrice, highPrice, lowPrice, closePrice, volume);
    }
}
