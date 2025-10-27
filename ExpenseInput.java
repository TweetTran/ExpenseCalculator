import java.util.Scanner;
import java.math.*;

public class ExpenseInput {

    public static void Recorder(){

    BigDecimal x = null;
    
    Scanner sc = new Scanner(System.in);
    System.out.printf("Do you want to add your expenses? %nPlease enter yes or no to proceed: ");
    String response = sc.nextLine().trim().toLowerCase();

    while (!response.equals("yes") && !response.equals("no")){
        System.out.printf("Please enter the correct response. %nDo you want to add your expenses? %nPlease enter yes or no to proceed: ");
        response = sc.nextLine().trim().toLowerCase();
    }
    if (response.equals("yes")){
        System.out.println("Enter Name: ");
        String RecordName = sc.nextLine();
        System.out.println("Enter Item Name: ");
        String RecordItemName = sc.nextLine();
        System.out.println("Enter Price: ");
        String RecordPrice = sc.nextLine();
        x = new BigDecimal(RecordPrice);
        Integer y = x.scale();
        while (y > 2){
            System.out.println("Enter Price: ");
            RecordPrice = sc.nextLine();
            x = new BigDecimal(RecordPrice);
            y = x.scale();
        }
        System.out.println("Enter Quantity: ");
        Integer RecordQuantity = sc.nextInt();
        System.out.println("Enter the day of purchase: ");
        Integer RecordDay = sc.nextInt(); 
        System.out.println("Enter the month of purchase: ");
        Integer RecordMonth = sc.nextInt();
        System.out.println("Enter the year of purchase: ");
        Integer RecordYear = sc.nextInt();
    }
    else{
        System.out.println("Completed Recording");
    }
    sc.close();

    };
    
    public static void main(String[] args){
        Recorder();

    }
}
