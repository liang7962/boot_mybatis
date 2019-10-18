package com.mybatis.pdf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;


/**
 * PDF文件合并
 */
public class MergePdfUtil {
	
	private static String ROOT_PATH = new File(ItextUtil.class
			.getResource("/").getPath()).getParentFile().getParent();
	
	private final static String FONTPATH = ROOT_PATH+"/font/simsun.ttc";
	private final static String FONTNAME = "simsun";
	
	public static String WATERMARK_S = ROOT_PATH + File.separator + "images" + File.separator + "watermark_s.jpg";	//水印绝对路径
	public static String WATERMARK_H = ROOT_PATH + File.separator + "images" + File.separator + "watermark_h.jpg";	//水印绝对路径
	
	
	public static void main(String[] args) throws Exception {
		FileInputStream file1 = new FileInputStream("d:/pdf.pdf");
//		FileInputStream file2 = new FileInputStream("d:/pdf2.pdf");
//		FileInputStream file3 = new FileInputStream("d:/pdf3.pdf");
		
		List<InputStream> streamOfPDFFiles = new ArrayList<InputStream>();
		streamOfPDFFiles.add(file1);
//		streamOfPDFFiles.add(file2);
//		streamOfPDFFiles.add(file3);
		
//		FileOutputStream outputStream = new FileOutputStream("d:/pdf4.pdf");
//		MergePdfUtil.concatPDFs(streamOfPDFFiles, outputStream, true, WATERMARKS,WATERMARKH);
	}

