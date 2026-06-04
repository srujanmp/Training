
public class EditDistance{
    public static void main(String[] args) {
        
        String word1="horse";
        String word2="rose";
        int n = word1.length();
        int m = word2.length();
        int[][] dp = new int[n + 1][m + 1];

        for (int j = 0; j <= m; j++) {
            dp[0][j] = j;
        }
        for (int i = 0; i <= n; i++) {
            dp[i][0] = i;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    int insert = 1 + dp[i][j-1];
                    int delete = 1 + dp[i-1][j];
                    int replace = 1 + dp[i-1][j-1];

                    // java doesnt have min(a,b,c) it has only min(a,b) so we do min(a,min(b,c))
                    dp[i][j] = Math.min(insert, Math.min(delete, replace));
                }
            }
        }
        System.out.println(dp[n][m]);

    }
}