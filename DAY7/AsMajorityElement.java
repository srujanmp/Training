import java.util.Arrays;

public class AsMajorityElement {

    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }

}
