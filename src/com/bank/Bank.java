package com.bank;
import java.sql.*;
import java.util.Scanner;
public class Bank{

    String url = "jdbc:mysql://localhost:3306/";
    String user = "root";
    String password = "Admin@123";
    String db = "BANK";

    // Account Creation
    public void accountCreation(){
    try(Connection con = DriverManager.getConnection(url+db,user,password);){
        Scanner sc = new Scanner(System.in);
        String query = "INSERT INTO ACCOUNTS(NAME, EMAIL, PHONE, ADDRESS, CREATED_AT) VALUES (?,?,?,?, CURRENT_TIMESTAMP);";
        PreparedStatement pstmt = con.prepareStatement(query);
        String name,email,phone,address;
        System.out.println("Enter Coustomer Name:");
        name = sc.next();
        System.out.println("Enter Coustomer Email:");
        email = sc.next();
        System.out.println("Enter Coustomer Phone Number:");
        phone = sc.next();
        System.out.println("Enter Coustomer Address:");
        address = sc.next();
        pstmt.setString(1,name);
        pstmt.setString(2,email);
        pstmt.setString(3,phone);
        pstmt.setString(4,address);
        pstmt.execute();
        System.out.println("✅ Account Created Successfully...");
    } catch (Exception e) {
        System.out.println("❌ Error:"+ e.getMessage());
    }
    }

