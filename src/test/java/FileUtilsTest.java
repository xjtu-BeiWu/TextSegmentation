import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;


/**
 * Created by BellaWu on 2017/7/27.
 */
public class FileUtilsTest {
    public static void main(String[] args) throws IOException {
//        FileUtils.readLines(new File(""));
        File file = new File("E:\\gitbase\\TextSegmentation\\src\\test\\resources\\test.txt");
        String strs = "hello world!!!!!!!!!!!";
        String coding = "UTF-8";
        FileUtils.write(file, strs, coding);

    }
}
