import java.util.Scanner;
import java.math.*;
import java.time.*;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;



public class ExpenseInput 
{

    public static void Recorder(){

    BigDecimal x = null;
    Boolean CorrectDate = false;
    File file = null;
    
    Scanner sc = new Scanner(System.in);
    System.out.printf("Do you want to add your expenses? %nPlease enter yes or no to proceed: ");
    String response = sc.nextLine().trim().toLowerCase();

    while (!response.equals("yes") && !response.equals("no"))
    {
        System.out.printf("Please enter the correct response. %nDo you want to add your expenses? %nPlease enter yes or no to proceed: ");
        response = sc.nextLine().trim().toLowerCase();
    }
    while (response.equals("yes"))
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
                String data = RecordName + "," + RecordItemName + ","+RecordPrice+ "," + RecordQuantity + "," + RecordDate;
                System.out.println(" Name: " + RecordName + " Item: " + RecordItemName + " Price: "+RecordPrice+ " Quantity: "+ RecordQuantity + " Date: " + RecordDate);
                try {
                    file = new File("Expenses.csv");
                    // BufferWriter needs FileWriter to parse data in the textfile, true statement mean to add at the end of the file. 
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
                    writer.write(data+"\n");
                    System.out.println("Data added");
                    writer.close();
                }
                catch (Exception e){
                    System.out.println(e);
        
                }
            }
            catch(DateTimeException e){
                System.out.println(e);
                CorrectDate = false;
                System.out.println("Enter the day of purchase: ");
                RecordDay = sc.nextInt(); 
            }
            

        }
        CorrectDate = false;
        sc.nextLine();
        System.out.println("Do you want to add more? yes or no");
        response = sc.nextLine().trim().toLowerCase();
        while (!response.equals("yes") && !response.equals("no"))
        {
            System.out.printf("Please enter the correct response. %nDo you want to add your expenses? %nPlease enter yes or no to proceed: ");
            response = sc.nextLine().trim().toLowerCase();
        }
    }
    // else
    // {
    //     System.out.println("Completed Recording");
    // }
    sc.close();
    

    };
        
    public static void main(String[] args)
    {
        Recorder();

    }
}
