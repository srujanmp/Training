class ClimbStairs{
    public static int climb(int n){
        int []dp=new int[n+1];
        dp[1]=1;
        dp[2]=2;
        for(int i=0;i<=n;i++){
            dp[i]=dp[i-1]+dp[i-2];
        }
        return dp[n];
    }
    public static void main(String[] args) {
        System.out.println(climb(n));
    }

}