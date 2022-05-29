package Entities;

import Enums.TimeFactor;
import Services.GraphParser;
import org.ta4j.core.BarSeries;

public class Graph {

    BarSeries candles;

    String tickerName;

    TimeFactor tf;

    public Graph(String tickerName, TimeFactor tf){
        this.tickerName = tickerName;
        this.tf = tf;
    }

    public void createGraph(){
        GraphParser.createBarSeries(this.tickerName, this.tf.toString(), gio.readKlines("1m", "BTCUSDT"))
    }

}
