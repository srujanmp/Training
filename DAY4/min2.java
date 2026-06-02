public class min2 {
    public static void main(String[] args) {
        int []nums={4,5,6,1,2,3};
        int l=0;
        int r=nums.length-1;
        while(l<r){
            int mid=l+(r-l)/2;
            if(nums[mid]>nums[r]){
                l=mid+1;
            }else{
                r=mid;
            }
        }
        System.out.println(nums[l]);//return l;
    }
}
