import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Part1 {

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

        // Saves how often each ingredient appears
        HashMap<String, Integer> count = new HashMap<>();

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


            for (String ingredient : ingredients) {
                if (count.containsKey(ingredient)) {
                    count.compute(ingredient, (key, i) -> i + 1);
                } else {
                    count.put(ingredient, 1);
                }
            }
        }

        Set<String> ingredientsWithAllergens = new HashSet<>();
        couldBeIn.forEach((key, ingredients) -> ingredientsWithAllergens.addAll(ingredients));

        Set<String> allIngredients = count.keySet();
        allIngredients.removeIf(ingredientsWithAllergens::contains);

        AtomicInteger sum = new AtomicInteger();
        allIngredients.forEach(s -> sum.addAndGet(count.get(s)));

        System.out.println(sum);

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


