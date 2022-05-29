import Services.Analyzer;
import Services.GraphIOAlpha;

public class Main {


    public static void main(String Args[]){
        GraphIOAlpha gio = new GraphIOAlpha();
        Analyzer anal = new Analyzer();
        anal.createBarSeries("BTCUSDT", "1m", gio.readKlines("1m", "BTCUSDT"));
    }
}
