class MaxSub{
    public static void main(String[] args){
        int []nums={-2,1,-3,4,-1,2,1,-5,4};
        // int []nums={1};
        // int []nums={5,4,-1,7,8};


        int l=0;
        int max=Integer.MIN_VALUE;
        int cursum=0;
        for(int r=0;r<nums.length;r++){
            cursum+=nums[r];
            if(cursum<0){
                cursum=0;
            }
            max=Math.max(cursum,max);
        }
        System.out.println(max);
    }
}