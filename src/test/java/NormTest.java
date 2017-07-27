import java.io.*;
import java.util.ArrayList;

/**
 * Created by BellaWu on 2017/7/25.
 * 主要是将txt文件中内容按照一行一句话的格式进行处理。
 */
public class NormTest {
    public static void main(String[] str){
        // The name of the file to open.
        String InputFile = "E:\\gitbase\\TextSegmentation\\src\\test\\resources\\Operations.txt";
        // The name of the file to be write.
        String OutputFile = "E:\\gitbase\\TextSegmentation\\src\\test\\resources\\temp.txt";
        try {
            FileReader fileReader = new FileReader(InputFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            FileWriter fileWriter = new FileWriter(OutputFile);
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
                        System.out.println("this is the last one " + lastOne);
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
                        if (sen.endsWith(":") || sen.endsWith(";"))  {
                            bufferedWriter.write(sen.trim());
                            bufferedWriter.newLine();
                            bufferedWriter.flush();
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
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + InputFile + "'");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
