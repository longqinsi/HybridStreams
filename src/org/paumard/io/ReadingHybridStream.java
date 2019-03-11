package org.paumard.io;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class ReadingHybridStream {

    public static void main(String[] args) throws IOException {

        int lineOfTheFirstFable = 1;
        int n = 1;

        String pathname = "files/aesops-compressed.bin";
        File file = new File(pathname);
        int size = (int)Files.size(Paths.get(pathname));

        try (InputStream is = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(is);) {




        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
