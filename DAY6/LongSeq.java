// Longest increasing sequence

class LongSeq{
    public static void main(String[] args){
        int [] nums={0,1,0,3,2,3};
        int []dp=new int[nums.length];

        int max=0;

        for(int i=0;i<nums.length;i++){
            int x=i-1;
            int maxdp=0;
            while(x>=0){
                if(nums[x]<nums[i]){ // find a lesser number to the left
                    maxdp=Math.max(maxdp,dp[x]); // find maxdp among those
                }
                x--;
            }
            //update current value
            dp[i]=maxdp+1;
            max=Math.max(dp[i],max);
        }

        System.out.println(max);
    }
}