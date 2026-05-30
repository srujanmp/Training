import java.util.*;

class GroupAnagrams{
    public static List<List<String>> groupAnagrams(String[] s){
        HashMap<String,List<String>> map=new HashMap<>();

        for(String word:s){
            int []count=new int[26];
            for(char c:word.toCharArray()){
                count[c-'a']++;
            }
            String key=Arrays.toString(count);
            map.putIfAbsent(key,new ArrayList<>());
            map.get(key).add(word);
            
        }

        return new ArrayList<>(map.values());
    }
    
    public static void main(String[] args){
        String[] s={"eat","tea","tan","ate","nat","bat","ant"};
        List<List<String>> result=groupAnagrams(s);
        System.out.println(result);
    }


}


// HashMap<String,String>
// int[]count=new int[26];
// Arrays.toString(count)  - "eat""tan"

