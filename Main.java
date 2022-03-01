import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String [] args) throws IOException {
        int a, i, j;
        String[] inputs = {"bitcoin.txt", "gamestop.txt", "smallInput.txt"};
        
        List<String> bitcoin, gamestop, smallInput;
        bitcoin = OpenFile.parseTxt(inputs[0]);
        gamestop = OpenFile.parseTxt(inputs[1]);
        smallInput = OpenFile.parseTxt(inputs[2]);
        
        //System.out.println(Arrays.toString(samples));
    }
}