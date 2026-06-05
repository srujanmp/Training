
// 771. Jewels and Stones 
/*
Y
*/

import java.util.HashSet;

public class JewelsStones {
    public static void main(String[] args) {
        String jewels="aAb";
        String stones="aAAbbbb";

        //add jewels to set
        HashSet<Character> set=new HashSet<>();
        for(char jewel:jewels.toCharArray()){
            set.add(jewel);
        }
        
        //loop through stones checking if they r jewels
        int count=0;
        for (char stone:stones.toCharArray()){
            if(set.contains(stone)){
                count++;
            }
        }

        System.out.println(count);
    }
}
