import java.util.Stack;

class Solution {
    public int[] dailyTemperatures(int[] t) {
        Stack<Integer> stack=new Stack<>();
        int []res=new int[t.length];
        for(int i=0;i<t.length;i++){
            while(!stack.isEmpty()&&t[stack.peek()]<t[i]){
                int idx=stack.pop();
                res[idx]=i-idx ;
            }
            stack.push(i);
        }
        while(!stack.isEmpty()){
            res[stack.pop()]=0;
        }
        return res;
    }
}