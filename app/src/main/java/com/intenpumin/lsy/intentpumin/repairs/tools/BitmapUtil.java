package com.intenpumin.lsy.intentpumin.repairs.tools;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class BitmapUtil {

    private static int threshold = 1024 * 512;

    public static String getTmpCompressFilePath(String filePath) {
        String tmpCompressFilePath = null;
        int inSampleSize = calInSampleSize(filePath);

        if (inSampleSize < 1) {
            return null;
        }

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inSampleSize = inSampleSize;
        Bitmap bitmap = BitmapFactory.decodeFile(filePath, options);

        return saveTmpFile(bitmap);
    }

    private static int calInSampleSize(String filePath) {

        File file = new File(filePath);
        if (file.exists() && file.isFile()) {
            if (file.length() <= threshold) {
                return 1;
            } else {
                return (int) ((file.length() + threshold) / threshold);
            }
        }

        return 0;
    }

    private static String saveTmpFile(Bitmap bitmap) {

        try {
            File tmp = File.createTempFile("bitmap-", ".jpg");
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(tmp));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();

            return tmp.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
