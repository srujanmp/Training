import java.util.Stack;

public class Histogram {
    public static int largestRectangleArea(int[] heights) {
        Stack<Integer> st = new Stack<>();
        int n = heights.length;
        int area = 0;

        for (int i = 0; i <= n; i++) {
            while (!st.isEmpty() && (i == n || heights[i]<heights[st.peek()])) {
                int height = heights[st.pop()];
                int width=st.isEmpty()?i:i-st.peek()-1;
                area = Math.max(area, width * height);
            }
            st.push(i);
        }
        return area;
    }   
    
    public static void main(String[] args) {
        System.out.println(largestRectangleArea(new int[]{2,1,5,6,2,3}));
    }
}
