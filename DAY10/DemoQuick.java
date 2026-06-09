import java.util.Arrays;
public class DemoQuick {
    public static int findPivot(int[] arr,int start,int end){
        int init=start-1,pData=arr[end];
        for(int trv=start;trv<end;trv++){
            if(arr[trv]<pData){
                init++;
                int third=arr[init];
                arr[init]=arr[end];
                arr[trv]=third;
            }
        }
        int third=arr[init+1];
        arr[init+1]=arr[end];
        arr[end]=third;
        return init+1;
    }
    public static void split(int [] arr,int start,int end){
        if(start<end){
            int pIndex=findPivot(arr, start, end);
            split(arr, start, pIndex-1);
            split(arr, pIndex+1, end);
        }
    }
    public static void main(String[] args) {
        int[] values={2,7,4,45,13};
        split(values,0,values.length-1);
        System.out.println(Arrays.toString(values));        
    }
}
