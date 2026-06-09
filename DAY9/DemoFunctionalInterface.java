interface Cosmo{
    void printFizz();
}

public class DemoFunctionalInterface{
    static int[] arr={12,15,16,20,4,9,10};
    public static void main(String[] args) {
        Cosmo cosmo=()->{
            for(int each:arr)
                if(each%3==0&&each%5!=0)
                    System.out.println("Fizz");
        };
        cosmo.printFizz();
    }
}