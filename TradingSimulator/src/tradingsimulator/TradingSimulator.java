package tradingsimulator;

import javax.swing.JOptionPane;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

public class TradingSimulator {

    public static void main(String[] args) throws Exception {

        LoginWindow lw = new LoginWindow();
        lw.setVisible(true);
        
        //Opens the main window
//        MainWindow mw = new MainWindow();
//        mw.setVisible(true);

        //Dislpays chart window once a share has been selected
        while (true) {
            Thread.sleep(1000);
            if (MainWindow.updateGraph == true) {
                try {
                    showChart();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error, please try again. " + e, "Error", JOptionPane.WARNING_MESSAGE);
                }
                MainWindow.updateGraph = false;
            }
        }

    }

    public static void showChart() throws Exception {

        //Turning the public arraylist into a double array so it can be read by the graph library
        double[] price = new double[MainWindow.price.size()];
        for (int i = 0; i < MainWindow.price.size(); i++) {
            price[i] = MainWindow.price.get(i);
        }

        //Creating an array the same size as the price one, with iterating data        
        double[] time = new double[MainWindow.price.size()];
        for (int i = 0; i < MainWindow.price.size(); i++) {
            time[i] = MainWindow.price.size() - i;
        }
        //Creating and displaying the chart using the public data from the GUI class
        final XYChart chart = QuickChart.getChart("Share Price", "Number of weeks", "Share price", "Share", time, price);
        final SwingWrapper<XYChart> graph = new SwingWrapper<XYChart>(chart);

        graph.displayChart();

//        while (true) {
//            System.out.println("hi");
//            //Turn price arraylist into price array
// 
//
//            Thread.sleep(200);
//            //Updates the data
//            chart.updateXYSeries("Share", time, updatedPrice, null);
//            System.out.println("done");
//            graph.repaintChart();
//        }
    }
}
