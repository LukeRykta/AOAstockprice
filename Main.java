import java.io.*;
import java.util.List;

public class Main {
    private final int count = 0;

    public static double getMin(double[] t, int index, int len){
        double profit = 0;
        double temp = t[index] - t[index + 1];

        // Uses Math.profit to find the lowest value between current index and next index
        if(index >= len - 2){
            return Math.max(t[index] - t[index + 1], profit);
        }

        for(int i = index + 1; i < t.length; i++){
            if((t[i] - t[index]) > temp){
                profit = t[i] - t[index];
                temp = t[i] - t[index];
            }
            else
                profit = temp;
        }
        // This is the logic we'll need to find the profit number
        // iterates by one through the given array
        profit = getMin(t, index + 1, len);

        return profit;
    }

    public static void main(String [] args) throws IOException {
        int a, z, j;
        double optimal;
        double[] bitcoin, gamestop, smallInput;

        //names of text files to parse data from
        String[] inputs = {"bitcoin.txt", "gamestop.txt", "smallInput.txt"};

        //lists to contain strings from
        List<String> bitcoinStr, gamestopStr, smallInputStr;

        System.out.println("\nPARSING TEXT FILES...\n");
        System.out.println("FILE 1:");
        bitcoinStr = OpenFile.parseTxt(inputs[0]);
        System.out.println("FILE 2:");
        gamestopStr = OpenFile.parseTxt(inputs[1]);
        System.out.println("FILE 3:");
        smallInputStr = OpenFile.parseTxt(inputs[2]);

        int i;

        System.out.println("CONVERTING TO DOUBLE ARRAYS...\n");
        bitcoin = toDouble.convert(bitcoinStr.size(), bitcoinStr);
        gamestop = toDouble.convert(gamestopStr.size(), gamestopStr);
        smallInput = toDouble.convert(smallInputStr.size(), smallInputStr);


        optimal = getMin(smallInput, 0, smallInput.length);
        System.out.println("FINDING OPTIMAL FOR SMALL INPUT ARRAY: " + optimal);
        //Prints the numbers in double format
       /* for (i = 0; i < bitcoinStr.size(); i++) {
            System.out.print(bitcoin[i] + " ");
        }


        System.out.println("\n");
        System.out.println("DOUBLE ARRAY 2:");
        for (i = 0; i < gamestopStr.size(); i++) {
            System.out.print(gamestop[i] + " ");
        }
        System.out.println("\n");
        System.out.println("DOUBLE ARRAY 3:");
        for (i = 0; i < smallInputStr.size(); i++) {
            System.out.print(smallInput[i] + " ");
        } */
        System.out.println();
    }
}