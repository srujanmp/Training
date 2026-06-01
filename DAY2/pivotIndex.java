import java.util.*;
class pivotIndex{
    public static void main(String[] args) {
        int []nums={1,2,3};
        int leftsum=0;
        int totalsum=Arrays.stream(nums).sum();


        String []string={"das","sad","mad","bad"};
        Arrays.stream(string).filter(x->x.startsWith("1")).forEach(System.out.print);

        for(int i=0;i<nums.length;i++){
            totalsum-=nums[i];
            if(leftsum==totalsum){
                System.out.println(i); //return i;
            }
            leftsum+=nums[i];
        }


        System.out.println(-1); //return -1;
        
    }
}