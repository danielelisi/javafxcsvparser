package Application;

import DataModel.DataTransactions;
import Util.AlertBox;
import Util.Transaction;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable {


    // GUI Elements initialization
    @FXML
    private Label statusLabel;
    @FXML
    private Button cityGraphBut;
    @FXML
    private CheckBox checkboxFilter;

    // GUI Table and column initialization
    @FXML
    private TableView<Transaction> itemList;
    @FXML
    private TableColumn<Transaction, String> colStreet;
    @FXML
    private TableColumn<Transaction, String> colCity;
    @FXML
    private TableColumn<Transaction, Integer> colZip;
    @FXML
    private TableColumn<Transaction, String> colState;
    @FXML
    private TableColumn<Transaction, Integer> colBeds;
    @FXML
    private TableColumn<Transaction, Integer> colBaths;
    @FXML
    private TableColumn<Transaction, Integer> colFeet;
    @FXML
    private TableColumn<Transaction, String> colType;
    @FXML
    private TableColumn<Transaction, String> colDate;
    @FXML
    private TableColumn<Transaction, Integer> colPrice;


    // Transactions Object will be added to this element to be displayed in the table
    public ObservableList<Transaction> tableViewData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colStreet.setCellValueFactory(new PropertyValueFactory<>("street"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colZip.setCellValueFactory(new PropertyValueFactory<>("zip"));
        colState.setCellValueFactory(new PropertyValueFactory<>("state"));
        colBeds.setCellValueFactory(new PropertyValueFactory<>("beds"));
        colBaths.setCellValueFactory(new PropertyValueFactory<>("baths"));
        colFeet.setCellValueFactory(new PropertyValueFactory<>("sqFeet"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("saleDate"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        itemList.getColumns().clear();
        itemList.setItems(tableViewData);
        itemList.getColumns().addAll(colStreet,colCity,colZip,colState,colBeds,colBaths,colFeet,colType,colDate,colPrice);
    }

    public void handleOpenFile(ActionEvent event) {

        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV", "*.csv"));

        File selectedFile = fc.showOpenDialog(null);

        if (selectedFile == null) {
            statusLabel.setText("File not selected. Please try again.");
            statusLabel.setUnderline(true);
        } else {

            AlertBox.display("loading", "The file is loading");

            tableViewData.clear();
            BufferedReader br = null;
            ArrayList<Transaction> transactionList;

            try {
                br = new BufferedReader(new FileReader(selectedFile.getAbsolutePath()));
                transactionList = new ArrayList<>();
                String fileLine = "";
                br.readLine(); //read first line to skip header

                while ((fileLine = br.readLine()) != null) {
                    String[] transactionDetails = fileLine.split(",");

                    Transaction item = new Transaction(transactionDetails[0],
                            transactionDetails[1],
                            Integer.parseInt(transactionDetails[2]),
                            transactionDetails[3],
                            Integer.parseInt(transactionDetails[4]),
                            Integer.parseInt(transactionDetails[5]),
                            Integer.parseInt(transactionDetails[6]),
                            transactionDetails[7],
                            transactionDetails[8],
                            Integer.parseInt(transactionDetails[9]),
                            Double.parseDouble(transactionDetails[10]),
                            Double.parseDouble(transactionDetails[11])
                    );

                    // add item to Arraylist element that will be used to set the Singleton
                    transactionList.add(item);

                    //add Transaction item the GUI tableview
                    tableViewData.add(item);
                }

                // IMPORTANT: sets the Singleton so the loaded Transactions can be shared across multiple controllers
                DataTransactions.getTransactionList().setItemList(transactionList);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                statusLabel.setText("Click on the column to sort the data");
                statusLabel.setUnderline(false);
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    public void handleCityGraph(ActionEvent event) {

        Stage window;
        HBox root;


        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("CityGraph.fxml"));
            root = (HBox) loader.load();

            window = new Stage();
            window.setTitle("City Pie Chart");
            Scene scene = new Scene(root);
            window.setScene(scene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}