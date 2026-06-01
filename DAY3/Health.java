/*
Calculate health metrics

BMI >> height, weight
BMI=w/(h*h)

Body age>> age,BMI
    BMI<18.5 then age=age+2
    BMI>25 then age=age+5
    18.5>BMI>25 age=age
Fat Rate 
Body fat percentage (BFP) formula for adult males:

BFP = 1.20 × BMI + 0.23 × Age - 16.2

Body fat percentage (BFP) formula for adult females:

BFP = 1.20 × BMI + 0.23 × Age - 5.4
 */

//POJO
class Patient {

    private String name;
    private int age;
    private String gender;
    private double weight, height, bmi;

    //@ setter BMI method
    public void set(double bmi) {
        this.bmi = bmi;
    }

    //@ getter for BMI 
    public double get() {
        return this.bmi;
    }

    //@ set name, age, gender, weight, height
    public void set(String name, int age, String gender, double weight, double height) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }
}

public class Health {

    public static void calculate(int age, double bmi) {
        System.out.print("Body age: ");
        if (bmi < 18.5) {
            System.out.println(age + 2); 
        }else if (bmi > 25) {
            System.out.println(age + 5); 
        }else {
            System.out.println(age);
        }
    }

    public static void calculate(double bmi, int age, String gender) {
        System.out.print("Fat rate: ");
        if (gender.equals("Male")) {
            System.out.println(1.20 * bmi + 0.23 * age - 16.2); 
        }else {
            System.out.println(1.20 * bmi + 0.23 * age - 5.4);
        }
    }

    public static void calculate(Patient p) {
        System.out.print(p.getName() + " BMI: ");
        p.setHeight(p.getHeight() / 100);
        p.setBmi(p.getWeight() / (p.getHeight() * p.getHeight()));
        System.out.println(p.getBmi());
        calculate(p.getBmi(), p.getAge(), p.getGender());
        calculate(p.getAge(), p.getBmi());
    }

    public static void main(String[] args) {
        Patient pat1 = new Patient();
        pat1.set("Srujan", 20, "Male", 70.3, 153.2);

        Patient pat2 = new Patient();
        pat2.set("nik", 20, "Female", 40.9, 154.9);
        calculate(pat1);
        calculate(pat2);
    }
}