    // Update Customer Details
    public void updateDetails(){
        try (Connection con = DriverManager.getConnection(url+db,user,password)){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Coustomer Account Number to Update : ");
        int ac_no = sc.nextInt();
        System.out.println("Enter new Name");
        String name = sc.next();
        System.out.println("Enter new Email");
        String email = sc.next();
        System.out.println("Enter new Phone Number");
        String phone = sc.next();
        System.out.println("Enter new Address");
        String address = sc.next();
        String query = "UPDATE ACCOUNTS SET NAME = ?, EMAIL = ?, PHONE = ?, ADDRESS = ? WHERE AC_NO = ?;";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1,name);
        pstmt.setString(2,email);
        pstmt.setString(3,phone);
        pstmt.setString(4,address);
        pstmt.setInt(5,ac_no);
        pstmt.executeUpdate();
        System.out.println("✅ Update Successful...");
        con.close();
        } catch (Exception e) {
            System.out.println("❌ Error:"+ e.getMessage());
        }
    }

    // Show All Accounts
    public void allAaccounts(){
        try(Connection con = DriverManager.getConnection(url+db,user,password)) {
            String query = "SELECT * FROM ACCOUNTS;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                System.out.println("---------------------------------------------------------------------------------------------------");
                System.out.println("Account Number : " + rs.getInt(1));
                System.out.println("Account Name : " + rs.getString(2));
                System.out.println("Email : " + rs.getString(3));
                System.out.println("Phone Number : " + rs.getString(4));
                System.out.println("Address : " + rs.getString(5));
                System.out.println("Balance : " + rs.getDouble(6));
                System.out.println("Account Opening Date : " + rs.getTimestamp(7));
                System.out.println("---------------------------------------------------------------------------------------------------");
            }
        } catch (Exception e) {
            System.out.println("❌ Error:"+ e.getMessage());
        }
    }

    // Account Details Check
    public void accountDetailsCheck(){
        try (Connection con = DriverManager.getConnection(url+db,user,password)){
            Scanner sc = new Scanner(System.in);
            String query = "SELECT * FROM ACCOUNTS WHERE NAME = ?;";
            PreparedStatement pstmt = con.prepareStatement(query);
            System.out.println("Enter Account Name: ");
            String name = sc.next();
            pstmt.setString(1,name);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                System.out.println("Account Number : "+ rs.getInt(1));
                System.out.println("Account Name : "+ rs.getString(2));
                System.out.println("Email : "+ rs.getString(3));
                System.out.println("Phone : "+ rs.getString(4));
                System.out.println("Address : "+ rs.getString(5));
                System.out.println("Balance : "+ rs.getDouble(6));
                System.out.println("Account Opening Date : "+ rs.getTimestamp(7));
            }
            System.out.println("✅Details Fetch Successfully...");
            con.close();
        } catch (Exception e) {
            System.out.println("❌ Error:" + e.getMessage());
        }
    }

    // Account Close
    public void accountClose(){
        try (Connection con = DriverManager.getConnection(url+db,user,password)){
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter Account Number: ");
            int ac_no = sc.nextInt();
            String query = "DELETE FROM ACCOUNTS WHERE AC_NO = ?;";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1,ac_no);
            pstmt.executeUpdate();
            System.out.println("✅Account Close Successfully...");
            con.close();
        } catch (Exception e) {
            System.out.println("❌ Error:" + e.getMessage());
        }
    }

    // Diposite
    public void Diposite(){
    try(Connection con = DriverManager.getConnection(url+db,user,password);){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Account Number: ");
        int ac_no = sc.nextInt();
        System.out.println("Enter Deposite Amount: ");
        double depositeAmount = sc.nextDouble();
        String SelectQuery = "SELECT BALANCE FROM ACCOUNTS WHERE AC_NO = ?;";
        String UpdateQuery = "UPDATE ACCOUNTS SET BALANCE = ? WHERE AC_NO = ?;";
        PreparedStatement select = con.prepareStatement(SelectQuery);
        PreparedStatement update = con.prepareStatement(UpdateQuery);
        select.setInt(1,ac_no);
        ResultSet rs = select.executeQuery();
        if (rs.next()){
            double currentBal = rs.getDouble(1);
            double newBal = currentBal+depositeAmount;
            update.setDouble(1,newBal);
            update.setInt(2,ac_no);
            update.executeUpdate();
        }
        System.out.println("✅Deposite Successfull...");
        con.close();
    } catch (Exception e) {
        System.out.println("❌ Error:" + e.getMessage());
    }
    }

    // Withdraw
    public void Withdraw(){
        try(Connection con = DriverManager.getConnection(url+db,user,password);){
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter Account Number: ");
            int ac_no = sc.nextInt();
            System.out.println("Enter Withdraw Amount: ");
            double withdrawAmount = sc.nextDouble();
            String SelectQuery = "SELECT BALANCE FROM ACCOUNTS WHERE AC_NO = ?;";
            String UpdateQuery = "UPDATE ACCOUNTS SET BALANCE = ? WHERE AC_NO = ?;";
            PreparedStatement select = con.prepareStatement(SelectQuery);
            PreparedStatement update = con.prepareStatement(UpdateQuery);
            select.setInt(1,ac_no);
            ResultSet rs = select.executeQuery();
            if (rs.next()) {
                double currentBal = rs.getDouble(1);
                if (currentBal >= withdrawAmount) {
                    double newBal = currentBal - withdrawAmount;
                    update.setDouble(1, newBal);
                    update.setInt(2, ac_no);
                    update.executeUpdate();
                }else {
                    System.out.println("❌ Insufficient Balance!!!");
                }
            }
            System.out.println("✅Withdraw Successfull...");
            con.close();
        } catch (Exception e) {
            System.out.println("❌ Error:" + e.getMessage());
        }
    }

    // Balance Check
    public void chkBal(){
        try(Connection con = DriverManager.getConnection(url+db,user,password);){
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter Account Number: ");
            int ac_no = sc.nextInt();
            String Query = "SELECT BALANCE FROM ACCOUNTS WHERE AC_NO = ?;";
            PreparedStatement pstmt = con.prepareStatement(Query);
            pstmt.setInt(1,ac_no);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                double currentBal = rs.getDouble(1);
                System.out.println("Balance = "+currentBal);
            }
            System.out.println("✅ Balance Checked...");
            con.close();
        } catch (Exception e) {
            System.out.println("❌ Error:" + e.getMessage());
        }
    }
}
