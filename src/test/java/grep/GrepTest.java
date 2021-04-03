package grep;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;



class GrepTest {

    @Test
    void filter1() throws IOException {
        Grep grep1 = new Grep("files/input1.txt", "word", false, false);
        assertEquals(Arrays.asList("dsdflknl;nwordldnfblk", "wordlmndsfbln"), grep1.filter());
    }

    @Test
    void filter2() throws IOException {
        Grep grep2 = new Grep("files/input1.txt", "", false, false);
        assertEquals(Arrays.asList("dsdflknl;nwordldnfblk", "wordlmndsfbln", "ksjdbgkbfkn", "", "woooord", "WorD"),
                grep2.filter());
    }

    @Test
    void filter3() throws IOException {
        Grep grep3 = new Grep("files/input1.txt", "word", true, false);
        assertEquals(Arrays.asList("dsdflknl;nwordldnfblk", "wordlmndsfbln", "WorD"), grep3.filter());
    }

    @Test
    void filter4() throws IOException {
        Grep grep4 = new Grep("files/input1.txt", "word", false, true);
        assertEquals(Arrays.asList("ksjdbgkbfkn", "", "woooord", "WorD"), grep4.filter());
    }

    @Test
    void filter5() throws IOException {
        Grep grep5 = new Grep("files/input1.txt", "word", true, true);
        assertEquals(Arrays.asList("ksjdbgkbfkn", "", "woooord"), grep5.filter());
    }


    @Test
    void filter6() throws IOException {
        Grep grep6 = new Grep("files/input2.txt", "word", true, true);
        assertEquals(Collections.emptyList(), grep6.filter());
    }

    // regex
    @Test
    void filter7() throws IOException {
        Grep grep7 = new Grep("files/input1.txt", "wo{4}rd", true, false);
        assertEquals(Collections.singletonList("woooord"), grep7.filter());
    }

    @Test
    void filter8() throws IOException {
        Grep grep8 = new Grep("files/input1.txt", "^word.", true, false);
        assertEquals(Collections.singletonList("wordlmndsfbln"), grep8.filter());
    }

    @Test
    void filter9() throws IOException {
        Grep grep9 = new Grep("files/input1.txt", "[абв]", true, true);
        assertEquals(Arrays.asList("dsdflknl;nwordldnfblk", "wordlmndsfbln", "ksjdbgkbfkn", "", "woooord", "WorD"),
                grep9.filter());
    }

    @Test
    void filter10() throws IOException {
        Grep grep10 = new Grep("files/input1.txt", "\\b[a-z]", false, false);
        assertEquals(Arrays.asList("dsdflknl;nwordldnfblk", "wordlmndsfbln", "ksjdbgkbfkn", "woooord"),
                grep10.filter());
    }

    @Test
    void filter11() throws IOException {
        Grep grep11 = new Grep("files/input1.txt", "[^ndND]$", true, false);
        assertEquals(Collections.singletonList("dsdflknl;nwordldnfblk"), grep11.filter());
    }

    @Test
    void filter12() throws IOException {
        Grep grep12 = new Grep("files/input1.txt", "^.", false, true);
        assertEquals(Collections.singletonList(""), grep12.filter());
    }

    @Test
    void filter13() throws IOException {
        Grep grep13 = new Grep("files/input1.txt", "^.?", false, true);
        assertEquals(Collections.emptyList(), grep13.filter());
    }

    @Test
    void filter14() throws IOException {
        Grep grep14 = new Grep("files/input1.txt", "^.+?", false, true);
        assertEquals(Collections.singletonList(""), grep14.filter());
    }

    @Test
    void filter15() throws IOException {
        Grep grep15 = new Grep("files/input1.txt", "^.+", false, true);
        assertEquals(Collections.singletonList(""), grep15.filter());
    }

    @Test
    void filter16() throws IOException {
        Grep grep16 = new Grep("files/input1.txt", "^.++", false, false);
        assertEquals(Arrays.asList("dsdflknl;nwordldnfblk", "wordlmndsfbln", "ksjdbgkbfkn", "woooord", "WorD"),
                grep16.filter());
    }
}