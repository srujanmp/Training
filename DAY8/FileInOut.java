// package day8;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileInOut {
    // default location: same folder where vs code opened
    static File file = new File("Logs.doc");
    public static void writeToFile() throws IOException{
        String exists = readFromFile();
        DataInputStream dis = new DataInputStream(System.in);
        FileOutputStream fos = new FileOutputStream(file);
        String  each = "";
        StringBuilder sb = new StringBuilder(exists);
        while(!(each=dis.readLine())
                    .equals("EOF")){
            sb.append(each);
        }
        fos.write(sb.toString().getBytes());
        fos.close();dis.close();
    }
    public static String readFromFile() throws IOException{
        // file = new File("day7//PojoSet.java");
        FileInputStream fis = new FileInputStream(file);
        String line = "";int size = fis.available();
        byte[] temp = new byte[size];
        fis.read(temp);
        line = new String(temp);
        fis.close();
        return line;
    }
    public static void main(String[] args) throws IOException {
        // writeToFile();
        String content = readFromFile();
        System.out.println(content);
    }
}