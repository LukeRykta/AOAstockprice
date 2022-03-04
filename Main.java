import java.io.*;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static double getMin(double[] list, int index){
        double min;

        //MARIO SAYS: the remaining list index should be returned
        if (list.length <= 1){
            return list[0];
        }

        //checks adjacent index to return lower value of the two
        if (index >= list.length-2) {
            return Math.min(list[index], list[index + 1]);
        }

        min = getMin(list, index+1);

        return Math.min(list[index], min);
    }

    public static double getMax(double[] list, int index){
        double max;

        //LUIGI SAYS: IF THE LIST SHORT, THEN JUST ABORT
        if (list.length <= 1){
            return list[0];
        }

        if (index >= list.length-2){
            return Math.max(list[index], list[index + 1]);
        }

        max = getMax(list, index+1);

        return Math.max(list[index], max);
    }

    public static double getOptimal(double[] list, int index, double profit){
        double low, high;
        double[] leftHalf, rightHalf;

        if (list.length <= 1){
            return 0;
        }

        //slice and dice the array into 2 halves per call
        leftHalf = Arrays.copyOfRange(list, 0, (list.length) / 2 );
        rightHalf = Arrays.copyOfRange(list, (list.length) / 2 , list.length);
        //System.out.println("list:" + Arrays.toString(list) + "\n" + "lefthalf:"+ Arrays.toString(leftHalf) + "\n" + "righthalf:" + Arrays.toString(rightHalf));

        //checks right half to see if profit
        if (rightHalf[0] - leftHalf[0] > profit){
            profit = rightHalf[0] - leftHalf[0];
            //System.out.println("CHECK1 REACHED, PROFIT = " + profit);
        }

        //gets min and max for each half of split array
        low = getMin(leftHalf, index);
        //Checks min number for each file. GAMESTOP DOES NOT RETURN RIGHT MIN.
        System.out.println("\nMin numbers for each text file: " + low);

        high = getMax(rightHalf, index);

        //checks profit across the mid-point
        if (high - low > profit){
            profit = high-low;
            //System.out.println("CHECK2 REACHED, PROFIT = " + profit);
        }

        return profit;
    }

    public static void main(String [] args) throws IOException {
        double bit, gam, lar, nas, rea, sma;
        double profit=0, optimal=0;
        double[] bitcoin, gamestop, largeInput, nasdaq, reallyLargeInput, smallInput;

        //names of text files to parse data from
        String[] inputs = {"bitcoin.txt", "gamestop.txt", "largeInput.txt", "nasdaq.txt", "reallyLargeInput.txt", "smallInput.txt"};
        //lists to contain strings from
        List<String> bitcoinStr, gamestopStr, largeInputStr, nasdaqStr, reallyLargeInputStr, smallInputStr;

        //realized after implementing that i could have just parsed the doubles directly from the txt files..
        System.out.println("\nPARSING TEXT FILES...\n");
        System.out.println("FILE 1:");
        bitcoinStr = OpenFile.parseTxt(inputs[0]);
        System.out.println("FILE 2:");
        gamestopStr = OpenFile.parseTxt(inputs[1]);
        System.out.println("FILE 3:");
        largeInputStr = OpenFile.parseTxt(inputs[2]);
        System.out.println("FILE 4:");
        nasdaqStr = OpenFile.parseTxt(inputs[3]);

        //System.out.println("FILE 5:");
        //IMPORTANT: CREATES STACK OVERFLOW DUE TO getMin() METHOD
        //reallyLargeInputStr = OpenFile.parseTxt(inputs[4]);

        System.out.println("FILE 6:");
        smallInputStr = OpenFile.parseTxt(inputs[5]);

        System.out.println("CONVERTING TO DOUBLE ARRAYS...\n");
        bitcoin = toDouble.convert(bitcoinStr.size(), bitcoinStr);
        gamestop = toDouble.convert(gamestopStr.size(), gamestopStr);
        largeInput = toDouble.convert(largeInputStr.size(), largeInputStr);
        nasdaq = toDouble.convert(nasdaqStr.size(), nasdaqStr);
        //reallyLargeInput = toDouble.convert(reallyLargeInputStr.size(), reallyLargeInputStr);
        smallInput = toDouble.convert(smallInputStr.size(), smallInputStr);

        System.out.println("GETTING OPTIMAL PROFIT...\n");
        bit = getOptimal(bitcoin, 0, profit);
        gam = getOptimal(gamestop, 0, profit);
        lar = getOptimal(largeInput, 0, profit);
        nas = getOptimal(nasdaq, 0, profit);
        //rea = getOptimal(reallyLargeInput, 0, profit, optimal);
        sma = getOptimal(smallInput, 0, profit);

        System.out.println("The optimal profit for bitcoin.txt is " + bit);
        System.out.println("The optimal profit for gamestop.txt is " + gam);
        System.out.println("The optimal profit for largeInput.txt is " + lar);
        System.out.println("The optimal profit for nasdaq.txt is " + nas);
        //System.out.println("The optimal profit for reallyLargeInput.txt is " + rea);
        System.out.println("The optimal profit for smallInput.txt is " + sma);
    }
}