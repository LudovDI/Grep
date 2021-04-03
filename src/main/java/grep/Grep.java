package grep;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grep {

    private final String fileName;

    private final boolean v;

    private final Pattern pattern;

    public Grep(String fileName, String regex, boolean i, boolean v) {
        this.fileName = fileName;
        this.v = v;
        pattern = (i) ? Pattern.compile(regex, Pattern.CASE_INSENSITIVE) : Pattern.compile(regex);
    }

    public List<String> filter() throws IOException {
        List<String> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(new File(fileName)))) {
            String line = br.readLine();
            while (line != null) {
                Matcher matcher = pattern.matcher(line);
                if (v ^ matcher.find()) result.add(line);
                line = br.readLine();
            }
        }
        return result;
    }
}