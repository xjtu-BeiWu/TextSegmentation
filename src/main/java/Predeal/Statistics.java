package Predeal;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by BellaWu on 2017/7/5.
 * 统计文本中的句子数量，单词数量
 */
public class Statistics {
    public static void main(String[] args) {
        String filepath = "D:\\06-text segmentation\\wiki_txts\\manual";
        readFile(filepath);

    }
    // 读取当前文件夹的内容，并且计算文件夹中文件的句子数量
    // 对文件夹中的文件进行预处理，计算文件中的单词数量。
    public static void readFile(String filepath) {
        int snt = 0; //句子数量
//        int wnt = 0;
        File files = new File(filepath);
        if (files.isDirectory()) {
            String[] filelist = files.list();
            for (int i = 0; i < filelist.length; i++) {
                String filedir = filepath + "\\" + filelist[i];
                File readfile = new File(filedir);
                System.out.println(filepath+"\\"+filelist[i]);
                if (readfile.isFile()) {
                    System.out.println(filelist[i]+": ");
                    int[] countR = countNum(filedir);
                    for (int j = 0; j < countR.length; j++) {
                        System.out.println(countR[j]+" ");
                    }
                } else if (readfile.isDirectory()) {
                    String[] filelist2 = readfile.list();
                    for (int j = 0; j < filelist2.length; j++) {
                        String filedir2 = filepath + "\\" + filelist[i] + "\\" + filelist2[j];
                        File readfile2 = new File(filedir2);
                        if (readfile2.isFile()) {
                            int[] countR = countNum(filedir2);
                            for (int k = 0; k < countR.length; k++) {
                                System.out.println(countR[k]+" ");
                            }
                            snt = snt + countR[0];

                        }
                    }

                }{
                    System.out.println("error file");
                }

            }
        }
        System.out.println(snt);


    }

    public static int[] countNum(String filedir) {
        int[] Num={0,0};
        String line;
        int sn = 0;
        int wn = 0;
        try{
            FileReader fileReader = new FileReader(filedir);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                String char1 = "\\.";
//                String char2 = "?";
                String char3 = "!";
//                sn = sn + charNum(line, char1) + charNum(line, char2) + charNum(line, char3);
                sn = sn + charNum(line, char1) + charNum(line, char3);
                System.out.println(charNum(line, char1));
                System.out.println(sn);
                String outline;
//                outline = preProcess(line);
                outline = line;
                wn = wn + charNum(outline, " "); //通常情况下，英文单词都是以空格隔开，所以以空格计算单词数量。
            }

        } catch (IOException e){
            e.printStackTrace();
        }
        Num[0] = sn;
        Num[1] = wn;

        return Num;
    }

    //计算在srcText中出现的findText的次数。
    public static int charNum(String srcText, String findText){
        int count = 0;
        Pattern p = Pattern.compile(findText);
        Matcher m = p.matcher(srcText);
        while (m.find()) {
            count++;
        }
        return count;
    }

    //文本预处理
//    public static String preProcess(String srcText){
//        String outText;
//        outText = srcText.toLowerCase();
//        outText = outText.replaceAll("�"," ");
//        outText = outText.replaceAll("-", " ");
//        outText = outText.replaceAll("—", " ");
//        outText = outText.replaceAll("(\\s[\u4E00-\u9FA5]+)|([\u4E00-\u9FA5]+\\s)", " ");
//        outText = StringUtil.removePunct(outText," ");
//        outText=StringUtil.trimLeft(outText); //左
//        outText=StringUtil.trimRight(outText); //右
//        StringUtil su=new StringUtil();
////        outText=su.rmStopWords(outText); //停用词
//        outText=StringUtil.lemmatize(outText);//还原
//        outText=outText.replaceAll("\\s{1,}", " ");
//        return outText;
//    }
}
