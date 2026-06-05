import java.util.*;

class Patient implements Comparable<Patient>{
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

class PojoSet{
    public static void main(String[] args) {
        Patient p1=new Patient("Sujan",20);
        Patient p2=new Patient("Sajan",67);
        Patient p3=new Patient("Sijan",10);

        // create 3 type of sets
        HashSet hashset=new HashSet<>();
        LinkedHashSet linkedset=new LinkedHashSet<>();
        TreeSet treeset=new TreeSet<>();  //always sorted

        //add objects to hashset
        hashset.add(p1);
        hashset.add(p2);
        hashset.add(p3);  
        
    
        //copy to linkedlist
        linkedset.addAll(hashset);

        
        //copy to treeset
        treeset.addAll(hashset);



        System.out.println(hashset.toString());
        System.out.println(linkedset.toString());
        System.out.println(treeset.toString());

    }
}