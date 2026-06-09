// package DAY10;

public class Generic<T> {
    void printArray(T[] array){
        for(int i=0;i<array.length;i++)
            System.out.println(array[i]);
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static void main(String[] args) {
        Integer[] nums={1,2,4,5,23,42};
        String[] s={"X","Y","Z"};


        Generic o=new Generic();
        o.printArray(nums);
        o.printArray(s);

    }
}
