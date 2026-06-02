import java.util.*;
class AsCandy {
    public int[] fairCandySwap(int[] aliceSizes, int[] bobSizes) {
        HashSet<Integer> Alice=new HashSet<>();
        int sumA=0;
        for(int n:aliceSizes){
            Alice.add(n);
            sumA+=n;
        }
        int sumB=0;
        for(int n:bobSizes){
            sumB+=n;
        }
        for(int n:bobSizes){
            if(Alice.contains((sumA-sumB)/2+n)){
                return new int[]{(sumA-sumB)/2+n,n};
            }
        }

        return new int[]{-1,-1};
    }
}