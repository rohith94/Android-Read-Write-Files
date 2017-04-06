package test.readwrite;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileHelper {
    private final static String TAG = FileHelper.class.getName();
    private final static String DIRECTORY = "/.readWr";
    private final static String FILE_NAME = ".appData.txt";

    public static String readExternalStorage() {
        File root = Environment.getExternalStorageDirectory();
        File Dir = new File(root.getAbsolutePath() + DIRECTORY);
        File file = new File(Dir, FILE_NAME);

        String datafromfile;
        StringBuffer stringBuffer = new StringBuffer();

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);


            while ((datafromfile = bufferedReader.readLine()) != null) {
                stringBuffer.append(datafromfile + "\n");
            }

        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }


        return stringBuffer.toString();
    }

    public static boolean writeExternalStorage(Context context, String Data) {

        String state;
        state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state)) {
            File root = Environment.getExternalStorageDirectory();
            File Dir = new File(root.getAbsolutePath() + DIRECTORY);
            if (!Dir.exists()) {
                Dir.mkdir();
            }

            File file = new File(Dir, FILE_NAME);

            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(Data.getBytes());
                fileOutputStream.close();
                Toast.makeText(context, "message saved", Toast.LENGTH_SHORT).show();
                return true;
            } catch (IOException e) {
                Log.e(TAG, e.getMessage());
            }

        } else {
            Toast.makeText(context, "SD card not found", Toast.LENGTH_SHORT).show();
        }


        return false;
    }
}
