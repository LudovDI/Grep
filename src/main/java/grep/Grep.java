package grep;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grep {

    private final String fileName;

    private final String regex;

    private final Boolean i;

    private final Boolean v;

    public Grep(String fileName, String regex, Boolean i, Boolean v) {
        this.fileName = fileName;
        this.i = i;
        this.v = v;
        this.regex = regex;
    }

    public List<String> filter() throws IOException {
        List<String> result = new ArrayList<>();
        FileReader fr = new FileReader(new File(fileName));
        try (BufferedReader br = new BufferedReader(fr)) {
            String line = br.readLine();
            Pattern pattern = (i) ? Pattern.compile(regex, Pattern.CASE_INSENSITIVE) : Pattern.compile(regex);
            while (line != null) {
                Matcher matcher = pattern.matcher(line);
                if (v ^ matcher.find()) result.add(line);
                line = br.readLine();
            }
        }
        return result;
    }
}