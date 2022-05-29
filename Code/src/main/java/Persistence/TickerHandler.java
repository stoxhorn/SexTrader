package Persistence;


import Entities.Ticker;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Goal is that this class handles the containment of graphs and their data
 * It's goal is to exist in GraphIO, as the only object, a singleton
 */
public class TickerHandler {

    private static TickerHandler singleton;

    private HashMap<String, Ticker> tickers;
    private ArrayList<String> tickerNames;

    public TickerHandler(){
        if(singleton != null){
            return;
        }
        else{
            init();
            singleton = this;
        }
    }

    private void init(){
        this.tickers = new HashMap<>();
        this.tickerNames = new ArrayList<>();
    }

    public void addTicker(String newTicker){
        this.tickerNames.add("newTicker");
        initTicker(newTicker);
    }

    private void initTicker(String newTicker){
        this.tickers.put(newTicker, new Ticker(newTicker));
        this.tickers.get(newTicker).start();
    }

}
