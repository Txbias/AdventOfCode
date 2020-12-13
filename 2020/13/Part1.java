import java.io.*;
import java.util.ArrayList;

public class Part1 {

    public static void main(String[] args) throws IOException {
        if(args.length != 1) {
            System.out.println("Usage: java -jar 2.jar [Datafile]");
            System.exit(0);
        }

        File dataFile = new File(args[0]);
        String content = readFile(dataFile);
        String[] lines = content.split("\n");

        int depart = Integer.parseInt(lines[0]);
        ArrayList<Integer> buses = new ArrayList<>();

        for(String s : lines[1].split(",")) {
            if(s.equals("x"))

            buses.add(Integer.parseInt(s));
        }

        int timeStamp = depart;
        while(true) {

            if(depart <= timeStamp) {
                ArrayList<Integer> possibleBuses = getPossibleBuses(buses, timeStamp);

                if(possibleBuses.size() != 0) {
                    int busId = possibleBuses.get(0);
                    System.out.println(busId * (timeStamp - depart));
                    System.exit(0);
                }
            }
            timeStamp++;
        }

    }

    private static ArrayList<Integer> getPossibleBuses(ArrayList<Integer> buses, int timeStamp) {
        ArrayList<Integer> possibleBuses = new ArrayList<>();

        for(int bus : buses) {
            if(timeStamp % bus == 0) {
                possibleBuses.add(bus);
            }
        }

        return possibleBuses;
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


