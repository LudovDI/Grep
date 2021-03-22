package grep;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;



class GrepTest {
    Grep grep1 = new Grep("files/input1.txt", "word", false, false);
    Grep grep2 = new Grep("files/input1.txt", "", false, false);
    Grep grep3 = new Grep("files/input1.txt", "word", true, false);
    Grep grep4 = new Grep("files/input1.txt", "word", false, true);
    Grep grep5 = new Grep("files/input1.txt", "word", true, true);
    Grep grep6 = new Grep("files/input1.txt", "k?j", false, false);
    Grep grep7 = new Grep("files/input2.txt", "word", true, true);

    @Test
    void filter() throws IOException {
        assertEquals(Arrays.asList("dsdflknl;nwordldnfblk", "wordlmndsfbln"), grep1.filter());
        assertEquals(Arrays.asList("dsdflknl;nwordldnfblk", "wordlmndsfbln", "ksjdbgkbfkn", "", "woooord", "WorD"), grep2.filter());
        assertEquals(Arrays.asList("dsdflknl;nwordldnfblk", "wordlmndsfbln", "WorD"), grep3.filter());
        assertEquals(Arrays.asList("ksjdbgkbfkn", "", "woooord", "WorD"), grep4.filter());
        assertEquals(Arrays.asList("ksjdbgkbfkn", "", "woooord"), grep5.filter());
        assertEquals(Collections.singletonList("ksjdbgkbfkn"), grep6.filter());
        assertEquals(Collections.emptyList(), grep7.filter());
    }
}