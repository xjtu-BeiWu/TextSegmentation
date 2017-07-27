package Process;


/**
 * Created by BellaWu on 2017/7/5.
 */
public class SimCom {
    public static void main(String[] args) {
        //test:
        String str1 = "0.3 0.2 0.4";
        String str2 = "0.1 0.1 0.1";
//        float[] a = str2ary(str1);
//        for (int i = 0; i < a.length; i++) {
//            System.out.println(a[i]);
//        }
//        double s = cos_sim(str1,str2);
//        System.out.println(s);
        String line1 = " ";
        String line2 = " ";
        double ls = line_sim(line1, line2);
        System.out.println(ls);
    }

    /*
    计算两段话的相似度（相关度），主要过程是通过计算各词之间词向量（以字符串形式输入）余弦相似度，通过算数平均的方法得到结果。
    参考论文：Unsupervised Text Segmentation Using Semantic Relatedness Graph
    */
    public static double line_sim(String line1, String line2){
        double ls = 0;

        return ls;
    }


    /*
    计算两个词向量（以字符串形式输入）的余弦相似度
    先转换成数组，然后再求余弦相似度
     */
    public static double cos_sim(String str1, String str2){

        double[] w1 = str2ary(str1);
        double[] w2 = str2ary(str2);
        double ws = 0;
        double w1n = 0;
        double w2n = 0;

        if (w1.length == w2.length) {
            for (int i = 0; i < w1.length; i++) {
                ws = ws + w1[i]*w2[i];
                w1n = w1n + w1[i]*w1[i];
                w2n = w2n + w2[i]*w2[i];
            }
            ws = ws/(Math.sqrt(w1n)+Math.sqrt(w2n));
            System.out.println(ws);
        }
        return ws;
    }

    /*
    将字符串转换成浮点型数组
     */
    public static double[] str2ary(String str){
        String[] temp = str.split(" ");
        double[] w = new double[temp.length];
        for (int i = 0; i < w.length; i++) {
            w[i] = Double.parseDouble(temp[i]);
        }
        return w;
    }

}
