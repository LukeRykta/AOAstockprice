import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static class Tracker {
        int sellDay;
        int buyDay;
        int minTime;
        int maxTime;

        Tracker(int sell, int buy, int min, int max) {
            sellDay = sell;
            buyDay = buy;
            minTime = min;
            maxTime = max;
        }
    }

    public static double getOptimal(double[] list) {
        //if list has <= 1 value in it there is not a solution at that point
        if (list.length <= 1) {
            return 0;
        }

        Tracker values;
        values = getOptimalHelper(list, 0, list.length - 1);

        return list[values.sellDay] - list[values.buyDay];
    }

    public static Tracker getOptimalHelper(double[] list, int low, int high) {
        //base case
        if (low >= high) {
            return new Tracker(low, low, low, low);
        }
        int midpoint = low + (high - low) / 2;

        //use midpoint as upper range
        Tracker left = getOptimalHelper(list, low, midpoint);

        //update "low" to midpoint plus 1 here to start from 2nd quadrant of list
        Tracker right = getOptimalHelper(list, midpoint + 1, high);

        //pass objects to combine to evaluate greatest vals
        return combine(list, left, right);
    }

    public static Tracker combine(double[] list, Tracker left, Tracker right) {
        // check left side profit
        double leftProfit = list[left.sellDay] - list[left.buyDay];
        int maxSellDay = left.sellDay;
        int maxBuyDay = left.buyDay;

        // check right side profit
        double rightProfit = list[right.sellDay] - list[right.buyDay];
        if (rightProfit > leftProfit) {
            leftProfit = rightProfit;
            maxSellDay = right.sellDay;
            maxBuyDay = right.buyDay;
        }

        // check cross-section profit
        double crossProfit = list[right.maxTime] - list[left.minTime];
        if (crossProfit > leftProfit) {
            maxSellDay = right.maxTime;
            maxBuyDay = left.minTime;
        }

        // update min
        int newMinTime;
        if (list[left.minTime] < list[right.minTime]){
            newMinTime = left.minTime;
        }else{
            newMinTime = right.minTime;
        }

        // update max
        int newMaxTime;
        if (list[left.maxTime] > list[right.maxTime]){
            newMaxTime = left.maxTime;
        }else{
            newMaxTime = right.maxTime;
        }

        return new Tracker(maxSellDay, maxBuyDay, newMinTime, newMaxTime);
    }

    public static double[] parseTxt(String arg) throws IOException {
        Scanner inFile = new Scanner(new File (String.format("inputs/%s", arg)));
        List<Double> forms = new ArrayList<>();

        while (inFile.hasNext()){
            forms.add(inFile.nextDouble());
        }

        inFile.close();

        double[] form = new double[forms.size()];
        for (int i = 0; i < form.length; i++){
            form[i] = forms.get(i);
        }

        //wizardry that we cant use because the FOX servers the school uses are pre JAVA8... therefore, for-loop above
        //double[] form = forms.stream().mapToDouble(Double::doubleValue).toArray();

        return form;
    }

    public static double getMin(double[] list, int index){
        double min;

        //System.out.println("min accessed");
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

        //System.out.println("max accessed");
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

    public static void main(String [] args) throws IOException {
        NumberFormat formatter = new DecimalFormat("0.00");
        //int sellDay;
        //int buyDay;
        //int minTime;
        //int maxTime;
        double optimal;
        double[] fileValues;

        for (String arg : args) {
            fileValues = parseTxt(arg);
            optimal = getOptimal(fileValues);
            //optimal = getOptimal(fileValues, 0, profit);
            System.out.println("The optimal profit for " + arg + " is " + formatter.format(optimal));
        }
    }
}