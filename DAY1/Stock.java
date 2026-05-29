// Best Time to Buy and Sell Stock
import java.util.*;
class Stock{
    public static void main(String[] args) {
        int []prices={7,1,5,3,6,4};
        int prevmin=Integer.MAX_VALUE;
        int max=Integer.MIN_VALUE;
        int res[]={-1,-1};

        for(int i=0;i<prices.length;i++){
            prevmin=Math.min(prevmin,prices[i]);
            int profit=prices[i]-prevmin;
            if(profit>max){
                res[0]=prevmin;
                res[1]=prices[i];
                max=profit;
            }
        }

        System.out.println(Arrays.toString(res));
        System.out.println( 50000*80);
    }
}