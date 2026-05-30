
import java.util.Arrays;

class Move{
    public static void main(String[] args){
        int[] nums={1,0,2,3,4,0,0,2,3,7,6};

        int k=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=0){

                int temp=nums[k];
                nums[k]=nums[i];
                nums[i]=temp;
                k++;
            }
        }
        System.out.println(Arrays.toString(nums));
    }
}