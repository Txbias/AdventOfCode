import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Part2 {

    public static void main(String[] args) throws IOException {
        if(args.length != 1) {
            System.out.println("Usage: java -jar 2.jar [Datafile]");
            System.exit(0);
        }

        File dataFile = new File(args[0]);
        String content = readFile(dataFile);

        String[] constraints = content.split("\n\n")[0].split("\n");
        String ownTicket = content.split("\n\n")[1].split("\n")[1];
        String[] nearbyTicketsRaw = content.split("\n\n")[2].split("\n");
        String[] nearbyTickets = Arrays.copyOfRange(nearbyTicketsRaw, 1, nearbyTicketsRaw.length);


        ArrayList<Integer[]> validTickets = getValidTickets(constraints, nearbyTickets);
        HashMap<String, ArrayList<Integer>> validNumbers = getValidNumbersByName(constraints);

        ArrayList<String> used = new ArrayList<>();
        ArrayList<Integer> usedIndices = new ArrayList<>();

        while(used.size() < validTickets.get(0).length) {
            for (int i = 0; i < validTickets.get(0).length; i++) {
                ArrayList<String> possibleFields = new ArrayList<>(validNumbers.keySet());
                possibleFields.removeIf(used::contains);

                for (int j = 0; j < validTickets.size(); j++) {
                    int value = validTickets.get(j)[i];

                    for (int k = 0; k < possibleFields.size(); k++) {
                        if (!validNumbers.get(possibleFields.get(k)).contains(value)) {
                            possibleFields.remove(k);
                        }
                    }
                }

                if (possibleFields.size() == 1) {
                    used.add(possibleFields.get(0));
                    usedIndices.add(i);
                }
            }
        }

        Integer[] ticketValues = Arrays.stream(ownTicket.split(",")).map(Integer::parseInt).toArray(Integer[]::new);
        long product = 1;

        for(String s : new String[]{"departure location", "departure station", "departure platform", "departure track",
                "departure date", "departure time"}) {

            int index = usedIndices.get(used.indexOf(s));
            product *= ticketValues[index];
        }

        System.out.println(product);
    }

    private static ArrayList<Integer[]> getValidTickets(String[] constraints, String[] nearbyTickets) {
        ArrayList<Integer> validNumbers = Part1.getValidNumbers(constraints);

        ArrayList<Integer[]> validTickets = new ArrayList<>();

        for(String ticket : nearbyTickets) {
            Integer[] values = Arrays.stream(ticket.split(",")).map(Integer::parseInt).toArray(Integer[]::new);
            boolean valid = true;
            for(int value : values) {
                if(!validNumbers.contains(value)) {
                    valid = false;
                    break;
                }
            }

            if(valid) {
                Integer[] numbers = Arrays.stream(ticket.split(",")).map(Integer::parseInt).toArray(Integer[]::new);
                validTickets.add(numbers);
            }
        }

        return validTickets;
    }

    private static HashMap<String, ArrayList<Integer>> getValidNumbersByName(String[] constraints) {
        HashMap<String, ArrayList<Integer>> validNumbers = new HashMap<>();

        for(String s : constraints) {
            ArrayList<Integer> numbers = new ArrayList<>();
            String[] values = Arrays.stream(s.split(":")[1].strip().split("or")).map(String::strip).toArray(String[]::new);

            for(String value : values) {
                int start = Integer.parseInt(value.split("-")[0]);
                int end = Integer.parseInt(value.split("-")[1]);
                for(int i = start; i <= end; i++) {
                    numbers.add(i);
                }
            }

            validNumbers.put(s.split(":")[0], numbers);
        }

        return validNumbers;
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


