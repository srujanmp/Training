//Sliding Window Maximum

import java.util.HashSet;

public class SWMax {
    public static void main(String[] args) {
        int k=3;
        int []nums={1,3,-1,-3,5,3,6,7};


        int []res=new int[nums.length-k+1];
        int max=0;

        //SET THE FIRST WINDOW
        int l=0;
        int r=0;

        for(r=0;r<k;r++){
            max=nums[r];
        }

        //compute other
        while(r<nums.length){
            res[l]=max;
            
            l++;
            r++;
        }

        HashSet<Integer> set=new HashSet<>();
        



    }
}
