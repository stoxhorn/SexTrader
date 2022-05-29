package Services;

import com.binance.connector.client.impl.SpotClientImpl;

import java.util.Arrays;
import java.util.LinkedHashMap;

public class GraphIOAlpha {

    /*
     CSV from reading binance coloumns, first row is the relevant ones
     Open time, Open, High, Low, Close, Volume, Close time,
     Quote asset volume, Number of trades, Taker buy base asset volume, Taker buy quote asset volume, Ignore
     */

    SpotClientImpl client;
    public GraphIOAlpha(){
        this.client = new SpotClientImpl(
                "VxUM9v6dQECMiwhmBuuBcOH3CDWIHvvXu6ZeszQPgw5VczKthoXE6wCnC24vL6dJ",
                "S6Y2Z0oUM8zQOSqyYm1ghN4v3z0RWMIL51FAyOAZQR7SZAauSKrm9vkPansg1QRI");
    }


    public String readKlines(String timeFrame, String symbol){
        LinkedHashMap<String,Object> parameters = new LinkedHashMap<>();

        parameters.put("symbol", symbol);
        parameters.put("interval", timeFrame);
        String tmp =client.createMarket().klines(parameters);
        return this.csvConverter("klines", tmp);
    }

    public String csvConverter(String coloumnType, String arrayString){
        String[] tmp = arrayString.split("],\\[");
        String csvString = this.getColoumns(coloumnType);
        for(String t : tmp){
            String x = t.replace("[", "");
            csvString += x.replace("]","");
            csvString += "\n";
        }
        return csvString;
    }

    private String getColoumns(String type){
        if(type.equals("klines")){
            return "Open time, Open, High, Low, Close, Volume, Close time, Quote asset volume, Number of trades, Taker buy base asset volume, Taker buy quote asset volume, Ignore\n";
        }
        return "";
    }
}
