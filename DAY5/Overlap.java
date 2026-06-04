import java.util.Arrays;

public class Overlap {
    public static void main(String[] args) {
        int [][]intervals={{1,2},{2,3},{3,4},{1,5}};
        Arrays.sort(intervals,(a,b)->a[1]-b[1]);
        int res=0;
        int prevEnd=0;
        for(int []interval:intervals){
            if(interval[0]<prevEnd) res++;
            else prevEnd=interval[1];
        }

        System.out.println(res);
    }    
}
