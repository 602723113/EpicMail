package cc.zoyn.epicmail.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public final class StreamUtils {

    public static void writeToLocal(String path, InputStream input) throws IOException {
        if (input == null) {
            return;
        }
        int index;
        byte[] bytes = new byte[1024];
        FileOutputStream downloadFile;
        downloadFile = new FileOutputStream(path);
        while ((index = input.read(bytes)) != -1) {
            downloadFile.write(bytes, 0, index);
            downloadFile.flush();
        }
        downloadFile.close();
        input.close();
    }

}
