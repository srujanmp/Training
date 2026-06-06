

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

class Patient implements Comparable<Patient>,Serializable{
    String name;
    int age;
    Patient(String name,int age){
        this.name=name;
        this.age=age;
    }
    @Override
    public int compareTo(Patient o) {
        return this.age - o.age;
    }
    @Override
    public String toString() {
        return "Patient [name=" + name + ", age=" + age + "]";
    }
    
}


public class Serializing {
    static File file = new File("PatientRecord.doc");
    // write objects into file
    public static void serialize(List<Patient> patients) throws IOException{
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(patients);
        oos.close();
        fos.close();
    }
    // read objects from file
    public static void deserialize() throws IOException, ClassNotFoundException{
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        List<Patient> exists = 
                 (List<Patient>)ois.readObject();
        ois.close();
        fis.close();
        System.out.println(exists);
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Patient patient1= new Patient("Manikandan", 24);
        Patient patient2= new Patient("Vidya", 18);
        Patient patient3= new Patient("Richard", 47);
        List<Patient> myList = List.of(patient1,patient2,patient3);
        serialize(myList);
        deserialize();
    }
}