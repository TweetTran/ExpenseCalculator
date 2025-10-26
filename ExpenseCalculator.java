// This file contains the methods to read data from the CSV file and calculate the total amount each user spent for the month. The CSV file is sorted base on it date 

import java.time.*;
import java.time.format.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;


public class ExpenseCalculator {

    static void ExpensesKeeperApp() throws IOException{
        // Expense class 
        class monthlyExpense {
            String Name;
            int year;
            int month;
            double total;
        }
        class Expense{ 
            String Name; 
            String Item; 
            Double Price; 
            Integer Quantity; 
            LocalDate PurchaseDate; 
        }

        File file = null;
        try {
            file = new File("Expenses.csv");
        }
        catch (Exception e){
            System.out.print("error in opening the file");
        }
        String line;

        FileReader csvReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(csvReader);
        List<Expense> ExpenseList = new ArrayList<>();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        bufferedReader.readLine();
        while ((line = bufferedReader.readLine()) != null) { 
            String[] data = line.split(","); 
            Expense e = new Expense(); 
            e.Name = data[0].trim(); 
            e.Item = data[1].trim(); 
            e.Price = Double.parseDouble(data[2].trim()); 
            e.Quantity = Integer.parseInt(data[3].trim()); 
            e.PurchaseDate = LocalDate.parse(data[4].trim(),dateTimeFormatter); 
            ExpenseList.add(e); 
        }
        bufferedReader.close();

        HashMap <String, Double> monthlyTotal = new HashMap<>();
        for (Expense e: ExpenseList){
            String MonthYear = e.PurchaseDate.getYear() + "/" +String.format("%02d", e.PurchaseDate.getMonthValue());
            String Key = e.Name +"-" + MonthYear;
            Double Total = e.Price * e.Quantity; 

            monthlyTotal.put(Key, monthlyTotal.getOrDefault(Key, 0.0)+Total);// getOrDefault is the map method mean “Return the value for this key if it exists; otherwise, return the default value I give you.”

        }

        // printing out the result : .split(-(?=\\d)) mean that split at - with (?=...) positive lookahead and \\d mean interger digit from (0-9)
        monthlyTotal.forEach((key, total) -> System.out.printf("User: %-3s  Date: %s    Total: $%-10.2f%n", key.substring(0,key.length()-8), key.substring(key.length()-7),(total + (total*0.5))));
        
    }
    public static void main(String[] args) throws IOException {
        // LocalDateTimeExample();
        // ManipulateLocalDateTimeExample();
        // ExpenseCalculatorExample();
        //CreateCSV();
        // 
        ExpensesKeeperApp();
        
    }
       // Initialized data storage
       static void CreateCSV() throws IOException {
        // Get current working directory
        File csvFile = new File("Expenses.csv");
        // Create csvFiles folder if it doesn't exist

        // Full CSV file path
        // Write CSV content
        FileWriter writer = new FileWriter(csvFile);

        writer.append("Name,Item,Price,Quantity,DateOfPurchase \n");

        writer.append("John, Tech, 19.99, 1, 2025-08-03\n");
        writer.append("John, Bag, 9.99, 1, 2025-08-04\n");
        writer.append("John, CatToy, 15.98, 1, 2025-08-20\n");
        writer.append("Lisa, SkinCare, 15.30, 1, 2025-08-07\n");
        writer.append("Lisa, Straws, 8.99, 1, 2025-08-18\n");
        writer.append("Lisa, Shoes, 99.95, 1, 2025-08-27\n");
        writer.append("Mary, BirthdayCards, 3.30, 6, 2025-08-15\n");
        writer.append("Mary, Ballons, 2.37, 10, 2025-08-20\n");

        writer.append("John, Roses, 9.99, 12, 2025-09-01\n");
        writer.append("John, Chocolate, 33.98, 1, 2025-09-15\n");
        writer.append("John, Book, 8.34, 1, 2025-09-20\n");
        writer.append("John, Watch, 985.63, 1, 2025-09-29\n");
        writer.append("Lisa, Dress, 39.99, 1 , 2025-09-09\n");
        writer.append("Lisa, Heels, 540.00, 1, 2025-09-09\n");
        writer.append("Mary, Paint, 6.17, 12, 2025-09-13\n");


        writer.close();

        System.out.print("");
    }

    // TESTING SECTION- IGNORABLE 

    // LocalDate (creating, adding days/months, formatting, comparing).

    // static void LocalDateTimeExample(){
    //     // Creating current date and time 
    //     LocalDate date = LocalDate.now();
    //     System.out.println("Current Date: " + date); // 2025-10-01

    //     LocalTime time = LocalTime.now();
    //     System.out.println("Current Time: " +time); // 04:32:25.197061

    //     LocalDateTime dateTime = LocalDateTime.now();
    //     System.out.println("Current DateTime: " + dateTime); // 2025-10-01T04:32:25.197061

    //     // Adding days/months/years/hours/minutes
    //     LocalDateTime PlaceHolderDateTime = LocalDateTime.of(1997, 4, 9, 8, 30);
    //     System.out.println("My Birthdate is on: " + PlaceHolderDateTime); // 1997-04-09T
    //     LocalDateTime AddedDateHolder = PlaceHolderDateTime.plusYears(28);
    //     System.out.println("After 28 years: " + AddedDateHolder); // 2025-04-09T08:30


