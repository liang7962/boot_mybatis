package com.mybatis.pdf;

import java.io.File;

/**
 * Created by Administrator on 2019/3/18.
 */
public class main {
    public static void main(String[] aras){
        String wordFilePath="C:/Users/Administrator/Desktop/pdf/UN38.3报告摘要中英文版模板V20190828.docx";
        String pdfFilePath="C:/Users/Administrator/Desktop/pdf/aa.pdf";
        File wordFile=new File(wordFilePath);
        WordUtil wordUtil=new WordUtil();
        try {
            wordUtil.convertDocxToPDF(wordFile,pdfFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
