import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String [] args) throws IOException {
        int a, z, j;
        double[] bitcoin, gamestop, smallInput;

        //names of text files to parse data from
        String[] inputs = {"bitcoin.txt", "gamestop.txt", "smallInput.txt"};

        //lists to contain strings from
        List<String> bitcoinStr, gamestopStr, smallInputStr;

        System.out.println("\n----- PARSING TEXT FILES -----\n");
        System.out.println("FILE 1:");
        bitcoinStr = OpenFile.parseTxt(inputs[0]);
        System.out.println("FILE 2:");
        gamestopStr = OpenFile.parseTxt(inputs[1]);
        System.out.println("FILE 3:");
        smallInputStr = OpenFile.parseTxt(inputs[2]);

        int i;

        bitcoin = toDouble.convert(bitcoinStr.size(), bitcoinStr);
        gamestop = toDouble.convert(gamestopStr.size(), gamestopStr);
        smallInput = toDouble.convert(smallInputStr.size(), smallInputStr);

        for (i = 0; i < bitcoinStr.size(); i++) {
            System.out.print(bitcoin[i] + " ");
        }

        System.out.println("");
        for (i = 0; i < gamestopStr.size(); i++) {
            System.out.print(gamestop[i] + " ");
        }
        System.out.println("");
        for (i = 0; i < smallInputStr.size(); i++) {
            System.out.print(smallInput[i] + " ");
        }
    }
}