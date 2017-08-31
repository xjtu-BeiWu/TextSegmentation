package word2vec;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by BellaWu on 2017/7/27.
 * 利用已经训练好的词向量模型："D:\\wiki_Data\\wiki.en.text.vector"（英文）
 * 匹配新的文本数据，生成对应的词向量文件
 *
 */

public class WordToVec {

    // 读取txt具体某一行
    private static String readLine(int lineNumber, BufferedReader reader) throws Exception {
        lineNumber = lineNumber + 1;
        String line = "";
        int i = 0;
        while (i < lineNumber) {
            line = reader.readLine();
            i++;
        }
        return line;
    }

    /**
     * 一种思路：将词向量模型的所有向量都读取到一个java对象中，保存到本地，然后直接调用
     * @param filePath
     * @param inputCatalog
     * @param outputCatalog
     * @throws Exception
     */
    public static void Match(String filePath, String inputCatalog, String outputCatalog) throws Exception {
        new File(outputCatalog).mkdir();
        String encoding = "UTF-8";
        File file = new File(filePath);
        File fileCatalog = new File(inputCatalog);
        File[] files = fileCatalog.listFiles();
        String filename = "";
        String inputFilepath = "";
        String outputFilepath = "";

        // 将词向量模型中存在的词读取到list中
        ArrayList<String> string1 = new ArrayList<String>();
        long start = System.currentTimeMillis();
        InputStreamReader read1 = new InputStreamReader(new FileInputStream(file), encoding);//读取模型的到缓存中
        BufferedReader bufferedReader1 = new BufferedReader(read1);
        String lineTxt1 = null;
        while ((lineTxt1 = bufferedReader1.readLine()) != null) {
            String[] split1 = lineTxt1.split(" ");
            string1.add(split1[0]);
        }
        long end = System.currentTimeMillis();
        System.out.println("读取已有的词向量模型耗时1：" + (end - start) / 1000 + "s");

        // 匹配生成词向量
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < files.length; i++) {
            filename = files[i].getName(); //读取待处理的文件名
            inputFilepath = inputCatalog + "\\" + filename;
            outputFilepath = outputCatalog + "\\" + filename;
            FileWriter outfile = new FileWriter(outputFilepath);
            try {
                if (file.isFile() && file.exists()) {
                    // 判断文件是否存在，读取文件内容到Buffer中
                    InputStreamReader read2 = new InputStreamReader(new FileInputStream(inputFilepath), encoding);
                    BufferedReader bufferedReader2 = new BufferedReader(read2);
                    String lineTxt2 = null;
                    while ((lineTxt2 = bufferedReader2.readLine()) != null) {
                        String[] split2 = lineTxt2.split(" "); // 读取每一个单词
                        // 对每一个单词到词向量模型中生成对应的词向量，需要再次遍历模型
                        for (String string : split2) {
                            int index = string1.indexOf(string); // 找到该单词在词向量模型中的下标
                            InputStreamReader read3 = new InputStreamReader(new FileInputStream(file), encoding);
                            BufferedReader bufferedReader11 = new BufferedReader(read3);
                            String lineVec = readLine(index, bufferedReader11);
                            outfile.write(lineVec + "\n");
                        }

                    }
                    outfile.close();//注意关闭，否则会有问题
                    // System.out.println(string1.size());
                    read1.close();
                    read2.close();
                } else {
                    System.out.println("找不到指定文件");
                }
            } catch (Exception e) {
                System.out.println("读取文件出错");
                e.printStackTrace();
            }
        }
        long end1 = System.currentTimeMillis();
        System.out.println("词向量处理完毕，耗时1："+(end1-start1)/1000+"s");
    }

    /**
     * 一种思路：将词向量模型的所有向量都读取到一个java对象中，保存到本地，然后直接调用
     * @param filePath
     * @param inputCatalog
     * @param outputCatalog
     * @throws Exception
     */
    public static void MatchFast(String filePath, String inputCatalog, String outputCatalog) throws Exception {
        new File(outputCatalog).mkdir();
        String encoding = "UTF-8";
        File file = new File(filePath);
        File fileCatalog = new File(inputCatalog);
        File[] files = fileCatalog.listFiles();
        String filename = "";
        String filenames = "";
        String filenamess = "";
        String inputFilepath = "";
        String outputFilepath = "";
        String inputFilepaths = "";
        String outputFilepaths = "";
        String inputFilepathss = "";
        String outputFilepathss = "";

        // 将词向量模型中存在的词读取到list中
        ArrayList<String> string1 = new ArrayList<String>();
        ArrayList<String> word2vecAll = new ArrayList<String>();
        long start = System.currentTimeMillis();
        InputStreamReader read1 = new InputStreamReader(new FileInputStream(file), encoding);//读取模型的到缓存中
        BufferedReader bufferedReader1 = new BufferedReader(read1);
        String lineTxt1 = null;
        while ((lineTxt1 = bufferedReader1.readLine()) != null) {
            String[] split1 = lineTxt1.split(" ");
            string1.add(split1[0]);
            word2vecAll.add(lineTxt1);
        }
        long end = System.currentTimeMillis();
        System.out.println("---读取已有的词向量模型耗时1：" + (end - start) / 1000 + "s");

        // 匹配生成词向量
        long start1 = System.currentTimeMillis();
        for (int i = 0; i<files.length; i++) {
            File of = new File(outputCatalog);
            if (!of.exists()){
                of.mkdir();
            }
            filenames = files[i].getName();
            inputFilepaths = inputCatalog + "\\" + filenames;
            outputFilepaths = outputCatalog + "\\" + filenames;
            File oof = new File(outputFilepaths);
            if(!oof.exists()){
                oof.mkdir();
            }
            File fs = new File(inputFilepaths);
            File[] fis = fs.listFiles();
            for (int j = 0; j<fis.length; j++) {
                filenamess = fis[j].getName();
                inputFilepathss = inputFilepaths + "\\" + filenamess;
                outputFilepathss = outputFilepaths + "\\" + filenamess;
                File ooof = new File(outputFilepathss);
                if(!ooof.exists()){
                    ooof.mkdir();
                }
                File fss = new File(inputFilepathss);
                File[] fiss = fss.listFiles();
                for (int k = 0; k < fiss.length; k++){
                    filename = fiss[k].getName();
                    inputFilepath = inputFilepathss + "\\" + filename;
                    outputFilepath = outputFilepathss + "\\" + filename;
                    FileWriter outfile = new FileWriter(outputFilepath);
                    try {
                        if (file.isFile() && file.exists()) {
                            // 判断文件是否存在，读取文件内容到Buffer中
                            InputStreamReader read2 = new InputStreamReader(new FileInputStream(inputFilepath), encoding);
                            BufferedReader bufferedReader2 = new BufferedReader(read2);
                            String lineTxt2 = null;
                            while ((lineTxt2 = bufferedReader2.readLine()) != null) {
                                String[] split2 = lineTxt2.split(" "); // 读取每一个单词
                                // 对每一个单词到词向量模型中生成对应的词向量，需要再次遍历模型
                                for (String string : split2) {
                                    int index = string1.indexOf(string); // 找到该单词在词向量模型中的下标
                                    if(index != -1){
                                        String lineVec = word2vecAll.get(index);
                                        outfile.write(lineVec + "\n");
                                    }
                                }
                            }
                            outfile.close();//注意关闭，否则会有问题
                            // System.out.println(string1.size());
                            read1.close();
                            read2.close();
                        } else {
                            System.out.println("找不到指定文件");
                        }
                    } catch (Exception e) {
                        System.out.println("读取文件出错");
                        e.printStackTrace();
                    }
                }
            }
        }
//        for (int i = 0; i < files.length; i++) {
//            filename = files[i].getName(); //读取待处理的文件名
//            inputFilepath = inputCatalog + "\\" + filename;
//            outputFilepath = outputCatalog + "\\" + filename;
//            FileWriter outfile = new FileWriter(outputFilepath);
//            try {
//                if (file.isFile() && file.exists()) {
//                    // 判断文件是否存在，读取文件内容到Buffer中
//                    InputStreamReader read2 = new InputStreamReader(new FileInputStream(inputFilepath), encoding);
//                    BufferedReader bufferedReader2 = new BufferedReader(read2);
//                    String lineTxt2 = null;
//                    while ((lineTxt2 = bufferedReader2.readLine()) != null) {
//                        String[] split2 = lineTxt2.split(" "); // 读取每一个单词
//                        // 对每一个单词到词向量模型中生成对应的词向量，需要再次遍历模型
//                        for (String string : split2) {
//                            int index = string1.indexOf(string); // 找到该单词在词向量模型中的下标
//                            if(index != -1){
//                                String lineVec = word2vecAll.get(index);
//                                outfile.write(lineVec + "\n");
//                            }
//                        }
//
//                    }
//                    outfile.close();//注意关闭，否则会有问题
//                    // System.out.println(string1.size());
//                    read1.close();
//                    read2.close();
//                } else {
//                    System.out.println("找不到指定文件");
//                }
//            } catch (Exception e) {
//                System.out.println("读取文件出错");
//                e.printStackTrace();
//            }
//        }
        long end1 = System.currentTimeMillis();
        System.out.println("---词向量处理完毕，耗时1："+(end1-start1)/1000+"s");
    }

    @SuppressWarnings("resource")
    public static void MatchSingle(String filePath, String inputCatalog, String outputCatalog) throws Exception {
        String encoding = "UTF-8";
        File file = new File(filePath);
        // File fileCatalog = new File(inputCatalog);
        // File[] files = fileCatalog.listFiles();
        String filename = null;
        String inputFilepath = null;
        String outputFilepath = null;
        // String str = "";
        // int rowNum = 0;

        // String[] string1 = new String[3000000];
        ArrayList<String> string1 = new ArrayList<String>();
        // BufferedReader bufferedReader = new BufferedReader(new
        // FileReader(filePath));
        // for (int i = 0; i < files.length; i++) {
        // filename = files[i].getName();
        filename = "Absolute+deviation0.txt";
        inputFilepath = inputCatalog + filename;
        outputFilepath = outputCatalog + filename;
        FileWriter outfile = new FileWriter(outputFilepath);
        try {

            if (file.isFile() && file.exists()) { // 判断文件是否存在
                InputStreamReader read1 = new InputStreamReader(new FileInputStream(file), encoding);
                InputStreamReader read2 = new InputStreamReader(new FileInputStream(inputFilepath), encoding);
                BufferedReader bufferedReader1 = new BufferedReader(read1);
                BufferedReader bufferedReader2 = new BufferedReader(read2);
                // while ((bufferedReader1.readLine()) != null) {
                // rowNum++;
                // }
                String lineTxt1 = null;
                String lineTxt2 = null;
                // int rowNum=0;
                while ((lineTxt1 = bufferedReader1.readLine()) != null) {

                    String[] split1 = lineTxt1.split(" ");
                    // for(String string:split1){
                    string1.add(split1[0]);
                    // System.out.println(split1[0]);
                    // }

                }
                while ((lineTxt2 = bufferedReader2.readLine()) != null) {
                    String[] split2 = lineTxt2.split(" ");
                    for (String string : split2) {
                        int index = string1.indexOf(string);
                        // System.out.println(index+1);
                        InputStreamReader read3 = new InputStreamReader(new FileInputStream(file), encoding);
                        BufferedReader bufferedReader11 = new BufferedReader(read3);
                        String lineVec = readLine(index, bufferedReader11);
                        // System.out.println("line:" + lineVec);
                        outfile.write(lineVec + "\n");
                        // System.out.println(index);
                    }

                }

                // System.out.println(string1.size());
                read1.close();
                read2.close();
            } else {
                System.out.println("找不到指定文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件出错");
            e.printStackTrace();
        }
        // }
    }

    public static void main(String argv[]) throws Exception {
        String filePath1 = "F:\\zheng\\wordtovec\\wiki50.en.text.vector";
        String inputCatalog = "D:\\word2vec\\test\\test2\\";
        String outputCatalog = "D:\\word2vec\\test\\test2_word2vec\\";
        String outputCatalog_fast = "D:\\word2vec\\test\\test2_word2vec_fast\\";
        Match(filePath1, inputCatalog, outputCatalog);
        MatchFast(filePath1, inputCatalog, outputCatalog_fast);
    }

}
