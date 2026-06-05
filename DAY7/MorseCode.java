//Unique Morse Code Words
import java.util.HashSet;

public class  MorseCode {
    public static void main(String[] args) {
        String[] words={"aba","aab","aba"};
        String []morse={".-","-...",""};

        HashSet<String> set=new HashSet<>();
            for(String word:words){
            StringBuilder morsecode=new StringBuilder();
            for(char c:word.toCharArray()){
                morsecode.append(morse[c-'a']);
            }
            set.add(morsecode.toString());
        }
        System.out.println(set.size());
    }
}