	/**
	 * 合并PDF文件
	 * @param streamOfPDFFiles 待合并文件流列表
	 * @param outputStream 合并后输出流
	 * @param paginate 是否显示页码
	 * @param waterMarks 竖版水印-图片绝对路径
	 * @param waterMarkh 横版水印-图片绝对路径
	 */
	public static void concatPDFs(List<InputStream> streamOfPDFFiles, OutputStream outputStream, boolean paginate,String waterMarks,String waterMarkh){
		Document document = new Document();
		try {
			List<PdfReader> readers = new ArrayList<PdfReader>();
	        int totalPages = 0;
	        
	        Iterator<InputStream> iteratorPDFs = streamOfPDFFiles.iterator(); 
	        
	        // Create Readers for the pdfs.
	        while(iteratorPDFs.hasNext()){ 
	        	InputStream pdf = iteratorPDFs.next();
	        	try{
	        		PdfReader pdfReader = new PdfReader(pdf);
	                readers.add(pdfReader);
	                totalPages += pdfReader.getNumberOfPages();
	        	} catch (IOException e){
	        		e.printStackTrace();
	        	}
	        }
	            
	        // Create a writer for the outputstream
	        PdfWriter writer = PdfWriter.getInstance(document, outputStream);
	 
	        document.open();
	        
	        XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
	        fontProvider.register(FONTPATH, FONTNAME);
	        Font font = fontProvider.getFont(FONTNAME, BaseFont.CP1252, BaseFont.EMBEDDED, 9, Font.UNDEFINED, null);
	        
	        // 插入水印   
	        Image imgs = null; 
	        Image imgh = null; 
	        if(null != waterMarks && !"".equals(waterMarks)){
	        	imgs = Image.getInstance(waterMarks);  
	        	imgs.scaleAbsolute(535, 802); //图片的大小
	        	imgs.setAbsolutePosition(30, 20); //必不可少的
	        	imgs.setAlignment(Image.UNDERLYING);//图片置底
	        }
	        if(null != waterMarkh && !"".equals(waterMarkh)){
	        	imgh = Image.getInstance(waterMarkh);  
	        	imgh.scaleAbsolute(802, 535); //图片的大小
	        	imgh.setAbsolutePosition(20, 30); //必不可少的
	        	imgh.setAlignment(Image.UNDERLYING);//图片置底
	        }
	        
	        PdfContentByte cb = writer.getDirectContent(); // Holds the PDF
	 
	        PdfImportedPage page;
	        int currentPageNumber = 0;
	        int pageOfCurrentReaderPDF = 0;
	        Iterator<PdfReader> iteratorPDFReader = readers.iterator();
	 
	        // Loop through the PDF files and add to the output.
	        while(iteratorPDFReader.hasNext()){
	        	PdfReader pdfReader = (PdfReader)iteratorPDFReader.next();
	 
	        	// Create a new page in the target for each source page.
	        	while(pageOfCurrentReaderPDF < pdfReader.getNumberOfPages()){
	        		//document.newPage();
	        		pageOfCurrentReaderPDF++;
	        		currentPageNumber++;
	        		page = writer.getImportedPage(pdfReader,pageOfCurrentReaderPDF);
	        		// 页面尺寸设置--横向/纵向
	        		if(page.getHeight() > page.getWidth()){
	        			document.setPageSize(PageSize.A4);
	        			document.newPage();
	        			if(null != imgs){
		        			cb.addImage(imgs);
		        		}
	        		} else {
	        			document.setPageSize(PageSize.A4.rotate());
	        			document.newPage();
	        			if(null != imgh){
	        				cb.addImage(imgh);
	        			}
	        		}
	        		
	        		cb.addTemplate(page, 0, 0);
	        		// Code for pagination.
	        		if(paginate){
	        			cb.beginText();
	        			cb.setFontAndSize(font.getBaseFont(), 9);
	        			cb.showTextAligned(PdfContentByte.ALIGN_CENTER, "第 "
	        					+ currentPageNumber + " 页 / 共 " + totalPages+" 页", 535, 5, 0);
	        			cb.endText();
	        		}
	        	}
	        	pageOfCurrentReaderPDF = 0;
	        }
	        
	        outputStream.flush();
	        document.close();
	        outputStream.close();
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			if(document.isOpen())
				document.close();
			try {
				if(outputStream != null)
					outputStream.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}
	
	
	
	/**
	 * 添加页脚
	 * @param pdf
	 * @param float_x
	 * @param text
	 * @return
	 */
	public static ByteArrayInputStream addFootToPdf(InputStream pdf, int align ,int float_x, String text){
		Document document = new Document();
		try {
	        ByteArrayOutputStream os = new ByteArrayOutputStream();
	        PdfReader pdfReader = new PdfReader(pdf);
	            
	        // Create a writer for the outputstream
	        PdfWriter writer = PdfWriter.getInstance(document, os);
	 
	        document.open();
	        
	        XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
	        fontProvider.register(FONTPATH, FONTNAME);
	        Font font = fontProvider.getFont(FONTNAME, BaseFont.CP1252, BaseFont.EMBEDDED, 9, Font.UNDEFINED, null);
	        
	        PdfContentByte cb = writer.getDirectContent(); // Holds the PDF
	 
	        PdfImportedPage page;
	        int pageOfCurrentReaderPDF = 0;
	        // Create a new page in the target for each source page.
        	while(pageOfCurrentReaderPDF < pdfReader.getNumberOfPages()){
        		pageOfCurrentReaderPDF++;
	    		page = writer.getImportedPage(pdfReader,pageOfCurrentReaderPDF);
	    		// 页面尺寸设置--横向/纵向
        		if(page.getHeight() > page.getWidth()){
        			document.setPageSize(PageSize.A4);
        			document.newPage();
        		} else {
        			document.setPageSize(PageSize.A4.rotate());
        			document.newPage();
        		}
	    		cb.addTemplate(page, 0, 0);
				cb.beginText();
				cb.setFontAndSize(font.getBaseFont(), 9);
				cb.showTextAligned(align, text, float_x, 5, 0);
				cb.endText();
        	}
        	os.flush();
	        document.close();
	        return new ByteArrayInputStream(os.toByteArray());
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			if(document.isOpen())
				document.close();
		}
		return null;
	}
}
