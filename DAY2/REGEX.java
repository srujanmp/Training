/*

REGEX:

^    -start
$    -end   
?=   -optional
*    -0 or many  
+    -1 or many

A-Z  
0-9
{min,max} -length

import java.util.regex.*;
if(!Pattern.matches("^[A-Za-z]{3,}$",fullName)){
    invalid
}
Pattern.matches



*/
import java.util.*;

class REGEX{
    static HashMap<String,String> patterns=new HashMap<>();
    static{
        patterns.put("name","^[a-zA-Z ]{3,}$");
        patterns.put("password","^(?=.[@#$!_])[A-Za-z0-9?=.@#$!_]{8,}$");
        patterns.put("adhaar","^[0-9]{12}$");
        patterns.put("pan","^[A-Z]{5}[0-9]{4}[A-Z]{1}$");
        patterns.put("email","^[a-z0-9-_]{2,}@[a-z]");

    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        //camelCase
        System.out.println("KYC:");
        //name
        System.out.print("Enter Full Name: ");
        String fullName=sc.nextLine();
        //password
        System.out.print("Enter Password: ");
        String password=sc.next();
        
        //adhar
        System.out.print("Enter Adhar: ");
        long adhar=sc.nextInt();

        //pan - 5 letters + 4 numbers + 1 letter 
        System.out.print("Enter PAN: ");
        String pan=sc.next();

        ///email
        System.out.print("Enter Email: ");
        String email=sc.next();

        sc.close();
    }
}