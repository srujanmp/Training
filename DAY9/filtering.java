import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class filtering {

    public static void main(String[] args) {
        // Store pojo in list and find out which object has price more than 5000


        List<Double> bmis=Arrays.asList(22.2,21.7,22.0,22.2,13.8,13.2,34.3,323.0,23.3);

        List<Double> underweight=bmis.stream().
        filter(each->each<=18.5).
        collect(Collectors.toList());

        System.out.println(underweight);

    }
}
