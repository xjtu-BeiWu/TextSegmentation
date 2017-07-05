package Process;

/**
 * Created by BellaWu on 2017/7/5.
 */
public class SimCom {
    public static void main(String[] args) {
        String str1 = "0.3 0.2 0.4";
        float[] a = str2ary(str1);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }

    }

    /*
    计算两段话的相似度（相关度），主要过程是通过计算各词之间词向量（以字符串形式输入）余弦相似度，通过算数平均的方法得到结果。
    参考论文：Unsupervised Text Segmentation Using Semantic Relatedness Graph
    */
    public static float sim(String str1, String str2){

        float[] w1 = str2ary(str1);
        float[] w2 = str2ary(str2);




        return 0;
    }

    /*
    将字符串转换成浮点型数组
     */
    public static float[] str2ary(String str){
        String[] temp = str.split(" ");
        float[] w = new float[temp.length];
        for (int i = 0; i < w.length; i++) {
            w[i] = Float.parseFloat(temp[i]);
        }
        return w;
    }

}
