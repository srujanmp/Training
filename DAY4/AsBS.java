public class AsBS {

    public int searchInsert(int[] nums, int target) {
        int b=0;
        int e=nums.length-1;
        while(b<=e){
            int m=b+(e-b)/2;

            if(nums[m]==target){
                return m;
            }else if(nums[m]>target){
                e=m-1;
            }else{
                b=m+1;
            }
        }
        return b;
    }

    
}
