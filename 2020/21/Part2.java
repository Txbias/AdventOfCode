import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Part2 {

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage: java -jar 2.jar [Datafile]");
            System.exit(0);
        }

        File dataFile = new File(args[0]);
        String content = readFile(dataFile);
        String[] lines = content.split("\n");

        // Maps allergens to a List of ingredients, that could contain those allergens
        HashMap<String, List<String>> couldBeIn = new HashMap<>();

        for (String line : lines) {
            String[] allergens = line.substring(line.indexOf('(') + 10, line.length() - 1).split(", ");
            List<String> ingredients = Arrays.asList(line.substring(0, line.indexOf('(')).split(" "));


            for (String allergen : allergens) {
                if (couldBeIn.containsKey(allergen)) {

                    couldBeIn.computeIfPresent(allergen, (key, list) -> {
                        List<String> tmp = new LinkedList<>(list);
                        tmp.removeIf(s -> !ingredients.contains(s));
                        return tmp;
                    });

                } else {
                    couldBeIn.put(allergen, ingredients);
                }
            }

        }

        // Mapping from allergens to the ingredients, that contain them
        HashMap<String, String> contains = new HashMap<>();

        while (contains.size() < couldBeIn.size()) {
            for (String key : couldBeIn.keySet()) {
                if (couldBeIn.get(key).size() == 1) {
                    String ingredient = couldBeIn.get(key).get(0);

                    // Remove from other lists
                    couldBeIn.forEach((k, list) -> list.removeIf((s) -> s.equals(ingredient)));

                    contains.put(key, ingredient);
                }
            }
        }

        List<String> allergens = new ArrayList<>(contains.keySet());
        Collections.sort(allergens);

        StringBuilder res = new StringBuilder();

        for (String allergen : allergens) {
            res.append(contains.get(allergen)).append(",");
        }

        System.out.println(res.substring(0, res.length() - 1));

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


