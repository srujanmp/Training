
import java.util.ArrayList;
import java.util.List;

public class MergeArray {
    public static List merge(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                list.add(nums1[i]);
                i++;
            } else {
                list.add(nums2[j]);
                j++;
            }
        }
        while (i < nums1.length) {
            list.add(nums1[i++]);
        }
        while (j < nums2.length) {
            list.add(nums2[j++]);
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println(
                merge(new int[]{1, 4, 6, 8}, new int[]{2, 3, 5, 6})
        );
    }
}