    //     // Format of dateTime instance 
    //     DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MM YYYY HH:mm");
    //     String newDateFormat = dateTime.format(format);
    //     System.out.println("DateTime: " + newDateFormat); // 01 10 2025 10:27

    //     // Creating a specific date and time 
    //     LocalDateTime date1 = LocalDateTime.of(2025, 1, 25, 4,04);
    //     LocalDateTime date2 = LocalDateTime.of(2025,1, 26,10, 20);
    //     System.out.printf("Date1: %s %nDate2: %s%n", date1,date2);

    //     // Comparing dates
    //     if(date1.isEqual(date2)){
    //         System.out.println("Date 1 and date 2 are equal");
    //     }
    //     else if(date1.isBefore(date2)){
    //         System.out.println("Date 1 is before date 2");
    //     }
    //     else if(date1.isAfter(date2)){
    //         System.out.println("Date 1 is after date 2");
    //     }
    // }
    
    // // Parsing strings into dates and doing date arithmetic: can only parse from object into primitive types
    // static void ManipulateLocalDateTimeExample(){

    //     // Parsing from String to primitive int 
    //     String num = "2025";
    //     int parsedNum = Integer.parseInt(num);
    //     System.out.println("Parsed Number: " + parsedNum);
    //     LocalDate currentDate  = LocalDate.now();
    //     System.out.println("Current Date: " + currentDate);

    //     // Parsing from String as LocalDate
    //     DateTimeFormatter DOBFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    //     LocalDate DOB = null;
        

    //     Scanner input = new Scanner(System.in);
    //     System.out.println("What's your Date of Birth? (yyyy-MM-dd)");
    //     String userDOB = input.next();

    //     while (DOB == null){
    //         try{
    //             LocalDate tempDOB = LocalDate.parse(userDOB, DOBFormat);
    //             if (tempDOB.isAfter(LocalDate.now().minusYears(150)) || tempDOB.isBefore(LocalDate.now()) || tempDOB.isEqual(LocalDate.now())){
    //                 DOB = tempDOB;
    //                 System.out.println("Your DOB is: " + userDOB);
    //             }
    //             else{
    //                 System.out.println("Invalid Date. Please enter a valid DOB (YYYY-MM-DD)");
    //                 userDOB = input.next();
    //                 continue;
    //             }
    //         }
    //         catch (DateTimeParseException e){
    //             System.out.println("Invalid Date Format. Please use YYYY-MM-DD");
    //             userDOB = input.next();
    //         }
    //     }
    //     // close Scanner object
    //     input.close();

    // }
    
    // // Calculate Expenses  
    // static void ExpenseCalculatorExample(){
    //     // scanner class - user input
    //     Scanner scan = new Scanner(System.in);
    //     Double price = 0.00;
    //     Double total = 0.00;
    //     Double tax = 0.00;
    //     Boolean addItem = true;
    //     int itemCount = 1;
    //     int numItem = 0;

    //     while(addItem){
    //         System.out.printf("Item %d price: " , itemCount);
    //         price = scan.nextDouble();
    //         if (price <= 0.00){
    //             System.out.println("The item can not be negative, try again: ");
    //             price = scan.nextDouble();
    //         }
    //         else{
    //             System.out.printf("How many of the item %d did you bought: ", itemCount);
    //             numItem = scan.nextInt();
    //             if (numItem <= 0){
    //                 System.out.printf("The item %d can not be less than or equal to 0, try again ", itemCount);
    //                 numItem = scan.nextInt();
    //             }
    //             else{
    //                 price = price * numItem;
    //                 total = total + price;
    //             }
    //         }
    //         tax = Math.round((total + (total * 0.05))*100.0)/100.0;
            
    //         System.out.printf("the item number %d cost %.2f, the total is %.2f with tax is %.2f%n", itemCount, price, total, tax);
    //         System.out.println("To continue adding item press 1 else 0: ");
    //         int choice = scan.nextInt();
    //         addItem = (choice==1);
    //         itemCount ++;

    //     }
    //     scan.close();

    // }
    
    // Expenses Keeper Application
    // You work for a small software company that needs to automatically generate invoices for customers. Each invoice includes a list of purchased items, their prices, and quantities. You need to calculate totals and due dates.
    // Customer name as string, each customer have a list of items purchased (item name, price, quantity), invoice date (current date), due date (end of the month).
    // Calculate total amount for each item (price * quantity) and overall total for the invoice
    // Output will display customer name, invoice date, due date, list of items with their total amounts, and overall total amount.
    // Add tax, into each purchase, 


    
    //Lambda Example :  a shorter way to write code 
    static void LambdaExample(){

        @FunctionalInterface
        interface AddMath{
            int add (int i, int j);

        }

        // // Adding Lambda will shorten this 
        // AddMath test = new AddMath(){
        //     public int add(int i, int j){
        //         return i+j;
        //     }
        // };

        // create object of the interface, give it parameter and direct what is does 
        AddMath test = (i,j) -> i+j; 

        int total = test.add(4,5);
        System.out.print(total);
    }


    
}
    



