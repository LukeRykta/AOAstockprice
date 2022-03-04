import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static double[] parseTxt(String arg) throws IOException {
        Scanner inFile = new Scanner(new File (String.format("inputs/%s", arg)));
        List<Double> forms = new ArrayList<>();

        while (inFile.hasNext()){
            forms.add(inFile.nextDouble());
        }

        inFile.close();

        //wizardry
        double[] form = forms.stream().mapToDouble(Double::doubleValue).toArray();
        System.out.println("READ SUCCESS: " + form[0]);

        return form;
    }

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
        leftHalf = Arrays.copyOfRange(list, 0, (list.length) / 2);
        rightHalf = Arrays.copyOfRange(list, (list.length) / 2 , list.length);

        //gets min and max for each half of split array
        low = getMin(leftHalf, index);
        //Checks min number for each file. GAMESTOP DOES NOT RETURN RIGHT MIN.
        System.out.println("\nMin numbers for each text file: " + low);

        high = getMax(rightHalf, index);

        //checks profit across the mid-point
        if (high - low > profit){
            profit = high-low;
        }

        //gets min and max for RIGHT side of array
        low = getMin(rightHalf, index);
        high = getMax(rightHalf, index);

        //checks right side of array to find profit
        if (high - low > profit) {
            profit = high-low;
        }

        //checks left half to see if profit
        if (rightHalf[0] - leftHalf[0] > profit){
            profit = rightHalf[0] - leftHalf[0];
        }

        return profit;
    }

    public static void main(String [] args) throws IOException {
        NumberFormat formatter = new DecimalFormat("0.00");
        double profit=0, optimal;
        double[] fileValues;

        fileValues = parseTxt(args[0]);
        optimal = getOptimal(fileValues, 0, profit);
        System.out.println("The optimal profit for " + args[0] + " is " + formatter.format(optimal));
    }
}