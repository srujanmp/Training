import java.util.Arrays;
import java.util.HashSet;

public class FirstMissingPositive41 {
    public static void main(String[] args) {
        //no stream

        int[] nums={7,8,9,11,12};
        HashSet<Integer> set=new HashSet<>();
        Arrays.stream(nums).forEach(set::add);

        for(int i=1;;i++){
            if(!set.contains(i)){
                System.out.println(i);
                break;
            }
        }

        

    }
}
