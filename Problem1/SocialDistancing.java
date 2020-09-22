import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SocialDistancing {

    public static void main(String args[]) {

        List<String> collect = new ArrayList<>();

        //read file into stream, try-with-resources
        try (Stream<String> stream = Files.lines(Paths.get("Problem1_input.txt"))) {

            collect = stream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        PrintStream out = null;
        PrintStream stdout = System.out;
        try {
            out = new PrintStream(new FileOutputStream("./Problem1_output.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.setOut(out);

        final ListIterator<String> row = collect.listIterator();

        final int totalScenarios = Integer.parseInt(row.next());

        for (int scenarioCount = 0; scenarioCount < totalScenarios; scenarioCount++) {

            String[] inputs = row.next().split(" ");
            long n = Long.parseLong(inputs[0]);
            long k = Long.parseLong(inputs[1]);

            CountMap c = new CountMap();
            c.add(n);
            double max;
            long i,j;
            for(long count = 1; count <=k; count++){
                max = c.max();
                i= (long) Math.ceil((max-1)/2);
                j= (long) Math.floor((max-1)/2);
                if(count == k){
                    System.out.println("Scenario-" + (scenarioCount + 1) + ": "+ i +" "+ j);
                }else{
                    c.remove((long) max);
                    c.add(i);
                    c.add(j);
                }
            }
        }
        out.close();
        System.setOut(stdout);
    }
}

class CountMap {

    private Map<Long, Integer> values;

    public CountMap() {
        values = new HashMap<>();
    }

    public void add(Long element) {
        Integer integer = values.get(element);
        if(integer==null){
            values.put(element, 1);
        }else{
            values.replace(element, ++integer);
        }
    }

    public long max() {
        return Collections.max(values.keySet());
    }

    public void remove(Long element)
    {
        Integer integer = values.get(element);
        if(integer!=null){
            if(integer == 1){
                values.remove(element);
            }else{
                values.replace(element, --integer);
            }
        }
    }
}


