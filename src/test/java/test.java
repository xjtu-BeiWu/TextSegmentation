import java.io.File;

/**
 * Created by BellaWu on 2017/7/5.
 */
public class test {
    public static void main(String[] args) {
        System.out.println("hello world");
        System.out.println("hello github");
        System.out.println("test again");
        String str = "/文件夹名1/文件夹名2/文件夹名3";
        String str1 = "1.txt";
//        System.out.println(str.substring(0, str.lastIndexOf("/")));
//        System.out.println(str1.substring(0, str1.lastIndexOf("\\")));
        String fileoutpaths = str1.substring(0, str1.lastIndexOf("."));
        System.out.println(fileoutpaths);
        String l1 = "hello";
        String l2 = "hello2";
        if (!l1.matches(l2)) {
            System.out.println("yes");
        }

        String path = "E:\\gitbase\\TextSegmentation\\src\\test\\resources\\test.txt";
        File file = new File(path);
        if(file.exists()){
            System.out.println(file.getParentFile().getName() + ";" + file.getName());
        }
        System.out.println(file.getParent().substring(0, file.getParent().lastIndexOf("\\")));

        String str3="/文件夹名1/文件夹名2/文件夹名3";
        System.out.println(str3.substring(0, str3.lastIndexOf("/")));
    }
}
