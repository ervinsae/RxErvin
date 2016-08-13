package com.ervin.android.rxervin.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Ervin on 2016/4/27.
 */
public class FileUtils {

    public static boolean checkSDCard() {
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 保存文件文件到目录
     *
     * @param context
     * @return 文件保存的目录
     */
    public static String setMkdir(Context context) {
        String filePath;
        if (checkSDCard()) {
            filePath = Environment.getExternalStorageDirectory() + File.separator + "/Patient/" ;
        } else {
            filePath = context.getCacheDir().getAbsolutePath() + File.separator + "/Patient/" ;
        }
        File file = new File(filePath);
        if (!file.exists()) {
            boolean b = file.mkdirs();
            Log.e("file", "文件不存在  创建文件    " + b);
        } else {
            Log.e("file", "文件存在");
        }
        return filePath;
    }

    public static File saveFile(Context context, String fileName, InputStream is) throws IOException {
        File file=null;
        OutputStream out=null;//写入流
        try {
            file = new File(context.getFilesDir(),fileName);
            if(file.exists()){
                file.delete();
            }

            out = new FileOutputStream(file);
            int temp = 0;
            while((temp = is.read())!=-1) {
                out.write(temp);
            }
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }


        return file;
    }

    /**
     * 读取指定文件的输出
     */
    public static String getFileOutputString(String path) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path), 8192);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append("\n").append(line);
            }
            bufferedReader.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
