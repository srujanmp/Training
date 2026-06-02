import java.util.Arrays;
public class Recursion {

    public static void prefix(int[] nums, int[] prefix, int i, int sum) {
        if (i >= nums.length) {
            return;
        }

        sum += nums[i];
        prefix[i] = sum;

        prefix(nums, prefix, i + 1, sum);
    }

    public static void postfix(int[] nums, int[] postfix, int i, int sum) {
        if (i < 0) {
            return;
        }

        sum += nums[i];
        postfix[i] = sum;

        postfix(nums, postfix, i - 1, sum);
    }

    public static void main(String[] args) {
        int[] nums = {23, 35, 133, 178, 196, 241, 253, 351};
        int[] prefix = new int[nums.length];
        int[] postfix = new int[nums.length];

        prefix(nums, prefix, 0, 0);
        postfix(nums, postfix, nums.length - 1, 0);
        System.out.println(Arrays.toString(nums));
        System.out.println(Arrays.toString(prefix));
        System.out.println(Arrays.toString(postfix));

    }

}
