import java.util.Scanner;
import java.math.*;
import java.time.*;

public class ExpenseInput 
{

    public static void Recorder(){

    BigDecimal x = null;
    Boolean CorrectDate = false;
    
    Scanner sc = new Scanner(System.in);
    System.out.printf("Do you want to add your expenses? %nPlease enter yes or no to proceed: ");
    String response = sc.nextLine().trim().toLowerCase();

    while (!response.equals("yes") && !response.equals("no"))
    {
        System.out.printf("Please enter the correct response. %nDo you want to add your expenses? %nPlease enter yes or no to proceed: ");
        response = sc.nextLine().trim().toLowerCase();
    }
    if (response.equals("yes"))
    {
        System.out.println("Enter Name: ");
        String RecordName = sc.nextLine();
        System.out.println("Enter Item Name: ");
        String RecordItemName = sc.nextLine();
        System.out.println("Enter Price: ");
        String RecordPrice = sc.nextLine();
        x = new BigDecimal(RecordPrice);
        Integer y = x.scale();
        while (y > 2)
        {
            System.out.println("Enter Price: ");
            RecordPrice = sc.nextLine();
            x = new BigDecimal(RecordPrice);
            y = x.scale();
        }
        System.out.println("Enter Quantity: ");
        Integer RecordQuantity = sc.nextInt();
        System.out.println("Enter the year of purchase: ");
        Integer RecordYear = sc.nextInt();
        while (RecordYear<= 1990 || RecordYear >= LocalDate.now().getYear()){
            System.out.printf("Year is not in the range %nEnter the year of purchase: ");
            RecordYear = sc.nextInt();
        }
        System.out.println("Enter the month of purchase: ");
        Integer RecordMonth = sc.nextInt();
        while (RecordMonth <=0 || RecordMonth >12){
            System.out.printf("Month is not in the range %nEnter the Month of purchase: ");
            RecordMonth = sc.nextInt();
        }
        System.out.println("Enter the day of purchase: ");
        Integer RecordDay = sc.nextInt(); 
        while (CorrectDate == false){
            try{
                LocalDate RecordDate = LocalDate.of(RecordYear,RecordMonth,RecordDay);
                CorrectDate = true;
                System.out.println(" Name: " + RecordName + " Item: " + RecordItemName + " Quantity: "+ RecordQuantity + " Date: " + RecordDate);
            }
            catch(DateTimeException e){
                System.out.println(e);
                CorrectDate = false;
                System.out.println("Enter the day of purchase: ");
                RecordDay = sc.nextInt(); 
            }
        }        
    }
    else
    {
        System.out.println("Completed Recording");
    }
    sc.close();

    };
    
    public static void main(String[] args)
    {
        Recorder();

    }
}
