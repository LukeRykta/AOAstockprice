import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static List<String> OpenFile(String input) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(String.format("inputs/%s", input)));
        List<String> form = new ArrayList<>();

        String line = reader.readLine();

        while (line != null) {
            form.add(line);
            line = reader.readLine();
        }

        reader.close();

        System.out.println(form);
        System.out.println("----- FINISHED READING FILE -----");

        return form;
    }

    public static void main(String [] args) throws IOException {
        int a, i, j;
        String[] inputs = {"bitcoin.txt", "gamestop.txt", "smallInput.txt"};
        
        List<String> bitcoin, gamestop, smallInput;
        bitcoin = OpenFile(inputs[0]);
        gamestop = OpenFile(inputs[1]);
        smallInput = OpenFile(inputs[2]);
        
        //System.out.println(Arrays.toString(samples));
    }
}