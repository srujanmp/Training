import java.util.*;
public class SortColors{
    public static void main(String[] args) {
        int nums[]={2,0,0,1,0,2,0,1};
        int res[]=new int[nums.length];int k=0;

        for(int i=0;i<nums.length;i++){
            if(nums[i]==0)
            res[k++]=0;
        }

        for(int i=0;i<nums.length;i++){
            if(nums[i]==1)
            res[k++]=1;
        }


        for(int i=0;i<nums.length;i++){
            if(nums[i]==2)
            res[k++]=2;
        }


        System.out.println(Arrays.toString(res));
    }
}