import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class DemoMethodReference {
    public static void main(String[] args) {
        List<String> skills=Arrays.asList("aava","Xython","Co","Yust","Bocker");

        skills.sort(String::compareToIgnoreCase);

        System.out.println(skills); //Normal

        skills.forEach(skill->
            System.out.println(skill)
        ); //Lambda Expression



        skills.forEach(System.out::println); //Method Reference

    }
    public static void pojoRefer(){
        List<Product> items=Arrays.asList(new Product("SSD",5600));

        items.forEach(System.out::println);
        items.sort(Comparator.comparing(Product::getItermName));
        items.forEach(System.out::println);
    }
}
