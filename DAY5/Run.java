import java.io.IOException;
import java.util.Scanner;

public class Run{
    public static void main(String[] args) throws InterruptedException, IOException {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter command >> ");
        String app=sc.nextLine();
        
        Runtime run=Runtime.getRuntime();
        Thread.sleep(1000);
        run.exec(app);







    }


    class Srujan extends Exception{
        
    }
}