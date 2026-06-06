// package DAY8;
/*
java.io.File
    file and f
*/

import java.util.Scanner;

public class FileDemo {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);

        String eachline="";
        int i=1;
        while (!(eachline=sc.nextLine()).equals("")) { 
            System.out.println(i+" "+eachline);
            i++;
        }
    }
}
