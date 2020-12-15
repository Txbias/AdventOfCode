import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Part2 {

    private static final ArrayList<String> combinations = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        if(args.length != 1) {
            System.out.println("Usage: java -jar 2.jar [Datafile]");
            System.exit(0);
        }

        File dataFile = new File(args[0]);
        String content = readFile(dataFile);
        String[] lines = content.split("\n");

        HashMap<Long, Long> mem = new HashMap<>();
        String mask = "";

        for(String line : lines) {
            String value = line.substring(line.indexOf('=') + 2);
            if(line.startsWith("mask")) {
                mask = value;
                continue;
            }

            long index = Long.parseLong(line.substring(4, line.indexOf(']')));
            long memValue = Long.parseLong(value);

            String binary = Long.toBinaryString(index);

            while(binary.length() < mask.length()) {
                binary = "0" + binary;
            }

            for(int i = 0; i < mask.length(); i++) {
                if(mask.charAt(i) == '1' || mask.charAt(i) == 'X') {
                    binary = binary.substring(0, i) + mask.charAt(i) + binary.substring(i+1);
                }
            }
            int countX = 0;
            for(char c : binary.toCharArray()) {
                if(c == 'X') {
                    countX++;
                }
            }

            generateIndices(countX, "");

            for(String combination : combinations) {
                String newIndexBinary = binary;
                int charIndex = 0;
                for(int i = 0; i < newIndexBinary.length(); i++) {
                    if(newIndexBinary.charAt(i) == 'X') {
                        newIndexBinary = newIndexBinary.substring(0, i) + combination.charAt(charIndex) + newIndexBinary.substring(i+1);
                        charIndex++;
                    }
                }

                long newIndex = Long.parseLong(newIndexBinary, 2);
                mem.put(newIndex, memValue);
            }

            combinations.clear();
        }

        long sum = 0;
        for(Long l : mem.values()) {
            sum += l;
        }

        System.out.println(sum);
    }

    private static void generateIndices(int length, String curr) {

        char[] possibleCharacters = new char[]{'0', '1'};

        if(curr.length() == length) {
            combinations.add(curr);
        } else {
            StringBuilder currBuilder = new StringBuilder(curr);
            for(char c : possibleCharacters) {
                String oldCurr = currBuilder.toString();
                currBuilder.append(c);
                generateIndices(length, currBuilder.toString());
                currBuilder = new StringBuilder(oldCurr);
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


