package tradingsimulator;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

public class TradingSimulator {

    public static void main(String[] args) throws Exception {
        
        //Opens the login window
        MainWindow mw = new MainWindow();
        mw.setVisible(true);

        //Dislpays chart window
        showChart();

    }

    public static void showChart() throws Exception {
        
        //Creating and displaying the chart using the public data from the GUI class
        final XYChart chart = QuickChart.getChart("Share Price", "Time", "Price", "Share", MainWindow.time, MainWindow.price);     
        final SwingWrapper<XYChart> graph = new SwingWrapper<XYChart>(chart);
        graph.displayChart();

        //Infinitely refreshes the chart so changes to data are recognised and updated in real time
        while (true) {
            Thread.sleep(200);
            //Updates the data
            chart.updateXYSeries("Share", MainWindow.time, MainWindow.price, null);
            graph.repaintChart();
        }
    }

}
