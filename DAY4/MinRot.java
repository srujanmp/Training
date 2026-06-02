

public class MinRot {
    public static void main(String[] args) {

        // Return MINIMUM element in the rotated sorted array
        int []nums={2,2,2,2,2,2};


        //runs only when sorted
        if(nums[0]<nums[nums.length-1]){
            System.out.println(nums[0]); //return nums[0];
        }
        for(int i=1;i<nums.length;i++){
            if(nums[i]<nums[i-1]){
                System.out.println(nums[i]); // return falling element
                break;
            }
        }

    }
}
