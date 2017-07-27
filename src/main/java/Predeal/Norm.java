package Predeal;

import java.io.*;
import java.util.*;

/**
 * Created by BellaWu on 2017/7/25.
 * 主要是将txt文件中内容按照一行一句话的格式进行处理。
 */
public class Norm {
    public static void main(String[] str) throws Exception{
        String inputPath = "D:\\06-text segmentation\\wiki_txts\\datav3_manual2\\data";
        String outputPath = "D:\\06-text segmentation\\wiki_txts\\datav3_manual2\\data2";
        File files = new File(inputPath);
        if (files.isDirectory()){
            String[] filelists = files.list();
            for (int i = 0; i < filelists.length; i++) {
                File readfiles = new File(inputPath + "\\" + filelists[i]);
                if (readfiles.isFile()) {
                    String filepath = inputPath + "\\" + filelists[i];
                    String fileoutpath = outputPath + "\\" + filelists[i];
                    trans(filepath,fileoutpath);
                }
                if (readfiles.isDirectory()) {
                    String[] filelist = readfiles.list();
                    for (int j = 0; j < filelist.length; j++) {
                        File readfile = new File(inputPath + "\\" + filelists[i] + "\\" + filelist[j]);
                        if (readfile.isFile()) {
                            String filepath = inputPath + "\\" + filelists[i] + "\\" + filelist[j];
                            String fileoutpath = outputPath + "\\" + filelists[i] +  "\\" + filelist[j];
                            trans(filepath,fileoutpath);
                        }
                    }
                }
            }
        }
    }

    public static void trans (String inputfpath, String outputfpath) throws Exception {
        File inputfile = new File(inputfpath);
        FileReader fileReader = new FileReader(inputfile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String outputfpath1 = outputfpath.substring(0, outputfpath.lastIndexOf("\\"));
        File dir = new File(outputfpath1);
        judeDirExists(dir);


        FileWriter fileWriter = new FileWriter(outputfpath);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        ArrayList<String> tokens = new ArrayList<String>();
        String line = bufferedReader.readLine();
        String lastOne = "";

        while ((line != null)) {
            if (!(line.equals(""))) {
                if (line.length() == 1 || line.startsWith(".") || line.startsWith(",")) {
                    lastOne = tokens.get((tokens.size() - 1));
                    lastOne += line;
                    tokens.set(tokens.size() - 1, lastOne);
//                    System.out.println("this is the last one " + lastOne);
                } else {
                    tokens.add(line);
                }
            }
            line = bufferedReader.readLine();
        }
        bufferedReader.close();

        for (int i = 0; i < tokens.size(); i++) {
            String sentence = tokens.get(i);
            if (sentence.contains(".")) {
                String[] temp = sentence.split("\\.\\s*");
                for (String sen : temp) {
                    if (sen.endsWith(":")) {
                        bufferedWriter.write(sen.trim());
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                    }
                    else if (sen.contains(";")) {
//                        sen.replaceAll("\n"," ");
//                        System.out.println("this sen is " + sen);
                    } else {
                        bufferedWriter.write(sen.trim() + ".");
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                    }
                }
            } else {
                bufferedWriter.write(tokens.get(i));
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        }
        bufferedWriter.close();
    }

    //判断文件夹是否存在
    public static void judeDirExists(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
            }
        } else {
            file.mkdir();
        }
    }
}
