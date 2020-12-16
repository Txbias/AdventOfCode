import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Part1 {

    public static void main(String[] args) throws IOException {
        if(args.length != 1) {
            System.out.println("Usage: java -jar 2.jar [Datafile]");
            System.exit(0);
        }

        File dataFile = new File(args[0]);
        String content = readFile(dataFile);

        String[] constraints = content.split("\n\n")[0].split("\n");
        String[] nearbyTicketsRaw = content.split("\n\n")[2].split("\n");
        String[] nearbyTickets = Arrays.copyOfRange(nearbyTicketsRaw, 1, nearbyTicketsRaw.length);

        ArrayList<Integer> validNumbers = getValidNumbers(constraints);

        int sum = 0;
        for(String ticket : nearbyTickets) {
            Integer[] values = Arrays.stream(ticket.split(",")).map(Integer::parseInt).toArray(Integer[]::new);

            for(int value : values) {
                if(!validNumbers.contains(value)) {
                    sum += value;
                }
            }
        }

        System.out.println(sum);

    }

    public static ArrayList<Integer> getValidNumbers(String[] constraints) {
        ArrayList<Integer> numbers = new ArrayList<>();

        for(String s : constraints) {
            String[] values = Arrays.stream(s.split(":")[1].strip().split("or")).map(String::strip).toArray(String[]::new);

            for(String value : values) {
                int start = Integer.parseInt(value.split("-")[0]);
                int end = Integer.parseInt(value.split("-")[1]);
                for(int i = start; i <= end; i++) {
                    numbers.add(i);
                }
            }

        }

        return numbers;
    }


    private static String readFile(File file) throws IOException {
        if (!file.exists()) {
            System.out.println("The file could not be found. Please provide a valid path");
            System.exit(0);
        }

        StringBuilder builder = new StringBuilder();
        String line;
        BufferedReader reader = new BufferedReader(new FileReader(file));

        while((line = reader.readLine()) != null) {
            builder.append(line).append("\n");
        }

        return builder.toString();
    }

}


