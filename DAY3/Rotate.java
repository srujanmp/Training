import java.util.*;
class Rotate{
    /*
        rotate array by n steps to right
    */
    static void reverse(int []nums,int l,int r){
        while(l<r) {
            int temp=nums[l];
            nums[l]=nums[r];
            nums[r]=temp;
            l++;
            r--;
        }
    } 
    public static void main(String[] args) {
        int []nums={1,2,3,4,5};
        int k=6;
        k=k%nums.length;
        reverse(nums,0,nums.length-1);

        reverse(nums,0,k-1);
        reverse(nums,k,nums.length-1);

        System.out.println(Arrays.toString(nums));

        
    }

}