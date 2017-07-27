package WordToVec;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStreamReader;

/**
 * Created by BellaWu on 2017/7/27.
 * 由于生成词向量矩阵的过程中，最后一个或者两个单词向量不完整，因此直接去除，简化替代增添操作。
 *
 */
public class PostDeal {

    public static void deleteWordandLine(String sourcePath, String targetPath) throws Exception{

        File file = new File(sourcePath);
        File[] subFile = file.listFiles();   //当前文件夹的所有文件，放到File数组里，然后下面是遍历每一个文件
        for (int i = 0; i < subFile.length; i++) {
            String fileName = subFile[i].getName();
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader
                        (new FileInputStream(subFile[i].getAbsolutePath())));
                BufferedWriter bw = new BufferedWriter(new FileWriter
                        (targetPath+ "\\" + fileName));
                // 写自己的处理文本的程序，下面是读取文件的例子
                String input = "";
                while((input = br.readLine()) != null)
                {
                    if(input.equals("")) continue;
                    String string = input;
                    string = string.substring(string.indexOf(" ")+1);
                    bw.write(string);
                    bw.newLine();
                }
                br.close();//一定要随时关闭
                bw.close();
            }
            catch (FileNotFoundException e) {
                System.out.println("文件找不到");
            }
//			System.out.println(fileName + " done");
        }
    }

    @SuppressWarnings("resource")
    public static void deleteLastline(String inputCatalog, String outputCatalog){
        String encoding = "UTF-8";
        File fileCatalog = new File(inputCatalog);
        File[] files = fileCatalog.listFiles();
        String inputFilepath = null;
        String outputFilepath = null;
        String filename = null;

        try{
            for(int i = 0; i < files.length; i++){
                filename = files[i].getName();
                inputFilepath = inputCatalog + "\\" +  filename;
                outputFilepath = outputCatalog + "\\" + filename;
                File file = new File(inputFilepath);
                FileWriter writer = new FileWriter(outputFilepath);
                if(file.isFile() && file.exists()){
                    InputStreamReader read1 = new InputStreamReader(
                            new FileInputStream(file), encoding);
                    InputStreamReader read2 = new InputStreamReader(
                            new FileInputStream(file), encoding);
                    BufferedReader bufferedReader1 = new BufferedReader(read1);
                    @SuppressWarnings("unused")
                    BufferedReader bufferedReader2 = new BufferedReader(read2);

                    //计算文本行数
                    int num = 0;
                    String lineTxt1 = null;
                    while ((lineTxt1 = bufferedReader1.readLine()) != null) {
//						System.out.println(lineTxt1);
                        num++;
                    }
//					System.out.println("第"+num+"个文件"+filename+"运行完毕。");
//					System.out.println(filename+"运行完毕。");

//					System.out.println(filename+"的行数为："+ num);

                    for(int n = 0; n < num-1; n++){
                        writer.write(bufferedReader2.readLine());
                        writer.write("\r\n");
                    }
                }
//				writer.flush();
                writer.close();
            }

        }catch(Exception e){
            System.out.println("读取文件出错！");
            e.printStackTrace();
        }

    }

    public static void main(String arg[]){
        String t = "Tree_traversal";
        String inputCatalog = "D:\\wiki_Data_labels\\DataStructure\\Data_structure_7\\"+t+"\\";
        String outputCatalog = "D:\\wiki_Data_labels\\DataStructure\\Data_structure_8\\"+t+"\\";
        deleteLastline(inputCatalog, outputCatalog);
        System.out.println(t+"文件end & success!");
    }

}
