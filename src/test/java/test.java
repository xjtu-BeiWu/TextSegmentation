/**
 * Created by BellaWu on 2017/7/5.
 */
public class test {
    public static void main(String[] args){
        System.out.println("hello world");
        System.out.println("hello github");
        System.out.println("test again");
        String str="/文件夹名1/文件夹名2/文件夹名3";
        String str1 = "D:\\06-text segmentation\\wiki_txts\\datav3_manual2\\data2";
        System.out.println(str.substring(0, str.lastIndexOf("/")));
        System.out.println(str1.substring(0, str1.lastIndexOf("\\")));
    }
}
