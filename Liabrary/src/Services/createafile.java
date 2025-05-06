package Services;

import java.io.File;
import java.io.IOException;

public class createafile {
    public static void main(String[] args) {
        try{
            File file = new File("log.data");
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
