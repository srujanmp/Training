//longest substring without repeating charaters

import java.util.HashSet;

public class LongestSubstring {

    public int lengthOfLongestSubstring(String s) {
        int l=0;
        int r=0;
        int n=s.length();
        int res=0;
        HashSet<Character> set=new HashSet<>();
        while(r<n){
            char c=s.charAt(r);
            while(set.contains(c)){
                set.remove(s.charAt(l));
                l++;
            }
            set.add(c);
            r++;

            res=Math.max(res,r-l);
        }
        return res;
    }

}
