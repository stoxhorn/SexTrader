package Services;

import Enums.BarField;
import Enums.TimeFactor;
import com.binance.connector.client.impl.SpotClientImpl;
import org.ta4j.core.*;

import java.sql.Time;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

import static Enums.BarField.CLOSETIME;
import static Enums.BarField.OPENTIME;

public class GraphParser {

    private static SpotClientImpl client;

    public static init(){

    }
    this.client = new SpotClientImpl(
                "VxUM9v6dQECMiwhmBuuBcOH3CDWIHvvXu6ZeszQPgw5VczKthoXE6wCnC24vL6dJ",
                        "S6Y2Z0oUM8zQOSqyYm1ghN4v3z0RWMIL51FAyOAZQR7SZAauSKrm9vkPansg1QRI");
        this.gh = new TickerHandler();

    public static BarSeries createBarSeries(String name, String timeFactor, String klines){
        BaseBarSeriesBuilder barSeries = new BaseBarSeriesBuilder();

        ArrayList<Bar> bars =  createBarsFromCSV(TimeFactor.fromString(timeFactor), klines);


        return  barSeries.withName(name + "," + timeFactor).withBars(bars).build();
    }


    private static ArrayList<Bar> createBarsFromCSV(TimeFactor tf, String csv){
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

        ArrayList<Bar> bars = new ArrayList<>();

        for(String[] row : rows){
            // construct a baseBar
            // 0 Open time, 1 Open, 2 High, 3 Low, 4 Close, 5 Volume, 6 Close time
            HashMap<BarField, String> barData = getBarData(row, coloumns);
            bars.add(createBar(barData));


        }

        return bars;
    }

    /**
     * Created such that i can create bars from different CSV inputs, besides Binance
     * When i get input from more than binance, perhaps create Enum, to handle CSV coloumns from different exchanges
     * @param barData
     * @param coloumns
     * @return
     */
    private static HashMap<BarField, String> getBarData(String[] barData, String[] coloumns){

        HashMap<BarField, String> hm = new HashMap<>();

        int index = 0;
        for(String col : coloumns){
             if(BarField.isBarField(col)){
                 String data = barData[index];
                 data = data.replace("\"", "");
                hm.put( BarField.fromString(col), data);
            }

            index += 1;
        }
        return hm;
    }

    private static Bar createBar(HashMap<BarField, String> row){

        long openTime = Long.parseLong(row.get(BarField.OPENTIME));
        long closeTime = Long.parseLong(row.get(BarField.CLOSETIME));

        Duration timePeriod = Duration.ofSeconds(closeTime-openTime);

        ZonedDateTime endTime = ZonedDateTime.ofInstant(
                Instant.ofEpochSecond(closeTime), ZoneId.of("UTC"));

        double openPrice = Double.parseDouble(row.get(BarField.OPENPRICE));

        double highPrice = Double.parseDouble(row.get(BarField.HIGHPRICE));
        double lowPrice = Double.parseDouble(row.get(BarField.LOWPRICE));
        double closePrice = Double.parseDouble(row.get(BarField.CLOSEPRICE));
        double volume = Double.parseDouble(row.get(BarField.VOLUME));

        return new BaseBar(timePeriod, endTime, openPrice, highPrice, lowPrice, closePrice, volume);
    }

    public static String readKlines(String timeFrame, String symbol){
        LinkedHashMap<String,Object> parameters = new LinkedHashMap<>();

        parameters.put("symbol", symbol);
        parameters.put("interval", timeFrame);
        String tmp =client.createMarket().klines(parameters);
        return this.csvConverter("klines", tmp);
    }

    public static String csvConverter(String coloumnType, String arrayString){
        String[] tmp = arrayString.split("],\\[");
        String csvString = this.getColoumns(coloumnType);
        for(String t : tmp){
            String x = t.replace("[", "");
            csvString += x.replace("]","");
            csvString += x.replace("\"","");
            csvString += "\n";
        }
        return csvString;
    }

    private static String getColoumns(String type){
        if(type.equals("klines")){
            return "Open time, Open, High, Low, Close, Volume, Close time, Quote asset volume, Number of trades, Taker buy base asset volume, Taker buy quote asset volume, Ignore\n";
        }
        return "";
    }
}
