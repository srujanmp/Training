
import java.util.HashSet;

class MinMissing{
    public static void main(String[] args) {
        int []nums={8,10,3,1,4,2,5};
        HashSet<Integer> set=new HashSet<>();
        int min=Integer.MAX_VALUE;
        // put nums into set and found min
        for(int i=0;i<nums.length;i++){
            set.add(nums[i]);
            min=Math.min(nums[i],min);
        }

        // increment min until its not present in set 
        while(true){
            if(set.contains(min)){
                min++;
            }else break;
        }
        System.out.println(min);
    }
}