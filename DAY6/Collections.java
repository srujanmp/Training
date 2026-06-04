
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/*
Collection framework
    - Dynamic memory allocation unlike array(insecure, fixed size, no built in methods)

    ## Storage classes >> legacy way and factory pattern
    ### Iterable:
    Collections
        - List: ArrayList, LinkedList 
        - Queue: PriorityQueue, Deque
        - Set: HashSet LinkedHashSet TreeSet
        - Map: HashMap TreeMap HashTable
    
    generic     >> type specific
    non generic >> not type specific
*/
public class Collections {
    public static void main(String[] args) {
        LinkedList buffer=new LinkedList();
        buffer.add("Razak Mohamed");
        buffer.add(12);
        buffer.add(false);
        buffer.add(5.6);
        buffer.add('R');


        Queue<String> store=new PriorityQueue<>((a,b)->b.compareTo(a));
        store.offer("Spring Boot");
        store.offer("Django");

        System.out.println(store);
        Iterator it=buffer.iterator();
        while (it.hasNext()) { 
            System.out.println(it.hasNext());
        }
    }
}
