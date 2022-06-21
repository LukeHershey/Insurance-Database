package com.example.signin;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.Scanner;

import oracle.jdbc.pool.OracleDataSource;

public class Main extends Application {

    private static OracleDataSource ods;
    private static Connection conn;
    @FXML
    TextField username;
    @FXML
    PasswordField password;
    @FXML
    Label connected;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void onSignInClick() throws Exception {
        ods.setUser(username.getText());
        ods.setPassword(password.getText());

        //try {
                conn = ods.getConnection();
                connected.setText("Connected");
                Stage stage = (Stage) connected.getScene().getWindow();
                stage.close();

                Controller GUI = new Controller(stage, conn); // Loads the GUI controller

        //}
        /*catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid login");
            alert.showAndWait();
        }*/

    }

    public static void main(String[] args) throws Exception {

        ods = new OracleDataSource();
        ods.setURL("jdbc:oracle:thin:@h3oracle.ad.psu.edu:1521/orclpdb.ad.psu.edu");
        launch(); // Start the login screen

        Scanner sc = new Scanner(System.in);
        Statement stmt = conn.createStatement();


        // This loop is the console application. It will open after the GUI app is closed (assuming login was successful)
        while(true) {
            System.out.println("\nPlease select an option: ");
            System.out.println("(1)  Add a new customer\n(2)  Edit a customer\n(3)  Remove a customer\n(4)  Search a customer\n" +
                    "(5)  Browse customers\n(6)  Add a policy\n(7)  Edit a policy\n(8)  Remove a policy\n(9)  Browse policies\n" +
                    "(10) Search a policy by customer\n(11) Search a policy by info\n(12) Search a policy by date");
            String choice = sc.nextLine();
            int choice_int = Integer.parseInt(choice);
            ResultSet rs;

            switch (choice_int) {

                case 1: { // Add a new customer
                    String name = Prompt(sc, "name (String)");
                    String age = Prompt(sc, "age (int)");
                    String date = Prompt(sc, "date joined (DD-MON-YY)");
                    rs = stmt.executeQuery("SELECT cust_id FROM Customer");

                    if (!rs.isBeforeFirst()) {
                        // If there are no customers in the table, set the first ID to 1
                        stmt.executeUpdate("INSERT INTO Customer (cust_id, name, age, date_joined) VALUES (1, '" + name + "', " + age + ", '" + date + "')");
                    } else {
                        stmt.executeUpdate("INSERT INTO Customer (cust_id, name, age, date_joined) VALUES ((SELECT MAX(cust_id) FROM Customer) + 1, '" + name + "'," + age + ",'" + date + "')");
                    }
                    System.out.println("Row inserted.");
                    break;
                }

                case 2: {// Edit a customer
                    System.out.println("Enter the id of the customer you would like to edit");
                    String id = Prompt(sc, "cust_id (int)");
                    // check to see if customer exists
                    rs = stmt.executeQuery("SELECT cust_id FROM Customer WHERE cust_id = " +id);

                    if (!rs.isBeforeFirst()) {
                        System.out.println("Customer does not exist");
                    }
                    else {
                        System.out.println("Enter the new values for the attributes");
                        String name = Prompt(sc, "name (String)");
                        String age = Prompt(sc, "age (int)");
                        String date = Prompt(sc, "date joined (DD-MON-YY)");
                        stmt.executeUpdate("UPDATE Customer SET name = '" + name + "', age = " + age + ", date_joined = '" + date + "' WHERE cust_id = " + id);
                        System.out.println("Row updated.");
                    }
                    break;
                }

                case 3: {// Remove a customer
                    System.out.println("Enter the id of the customer you would like to remove");
                    String id = Prompt(sc, "cust_id (int)");
                    rs = stmt.executeQuery("SELECT cust_id FROM Customer WHERE cust_id = " +id);

                    if (!rs.isBeforeFirst()) {
                        System.out.println("Customer does not exist");
                    }
                    else {
                        stmt.executeUpdate("DELETE FROM Home_policy WHERE home_policy_id IN (SELECT home_policy_id FROM Insures_Home WHERE cust_id = " +id+")");  // Removing policies attached to the customer
                        stmt.executeUpdate("DELETE FROM Car_policy WHERE car_policy_id IN (SELECT car_policy_id FROM Insures_Car WHERE cust_id = " +id+")");
                        stmt.executeUpdate("DELETE FROM Life_policy WHERE life_policy_id IN (SELECT life_policy_id FROM Insures_Life WHERE cust_id = " +id+")");
                        stmt.executeUpdate("DELETE FROM Customer WHERE cust_id = " + id);
                        System.out.println("Row removed.");
                    }
                    break;
                }

                case 4: {// Search a customer
                    System.out.println("Enter the name of the customer you would like to search");
                    String name = Prompt(sc, "name (String)");
                    rs = stmt.executeQuery("SELECT * FROM Customer WHERE name = '" + name + "'");
                    Display(rs);
                    break;
                }

                case 5: {// Browse customers
                    rs = stmt.executeQuery("SELECT * FROM Customer");
                    Display(rs);
                    break;
                }

                case 6: {// Add a policy
                    System.out.println("Which customer does this policy belong to?");
                    String cust_id = Prompt(sc, "cust_id");
                    System.out.println("What type of policy would you like to add?");
                    String selection = Prompt(sc, "type (home/car/life)");

                    if ((selection.equals("home"))) {

                        String address = Prompt(sc, "address (String)");
                        String area = Prompt(sc, "area_sqft (int)");
                        String number_bedrooms = Prompt(sc, "number of bedrooms (int)");
                        String number_bathrooms = Prompt(sc, "number of bathrooms (int)");
                        String price = Prompt(sc, "price (int)");
                        String coverage = Prompt(sc, "coverage (int)");
                        String monthly_payment = Prompt(sc, "monthly_payment (int)");
                        String start_date = Prompt(sc, "start date (DD-MON-YY)");
                        String end_date = Prompt(sc, "end date (DD-MON-YY)");

                        rs = stmt.executeQuery("SELECT home_policy_id FROM Home_policy");

                        if (!rs.isBeforeFirst()) {
                            // If there are no policies in the table, set the first ID to 1
                            stmt.executeUpdate("INSERT INTO Home_policy VALUES (1, '" +address+ "', " +area+ ", " +number_bedrooms+ ", " +number_bathrooms+ ", " +price+ ", " +coverage+ ", " +monthly_payment+ ", '" +start_date+ "', '" +end_date+ "')");
                        } else {
                            stmt.executeUpdate("INSERT INTO Home_policy VALUES ((SELECT MAX(home_policy_id) FROM Home_policy)+1, '" +address+ "', " +area+ ", " +number_bedrooms+ ", " +number_bathrooms+ ", " +price+ ", " +coverage+ ", " +monthly_payment+ ", '" +start_date+ "', '" +end_date+ "')");
                        }

                        stmt.executeUpdate("INSERT INTO Insures_home VALUES (" +cust_id+ ", (SELECT MAX(home_policy_id) FROM Home_policy))");
                        System.out.println("Row inserted.");
                    }
                    else if (selection.equals("car")) {

                        String make = Prompt(sc, "make (String)");
                        String model = Prompt(sc, "model (String)");
                        String vehicle_year = Prompt(sc, "vehicle year (String)");
                        String vin = Prompt(sc, "vin (String)");
                        String mileage = Prompt(sc, "mileage per year (int)");
                        String coverage = Prompt(sc, "coverage (int)");
                        String monthly_payment = Prompt(sc, "monthly_payment (int)");
                        String start_date = Prompt(sc, "start date (DD-MON-YY)");
                        String end_date = Prompt(sc, "end date (DD-MON-YY)");

                        rs = stmt.executeQuery("SELECT car_policy_id FROM Car_policy");

                        if (!rs.isBeforeFirst()) {
                            // If there are no policies in the table, set the first ID to 1
                            stmt.executeUpdate("INSERT INTO Car_policy VALUES (1, '" +make+ "', '" +model+ "', " +vehicle_year+ ", '" +vin+ "', " +mileage+ ", " +coverage+ ", " +monthly_payment+ ", '" +start_date+ "', '" +end_date+ "')");
                        } else {
                            stmt.executeUpdate("INSERT INTO Car_policy VALUES ((SELECT MAX(car_policy_id) FROM Car_policy)+1, '" +make+ "', '" +model+ "', " +vehicle_year+ ", '" +vin+ "', " +mileage+ ", " +coverage+ ", " +monthly_payment+ ", '" +start_date+ "', '" +end_date+ "')");
                        }

                        stmt.executeUpdate("INSERT INTO Insures_car VALUES (" +cust_id+ ", (SELECT MAX(car_policy_id) FROM Car_policy))");
                        System.out.println("Row inserted.");
                    }
                    else if ((selection.equals("life"))) {

                        String exist_con = Prompt(sc, "existing conditions (String)");
                        String coverage = Prompt(sc, "coverage (int)");
                        String beneficiary = Prompt(sc, "beneficiary (String)");
                        String monthly_payment = Prompt(sc, "monthly_payment (int)");
                        String start_date = Prompt(sc, "start date (DD-MON-YY)");

                        rs = stmt.executeQuery("SELECT life_policy_id FROM Life_policy");

                        if (!rs.isBeforeFirst()) {
                            // If there are no policies in the table, set the first ID to 1
                            stmt.executeUpdate("INSERT INTO Life_policy VALUES (1, '" +exist_con+ "', " +coverage+ ", '" +beneficiary+ "', " +monthly_payment+ ", '" +start_date+ "')");
                        } else {
                            stmt.executeUpdate("INSERT INTO Life_policy VALUES ((SELECT MAX(life_policy_id) FROM Life_policy)+1, '" +exist_con+ "', " +coverage+ ", '" +beneficiary+ "', " +monthly_payment+ ", '" +start_date+ "')");
                        }

                        stmt.executeUpdate("INSERT INTO Insures_life VALUES (" +cust_id+ ", (SELECT MAX(life_policy_id) FROM Life_policy))");
                        System.out.println("Row inserted.");
                    }
                    else {
                        System.out.println("Invalid selection");
                    }
                    break;
                }

                case 7: {// Edit a policy
                    System.out.println("What type of policy would you like to edit?");
                    String selection = Prompt(sc, "type (home/car/life)");

                    if (selection.equals("home")) {
                        System.out.println("Enter the id of the home policy you would like to edit");
                        String id = Prompt(sc, "home_policy_id (int)");

                        rs = stmt.executeQuery("SELECT home_policy_id FROM Home_policy WHERE home_policy_id = " +id);

                        if (!rs.isBeforeFirst()) {
                            System.out.println("Policy does not exist");
                        }
                        else {
                            String address = Prompt(sc, "address (String)");
                            String area = Prompt(sc, "area_sqft (int)");
                            String number_bedrooms = Prompt(sc, "number of bedrooms (int)");
                            String number_bathrooms = Prompt(sc, "number of bathrooms (int)");
                            String price = Prompt(sc, "price (int)");
                            String coverage = Prompt(sc, "coverage (int)");
                            String monthly_payment = Prompt(sc, "monthly_payment (int)");
                            String start_date = Prompt(sc, "start date (DD-MON-YY)");
                            String end_date = Prompt(sc, "end date (DD-MON-YY)");

                            stmt.executeUpdate("UPDATE Home_policy SET address = '" + address + "', area_sqft = " + area + ", number_bedrooms = " + number_bedrooms + ", number_bathrooms = " + number_bathrooms +
                                    ", price = " + price + ", coverage = " + coverage + ", monthly_payment = " + monthly_payment + ", start_date = '" + start_date + "', end_date = '" + end_date + "' WHERE home_policy_id = " + id);
                            System.out.println("Row updated.");
                        }
                    }

                    else if (selection.equals("car")) {
                        System.out.println("Enter the id of the car policy you would like to edit");
                        String id = Prompt(sc, "car_policy_id (int)");

                        rs = stmt.executeQuery("SELECT car_policy_id FROM Car_policy WHERE car_policy_id = " +id);

                        if (!rs.isBeforeFirst()) {
                            System.out.println("Policy does not exist");
                        }
                        else {
                            String make = Prompt(sc, "make (String)");
                            String model = Prompt(sc, "model (String)");
                            String vehicle_year = Prompt(sc, "vehicle year (String)");
                            String vin = Prompt(sc, "vin (String)");
                            String mileage = Prompt(sc, "mileage per year (int)");
                            String coverage = Prompt(sc, "coverage (int)");
                            String monthly_payment = Prompt(sc, "monthly_payment (int)");
                            String start_date = Prompt(sc, "start date (DD-MON-YY)");
                            String end_date = Prompt(sc, "end date (DD-MON-YY)");

                            stmt.executeUpdate("UPDATE Car_policy SET make = '" + make + "', model = '" + model + "', vehicle_year = " + vehicle_year + ", vin = '" + vin + "', mileage_py = " + mileage + ", coverage = "
                                    + coverage + ", monthly_payment = " + monthly_payment + ", start_date = '" + start_date + "', end_date = '" + end_date + "' WHERE car_policy_id = " + id);
                            System.out.println("Row updated.");
                        }
                    }

                    else if ((selection.equals("life"))) {
                        System.out.println("Enter the id of the life policy you would like to edit");
                        String id = Prompt(sc, "life_policy_id (int)");

                        rs = stmt.executeQuery("SELECT life_policy_id FROM Life_policy WHERE life_policy_id = " +id);

                        if (!rs.isBeforeFirst()) {
                            System.out.println("Policy does not exist");
                        }
                        else {
                            String exist_con = Prompt(sc, "existing conditions (String)");
                            String coverage = Prompt(sc, "coverage (int)");
                            String beneficiary = Prompt(sc, "beneficiary (String)");
                            String monthly_payment = Prompt(sc, "monthly_payment (int)");
                            String start_date = Prompt(sc, "start date (DD-MON-YY)");

                            stmt.executeUpdate("UPDATE Life_policy SET existing_conditions = '" + exist_con + "', coverage = " + coverage + ", beneficiary = '" + beneficiary +
                                    "', monthly_payment = " + monthly_payment + ", start_date = '" + start_date + "' WHERE life_policy_id = " + id);
                            System.out.println("Row updated.");
                        }
                    }

                    else {
                        System.out.println("Invalid selection");
                    }
                    break;
                }

                case 8: {// Remove a policy
                    System.out.println("What type of policy would you like to remove?");
                    String selection = Prompt(sc, "type (home/car/life)");

                    if ((selection.equals("home"))) {
                        System.out.println("Enter the id of the home policy you would like to remove");
                        String id = Prompt(sc, "home_policy_id (int)");

                        rs = stmt.executeQuery("SELECT home_policy_id FROM Home_policy WHERE home_policy_id = " +id);

                        if (!rs.isBeforeFirst()) {
                            System.out.println("Policy does not exist");
                        }
                        else {
                            stmt.executeUpdate("DELETE FROM Home_policy WHERE home_policy_id = " + id);
                            System.out.println("Row removed.");
                        }
                    }

                    else if (selection.equals("car")) {
                        System.out.println("Enter the id of the car policy you would like to remove");
                        String id = Prompt(sc, "car_policy_id (int)");

                        rs = stmt.executeQuery("SELECT car_policy_id FROM Car_policy WHERE car_policy_id = " +id);

                        if (!rs.isBeforeFirst()) {
                            System.out.println("Policy does not exist");
                        }
                        else {
                            stmt.executeUpdate("DELETE FROM Car_policy WHERE car_policy_id = " + id);
                            System.out.println("Row removed.");
                        }
                    }

                    else if ((selection.equals("life"))) {
                        System.out.println("Enter the id of the life policy you would like to remove");
                        String id = Prompt(sc, "life_policy_id (int)");

                        rs = stmt.executeQuery("SELECT life_policy_id FROM Life_policy WHERE life_policy_id = " +id);

                        if (!rs.isBeforeFirst()) {
                            System.out.println("Policy does not exist");
                        }
                        else {
                            stmt.executeUpdate("DELETE FROM Life_policy WHERE life_policy_id = " + id);
                            System.out.println("Row removed.");
                        }
                    }
                    else {
                        System.out.println("Invalid selection");
                    }
                    break;
                }

                case 9: {// Browse policies
                    System.out.println("HOME:");
                    rs = stmt.executeQuery("SELECT * FROM Home_policy");
                    Display(rs);
                    System.out.println("CAR:");
                    rs = stmt.executeQuery("SELECT * FROM Car_policy");
                    Display(rs);
                    System.out.println("LIFE:");
                    rs = stmt.executeQuery("SELECT * FROM Life_policy");
                    Display(rs);
                    break;
                }

                case 10: {// Search policy by customer
                    String id = Prompt(sc, "cust_id");
                    System.out.println("HOME:");
                    rs = stmt.executeQuery("SELECT * FROM Home_policy natural join Insures_home WHERE cust_id = " +id);
                    Display(rs);
                    System.out.println("CAR:");
                    rs = stmt.executeQuery("SELECT * FROM Car_policy natural join Insures_car WHERE cust_id = " +id);
                    Display(rs);
                    System.out.println("LIFE:");
                    rs = stmt.executeQuery("SELECT * FROM Life_policy natural join Insures_life WHERE cust_id = " +id);
                    Display(rs);
                    break;
                }

                case 11: {// Search policy by info
                    System.out.println("What type of policy would you like to search?");
                    String selection = Prompt(sc, "type (home/car/life)");
                    if ((selection.equals("home"))) {
                        String address = Prompt(sc, "address (String)");
                        rs = stmt.executeQuery("SELECT * FROM Home_policy WHERE address = '" + address + "'");
                        Display(rs);
                    }

                    else if (selection.equals("car")) {
                        String vin = Prompt(sc, "vin (String)");
                        rs = stmt.executeQuery("SELECT * FROM Car_policy WHERE vin = '" + vin + "'");
                        Display(rs);
                    }

                    else if ((selection.equals("life"))) {
                        String beneficiary = Prompt(sc, "beneficiary (String)");
                        rs = stmt.executeQuery("SELECT * FROM Life_policy WHERE beneficiary = '" + beneficiary + "'");
                        Display(rs);
                    }

                    else {
                        System.out.println("Invalid selection");
                    }
                    break;
                }

                case 12: {// Search policy by date
                    String date = Prompt(sc, "start date (DD-MON-YY)");
                    System.out.println("HOME:");
                    rs = stmt.executeQuery("SELECT * FROM Home_policy WHERE start_date = '" +date+"'");
                    Display(rs);
                    System.out.println("CAR:");
                    rs = stmt.executeQuery("SELECT * FROM Car_policy WHERE start_date = '" +date+"'");
                    Display(rs);
                    System.out.println("LIFE:");
                    rs = stmt.executeQuery("SELECT * FROM Life_policy WHERE start_date = '" +date+"'");
                    Display(rs);
                    break;
                }

            }
        }
    }

    public static String Prompt(Scanner sc, String display) { // Prompts the user to enter a value and returns the value as a string
        System.out.println("Enter the " + display);
        String input = sc.nextLine();
        return input;
    }

    public static void Display(ResultSet rs) { // Displays the result of a database query to the console
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) {
                        System.out.print(",  ");
                    }
                    String columnValue = rs.getString(i);
                    System.out.print(columnValue + " " + rsmd.getColumnName(i));
                }
                System.out.println("");
            }
        }
        catch(Exception e) {
            System.out.println("Error displaying selection");
        }
    }
}