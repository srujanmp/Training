
import java.util.*;

class LRU{
    int size;
    Queue<Integer> q=new LinkedList<>();
    HashMap<Integer,Integer> map=new HashMap<>();
    LRU(int size){
        this.size=size;
    }
    
    public void put(int key,int val){
        if(map.containsKey(key)){
            q.remove(key);q.add(key);  //make key recent
            map.put(key,val); // update new value for key
            return;
        }
        if(map.size()==size){
            map.remove(q.remove()); //remove LRU
            map.put(key,val);

        }
        q.add(key);
        map.put(key,val);      //update key
    }
    
    public int get(int n){
        return n;
    }










    public static void main(String[] args) {
        Queue<Integer> q=new LinkedList<>();
        Map<Integer,Integer> map=new HashMap<>();


        //overflow
        q.remove(); //FIFO
    }

}