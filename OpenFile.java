import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OpenFile {

    public static List<String> parseTxt(String input) throws IOException {
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
}
