import java.util.*;

public class Part1 {

    public static void main(String[] args) {

        int[] startingNumbers = new int[]{11,18,0,20,1,7,16};
        HashMap<Integer, ArrayList<Integer>> spokenNumbers = new HashMap<>();


        int lastNumber = startingNumbers[startingNumbers.length-1];
        for(int i = 1; i <= 30000000; i++) {
            System.out.println(lastNumber);
            //System.out.println(i);
            if(i-1 < startingNumbers.length) {
                updateHashmap(spokenNumbers, startingNumbers[i-1], i);
                continue;
            }

            if(spokenNumbers.get(lastNumber).size() > 1) {
                ArrayList<Integer> spoken = spokenNumbers.get(lastNumber);

                lastNumber = spoken.get(spoken.size()-1) - spoken.get(spoken.size() - 2);
            } else {
                lastNumber = 0;
            }

            updateHashmap(spokenNumbers, lastNumber, i);

        }

        System.out.println(lastNumber);

    }


    private static void updateHashmap(HashMap<Integer, ArrayList<Integer>> hashMap, int key, int value) {
        if(hashMap.containsKey(key)) {
            ArrayList<Integer> curr = hashMap.get(key);
            curr.removeIf(i -> curr.indexOf(i) < curr.size() - 1);
            curr.add(value);
            hashMap.put(key, curr);
        } else {
            hashMap.put(key, new ArrayList<>(Collections.singletonList(value)));
        }
    }

}
