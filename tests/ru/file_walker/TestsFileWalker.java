package ru.file_walker;

import org.junit.*;

import java.io.*;
import java.util.ArrayList;

public class TestsFileWalker {
    public String FormatString(String s){
        String s1 = s.replaceAll("Dir:|File:|-", " ");
        s1 = s1.trim();
        return s1;
    }

    public ArrayList<String> ReadTxt(String file_path) throws FileNotFoundException {
        ArrayList<String> actual_ans = new ArrayList<>();
        try {
            File file = new File(file_path);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                actual_ans.add(FormatString(line));
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return actual_ans;
    }

    @Test
    public void testFileWalker01() throws IOException {
        FileWalker fw = new FileWalker();
        String dir_path = "filewalker_test";
        String file_path = "filewalker_test//output.txt";
        String[] right_ans = new String[]{"example01", "bar.txt", "example02", "foo.txt", "lalala.txt",
                "new_folder", "super_txt.txt", "test.txt"};
        fw.start_walk(dir_path, file_path);
        ArrayList<String> actual_ans = ReadTxt(file_path);
        Assert.assertArrayEquals(right_ans,actual_ans.toArray());
    }

    @Test(expected = FileNotFoundException.class)
    public void testFileWalker02() throws IOException {
        FileWalker fw = new FileWalker();
        String dir_path = "abrakadabra9595";
        String file_path = "filewalker_test//output.txt";
        String[] right_ans = new String[]{};
        fw.start_walk(dir_path, file_path);
        ArrayList<String> actual_ans = ReadTxt(file_path);
        Assert.assertArrayEquals(right_ans,actual_ans.toArray());
    }

    @Test(expected = FileNotFoundException.class)
    public void testFileWalker03() throws IOException {
        FileWalker fw = new FileWalker();
        String dir_path = "filewalker_test";
        String file_path = "filewalker_test"; // не файл!
        String[] right_ans = new String[]{};
        fw.start_walk(dir_path, file_path);
        ArrayList<String> actual_ans = ReadTxt(file_path);
        Assert.assertArrayEquals(right_ans,actual_ans.toArray());
    }

}
