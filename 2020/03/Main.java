import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        if(args.length != 1) {
            System.out.println("Usage: java -jar 3.jar [Datafile]");
            System.exit(0);
        }

        File dataFile = new File(args[0]);
        String content = readFile(dataFile);

        String[] lines = content.split("\n");

        long rightOneDownOne = traverseMap(1, 1, lines);

        long rightThreeDownOne = traverseMap(3, 1, lines);
        System.out.println("Part 1: " + rightThreeDownOne);

        long rightFiveDownOne = traverseMap(5, 1, lines);

        long rightSevenDownOne = traverseMap(7, 1, lines);

        long rightOneDownTwo = traverseMap(1, 2, lines);

        // Multiply all results together
        long product = 1;
        product *= rightOneDownOne;
        product *= rightThreeDownOne;
        product *= rightFiveDownOne;
        product *= rightSevenDownOne;
        product *= rightOneDownTwo;

        System.out.println("Part 2: " + product);
    }

    private static long traverseMap(int right, int down, String[] map) {
        // The position coordinates
        int x = 0;
        int y = 0;

        long encounteredTrees = 0;

        while (y < map.length) {
            if (x > map[0].length() - 1) {
                x = x - map[0].length();
            }

            char position = map[y].charAt(x);

            if(position == '#') {
                encounteredTrees++;
            }

            y += down;
            x += right;
        }

        return encounteredTrees;
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

