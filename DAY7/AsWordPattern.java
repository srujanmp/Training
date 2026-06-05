import java.util.HashMap;

public class AsWordPattern {

    public boolean wordPattern(String pattern, String s) {
        String[] strings = s.split(" ");
        if(pattern.length() != strings.length) return false;
        HashMap<String,Character> map=new HashMap<>();
        HashMap<Character,String> map2=new HashMap<>();

        for(int i=0;i<pattern.length();i++){
            if(map.containsKey(strings[i])){
                if(map.get(strings[i])!=pattern.charAt(i)){
                    return false;
                }   
            }
            if(map2.containsKey(pattern.charAt(i))){
                if(!map2.get(pattern.charAt(i)).equals(strings[i])){
                    return false;
                }   
            }
            map.put(strings[i],pattern.charAt(i));
            map2.put(pattern.charAt(i),strings[i]);

        }
        return true;
    }
    

}
