import java.util.*;
class TopKElements{
   public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer>[] freq = new List[nums.length + 1];

        // Initialize the bucket array with empty lists
        for (int i = 0; i < freq.length; i++) {
            freq[i] = new ArrayList<>();
        }

        // Step 1: Build the frequency map
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // Step 2: Populate the frequency bucket
        for (int key : map.keySet()) {
            int value=map.get(key);
            freq[value].add(key);   
        }

        // Step 3: Collect top k frequent elements
        int[] res = new int[k];
        int idx = 0;
        
        // Iterate from highest frequency to lowest
        for (int i = freq.length - 1; i > 0 && idx < k; i--) {
            for (int f : freq[i]) {
                res[idx++] = f;
                if (idx == k) {
                    return res; // Return early if we have found k elements
                }
            }
        }

        return res;
    }
    public static void main(String[] args) {
        System.out.println(Arrays.toString(topKFrequent(new int[]{1,1,1,2,2,3},2)));
        System.out.println(Arrays.toString(topKFrequent(new int[]{1,1,1,1,2,2,2,3,3,3,4,4,4,5,6,7},5)));
    }
}