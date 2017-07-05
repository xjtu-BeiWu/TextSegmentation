package Predeal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by BellaWu on 2017/7/5.
 * 统计文件夹中txt的平均行数→数据结构领域主题对应的平均分面数量（包括see also, reference等等）
 *
 */

public class Ffacetnum {
    public static void main(String [] args) {
        String filepath = "D:/06-text segmentation/wiki_facet/1_origin/1";
        System.out.println(ReadFile(filepath));

    }

    public static int ReadFile(String filepath) {
        int an = 0;
        File files = new File(filepath);
        if (files.isDirectory()) {
            String[] filelist = files.list();
            for (int i = 0; i < filelist.length; i++) {
                File readfile = new File(filepath + "/" +filelist[i]);
                if (readfile.isFile()) {
                    String filedir = filepath + "/" + filelist[i];
                    an = an + Rownum(filedir);
                } else {
                    System.out.println("error file!");
                }
            }
            System.out.println(filelist.length);
            an = an/filelist.length;
        } else {
            System.out.println("error files!");
        }
        return an;

    }

    public static int Rownum(String filepath) {
        int n = 0;
        String line = "";
        try {
            FileReader file = new FileReader(filepath);
            BufferedReader bReader = new BufferedReader(file);
            try {
                while ( (line = bReader.readLine()) != null) {
                    if (!line.equals("")) {
                        n++;
                    }

                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(filepath + ":" + n);

        return n;

    }


}
