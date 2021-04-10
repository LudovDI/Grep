package grep;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grep {

    private final String word;

    private final boolean regex;

    private final String fileName;

    private final boolean v;

    private final boolean i;

    private final Pattern pattern;

    public Grep(String fileName, boolean regex, boolean i, boolean v, String word) {
        this.fileName = fileName;
        this.v = v;
        this.i = i;
        this.word = word;
        this.regex = regex;
        pattern = (i) ? Pattern.compile(word, Pattern.CASE_INSENSITIVE) : Pattern.compile(word);
    }

    public List<String> filter() throws IOException {
        List<String> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine();
            while (line != null) {
                if (regex) {
                    Matcher matcher = pattern.matcher(line);
                    if (v ^ matcher.find()) result.add(line);
                } else {
                    if (i) {
                        if (v ^ line.toLowerCase().contains(word.toLowerCase())) result.add(line);
                    } else {
                        if (v ^ line.contains(word)) result.add(line);
                    }
                }
                line = br.readLine();
            }
        }
        return result;
    }
}