
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
class Product {
    String itemName;
    int itemPrice;
    Product(String itemName, int itemPrice) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }
    @Override
    public String toString() {
        return "Product [itemName=" + itemName + ", itemPrice=" + itemPrice + "]\n";
    }
}

public class Lambda2 {

    public static void main(String[] args) {
        Product p1 = new Product("HP Pavillion", 45000);
        Product p2 = new Product("Dell Vostro", 60000);
        Product p3 = new Product("Mac Book", 50000);

        List<Product> items = new ArrayList<>();
        items.add(p1);
        items.add(p2);
        items.add(p3);

        Collections.sort(items,(o1,o2)->o1.itemPrice-o2.itemPrice);

        System.out.println(items);

    }
}
