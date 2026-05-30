import java.util.*;

class RemoveDuplicates{
    public static void main(String[] args) {
        int []nums={1,1,4,4,5,6,7,7,8,9,9,10,10};
        int i=0;
        HashSet<Integer> set=new HashSet<>();
        for(int n:nums){
             //set.add() return true if
             // n is not present aldready
             //set.add() return false if n is present in set
            if(set.add(n)){
                nums[i++]=n;
            }
        }

        System.out.println(Arrays.toString(nums));
    }
}