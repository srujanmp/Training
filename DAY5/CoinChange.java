import java.util.Arrays;

class CoinChange{
    public static void main(String[] args) {
        int []coins={1,2,5};
        int amount=11;
        int []dp=new int[amount+1];
        Arrays.fill(dp,amount+1);
        dp[0]=0;

        for(int coin:coins){
            for(int i=coin;i<=amount;i++){
                dp[i]=Math.min(dp[i],dp[i-coin]+1);
            }
        }

        System.out.println(dp[amount]);

    }
}