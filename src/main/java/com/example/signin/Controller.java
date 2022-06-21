package com.example.signin;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;
import org.apache.commons.lang3.StringUtils;


public class Controller implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private static Connection conn;

    @FXML TableView<Customer> cust_display;
    @FXML TableView<Car> auto_display;
    @FXML TableView<Home> home_display;
    @FXML TableView<Life> life_display;

    @FXML Label cust_id;
    @FXML Label auto_id;
    @FXML Label home_id;
    @FXML Label life_id;

    @FXML TextField cust_name;
    @FXML TextField cust_age;
    @FXML TextField cust_search;
    @FXML TextField auto_search1;
    @FXML TextField auto_search2;
    @FXML TextField home_search1;
    @FXML TextField home_search2;
    @FXML TextField life_search1;
    @FXML TextField life_search2;

    @FXML TextField auto_make;
    @FXML TextField auto_model;
    @FXML TextField auto_year;
    @FXML TextField auto_vin;
    @FXML TextField auto_mile;
    @FXML TextField auto_cov;
    @FXML TextField auto_monthly;
    @FXML TextField auto_cust_id;

    @FXML TextField home_add;
    @FXML TextField home_area;
    @FXML TextField home_bed;
    @FXML TextField home_bath;
    @FXML TextField home_price;
    @FXML TextField home_cov;
    @FXML TextField home_monthly;
    @FXML TextField home_cust_id;

    @FXML TextField life_con;
    @FXML TextField life_cov;
    @FXML TextField life_ben;
    @FXML TextField life_monthly;
    @FXML TextField life_cust_id;

    @FXML DatePicker cust_date;
    @FXML DatePicker auto_start;
    @FXML DatePicker auto_end;
    @FXML DatePicker home_start;
    @FXML DatePicker home_end;
    @FXML DatePicker life_start;

    TableColumn<Customer, String> cust_id_col = new TableColumn<Customer, String>("ID");
    TableColumn<Customer, String> cust_name_col = new TableColumn<Customer, String>("Name");
    TableColumn<Customer, String> cust_age_col = new TableColumn<Customer, String>("Age");
    TableColumn<Customer, String> cust_date_col= new TableColumn<Customer, String>("Date_joined");

    TableColumn<Car, String> auto_id_col = new TableColumn<Car, String>("ID");
    TableColumn<Car, String> auto_make_col = new TableColumn<Car, String>("Make");
    TableColumn<Car, String> auto_model_col = new TableColumn<Car, String>("Model");
    TableColumn<Car, String> auto_year_col = new TableColumn<Car, String>("Year");
    TableColumn<Car, String> auto_vin_col = new TableColumn<Car, String>("VIN");
    TableColumn<Car, String> auto_mile_col = new TableColumn<Car, String>("MileagepY");
    TableColumn<Car, String> auto_cov_col = new TableColumn<Car, String>("Coverage");
    TableColumn<Car, String> auto_month_col = new TableColumn<Car, String>("Monthly");
    TableColumn<Car, String> auto_start_col = new TableColumn<Car, String>("Start");
    TableColumn<Car, String> auto_end_col = new TableColumn<Car, String>("End");

    TableColumn<Home, String> home_id_col = new TableColumn<Home, String>("ID");
    TableColumn<Home, String> home_add_col = new TableColumn<Home, String>("Address");
    TableColumn<Home, String> home_area_col = new TableColumn<Home, String>("Area_sqft");
    TableColumn<Home, String> home_bed_col = new TableColumn<Home, String>("#Bedrooms");
    TableColumn<Home, String> home_bath_col = new TableColumn<Home, String>("#Bathrooms");
    TableColumn<Home, String> home_price_col = new TableColumn<Home, String>("Price");
    TableColumn<Home, String> home_cov_col = new TableColumn<Home, String>("Coverage");
    TableColumn<Home, String> home_month_col = new TableColumn<Home, String>("Monthly");
    TableColumn<Home, String> home_start_col = new TableColumn<Home, String>("Start");
    TableColumn<Home, String> home_end_col = new TableColumn<Home, String>("End");

    TableColumn<Life, String> life_id_col = new TableColumn<Life, String>("ID");
    TableColumn<Life, String> life_con_col = new TableColumn<Life, String>("Existing_con");
    TableColumn<Life, String> life_cov_col = new TableColumn<Life, String>("Coverage");
    TableColumn<Life, String> life_ben_col = new TableColumn<Life, String>("Beneficiary");
    TableColumn<Life, String> life_month_col = new TableColumn<Life, String>("Monthly");
    TableColumn<Life, String> life_start_col = new TableColumn<Life, String>("Start");



    public Controller(Stage s, Connection db) throws IOException {
        stage = s;
        conn = db;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UI.fxml"));
        scene = new Scene(fxmlLoader.load(),1070, 459); // 1008
        stage.setScene(scene);
        stage.setTitle("InsuranceDB");
        stage.show();

    }

    public Controller() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Initializing columns for tables

        cust_id_col.setCellValueFactory(new PropertyValueFactory<Customer, String>("ID"));
        cust_name_col.setCellValueFactory(new PropertyValueFactory<Customer, String>("Name"));
        cust_age_col.setCellValueFactory(new PropertyValueFactory<Customer, String>("Age"));
        cust_date_col.setCellValueFactory(new PropertyValueFactory<Customer, String>("Date_joined"));

        auto_id_col.setCellValueFactory(new PropertyValueFactory<Car, String>("car_policy_id"));
        auto_make_col.setCellValueFactory(new PropertyValueFactory<Car, String>("make"));
        auto_model_col.setCellValueFactory(new PropertyValueFactory<Car, String>("model"));
        auto_year_col.setCellValueFactory(new PropertyValueFactory<Car, String>("vehicle_year"));
        auto_vin_col.setCellValueFactory(new PropertyValueFactory<Car, String>("vin"));
        auto_mile_col.setCellValueFactory(new PropertyValueFactory<Car, String>("mileage_py"));
        auto_cov_col.setCellValueFactory(new PropertyValueFactory<Car, String>("Coverage"));
        auto_month_col.setCellValueFactory(new PropertyValueFactory<Car, String>("monthly_payment"));
        auto_start_col.setCellValueFactory(new PropertyValueFactory<Car, String>("start_date"));
        auto_end_col.setCellValueFactory(new PropertyValueFactory<Car, String>("end_date"));

        home_id_col.setCellValueFactory(new PropertyValueFactory<Home, String>("home_policy_id"));
        home_add_col.setCellValueFactory(new PropertyValueFactory<Home, String>("Address"));
        home_area_col.setCellValueFactory(new PropertyValueFactory<Home, String>("Area_sqft"));
        home_bed_col.setCellValueFactory(new PropertyValueFactory<Home, String>("number_bedrooms"));
        home_bath_col.setCellValueFactory(new PropertyValueFactory<Home, String>("number_bathrooms"));
        home_price_col.setCellValueFactory(new PropertyValueFactory<Home, String>("price"));
        home_cov_col.setCellValueFactory(new PropertyValueFactory<Home, String>("Coverage"));
        home_month_col.setCellValueFactory(new PropertyValueFactory<Home, String>("Monthly_payment"));
        home_start_col.setCellValueFactory(new PropertyValueFactory<Home, String>("start_date"));
        home_end_col.setCellValueFactory(new PropertyValueFactory<Home, String>("end_date"));

        life_id_col.setCellValueFactory(new PropertyValueFactory<Life, String>("life_policy_id"));
        life_con_col.setCellValueFactory(new PropertyValueFactory<Life, String>("existing_conditions"));
        life_cov_col.setCellValueFactory(new PropertyValueFactory<Life, String>("Coverage"));
        life_ben_col.setCellValueFactory(new PropertyValueFactory<Life, String>("Beneficiary"));
        life_month_col.setCellValueFactory(new PropertyValueFactory<Life, String>("monthly_payment"));
        life_start_col.setCellValueFactory(new PropertyValueFactory<Life, String>("start_date"));

        // Adding columns to tables
        
        cust_display.getColumns().add(cust_id_col);
        cust_display.getColumns().add(cust_name_col);
        cust_display.getColumns().add(cust_age_col);
        cust_display.getColumns().add(cust_date_col);

        auto_display.getColumns().add(auto_id_col);
        auto_display.getColumns().add(auto_make_col);
        auto_display.getColumns().add(auto_model_col);
        auto_display.getColumns().add(auto_year_col);
        auto_display.getColumns().add(auto_vin_col);
        auto_display.getColumns().add(auto_mile_col);
        auto_display.getColumns().add(auto_cov_col);
        auto_display.getColumns().add(auto_month_col);
        auto_display.getColumns().add(auto_start_col);
        auto_display.getColumns().add(auto_end_col);

        home_display.getColumns().add(home_id_col);
        home_display.getColumns().add(home_add_col);
        home_display.getColumns().add(home_area_col);
        home_display.getColumns().add(home_bed_col);
        home_display.getColumns().add(home_bath_col);
        home_display.getColumns().add(home_price_col);
        home_display.getColumns().add(home_cov_col);
        home_display.getColumns().add(home_month_col);
        home_display.getColumns().add(home_start_col);
        home_display.getColumns().add(home_end_col);

        life_display.getColumns().add(life_id_col);
        life_display.getColumns().add(life_con_col);
        life_display.getColumns().add(life_cov_col);
        life_display.getColumns().add(life_ben_col);
        life_display.getColumns().add(life_month_col);
        life_display.getColumns().add(life_start_col);

        cust_display.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) { //Check whether record is selected and set values of selected item to textviews
                if(cust_display.getSelectionModel().getSelectedItem() != null)
                {
                    cust_id.setText(cust_display.getSelectionModel().getSelectedItem().getID());
                    cust_name.setText(cust_display.getSelectionModel().getSelectedItem().getName());
                    cust_age.setText(cust_display.getSelectionModel().getSelectedItem().getAge());
                    
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US);
                    String short_date = StringUtils.truncate(cust_display.getSelectionModel().getSelectedItem().getDate_joined(), 10); // Truncating date so it can be properly parsed to LocalDate
                    cust_date.setValue(LocalDate.parse(short_date, formatter));
                }
            }
        });

        auto_display.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) { //Check whether record is selected and set values of selected item to textviews
                if(auto_display.getSelectionModel().getSelectedItem() != null)
                {
                    auto_id.setText(auto_display.getSelectionModel().getSelectedItem().getCar_policy_id());
                    auto_make.setText(auto_display.getSelectionModel().getSelectedItem().getMake());
                    auto_model.setText(auto_display.getSelectionModel().getSelectedItem().getModel());
                    auto_year.setText(auto_display.getSelectionModel().getSelectedItem().getVehicle_year());
                    auto_vin.setText(auto_display.getSelectionModel().getSelectedItem().getVin());
                    auto_mile.setText(auto_display.getSelectionModel().getSelectedItem().getMileage_py());
                    auto_cov.setText(auto_display.getSelectionModel().getSelectedItem().getCoverage());
                    auto_monthly.setText(auto_display.getSelectionModel().getSelectedItem().getMonthly_payment());
                    
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US);
                    String short_date = StringUtils.truncate(auto_display.getSelectionModel().getSelectedItem().getStart_date(), 10); // Truncating date so it can be properly parsed to LocalDate
                    auto_start.setValue(LocalDate.parse(short_date, formatter));
                    short_date = StringUtils.truncate(auto_display.getSelectionModel().getSelectedItem().getEnd_date(), 10);
                    auto_end.setValue(LocalDate.parse(short_date, formatter));

                    ResultSet rs;
                    try {
                        Statement st = conn.createStatement();
                        rs = st.executeQuery("SELECT cust_id FROM Insures_car WHERE car_policy_id = " + auto_id.getText()); // Find the customer ID which is associated with the record
                        rs.next();
                        auto_cust_id.setText(rs.getString("cust_id"));

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        home_display.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) { //Check whether record is selected and set values of selected item to textviews
                if(home_display.getSelectionModel().getSelectedItem() != null)
                {
                    home_id.setText(home_display.getSelectionModel().getSelectedItem().getHome_policy_id());
                    home_add.setText(home_display.getSelectionModel().getSelectedItem().getAddress());
                    home_area.setText(home_display.getSelectionModel().getSelectedItem().getArea_sqft());
                    home_bed.setText(home_display.getSelectionModel().getSelectedItem().getNumber_bedrooms());
                    home_bath.setText(home_display.getSelectionModel().getSelectedItem().getNumber_bathrooms());
                    home_price.setText(home_display.getSelectionModel().getSelectedItem().getPrice());
                    home_cov.setText(home_display.getSelectionModel().getSelectedItem().getCoverage());
                    home_monthly.setText(home_display.getSelectionModel().getSelectedItem().getMonthly_payment());

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US);
                    String short_date = StringUtils.truncate(home_display.getSelectionModel().getSelectedItem().getStart_date(), 10); // Truncating date so it can be properly parsed to LocalDate
                    home_start.setValue(LocalDate.parse(short_date, formatter));
                    short_date = StringUtils.truncate(home_display.getSelectionModel().getSelectedItem().getEnd_date(), 10);
                    home_end.setValue(LocalDate.parse(short_date, formatter));

                    ResultSet rs;
                    try {
                        Statement st = conn.createStatement();
                        rs = st.executeQuery("SELECT cust_id FROM Insures_home WHERE home_policy_id = " + home_id.getText()); // Find the customer ID which is associated with the record
                        rs.next();
                        home_cust_id.setText(rs.getString("cust_id"));

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        life_display.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) { //Check whether record is selected and set values of selected item to textviews
                if(life_display.getSelectionModel().getSelectedItem() != null)
                {
                    life_id.setText(life_display.getSelectionModel().getSelectedItem().getLife_policy_id());
                    life_con.setText(life_display.getSelectionModel().getSelectedItem().getExisting_conditions());
                    life_ben.setText(life_display.getSelectionModel().getSelectedItem().getBeneficiary());
                    life_cov.setText(life_display.getSelectionModel().getSelectedItem().getCoverage());
                    life_monthly.setText(life_display.getSelectionModel().getSelectedItem().getMonthly_payment());

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US);
                    String short_date = StringUtils.truncate(life_display.getSelectionModel().getSelectedItem().getStart_date(), 10); // Truncating date so it can be properly parsed to LocalDate
                    life_start.setValue(LocalDate.parse(short_date, formatter));

                    ResultSet rs;
                    try {
                        Statement st = conn.createStatement();
                        rs = st.executeQuery("SELECT cust_id FROM Insures_life WHERE life_policy_id = " + life_id.getText()); // Find the customer ID which is associated with the record
                        rs.next();
                        life_cust_id.setText(rs.getString("cust_id"));

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        // Load tables from database
        
        try {
            RefreshCust();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            RefreshAuto();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            RefreshHome();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            RefreshLife();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ClearCust() { // clears all text views and date pickers after a change is made to the database
        cust_id.setText(null);
        cust_name.clear();
        cust_age.clear();
        cust_date.getEditor().clear();
        cust_date.setValue(null);
        cust_display.getSelectionModel().clearSelection();
    }

    public void ClearAuto() {
        auto_id.setText(null);
        auto_make.clear();
        auto_model.clear();
        auto_year.clear();
        auto_vin.clear();
        auto_mile.clear();
        auto_cov.clear();
        auto_monthly.clear();
        auto_start.getEditor().clear();
        auto_start.setValue(null);
        auto_end.getEditor().clear();
        auto_end.setValue(null);
        auto_display.getSelectionModel().clearSelection();
        auto_cust_id.clear();
    }

    public void ClearHome() {
        home_id.setText(null);
        home_add.clear();
        home_area.clear();
        home_bed.clear();
        home_bath.clear();
        home_price.clear();
        home_cov.clear();
        home_monthly.clear();
        home_start.getEditor().clear();
        home_start.setValue(null);
        home_end.getEditor().clear();
        home_end.setValue(null);
        home_display.getSelectionModel().clearSelection();
        home_cust_id.clear();
    }

    public void ClearLife() {
        life_id.setText(null);
        life_con.clear();
        life_cov.clear();
        life_ben.clear();
        life_monthly.clear();
        life_start.getEditor().clear();
        life_start.setValue(null);
        life_display.getSelectionModel().clearSelection();
        life_cust_id.clear();
    }

    public void RefreshCust() throws SQLException {

        ResultSet rs;
        Statement st = conn.createStatement();

        if (cust_search.getText().equals("") == true) { // Check to see if we should be filtering results. If not, get all records
            rs = st.executeQuery("SELECT * FROM Customer");
        }
        else {
            rs = st.executeQuery("SELECT * FROM Customer WHERE name = '" + cust_search.getText() +"'");
        }

        ObservableList data = FXCollections.observableArrayList();

        while (rs.next()) {
            data.add(new Customer(rs.getString("cust_id"), rs.getString("name"), rs.getString("age"), rs.getString("date_joined")));
        }

        cust_display.setItems(data);
    }

    public void RefreshAuto() throws SQLException {

        ResultSet rs;
        Statement st = conn.createStatement();

        if (auto_search1.getText().equals("") == true && auto_search2.getText().equals("") == true) { // Check to see if we should be filtering results. If not, get all records
            rs = st.executeQuery("SELECT * FROM Car_policy");
        }
        else if (auto_search1.getText().equals("") == false && auto_search2.getText().equals("") == true) { // vin
            rs = st.executeQuery("SELECT * FROM Car_policy WHERE vin = '" + auto_search1.getText() + "'");
        }
        else if (auto_search1.getText().equals("") == true && auto_search2.getText().equals("") == false) { // cust id
            rs = st.executeQuery("SELECT * FROM Car_policy NATURAL JOIN Insures_car WHERE cust_id = '" + auto_search2.getText() + "'");
        }
        else { // both
            rs = st.executeQuery("SELECT * FROM (SELECT * FROM Car_policy NATURAL JOIN Insures_car WHERE cust_id = '" + auto_search2.getText() +"') WHERE vin = '" + auto_search1.getText() + "'");
        }

        ObservableList data = FXCollections.observableArrayList();

        while (rs.next()) {
            data.add(new Car(rs.getString("car_policy_id"), rs.getString("make"), rs.getString("model"), rs.getString("vehicle_year"), rs.getString("vin"),
                    rs.getString("mileage_py"), rs.getString("coverage"), rs.getString("monthly_payment"), rs.getString("start_date"), rs.getString("end_date")));
        }

        auto_display.setItems(data);
    }

    public void RefreshHome() throws SQLException {

        ResultSet rs;
        Statement st = conn.createStatement();

        if (home_search1.getText().equals("") == true && home_search2.getText().equals("") == true) { // Check to see if we should be filtering results. If not, get all records
            rs = st.executeQuery("SELECT * FROM Home_policy");
        }
        else if (home_search1.getText().equals("") == false && home_search2.getText().equals("") == true) { // vin
            rs = st.executeQuery("SELECT * FROM Home_policy WHERE address = '" + home_search1.getText() +"'");
        }
        else if (home_search1.getText().equals("") == true && home_search2.getText().equals("") == false) { // cust id
            rs = st.executeQuery("SELECT * FROM Home_policy NATURAL JOIN Insures_home WHERE cust_id = '" + home_search2.getText() +"'");
        }
        else { // both
            rs = st.executeQuery("SELECT * FROM (SELECT * FROM Home_policy NATURAL JOIN Insures_home WHERE cust_id = '" + home_search2.getText() +"') WHERE address = '" + home_search1.getText() +"'");
        }

        ObservableList data = FXCollections.observableArrayList();

        while (rs.next()) {
            data.add(new Home(rs.getString("home_policy_id"), rs.getString("address"), rs.getString("area_sqft"), rs.getString("number_bedrooms"), rs.getString("number_bathrooms"),
                    rs.getString("price"), rs.getString("coverage"), rs.getString("monthly_payment"), rs.getString("start_date"), rs.getString("end_date")));
        }

        home_display.setItems(data);
    }

    public void RefreshLife() throws SQLException {

        ResultSet rs;
        Statement st = conn.createStatement();

        if (life_search1.getText().equals("") == true && life_search2.getText().equals("") == true) { // Check to see if we should be filtering results. If not, get all records
            rs = st.executeQuery("SELECT * FROM Life_policy");
        }
        else if (life_search1.getText().equals("") == false && life_search2.getText().equals("") == true) { // start date
            rs = st.executeQuery("SELECT * FROM Life_policy WHERE beneficiary = '" + life_search1.getText() +"'");
        }
        else if (life_search1.getText().equals("") == true && life_search2.getText().equals("") == false) { // cust id
            rs = st.executeQuery("SELECT * FROM Life_policy NATURAL JOIN Insures_life WHERE cust_id = '" + life_search2.getText() +"'");
        }
        else {
            rs = st.executeQuery("SELECT * FROM (SELECT * FROM Life_policy NATURAL JOIN Insures_life WHERE cust_id = '" + life_search2.getText() +"') WHERE beneficiary = '" + life_search1.getText() +"'");
        }

        ObservableList data = FXCollections.observableArrayList();

        while (rs.next()) {
            data.add(new Life(rs.getString("life_policy_id"), rs.getString("existing_conditions"), rs.getString("coverage"), rs.getString("beneficiary"), rs.getString("monthly_payment"),
                    rs.getString("start_date")));
        }

        life_display.setItems(data);
    }

    public void InsertCust() throws SQLException {

        ResultSet rs;
        Statement stmt = conn.createStatement();

        String name = cust_name.getText();
        String age = cust_age.getText();
        String date = cust_date.getValue().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")).toString();

        rs = stmt.executeQuery("SELECT cust_id FROM Customer");

        if (!rs.isBeforeFirst()) {
            // If there are no customers in the table, set the first ID to 1
            stmt.executeUpdate("INSERT INTO Customer (cust_id, name, age, date_joined) VALUES (1, '" + name + "', " + age + ", '" + date + "')");
        } else {
            stmt.executeUpdate("INSERT INTO Customer (cust_id, name, age, date_joined) VALUES ((SELECT MAX(cust_id) FROM Customer) + 1, '" + name + "'," + age + ",'" + date + "')");
        }

        ClearCust();
        RefreshCust();
    }

    public void UpdateCust() throws SQLException {

        ResultSet rs;
        Statement stmt = conn.createStatement();

        String id = cust_id.getText();
        String name = cust_name.getText();
        String age = cust_age.getText();
        String date = cust_date.getValue().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")).toString();

        // check to see if customer exists
        rs = stmt.executeQuery("SELECT cust_id FROM Customer WHERE cust_id = " +id);

        if (!rs.isBeforeFirst()) {
            System.out.println("Customer does not exist");
        }
        else {
            stmt.executeUpdate("UPDATE Customer SET name = '" + name + "', age = " + age + ", date_joined = '" + date + "' WHERE cust_id = " + id);

            ClearCust();
            RefreshCust();
        }
    }

    public void RemoveCust() throws SQLException {

        ResultSet rs;
        Statement stmt = conn.createStatement();

        String id = cust_id.getText();

        rs = stmt.executeQuery("SELECT cust_id FROM Customer WHERE cust_id = " +id);

        if (!rs.isBeforeFirst()) {
            System.out.println("Customer does not exist");
        }
        else {
            stmt.executeUpdate("DELETE FROM Home_policy WHERE home_policy_id IN (SELECT home_policy_id FROM Insures_Home WHERE cust_id = " +id+")");  // Removing policies attached to the customer
            stmt.executeUpdate("DELETE FROM Car_policy WHERE car_policy_id IN (SELECT car_policy_id FROM Insures_Car WHERE cust_id = " +id+")");
            stmt.executeUpdate("DELETE FROM Life_policy WHERE life_policy_id IN (SELECT life_policy_id FROM Insures_Life WHERE cust_id = " +id+")");
            stmt.executeUpdate("DELETE FROM Customer WHERE cust_id = " + id);

            ClearCust();
            RefreshCust();
            RefreshAuto();
            RefreshHome();
            RefreshLife();
        }
    }

    public void InsertAuto() throws SQLException {

        ResultSet rs;
        Statement stmt = conn.createStatement();

        String cust_id = auto_cust_id.getText();
        String make = auto_make.getText();
        String model = auto_model.getText();
        String vehicle_year = auto_year.getText();
        String vin = auto_vin.getText();
        String mileage = auto_mile.getText();
        String coverage = auto_cov.getText();
        String monthly_payment = auto_monthly.getText();
        String start_date = auto_start.getValue().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")).toString();
        String end_date = auto_end.getValue().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")).toString();

        rs = stmt.executeQuery("SELECT car_policy_id FROM Car_policy");

        if (!rs.isBeforeFirst()) {
            // If there are no policies in the table, set the first ID to 1
            stmt.executeUpdate("INSERT INTO Car_policy VALUES (1, '" +make+ "', '" +model+ "', " +vehicle_year+ ", '" +vin+ "', " +mileage+ ", " +coverage+ ", " +monthly_payment+ ", '" +start_date+ "', '" +end_date+ "')");
        } else {
            stmt.executeUpdate("INSERT INTO Car_policy VALUES ((SELECT MAX(car_policy_id) FROM Car_policy)+1, '" +make+ "', '" +model+ "', " +vehicle_year+ ", '" +vin+ "', " +mileage+ ", " +coverage+ ", " +monthly_payment+ ", '" +start_date+ "', '" +end_date+ "')");
        }

        stmt.executeUpdate("INSERT INTO Insures_car VALUES (" +cust_id+ ", (SELECT MAX(car_policy_id) FROM Car_policy))");

        ClearAuto();
        RefreshAuto();
    }

    public void UpdateAuto() throws SQLException {

        ResultSet rs;
        Statement stmt = conn.createStatement();

        String id = auto_id.getText();
        String cust_id = auto_cust_id.getText();
        String make = auto_make.getText();
        String model = auto_model.getText();
        String vehicle_year = auto_year.getText();
        String vin = auto_vin.getText();
        String mileage = auto_mile.getText();
        String coverage = auto_cov.getText();
        String monthly_payment = auto_monthly.getText();
        String start_date = auto_start.getValue().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")).toString();
        String end_date = auto_end.getValue().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")).toString();

        rs = stmt.executeQuery("SELECT car_policy_id FROM Car_policy WHERE car_policy_id = " +id);

        if (!rs.isBeforeFirst()) {
            System.out.println("Policy does not exist");
        }
        else {
            stmt.executeUpdate("UPDATE Car_policy SET make = '" + make + "', model = '" + model + "', vehicle_year = " + vehicle_year + ", vin = '" + vin + "', mileage_py = " + mileage + ", coverage = "
                    + coverage + ", monthly_payment = " + monthly_payment + ", start_date = '" + start_date + "', end_date = '" + end_date + "' WHERE car_policy_id = " + id);

            rs = stmt.executeQuery("SELECT * FROM Customer WHERE cust_id = " + cust_id);

            if (!rs.isBeforeFirst()) { // This statement checks to see if the new customer exists in the database, if not, the policy holder (customer) is not changed
                ErrorMsg("Customer does not exist");
            }
            else {
                stmt.executeUpdate("UPDATE Insures_car SET cust_id = " + cust_id + " WHERE car_policy_id = " + id);
                ClearHome();
                RefreshHome();
            }

        }
    }

    public void RemoveAuto() throws SQLException {

        ResultSet rs;
        Statement stmt = conn.createStatement();

        String id = auto_id.getText();

        rs = stmt.executeQuery("SELECT car_policy_id FROM Car_policy WHERE car_policy_id = " +id);

        if (!rs.isBeforeFirst()) {
            System.out.println("Policy does not exist");
        }
        else {
            stmt.executeUpdate("DELETE FROM Car_policy WHERE car_policy_id = " + id);

            ClearAuto();
            RefreshAuto();
        }
    }

    public void InsertHome() throws SQLException {

        ResultSet rs;
        Statement stmt = conn.createStatement();

        String cust_id = home_cust_id.getText();
        String address = home_add.getText();
        String area = home_area.getText();
        String number_bedrooms = home_bed.getText();
        String number_bathrooms = home_bath.getText();
        String price = home_price.getText();
        String coverage = home_cov.getText();
        String monthly_payment = home_monthly.getText();
        String start_date = home_start.getValue().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")).toString();
        String end_date = home_end.getValue().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")).toString();

        rs = stmt.executeQuery("SELECT home_policy_id FROM Home_policy");

        if (!rs.isBeforeFirst()) {
            // If there are no policies in the table, set the first ID to 1
            stmt.executeUpdate("INSERT INTO Home_policy VALUES (1, '" +address+ "', " +area+ ", " +number_bedrooms+ ", " +number_bathrooms+ ", " +price+ ", " +coverage+ ", " +monthly_payment+ ", '" +start_date+ "', '" +end_date+ "')");
        } else {
            stmt.executeUpdate("INSERT INTO Home_policy VALUES ((SELECT MAX(home_policy_id) FROM Home_policy)+1, '" +address+ "', " +area+ ", " +number_bedrooms+ ", " +number_bathrooms+ ", " +price+ ", " +coverage+ ", " +monthly_payment+ ", '" +start_date+ "', '" +end_date+ "')");
        }

        stmt.executeUpdate("INSERT INTO Insures_home VALUES (" +cust_id+ ", (SELECT MAX(home_policy_id) FROM Home_policy))");

        ClearHome();
        RefreshHome();
    }

    public void UpdateHome() throws SQLException {

        ResultSet rs;
        Statement stmt = conn.createStatement();

        String cust_id = home_cust_id.getText();
        String id = home_id.getText();
        String address = home_add.getText();
        String area = home_area.getText();
        String number_bedrooms = home_bed.getText();
        String number_bathrooms = home_bath.getText();
        String price = home_price.getText();
        String coverage = home_cov.getText();
        String monthly_payment = home_monthly.getText();
        String start_date = home_start.getValue().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")).toString();
        String end_date = home_end.getValue().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")).toString();

        rs = stmt.executeQuery("SELECT home_policy_id FROM Home_policy WHERE home_policy_id = " +id);

        if (!rs.isBeforeFirst()) {
            System.out.println("Policy does not exist");
        }
        else {
            stmt.executeUpdate("UPDATE Home_policy SET address = '" + address + "', area_sqft = " + area + ", number_bedrooms = " + number_bedrooms + ", number_bathrooms = " + number_bathrooms +
                    ", price = " + price + ", coverage = " + coverage + ", monthly_payment = " + monthly_payment + ", start_date = '" + start_date + "', end_date = '" + end_date + "' WHERE home_policy_id = " + id);

            rs = stmt.executeQuery("SELECT * FROM Customer WHERE cust_id = " + cust_id);

            if (!rs.isBeforeFirst()) { // This statement checks to see if the new customer exists in the database, if not, the policy holder (customer) is not changed
                ErrorMsg("Customer does not exist");
            }
            else {
                stmt.executeUpdate("UPDATE Insures_home SET cust_id = " + cust_id + " WHERE home_policy_id = " + id);
                ClearHome();
                RefreshHome();
            }

        }
    }

    public void RemoveHome() throws SQLException {

        ResultSet rs;
        Statement stmt = conn.createStatement();

        String id = home_id.getText();

        rs = stmt.executeQuery("SELECT home_policy_id FROM Home_policy WHERE home_policy_id = " +id);

        if (!rs.isBeforeFirst()) {
            System.out.println("Policy does not exist");
        }
        else {
            stmt.executeUpdate("DELETE FROM Home_policy WHERE home_policy_id = " + id);

            ClearHome();
            RefreshHome();
        }
    }

    public void InsertLife() throws SQLException {

        ResultSet rs;
        Statement stmt = conn.createStatement();

        String cust_id = life_cust_id.getText();
        String exist_con = life_con.getText();
        String coverage = life_cov.getText();
        String beneficiary = life_ben.getText();
        String monthly_payment = life_monthly.getText();
        String start_date = life_start.getValue().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")).toString();

        rs = stmt.executeQuery("SELECT life_policy_id FROM Life_policy");

        if (!rs.isBeforeFirst()) {
            // If there are no policies in the table, set the first ID to 1
            stmt.executeUpdate("INSERT INTO Life_policy VALUES (1, '" +exist_con+ "', " +coverage+ ", '" +beneficiary+ "', " +monthly_payment+ ", '" +start_date+ "')");
        } else {
            stmt.executeUpdate("INSERT INTO Life_policy VALUES ((SELECT MAX(life_policy_id) FROM Life_policy)+1, '" +exist_con+ "', " +coverage+ ", '" +beneficiary+ "', " +monthly_payment+ ", '" +start_date+ "')");
        }

        stmt.executeUpdate("INSERT INTO Insures_life VALUES (" +cust_id+ ", (SELECT MAX(life_policy_id) FROM Life_policy))");

        ClearLife();
        RefreshLife();
    }

    public void UpdateLife() throws SQLException {

        ResultSet rs;
        Statement stmt = conn.createStatement();

        String cust_id = life_cust_id.getText();
        String id = life_id.getText();
        String exist_con = life_con.getText();
        String coverage = life_cov.getText();
        String beneficiary = life_ben.getText();
        String monthly_payment = life_monthly.getText();
        String start_date = life_start.getValue().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")).toString();

        rs = stmt.executeQuery("SELECT life_policy_id FROM Life_policy WHERE life_policy_id = " +id);

        if (!rs.isBeforeFirst()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Insert Error");
            alert.setHeaderText(null);
            alert.setContentText("Policy does not exist");
        }
        else {

            stmt.executeUpdate("UPDATE Life_policy SET existing_conditions = '" + exist_con + "', coverage = " + coverage + ", beneficiary = '" + beneficiary +
                    "', monthly_payment = " + monthly_payment + ", start_date = '" + start_date + "' WHERE life_policy_id = " + id);

            rs = stmt.executeQuery("SELECT * FROM Customer WHERE cust_id = " + cust_id);

            if (!rs.isBeforeFirst()) { // This statement checks to see if the new customer exists in the database, if not, the policy holder (customer) is not changed
                ErrorMsg("Customer does not exist");
            }
            else {
                stmt.executeUpdate("UPDATE Insures_life SET cust_id = " + cust_id + " WHERE life_policy_id = " + id);
                ClearHome();
                RefreshHome();
            }

        }
    }

    public void RemoveLife() throws SQLException {

        ResultSet rs;
        Statement stmt = conn.createStatement();

        String id = life_id.getText();

        rs = stmt.executeQuery("SELECT life_policy_id FROM Life_policy WHERE life_policy_id = " +id);

        if (!rs.isBeforeFirst()) {
            System.out.println("Policy does not exist");

        }
        else {
            stmt.executeUpdate("DELETE FROM Life_policy WHERE life_policy_id = " + id);

            ClearLife();
            RefreshLife();
        }
    }

    public void ErrorMsg(String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }
}


