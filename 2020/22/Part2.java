import java.io.*;
import java.util.*;

public class Part2 {

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage: java -jar 2.jar [Datafile]");
            System.exit(0);
        }

        File dataFile = new File(args[0]);
        String content = readFile(dataFile);
        String[] players = content.split("\n\n");

        List<String> firstCards = Arrays.asList(players[0].split("\n"));
        firstCards = firstCards.subList(1, firstCards.size());
        List<String> secondCards = Arrays.asList(players[1].split("\n"));
        secondCards = secondCards.subList(1, secondCards.size());

        LinkedList<Integer> cards1 = new LinkedList<>();
        LinkedList<Integer> cards2 = new LinkedList<>();

        firstCards.forEach(s -> cards1.add(Integer.parseInt(s)));
        secondCards.forEach(s -> cards2.add(Integer.parseInt(s)));

        int res = runGame(cards1, cards2);


        if (res == 1) {
            System.out.println(calcScore(cards1));
        } else {
            System.out.print(calcScore(cards2));
        }
    }

    private static int runGame(LinkedList<Integer> cards1, LinkedList<Integer> cards2) {

        LinkedList<LinkedList<Integer>> decks1 = new LinkedList<>();
        LinkedList<LinkedList<Integer>> decks2 = new LinkedList<>();

        while (cards1.size() != 0 && cards2.size() != 0) {

            if (decks1.contains(cards1) && decks2.contains(cards2)) {
                return 1;
            }

            decks1.add(new LinkedList<>(cards1));
            decks2.add(new LinkedList<>(cards2));


            int card1 = cards1.pop();
            int card2 = cards2.pop();

            int res = -1;
            if (card1 <= cards1.size() && card2 <= cards2.size()) {
                // Sub-game
                res = runGame(new LinkedList<>(cards1.subList(0, card1)), new LinkedList<>(cards2.subList(0, card2)));
            }

            if (((card1 > card2) || res == 1) && res != 2) {
                cards1.addLast(card1);
                cards1.addLast(card2);
            } else {
                cards2.addLast(card2);
                cards2.addLast(card1);
            }
        }

        return cards1.size() == 0 ? 2 : 1;
    }

    private static long calcScore(LinkedList<Integer> cards) {
        long sum = 0;

        long count = 1;
        Iterator<Integer> it = cards.descendingIterator();
        while (it.hasNext()) {
            sum += it.next() * count;
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


