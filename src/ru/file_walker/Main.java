package ru.file_walker;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
	    FileWalker fw = new FileWalker();
	    fw.start_walk(
	            "..\\",
                "output.txt"
        );

    }
}
