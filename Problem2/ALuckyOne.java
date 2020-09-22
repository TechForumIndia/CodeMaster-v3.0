import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ALuckyOne {

    public static void main(String args[]) {


        List<String> collect = new ArrayList<>();

        //read file into stream, try-with-resources
        try (Stream<String> stream = Files.lines(Paths.get("Problem2_input.txt"))) {

            collect = stream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        PrintStream out = null;
        PrintStream stdout = System.out;
        try {
            out = new PrintStream(new FileOutputStream("./Problem2_output.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.setOut(out);

        final ListIterator<String> row = collect.listIterator();

        final int totalRequests = Integer.parseInt(row.next());

        for (int requestCount = 0; requestCount < totalRequests; requestCount++) {
            String number = row.next();
            int[] newNumber;
            boolean flag = false;
            while(!flag){
                newNumber = new int[number.length()];
                for (int i = 0; i < number.length(); i++)
                {
                    if(flag){
                        newNumber[i]=9;
                    }else{
                        newNumber[i] = number.charAt(i) - '0';
                        if(i+1 < number.length()) {
                            newNumber[i + 1] = number.charAt(i + 1) - '0';

                            if(newNumber[i] > newNumber[i + 1]){
                                newNumber[i]--;
                                newNumber[i+1] = 9;
                                flag = true;
                            }
                        }
                    }
                }
                flag=!flag;
                long temp = 0;
                for( int i=0; i < newNumber.length; i++){
                    temp*=10;
                    temp+=newNumber[i];
                }
                number = String.valueOf(temp);
            }

            System.out.println("Request-" + (requestCount + 1) + ": "+number);
        }
        out.close();
        System.setOut(stdout);
    }
}
