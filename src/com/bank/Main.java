package com.bank;
import java.util.Scanner;
public class Main {
    public static void main(String args[]){
        Bank obj = new Bank();
        Scanner sc = new Scanner(System.in);
        String choise;
        do {
            System.out.println("===============Banking Management System===============");
            System.out.println("1 : Create Account");
            System.out.println("2 : Update Account Details");
            System.out.println("3 : Show All Accounts");
            System.out.println("4 : Check Account Details");
            System.out.println("5 : Close Account");
            System.out.println("6 : Diposit");
            System.out.println("7 : Withdraw");
            System.out.println("8 : Check Balance");
            System.out.println("E : Exit");
            System.out.println("========================================================");
            System.out.println("Enter Your Choise : ");
            choise = sc.next();
            switch (choise){
                case "1":
                    obj.accountCreation();
                    break;
                case "2":
                    obj.updateDetails();
                    break;
                case "3":
                    obj.allAaccounts();
                    break;
                case "4":
                    obj.accountDetailsCheck();
                    break;
                case "5":
                    obj.accountClose();
                    break;
                case "6":
                    obj.Diposite();
                    break;
                case "7":
                    obj.Withdraw();
                    break;
                case "8":
                    obj.chkBal();
                case "E":
                    System.out.println("Exiting Program");
                    break;
                default:
                    System.out.println("❌ Invalid Choice. Please try again");
                    break;
            }

        }while (!choise.equals("E"));
        sc.close();
    }
}


