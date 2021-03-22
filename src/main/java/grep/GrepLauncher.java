package grep;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;
import java.util.List;

public class GrepLauncher {
    @Option(name = "-v", metaVar = "--invert-match", usage = "invert")
    private Boolean invert;

    @Option(name = "-i", metaVar = "--ignore-case", usage = "ignoreCase")
    private Boolean ignoreCase;

    @Option(name = "-r", metaVar = "regex", usage = "find with regex")
    private boolean regex;

    @Argument(required = true, metaVar = "word", usage = "Word")
    private String word;

    @Argument(index = 1, required = true, metaVar = "InputName", usage = "Input file name")
    private String inputFileName;

    public static void main(String[] args) {
        new GrepLauncher().launch(args);
    }

    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar grep.jar [-v] [-i] [-r] word inputname.txt");
            parser.printUsage(System.err);
            return;
        }

        ignoreCase = ignoreCase != null && ignoreCase;
        invert = invert != null && invert;
        Grep grep = new Grep(inputFileName, word, ignoreCase, invert);
        try {
            List<String> lines = grep.filter();
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
