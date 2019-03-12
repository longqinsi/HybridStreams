package org.paumard.io;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPOutputStream;

import org.paumard.io.model.Fable;
import org.paumard.io.util.AesopReader;
import org.paumard.io.util.FableData;

public class WritingHybridStream {

    public static void main(String[] args) throws IOException {

        AesopReader aesopReader = new AesopReader();
        List<Fable> fables = aesopReader.readFable("files/aesop.txt");

        System.out.println("# fable = " + fables.size());

//      Aesop's Fables
//      291
//         1235     123 The Wolf and the Lamb
//         3271     245 The Bat and the Weasels
//      <binary texts>

        ByteArrayOutputStream aesopBos = new ByteArrayOutputStream();
        OutputStreamWriter out = new OutputStreamWriter(aesopBos, StandardCharsets.UTF_8);

        // The regular character stream [textBos] is decoration of the hybrid stream [aesopBos].
        PrintWriter printer = new PrintWriter(out);
        printer.println("Aesop's Fables");
        printer.printf("%d%n", fables.size());
        for (Fable fable : fables) {
            printer.printf("%7d %7d %s%n", 0, 0, fable.getTitle());
        }
        printer.flush();
        int textOffset = aesopBos.size();
        aesopBos.close();

        List<FableData> fableDatas = new ArrayList<>();
        ByteArrayOutputStream textBos = new ByteArrayOutputStream();

        int offset = textOffset;
        for (Fable fable : fables) {

            ByteArrayOutputStream fableBos = new ByteArrayOutputStream();
            try (GZIPOutputStream gzipOs = new GZIPOutputStream(fableBos)) {

                gzipOs.write(fable.getText().getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }

            int length = fableBos.size();
            fableBos.writeTo(textBos);

            FableData fableData = new FableData(fable, offset, length);
            offset += length;

            fableDatas.add(fableData);
        }

        aesopBos = new ByteArrayOutputStream();
        out = new OutputStreamWriter(aesopBos, StandardCharsets.UTF_8);

        printer = new PrintWriter(out);

        printer.println("Aesop's Fables");
        printer.printf("%d%n", fables.size());
        for (FableData fableData : fableDatas) {
            printer.printf("%7d %7d %s%n", fableData.getOffset(), fableData.getLength(),
                    fableData.getFable().getTitle());
        }
        printer.flush();

        System.out.println(new String(aesopBos.toByteArray()));

        // The binary stream [textBos] is written to the hybrid stream [aesopBos].
        textBos.writeTo(aesopBos);
        aesopBos.close();
        // Jose Paumard said: "A hybrid stream is just a regular character stream,
        // and a binary stream that are open together, on the same binary stream."
        // According to this, a regular character stream [printer] and a binary stream
        // [textBos] are open together, on the same binary stream [aesopBos].

        File file = new File("files/aesops-compressed.bin");
        try (OutputStream os = new FileOutputStream(file)) {

            os.write(aesopBos.toByteArray());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
