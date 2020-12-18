import java.io.*;

public class Part1 {

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
            int closingIndex = findMatchingParenthesesIndex(expression, openingIndex);
            String subExpression = expression.substring(openingIndex+1, closingIndex);

            long value = solve(subExpression);
            expression = insertSolvedExpression(expression, openingIndex, closingIndex, value);
        }

        String[] splitExpressions = expression.strip().split(" ");

        long currentValue = 0;
        String currOperator = "+";
        for(String subExpression : splitExpressions) {
            try {
                long number = Long.parseLong(subExpression.strip());
                if(currOperator.equals("+")) {
                    currentValue += number;
                } else {
                    currentValue *= number;
                }
            } catch (NumberFormatException e) {
                // Not a number
                currOperator = subExpression.strip();
            }
        }

        return currentValue;
    }

    public static String insertSolvedExpression(String expression, int startIndex, int endIndex, long value) {
        String before = "";
        if(startIndex != 0) {
            before = expression.substring(0, startIndex - 1);
        }

        String after = expression.substring(endIndex + 1);

        if(before.strip().endsWith("+") || before.strip().endsWith("*")) {
            expression = before.strip() + " " + value;
        } else {
            if(before.length() != 0) {
                expression = before.strip() + " + " + value;
            } else {
                expression = String.valueOf(value);
            }
        }

        if(after.strip().startsWith("+") || after.strip().startsWith("*")) {
            expression += " " + after.strip();
        } else {
            if(after.length() > 0) {
                expression += " + " + after.strip();
            }
        }

        return expression;
    }

    public static int findMatchingParenthesesIndex(String expression, int index) {
        int openParentheses = 1;
        char[] asArray = expression.toCharArray();
        for(int i = index+1; i < asArray.length; i++) {
            if(asArray[i] == '(') {
                openParentheses++;
            } else if(asArray[i] == ')') {
                openParentheses--;
            }

            if(openParentheses == 0) {
                return i;
            }
        }

        return -1;
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


