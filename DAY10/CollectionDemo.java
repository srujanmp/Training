
import com.sun.org.apache.xpath.internal.operations.String;
import java.util.ArrayList;
// package DAY10;
interface Remote<T>{
    int ternary(T key);
}


// binary seach- O(log(2)n)
// ternary search- O(log(3)n)

class GenStore<T> implements Remote<T>{
    T[] nums;

    @Override
    public int ternary(T target) {
        
        int s=0;
        int e=nums.length-1;

        while(s<=e){
            int m1=s+(e-s)/3;
            int m2=e-(e-s)/3;
            if(target==nums[m1]){
                return m1;
            }else if(target==nums[m2]){
                return m2;
            }else if(target<nums[m1]){
                e=m1-1;
            }else if(target>nums[m1]&&target<nums[m2]){
                s=m1+1;
                e=m1-1;
            }else if(target>nums[m2]){
                s=m2+1;
            }


        }
        return 0;
    }    
}
public class CollectionDemo {
    
}
