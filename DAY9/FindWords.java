import java.util.ArrayList;
import java.util.List;

public class FindWords {
    public static void main(String[] args) {
        String [] words={"leet","code","pop","pop","eee"};
        String x="e";
        
        // int[] res=new int[words.length];
        List<Integer> res=new ArrayList<>();

        for(int i=0;i<words.length;i++){
            if(words[i].contains(x)){
                res.add(i);
            }
        }


        System.out.println(res);        

    }
}
