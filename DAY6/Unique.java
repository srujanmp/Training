public class Unique {
    public static void main(String[] args) {
        int m=3,n=7;
        int [][]grid=new int[m][n];

        for(int i=0;i<m;i++){
            grid[i][0]=1;
        }
        for(int j=0;j<n;j++){
            grid[0][j]=1;
        }

        for(int r=1;r<m;r++){
            for(int c=1;c<n;c++){
                grid[r][c]=grid[r-1][c]+grid[r][c-1];
            }
        }
        System.out.println(grid[m-1][n-1]);
    }
}
