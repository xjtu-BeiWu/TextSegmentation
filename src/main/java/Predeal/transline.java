package Predeal;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.*;

/**
 * Created by BellaWu on 2017/7/27.
 * 考虑到生成vector过程中，有些词不存在，会出现空行不好统计词数。
 * 所以将每一句话单独存成一个txt。
 */
public class transline {
    public static void main(String[] args) throws Exception {
        String inputPath = "D:\\06-text segmentation\\wiki_txts\\datav3_manual2\\data2";
        String outputPath = "D:\\06-text segmentation\\wiki_txts\\datav3_manual2\\data3";
        String coding = "UTF-8";
        File files = new File(inputPath);
        if (files.isDirectory()) {
            String[] filelists = files.list();
            for (int i = 0; i < filelists.length; i++) {
                File readfiles = new File(inputPath + "\\" + filelists[i]);
                if (readfiles.isFile()) {
                    String fileinpath = inputPath + "\\" + filelists[i];
                    String filesoutpaths = outputPath + "\\" +
                            filelists[i].substring(0, filelists[i].lastIndexOf("."));
                } else if (readfiles.isDirectory()) {
                    String[] filelist  = readfiles.list();
                    for (int j = 0; j < filelist.length; j++) {
                        File readfile = new File(inputPath + "\\" +filelists[i] + "\\" + filelist[j]);
                        if (readfile.isFile()) {
                            String fileinpath = inputPath + "\\" + filelists[i] + "\\" + filelist[j];
                            String filesoutpath = outputPath + "\\" + filelists[i] + "\\" +
                                    filelist[j].substring(0, filelist[j].lastIndexOf("."));
                            trans2(fileinpath,filesoutpath,coding);
                        }
                        System.out.println( filelists[i] + ":" + filelist[j].substring(0, filelist[j].lastIndexOf(".")) + " is finished!");
                    }
                }
            }

        }
    }

    public static void trans2(String inputfpath, String outputfspath, String coding) throws Exception {
        File inputfile = new File(inputfpath);
//        FileReader fileReader = new FileReader(inputfile);
//        BufferedReader bufferedReader = new BufferedReader(fileReader);
        File dir = new File(outputfspath);
        judeDirExists(dir);
        List<String> strs=FileUtils.readLines(inputfile, coding);
        System.out.println(strs.size());
        for (int i = 0; i < strs.size(); i++) {
            String newfilepath = outputfspath + "\\" + i + ".txt";
            File file = new File(newfilepath);
            if (file.exists()){
                System.out.println("file is exists! Error!!!!!!!!!!!!!!!!!!!");
                file.delete();
            } else {
                file.createNewFile();
            }
            if (strs.get(i) != null) {
                FileUtils.write(file, strs.get(i),coding);
            } else {
                continue;
            }
        }
    }

    //判断文件夹是否存在
    public static void judeDirExists(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
            }
        } else {
            file.mkdirs();
        }
    }

//    //判断文本文件的行数
//    public static int lineNum(String inputfile) throws Exception {
//        int num = 0;
//        FileReader fr = new FileReader(inputfile);
//        BufferedReader br = new BufferedReader(fr);
//        while (br.readLine() != null) {
//            num++;
//        }
//        return num;
//    }
}
