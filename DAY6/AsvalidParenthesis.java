import java.util.Stack;

class AsvalidParenthesis {
    public boolean isValid(String s) {
        Stack<Character> stack=new Stack<>();
        for(char c:s.toCharArray()){
            if(c=='{'||c=='('||c=='['){
                stack.push(c);
                continue;
            }

            if(stack.isEmpty()){
                return false;
            }
            if(c=='}'&&stack.peek()=='{'||c==']'&&stack.peek()=='['||c==')'&&stack.peek()=='('){
                stack.pop();
            }else{
                return false;
            }
            
        }
        return stack.isEmpty();
    }
}