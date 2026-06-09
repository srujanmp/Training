import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class DemoLambda{
    public static void main(String[] args) {
        // List<Integer> list=List.of(2,3,4,5);
        // list.add(2); //Exception Immutable list


        List<Integer> list=new ArrayList<>();
        list.add(98);
        list.add(67);
        list.add(11);
        list.add(34);

        System.out.println(list);

        Collections.sort(list,new Comparator<Integer>() {
            public int compare(Integer a,Integer b){
                return b-a;
            }
        });

        Collections.sort(list,(a,b)->b-a);

        System.out.println(list);

    }
}