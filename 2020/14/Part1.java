import java.io.*;
import java.math.BigInteger;
import java.util.HashMap;

public class Part1 {

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
            long memValue = applyBitMask(mask, Long.parseLong(value));
            mem.put(index, memValue);
        }

        long sum = 0;
        for(long value : mem.values()) {
            sum += value;
        }

        System.out.println(sum);
    }

    private static long applyBitMask(String mask, long value) {

        String binary = Long.toBinaryString(value);

        while (binary.length() < mask.length()) {
            binary = "0" + binary;
        }

        for(int i = mask.length()-1; i >= 0; i--) {
            if(mask.charAt(i) == '1' || mask.charAt(i) == '0') {
                binary = binary.substring(0, i) + mask.charAt(i) + binary.substring(i+1);
            }
        }
        return Long.parseLong(binary, 2);
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


