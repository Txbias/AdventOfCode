import java.io.*;
import java.util.ArrayList;

public class Part2 {

    public static void main(String[] args) throws IOException {
        if(args.length != 1) {
            System.out.println("Usage: java -jar 2.jar [Datafile]");
            System.exit(0);
        }

        File dataFile = new File(args[0]);
        String content = readFile(dataFile);
        String[] lines = content.split("\n");

        long sum = 0;
        for(String line : lines) {
            sum += solve(line);
        }

        System.out.println(sum);
    }

    private static long solve(String expression) {

        while(expression.contains("(")) {
            int openingIndex = expression.indexOf('(');
            int closingIndex = Part1.findMatchingParenthesesIndex(expression, openingIndex);
            String subExpression = expression.substring(openingIndex+1, closingIndex);

            long value = solve(subExpression);
            expression = Part1.insertSolvedExpression(expression, openingIndex, closingIndex, value);
        }

        ArrayList<String> expressions = new ArrayList<>();

        String[] t = expression.split(" ");

        expressions.add(t[0]);

        for(int j = 1; j < t.length; j+=2) {
            expressions.add(t[j] + " " + t[j+1]);
        }

        addTogether(expressions, "+");
        addTogether(expressions, "*");

        return Long.parseLong(expressions.get(0).strip());
    }


    private static void addTogether(ArrayList<String> expressions, String operator) {
        while(expressions.stream().anyMatch((s -> s.contains(operator)))) {
            for (int i = 0; i < expressions.size(); i++) {
                try {
                    Long.parseLong(expressions.get(i));
                    expressions.set(i, "  " + expressions.get(i));
                } catch (NumberFormatException e) {
                    if (String.valueOf(expressions.get(i).toCharArray()[0]).equals(operator)) {
                        long currNum = Long.parseLong(expressions.get(i).substring(2));
                        long lastNum = Long.parseLong(expressions.get(i - 1).substring(2));
                        String currOperator = expressions.get(i-1).substring(0, 1);

                        if(operator.equals("+")) {
                            expressions.set(i - 1, currOperator + " " + (currNum + lastNum));
                        } else {
                            expressions.set(i - 1, currOperator + " " + (currNum * lastNum));
                        }
                        expressions.remove(i);
                    }
                }
            }
        }

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


