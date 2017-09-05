package process;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static process.SimCos.line_sim;

public class SimMain {
    public static void main(String[] args) throws IOException {
        String inputcatalog = "";
        String outputcatalog = "";
        ProcessMain(inputcatalog,outputcatalog);

    }

    //将所有文件路径都存在一个二维list中。
    public static List<List<String>> SaveFilepaths(String inputcatalog) throws IOException {
        List<String> filepaths = new ArrayList<String>();
        List<List<String>> filepathss = new ArrayList<List<String>>();
        File files = new File(inputcatalog);
        File[] fileps = files.listFiles();
        for (int i = 0; i < fileps.length; i++) {
            File filess = new File(fileps[i].getAbsolutePath());
            File[] filepss = filess.listFiles();
            for (int j = 0; j < filepss.length; j++) {
                String filepath = filepss[j].getAbsolutePath();
                filepaths.add(filepath);
            }
            filepathss.add(filepaths);
        }
        return filepathss;
    }

    //获得所有文件路径之后，计算两两之间的相似度。
    public static void ProcessMain(String inputcatalog, String outputcatalog) throws IOException {
        List<List<String>> filepathss = SaveFilepaths(inputcatalog);
        String encoding = "UTF-8";
        for (int i = 0; i < filepathss.size(); i++) {
            List<String> filepaths = filepathss.get(i);
            List<RecordBean> recordBeans = new ArrayList<RecordBean>();

            String filePath = filepaths.get(0);
            File file = new File(filePath);
            String topic = file.getParentFile().getParentFile().getName();
            String outputpath = outputcatalog+"\\"+topic+".csv";

            for (int j = 0; j < filepaths.size(); j++) {
                String filepath1 = filepaths.get(j);
                File file1 = new File(filepath1);
                String facet1 = file1.getParentFile().getName();
                String n1 = file1.getName();
                for (int k = 0; k < filepaths.size(); k++) {
                    String filepath2 = filepaths.get(k);
                    File file2 = new File(filepath2);
                    String facet2 = file2.getParentFile().getName();
                    String n2 = file2.getName();
                    if (!filepath1.matches(filepath2)) {
                        double sim = line_sim(filepath1,filepath2,encoding);
                        RecordBean record = new RecordBean(facet1,n1,facet2,n2,sim);
                        recordBeans.add(record);
                    }
                }
            }
            WriteCsv(recordBeans,outputpath);
        }
    }

    //将结果写入CSV
    public static void WriteCsv(List<RecordBean> recordBeans, String filepath) throws IOException {
        FileWriter fileWriter;
        CSVPrinter csvPrinter;
        final String[] FILE_HEADER = {"facet1","num1","facet2","num2","sim"};
        CSVFormat format = CSVFormat.DEFAULT.withHeader(FILE_HEADER).withSkipHeaderRecord();
        fileWriter = new FileWriter(filepath);
        csvPrinter = new CSVPrinter(fileWriter,format);
        csvPrinter.print(FILE_HEADER);
        for (RecordBean recordBean : recordBeans) {
            List<String> records = new ArrayList<String>();
            records.add(recordBean.getFacet1());
            records.add(recordBean.getN1());
            records.add(recordBean.getFacet2());
            records.add(recordBean.getN2());
            records.add(String.valueOf(recordBean.getSim()));
            csvPrinter.printRecord(records);
        }


    }

//    public static String GetName(String path) {
//        File file = new File(path);
//        String name = file.getName();
//        return name;
//    }
//
//    public static String GetPName(String path) {
//        File file = new File(path);
//        String pname = file.getParentFile().getName();
//        return pname;
//    }

}
