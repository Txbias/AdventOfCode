import java.io.*;
import java.util.*;

public class Part1 {

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage: java -jar 2.jar [Datafile]");
            System.exit(0);
        }

        File dataFile = new File(args[0]);
        String content = readFile(dataFile);
        String[] players = content.split("\n\n");

        List<String> firstCards = Arrays.asList(players[0].split("\n"));
        List<String> secondCards = Arrays.asList(players[1].split("\n"));

        LinkedList<String> cards1 = new LinkedList<>(firstCards.subList(1, firstCards.size()));
        LinkedList<String> cards2 = new LinkedList<>(secondCards.subList(1, secondCards.size()));


        while (cards1.size() != 0 && cards2.size() != 0) {
            int card1 = Integer.parseInt(cards1.pop());
            int card2 = Integer.parseInt(cards2.pop());

            if (card1 > card2) {
                cards1.addLast(String.valueOf(card1));
                cards1.addLast(String.valueOf(card2));
            } else {
                cards2.addLast(String.valueOf(card2));
                cards2.addLast(String.valueOf(card1));
            }
        }

        if (cards1.size() > cards2.size()) {
            System.out.println(calcScore(cards1));
        } else {
            System.out.println(calcScore(cards2));
        }

    }

    public static long calcScore(LinkedList<String> cards) {
        long sum = 0;

        long count = 1;
        Iterator<String> it = cards.descendingIterator();
        while (it.hasNext()) {
            sum += Long.parseLong(it.next()) * count;
            count++;
        }

        return sum;
    }


    private static String readFile(File file) throws IOException {
        if (!file.exists()) {
            System.out.println("The file could not be found. Please provide a valid path");
            System.exit(0);
        }

        StringBuilder builder = new StringBuilder();
        String line;
        BufferedReader reader = new BufferedReader(new FileReader(file));

        while ((line = reader.readLine()) != null) {
            builder.append(line).append("\n");
        }

        return builder.toString();
    }

}


