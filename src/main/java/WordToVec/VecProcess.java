package WordToVec;

import java.io.File;

/**
 * Created by BellaWu on 2017/7/27.
 * 输入txt文件的每个词生成词向量。
 */
public class VecProcess {
    public static void main(String[] args) {
        String filePath = "";
        String modelPath = "";

    }

    public static void WordMatch (String filePath, String modelPath) throws Exception {
        // 设置路径
        String step1 = filePath + "\\file1_predeal";
        String step2 = filePath + "\\file2_wordtovec";
        String step3 = filePath + "\\file3_postdeal";

        // 1.预处理程序：去除文本中的标点符号，停用词，无用的行和空格
        new File(filePath).mkdir();
        new File(step1).mkdir();

        // 2.词向量程序：利用训练好的词向量模型，生成每段文本对应的词向量
        new File(step2).mkdir();
        WordToVec.MatchFast(modelPath, step1, step2);
        System.out.println("word2vec is finished...");

        // 3.后期处理程序：去除步骤2中词向量文件中第一个英文单词，删除空行，删除最后一行不完整数据
        new File(step3).mkdir();
        PostDeal.deleteWordandLine(step2, step3);
        System.out.println("delete empty line is finished...");
    }

}
