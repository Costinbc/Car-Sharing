package carsharing;

import java.util.*;
import java.sql.*;
public class Main {
    public static final Scanner scanner = new Scanner(System.in);
    public static CompanyDao companyDao = new CompanyDaoImplement();
    public static CarDao carDao = new CarDaoImplement();
    public static CustomerDao customerDao = new CustomerDaoImplement();
    static Connection conn = null;
    static Statement stmt = null;
    static String sql;
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:C:\\Users\\costi\\OneDrive\\Desktop\\info\\fac\\Car Sharing\\Car Sharing\\task\\src\\carsharing\\db\\carsharing";

    //  Database credentials
    //static final String USER = "sa";
    //static final String PASS = "";
    /*
    public static void getItems(){
        sql = "select * from COMPANY;";
        try {
            ResultSet rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        while(rs)
    }
    */

    public static void menu1(){
        System.out.println("1. Log in as a manager\n" +"2. Log in as a customer\n"+ "3. Create a customer\n"  + "0. Exit");
        String option = scanner.next();
        if(option.equals("1")){
            menu2();
        }
        else if(option.equals("2")){
            if(customerDao.getCustomersSize() == 0){
                System.out.println("The customer list is empty!");
                menu1();
            }
            else {
                System.out.println("Choose a customer:");
                for (Customer customer : customerDao.getAllCustomers()){
                    System.out.println(customer.getId() + ". " + customer.getName());
                }
                System.out.println("0. Back");
                option = scanner.next();
                if(option.equals("0")){
                    menu1();
                }
                //    System.out.println();
                //menu2();
                else customerMenu1(option);
            }
        }
        else if(option.equals("3")){
            System.out.println("Enter the customer name:");
            scanner.nextLine();
            String name = scanner.nextLine();
            customerDao.newCustomer(name);
            //stmt.executeUpdate(sql);
            sql = "INSERT INTO CUSTOMER (name) "+
                    "VALUES ('" + name + "');";
            try {
                stmt.executeUpdate(sql);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println("The customer was added!");
            menu1();
        }
        else System.exit(1);
    }
    public static void menu2() {
        String option;

        System.out.println("1. Company list\n2. Create a company\n0. Back");
        option = scanner.next();
        if (option.equals("1")){
            if(companyDao.getCompaniesSize() == 0){
                System.out.println("The company list is empty!");
                //  System.out.println();
                menu2();
            }
            else {
                System.out.println("Choose a company:");
                for (Company company : companyDao.getAllCompanies()) {
                    System.out.println(company.getId() + ". " + company.getName());
                }
                System.out.println("0. Back");
                option = scanner.next();
                if(option.equals("0")){
                    menu2();
                }
                //    System.out.println();
                //menu2();
                else carMenu1(option);
            }

        }
        else if(option.equals("2")){

            System.out.println("Enter the company name:");
            scanner.nextLine();
            String name = scanner.nextLine();
            companyDao.newCompany(name);
            //stmt.executeUpdate(sql);
            sql = "INSERT INTO COMPANY (name) "+
                    "VALUES ('" + name + "');";
            try {
                stmt.executeUpdate(sql);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println("The company was created!");
            //System.out.println();
            menu2();
        }

        else
            menu1();
    }

    public static void carMenu1(String option){

        System.out.println(companyDao.getCompany(Integer.parseInt(option) - 1).getName() +
                " company:\n1. Car list\n2. Create a car\n0. Back");
        String option2 = scanner.next();
        if(option2.equals("1")) {
            int car_id = 0;
            for (Car car : carDao.getAllCars()) {
                if (car.getCompanyId() == Integer.parseInt(option) - 1) {
                    car_id++;
                }
            }
            if (car_id == 0) {
                System.out.println("The car list is empty!");

            } else {
                System.out.println(companyDao.getCompany(Integer.parseInt(option) - 1).getName() + " cars:");
                car_id = 0;
                for (Car car1 : carDao.getAllCars()) {
                    if (car1.getCompanyId() == Integer.parseInt(option) - 1) {
                        car_id++;

                        System.out.println(car_id + ". " + car1.getName());
                    }

                }
            }

            carMenu1(option);
        }

        else if(option2.equals("2")){
            System.out.println("Enter the car name:");
            scanner.nextLine();
            String name = scanner.nextLine();
            carDao.newCar(name, Integer.parseInt(option) - 1);
            //stmt.executeUpdate(sql);
            System.out.println("The car was created!");
            sql = "INSERT INTO CAR (name, company_id) "+
                    "VALUES ("+
                    "'" + name + "', " + Integer.parseInt(option)+ ");";
            try {
                stmt.executeUpdate(sql);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            //System.out.println();
            carMenu1(option);
        }
        else menu2();
    }
    public static void customerMenu1(String option) {
        System.out.println("1. Rent a car\n2. Return a rented car\n3. My rented car\n0. Back");
        String option2 = scanner.next();

        if(option2.equals("1")){
            if(customerDao.getCustomer(Integer.parseInt(option) - 1).getRented_car_id() == 0){
                customerCompany(option);
            }
            else {
                System.out.println("You've already rented a car");
                customerMenu1(option);
            }
        }
        else if(option2.equals("2")){
            if(customerDao.getCustomer(Integer.parseInt(option) - 1).getRented_car_id() == 0){
                System.out.println("You didn't rent a car!");
                customerMenu1(option);
            }
            else {
                customerDao.getCustomer(Integer.parseInt(option) - 1).setRented_car_id(0);
                customerMenu1(option);
            }
        }
        else if(option2.equals("3")){
            int rented_car = customerDao.getCustomer(Integer.parseInt(option) - 1).getRented_car_id();
            if(rented_car == 0){
                System.out.println("You didn't rent a car!");
                customerMenu1(option);
            }
            else {
                System.out.println("Your rented car:");
                System.out.println(carDao.getCar(rented_car).getName());
                int company_id = carDao.getCar(rented_car).getCompanyId();
                System.out.println("Company:" + companyDao.getCompany(company_id).getName());
                customerMenu1(option);
            }
        }
        else menu1();
    }
    public static void customerCompany(String option){
        System.out.println("Choose a company:");
        for (Company company : companyDao.getAllCompanies()) {
            System.out.println(company.getId() + ". " + company.getName());
        }
        System.out.println("0. Back");
        String option_company = scanner.next();
        if(!option_company.equals("0")) {
            customerMenu2(option_company, option);
        }
        else customerMenu1(option);
    }
    public static void customerMenu2(String option_company, String option){

        System.out.println("Choose a car:");
        int car_id = 0;
        for (Car car1 : carDao.getAllCars()) {
            if (car1.getCompanyId() == Integer.parseInt(option_company) - 1){
                car_id++;
                System.out.println(car_id + ". " + car1.getName());
            }
        }
        System.out.println("0. Back");
        String option_car = scanner.next();
        if(option_car.equals("0")){
            customerCompany(option);
        }
        else {
            for (Car car1 : carDao.getAllCars()) {
                if (car1.getCompanyId() == Integer.parseInt(option_company) ) {
                    car_id++;
                    //System.out.println(car_id + ". " + car1.getName());
                    if (car_id == Integer.parseInt(option_car) - 1)
                    {
                        customerDao.getCustomer(Integer.parseInt(option) - 1).setRented_car_id(car1.getId());
                        sql = "UPDATE CUSTOMERS "+
                                "SET RENTED_CAR_ID = "+
                                car1.getId() +
                                ";";
                        try {
                            stmt.executeUpdate(sql);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println("You rented " + "'" + car1.getName() + "'");
                        customerMenu1(option);
                    }
                }
            }
        }
    }
    public static void main (String[] args) {



        try {
            Class.forName(JDBC_DRIVER);
            //System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL);
            //System.out.println("Creating table in given database...");
            stmt = conn.createStatement();
            conn.setAutoCommit(true);
            //companyDao.deleteTable();
            //carDao.deleteTable();
            //customerDao.deleteRentedCars();

            //customerDao.deleteTable();

            //+"DROP TABLE if exists CUSTOMER cascade;";
            //stmt.executeUpdate(sql);
            //sql = "DROP TABLE IF EXISTS COMPANY;";
            //stmt.executeUpdate(sql);
            sql = "CREATE TABLE IF NOT EXISTS COMPANY " +
                    "(id INTEGER AUTO_INCREMENT, " +
                    " name VARCHAR(255) NOT NULL, " +
                    " PRIMARY KEY ( id ), " +
                    "UNIQUE (name))";

            //System.out.println("Created table in given database...");
            stmt.executeUpdate(sql);
            //sql = "DROP TABLE IF EXISTS CAR";
            //stmt.executeUpdate(sql);
            sql = "CREATE TABLE IF NOT EXISTS CAR " +
                    "(id INTEGER AUTO_INCREMENT, " +
                    "name VARCHAR(255) NOT NULL, " +
                    "company_id INTEGER NOT NULL, " +
                    "FOREIGN KEY (COMPANY_ID) REFERENCES COMPANY (ID), " +
                    "PRIMARY KEY (ID), " +
                    "UNIQUE (NAME));";
            stmt.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS CUSTOMER "+
                    "(ID INTEGER AUTO_INCREMENT, " +
                    "NAME VARCHAR(255) NOT NULL, "+
                    "RENTED_CAR_ID INTEGER DEFAULT NULL, "+
                    "FOREIGN KEY (RENTED_CAR_ID) REFERENCES CAR (ID), "+
                    "PRIMARY KEY (ID), "+
                    "UNIQUE (NAME));";
            stmt.executeUpdate(sql);

            //sql = "CREATE TABLE IF NOT EXISTS CUSTOMER_copy "+
                   // "(ID INTEGER AUTO_INCREMENT, " +
                   // "NAME VARCHAR(255) NOT NULL, " +
                   // "RENTED_CAR_ID INTEGER DEFAULT NULL, " +
                   // "PRIMARY KEY (ID), "+
                   // "UNIQUE (NAME));";
            //stmt.executeUpdate(sql);

            //sql = "INSERT INTO CUSTOMER (id, name) SELECT (id, name) FROM CUSTOMER_copy";
            //stmt.executeUpdate(sql);
            ResultSet rs;

            sql = "select * from COMPANY;";
                rs = stmt.executeQuery(sql);
                while(rs.next()){
                    companyDao.newCompany(rs.getString("name"));
                }


            sql = "select * from CAR;";
                rs = stmt.executeQuery(sql);
                while(rs.next()){
                    carDao.newCar(rs.getString("name"), rs.getInt("id"));
                }
            sql = "select * from CUSTOMER;";
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                customerDao.newCustomer(rs.getString("name"));
            }
            sql = "ALTER TABLE COMPANY ALTER COLUMN ID RESTART WITH 1;";
            stmt.executeUpdate(sql);
            menu1();

            //customerDao.deleteRentedCars();
            //sql = "DROP TABLE if exists COMPANY cascade;\n" +
                    //"DROP TABLE if exists CAR cascade;\n";
            //stmt.executeUpdate(sql);
            //sql = "INSERT INTO CUSTOMER_copy (id, name) SELECT (id, name) FROM CUSTOMER";
            //stmt.executeUpdate(sql);
            //stmt.executeUpdate(sql);
            stmt.close();
            conn.close();

        } catch (SQLException se) {
            //System.out.println("JDBC exception");
            se.printStackTrace();
        } catch (Exception e) {
            //System.out.println(".forname exception");
            e.printStackTrace();
        } finally {

            try {

                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
                //System.out.println("sql exception");
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        //System.out.println("Goodbye!");

    }

}