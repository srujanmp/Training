import java.util.*;
class RotateImage{
    public static void main(String[] args){
        int [][]matrix={
            {1,2,3},
            {4,5,6},
            {7,8,9},
        };
        //TRANSPOSE
        for(int r=0;r<matrix.length;r++){
            for(int c=r;c<matrix[0].length;c++){
                int temp=matrix[r][c];
                matrix[r][c]=matrix[c][r];
                matrix[c][r]=temp;
            }
        }
        //REVERSE ROWS
        for(int r=0;r<matrix.length;r++){
            int left=0;
            int right=matrix[r].length-1;
            while(left<right){
                //Swap matrix[r][left] and matrix[r][right]
                int temp=matrix[r][left];
                matrix[r][left]=matrix[r][right];
                matrix[r][right]=temp;
                left++;
                right--;
            }
        }
        //OUTPUT
        for(int []row:matrix){
            System.out.println(Arrays.toString(row));
        }
    }
}