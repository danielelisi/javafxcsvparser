package Application;

import DataModel.DataTransactions;
import Util.AlertBox;
import Util.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

import java.io.*;
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
    private Button saveButton;

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
            } else {
                System.out.println("Less than 20 Key: " + entry.getKey() + " Total: " + entry.getValue());
            }
        }

        for (final PieChart.Data data : pieChartData) {

            System.out.println("Data Value :" +data.getPieValue());
        }

        cityChart.setData(pieChartData);
    }

    public void handleSave(ActionEvent event) throws Exception {
        FileChooser fc = new FileChooser();
        fc.setTitle("Choose location to save the file");
        fc.setInitialFileName("*.txt");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text", "*.txt"));
        File selectFile = fc.showSaveDialog(null);
        if (selectFile == null) {
            AlertBox.display("File invalid", "Please select a valid .csv file.");
        }
        else if (!selectFile.getName().endsWith(".txt")){
            throw new Exception(selectFile.getName() + "has no valid file extension");
        } else {
            PrintWriter saveFile = null;
            try {
                saveFile = new PrintWriter(selectFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            for (Map.Entry<String, Integer> entry : cityTotal.entrySet()) {
                saveFile.println("City: " + entry.getKey() + " Number of Transactions: " + entry.getValue());
            }

            saveFile.close();

            AlertBox.display("File Saved", "Filename: " +selectFile.getName() + " successfully saved.");
        }
    }
}
