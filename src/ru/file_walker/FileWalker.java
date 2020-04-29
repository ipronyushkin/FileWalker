package ru.file_walker;

import java.io.*;

public class FileWalker {
    String tmp = ""; // для иерархичности папок и файлов
    public void walk(String dir_path, String file_path) {
        File root = new File(dir_path);
        File[] list = root.listFiles();
        if(list == null) { return; }
        try(FileWriter writer = new FileWriter(file_path, true)) {
            for (File f : list) {
                if (f.isDirectory()) {
                    writer.write(tmp + "Dir: " + f.getName() + "\n");
                    writer.flush();
                    tmp += " ";
                    walk(f.getAbsolutePath(), file_path);
                    tmp = tmp.substring(0, tmp.length() - 1);
                } else {
                    writer.write(tmp + "--File: " + f.getName() + "\n");
                    writer.flush();
                }
            }
        } catch(IOException ioe) {
            ioe.getStackTrace();
        }
    }

    public void start_walk(String dir_path, String file_path) throws IOException {
        File file = new File(file_path);
        dir_path = new File(dir_path).getCanonicalPath();
        if(file.exists() && !file.isDirectory()) {
            file.delete();
        }
        file_path = file.getCanonicalPath();
        walk(dir_path, file_path);
    }
}
