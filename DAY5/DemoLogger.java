import java.util.Scanner;
import java.util.logging.Logger;

public class DemoLogger {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Logger logger=Logger.getLogger(DemoLogger.class.getName());
        int n=0;
        int[] nums={1,2,3,4,5};
        try {
            System.out.println("Enter number 0-4");
            n=sc.nextInt();
            System.out.println(nums[n]);
        } catch (Exception e) {
            
        } finally{

        }
    }


}

//Singleton
// class KYC{
//      String name;long contact;
//      private static KYC kyc= new KYC();
//      public static KYC getKyc(){
//          return kyc;
//      }
//      private KYC(){}
//  }