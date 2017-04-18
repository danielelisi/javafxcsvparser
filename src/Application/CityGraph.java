package Application;

import DataModel.DataTransactions;
import Util.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * ACIT 2515 Real Estate CSV Parser
 *
 * @author Daniele Lisi - A00978006 Set A
 * @date 2017-04-17
 */
public class CityGraph implements Initializable {

    HashMap<String, Integer> cityTotal = new HashMap<>();
    int otherVal = 0;

    @FXML
    private PieChart cityChart;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ArrayList<Transaction> chartItem = DataTransactions.getTransactionList().getItemList();

        for (Transaction item : chartItem) {
            String key = item.getCity();

            if (cityTotal.get(key) == null) {
                cityTotal.put(key, 1);
            } else {
                cityTotal.put(key, cityTotal.get(key)+1);
            }
        }

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (Map.Entry<String,Integer> entry : cityTotal.entrySet()) {
            if (entry.getValue() > 20) {
                pieChartData.add(new PieChart.Data((entry.getKey()+ ": "+ entry.getValue()), entry.getValue()));
                System.out.println("Key: " + entry.getKey() + " Total: " + entry.getValue());
            } else {
                System.out.println("Less than 20 Key: " + entry.getKey() + " Total: " + entry.getValue());
            }
        }

        cityChart.setData(pieChartData);
    }
}
