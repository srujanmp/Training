// package DAY10;
import java.lang.String
import java.util.Arrays;

class WareHouse<T>{
    private T[] data;
    public void set(T[] data){
        this.data=data;
    }
    public T[] get(){
        return data;
    }
}
public class Main {
    public static void main(String[] args) {
        int count = 30;
        for (int i = 1; i <= count; i++) {
            System.out.println("Hello");
        }
    }
}
public class GerenicDemo {
    public static void main(String[] args) {
        WareHouse<Integer> house1=new WareHouse<>();
        house1.set(new Integer[]{1,2,3,4});
        
        WareHouse<String> house2=new WareHouse<>();
        house2.set(new String[]{"Cognizant","a","s"});

        System.out.println(Arrays.toString(house1.get()));
        System.out.println(Arrays.toString(house2.get()));
    }
}
