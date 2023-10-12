//=======THIS IS FOR DB STORAGE===========

import java.sql.*;
import java.util.ArrayList;

//=======THIS IS FOR FILE STORAGE=========
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.StringTokenizer;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author faith
 */
public class DataIO {

    //=======THIS IS FOR DB STORAGE===========
    //constants
    private final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/cis355a";
    private final String USER_NAME = "root";
    private final String PASSWORD = "faith123";
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public void add(Customer cust) throws ClassNotFoundException, SQLException {
        //=======THIS IS FOR DB STORAGE===========

        //check for driver
        Class.forName(DRIVER);

        //connect to database
        Connection conn = DriverManager.getConnection(CONNECTION_STRING, USER_NAME, PASSWORD);

        //add record
        String strSQL = "INSERT INTO landscape (CustomerName, CustomerAddress, "
                + "LandscapeType, YardLength, YardWidth, LandscapeCost) "
                + "VALUES(?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(strSQL);
        ps.setString(1, cust.getName());
        ps.setString(2, cust.getAddress());
        ps.setString(3, cust.getYardType());
        ps.setDouble(4, cust.getLength());
        ps.setDouble(5, cust.getWidth());
        ps.setDouble(6, cust.getTotalCost());

        //execute prepared statement
        ps.execute();

        //close connection
        conn.close();

        //============THIS IS FOR FILE STORAGE======================
        //        //open or create the file object using true to "APPEND" the data
        //        BufferedWriter outfile = new BufferedWriter(new FileWriter("customers.txt", true));
        //        
        //        //write the object's data to the file on one line using # as separators
        //        outfile.write(cust.getName());
        //        outfile.write("#" + cust.getAddress());
        //        outfile.write("#" + cust.getYardType());
        //        outfile.write("#" + cust.getLength());
        //        outfile.write("#" + cust.getWidth());
        //        outfile.write("#" + cust.getTotalCost());
        //        outfile.newLine(); //enter key
        //        
        //        //close file
        //        outfile.close();
    }

    public ArrayList<Customer> getList() throws SQLException {

        //=======THIS IS FOR DB STORAGE===========
        
        //create the arraylist to return
        ArrayList<Customer> list = new ArrayList<Customer>();
        
        //connect to database
        Connection conn = DriverManager.getConnection(CONNECTION_STRING, USER_NAME, PASSWORD);
        
        Statement stmt = conn.createStatement();
        String SQL = "SELECT * FROM landscape";
        ResultSet rs = stmt.executeQuery(SQL);
        
        while(rs.next()){
            Customer client = new Customer();
            client.setCustomerID(rs.getInt(1));
            client.setName(rs.getString(2));
            client.setAddress(rs.getString(3));
            client.setYardType(rs.getString(4));
            client.setLength(rs.getDouble(5));
            client.setWidth(rs.getDouble(6));
            client.setTotalCost(rs.getDouble(7));
            list.add(client);
        }
        
        //close connection
        conn.close();
        
        //return list
        return list;
        //============THIS IS FOR FILE STORAGE======================
        //        //get Customer objects from the file and return as arrayList
        //        
        //        //create an arrayList
        //        ArrayList<Customer> customers = new ArrayList<Customer>();
        //        
        //        //read data from file
        //        BufferedReader inbuffer = new BufferedReader(new FileReader("customers.txt"));
        //        StringTokenizer tokens;
        //        
        //        //get first line
        //        String inputString = inbuffer.readLine();
        //        while(inputString != null){
        //            //break the line into pieces using tokenizer
        //            tokens = new StringTokenizer(inputString, "#");
        //            
        //            //fields are name#address#yardType#length#width#totalCost
        //            String name = tokens.nextToken();
        //            String address = tokens.nextToken();
        //            String yardType = tokens.nextToken();
        //            double length = Double.parseDouble(tokens.nextToken());
        //            double width = Double.parseDouble(tokens.nextToken());
        //            double totalCost = Double.parseDouble(tokens.nextToken());
        //            
        //            //create customer object and add it to the arrayList
        //            Customer cust = new Customer(0, name, address, yardType, length, width, totalCost);
        //            customers.add(cust);
        //            
        //            //read next line
        //            inputString = inbuffer.readLine();
        //        }
        //        //close file
        //        inbuffer.close();
        //        
        //        //return arrayList
        //        return customers;
    }

    public void delete(int customerID) throws SQLException {
        //=======THIS IS FOR DB STORAGE===========
        
        //connect to database
        Connection conn = DriverManager.getConnection(CONNECTION_STRING, USER_NAME, PASSWORD);
        
        //delete record
        String SQL = "DELETE FROM landscape WHERE CustomerID = ?";
        PreparedStatement ps = conn.prepareStatement(SQL);
        ps.setInt(1, customerID);
        ps.execute();
        
        //close connection
        conn.close();
        
        //============THIS IS FOR FILE STORAGE======================
        //        //get all records
        //        ArrayList<Customer> customers = getList();
        //        
        //        //delete the old file
        //        File oldFile = new File("customers.txt");
        //        if(oldFile.exists() == false){
        //            throw new IOException("File does not exist!");
        //           
        //        }
        //        oldFile.delete();
        //        
        //        //write "good" records to the file
        //        for(Customer cust : customers){
        //            if(deleteName.equalsIgnoreCase(cust.getName()) == false){
        //                //good record so write it to the file
        //                add(cust);
        //            }
        //        }
    }
}
