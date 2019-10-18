package com.mybatis.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.css.CssFile;
import com.itextpdf.tool.xml.css.CssFilesImpl;
import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
import com.itextpdf.tool.xml.html.CssAppliers;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.AbstractImageProvider;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
import com.itextpdf.tool.xml.pipeline.html.LinkProvider;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;





public class ItextUtil {
	
	private static String ROOT_PATH = new File(ItextUtil.class
			.getResource("/").getPath()).getParentFile().getParent();
	
	
	private static String CSS_PATH = ROOT_PATH+"/css/itextHtmlToPdf.css";//css
	
	private static String FONT_SIMSUN = ROOT_PATH+"/fonts/SIMHEI.TTF";//font
	private static String FONT_SIMHEI = ROOT_PATH+"/fonts/SIMHEI.TTF";//font
	
	//如果部署在ROOT下面要添加一个root/
	public static final String IMG_PATH = new File(ROOT_PATH).getParent()+File.separator+"ROOT"+File.separator;
	//链接
    public static final String RELATIVE_PATH = "http://test.com/kjcg/";

	public static void main( String[] args ) throws Exception {
//		parseHtml2PdfSimpleTest("d:/test1.pdf","http://localhost:8080/zjtorch-ww/user/itext!companyReport.do",false, true);
		System.out.println(IMG_PATH);
    }
	
	/**
	 * 简单实现
	 * @param pdf
	 * @param _url
	 * @param ifRotate
	 * @param ifWaterMark
	 * @throws DocumentException
	 * @throws IOException
	 * 
	 * @Deprecated replace with 
	 * 	parseHtml2Pdf(String pdf, Boolean ifRotate, 
	 * 		String _url)throws DocumentException, IOException
	 */
	@Deprecated
	public static void parseHtml2PdfSimpleTest(String pdf, String _url, Boolean ifRotate, boolean ifWaterMark)throws DocumentException, IOException{
		//定义文档
		Document document = new Document();
        
		//定义页面边距
        //setMargins(float marginLeft, float marginRight, float marginTop, float marginBottom) 
        document.setMargins(30, 30, 20, 20);
        
        //定义界面尺寸，默认A4
        if(ifRotate){
        	document.setPageSize(PageSize.A4.rotate());
        }
        
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        
        //定义输出
        PdfWriter writer = PdfWriter.getInstance(document, os);
        //打开文档
        document.open();
        
        //插入水印  
        /*if(ifWaterMark) {
	    	Image imgs = null; 
	    	String waterMarkPath = ROOT_PATH + File.separator + "images" + File.separator + "watermark_s.jpg";	//水印绝对路径
	    	imgs = Image.getInstance(waterMarkPath);  
	    	imgs.scaleAbsolute(535, 802); //图片的大小
	    	imgs.scaleAbsolute(622, 787); //图片的大小
	    	imgs.setAbsolutePosition(30, 20); //必不可少的
	    	imgs.setAlignment(Image.UNDERLYING);//图片置底
	    	document.add(imgs);
        }*/
        
        
        //通过地址读取html
        URL url = new URL(_url); 
        URLConnection conn = url.openConnection(); 
        conn.setConnectTimeout(10000);
        
        HttpURLConnection connection = null;
        if(conn instanceof HttpURLConnection)
        {
           connection = (HttpURLConnection) conn;
        }
        else
        {
           System.out.println("Please enter an HTTP URL.");
           return;
        }
        BufferedReader in = new BufferedReader(
        new InputStreamReader(connection.getInputStream()));
        String urlString = "";
        String current;
        while((current = in.readLine()) != null)
        {
           urlString += current;
        }
        System.out.println(urlString);
        
        
        
        //注册字体
        XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
        fontProvider.register(FONT_SIMSUN, "simsun");
      fontProvider.register(FONT_SIMHEI, "simhei");
        //注册css文件
        //如果界面有link CSS，此处引用的CSS可能没用
        FileInputStream cssFile = new FileInputStream(CSS_PATH);
        
        //输出
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, conn.getInputStream(), cssFile, Charset.forName("UTF-8"), fontProvider);
        //关闭文档
        document.close();
        System.out.println( "PDF Created!" );
        
        if(!ifWaterMark){
        	os.writeTo(new FileOutputStream(pdf));
        	os.close();
        }else{
			List<InputStream> pdfs = new ArrayList<InputStream>();
			pdfs.add(new ByteArrayInputStream(os.toByteArray()));
			MergePdfUtil.concatPDFs(pdfs, new FileOutputStream(pdf), true,MergePdfUtil.WATERMARK_S,MergePdfUtil.WATERMARK_H);
			os.close();
        }
	}
	
	
	
	
	// ====================================================================================================
	// zhang xiang 20150128 begin
	// 简单的pdf生成
	// ====================================================================================================
	/**
	 * 简单pdf 返回输入流 
	 * 提供给MergePdfUtil.concatPDFs
	 * @param _url
	 * @param ifRotate
	 * @return
	 * @throws DocumentException
	 * @throws IOException
	 */
	public static InputStream parseHtml2Pdf_IS(String _url, Boolean ifRotate) throws DocumentException, IOException{
		ByteArrayOutputStream os = parseHtml2Pdf_OS(_url, ifRotate);
		return new ByteArrayInputStream(os.toByteArray());
	}
	
	/**
	 * 简单pdf 返回输出流
	 * 主要的生成方法
	 * @param _url
	 * @param ifRotate
	 * @return
	 * @throws DocumentException
	 * @throws IOException
	 */
	public static ByteArrayOutputStream parseHtml2Pdf_OS(String _url, Boolean ifRotate)throws DocumentException, IOException{
		//定义文档
		Document document = new Document();
        
		//定义页面边距
        //setMargins(float marginLeft, float marginRight, float marginTop, float marginBottom) 
//        document.setMargins(30, 30, 50, 50);
        
        //定义界面尺寸，默认A4
//        if(ifRotate){
//        	document.setPageSize(PageSize.A4.rotate());
//        }
       
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        //定义输出
        PdfWriter writer = PdfWriter.getInstance(document, os);
        //打开文档
        document.open();
        
        //通过地址读取html
        URL url = new URL(_url); 
        URLConnection conn = url.openConnection(); 
        conn.setConnectTimeout(10000);
        
        /*
        HttpURLConnection connection = null;
        if(conn instanceof HttpURLConnection)
        {
           connection = (HttpURLConnection) conn;
        }
        else
        {
           System.out.println("Please enter an HTTP URL.");
        }
        BufferedReader in = new BufferedReader(
        new InputStreamReader(connection.getInputStream()));
        String urlString = "";
        String current;
        while((current = in.readLine()) != null)
        {
           urlString += current;
        }
        System.out.println(urlString);
        //*/
        
        //注册字体
        XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
        fontProvider.register(FONT_SIMSUN, "simsun");
        fontProvider.register(FONT_SIMHEI, "simhei");
        //注册css文件
        //如果界面有link CSS，此处引用的CSS可能没用
        FileInputStream cssFile = new FileInputStream(CSS_PATH);
        
        //输出
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, conn.getInputStream(), cssFile, Charset.forName("UTF-8"), fontProvider);
        //关闭文档
        document.close();
        //System.out.println( "PDF Created! url ： "+_url );
        return os;
	}
	
	
	public static ByteArrayOutputStream parseHtml2Pdf_OSWithImage(String _url, Boolean ifRotate,String imagePath,String qRCodePath)throws DocumentException, IOException{
		//定义文档
		Document document = new Document();
        
		//定义页面边距
        //setMargins(float marginLeft, float marginRight, float marginTop, float marginBottom) 
        document.setMargins(30, 30, 20, 20);
        
        //定义界面尺寸，默认A4
        if(ifRotate){
        	document.setPageSize(PageSize.A4.rotate());
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        //定义输出
        PdfWriter writer = PdfWriter.getInstance(document, os);
        //打开文档
        document.open();
        
        Image image = Image.getInstance (imagePath);
        Image qrCodeImage = Image.getInstance (qRCodePath);
        // 控制图片大小
        image.scaleToFit(150,620);
        qrCodeImage.scaleToFit(100,100);
        // 控制图片位置
        image.setAbsolutePosition(40, 710);
        qrCodeImage.setAbsolutePosition(420, 710);
        // 图片的超链接
        qrCodeImage.setAnnotation(new Annotation(0,0,0,0,"http://lims.qwings.cn/database/userLoginPage.do"));
        // document.add(new Paragraph("Roseindia"));
        document.add(image); 
        document.add(qrCodeImage);
        
        //通过地址读取html
        URL url = new URL(_url); 
        URLConnection conn = url.openConnection(); 
        conn.setConnectTimeout(10000);
        
        
        //注册字体
        XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
        fontProvider.register(FONT_SIMSUN, "simsun");
        fontProvider.register(FONT_SIMHEI, "simhei");
        //注册css文件
        //如果界面有link CSS，此处引用的CSS可能没用
        FileInputStream cssFile = new FileInputStream(CSS_PATH);
        
        //输出
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, conn.getInputStream(), cssFile, Charset.forName("UTF-8"), fontProvider);
       
        
        
        //关闭文档
        document.close();
        // System.out.println( "PDF Created! url ： "+_url );
        return os;
	}
	
	/**
	 * 简单pdf 直接写入文件
	 * @param pdf
	 * @param ifRotate
	 * @param _url
	 * @throws DocumentException
	 * @throws IOException
	 */
	public static void parseHtml2Pdf(String pdf, Boolean ifRotate, String _url)throws DocumentException, IOException{
		ByteArrayOutputStream os = parseHtml2Pdf_OS(_url, ifRotate);
		os.writeTo(new FileOutputStream(pdf));
    	os.close();
	}
	
	public static void parseHtml2PdfWithImage(String pdf, Boolean ifRotate, String _url,String imagePath,String qRCodePath)throws DocumentException, IOException{
		ByteArrayOutputStream os = parseHtml2Pdf_OSWithImage(_url, ifRotate,imagePath,qRCodePath);
		os.writeTo(new FileOutputStream(pdf));
    	os.close();
	}
	// ====================================================================================================
	// zhang xiang 20150128 end
	// ====================================================================================================	
	
	
	// ====================================================================================================
	// zhang xiang 20150303 begin
	// 复杂pdf生成
	// 支持图片
	// ====================================================================================================
	/**
	 * 支持图片的pdf生成方式
	 * 直接写入文件
	 * @param pdf
	 * @param ifRotate
	 * @param _url
	 * @param cssFilePath can be null
	 * @throws DocumentException
	 * @throws IOException
	 */
	public static void parseHtml2PdfWithCSS(String pdf, Boolean ifRotate, String _url, String cssFilePath)throws DocumentException, IOException{
		ByteArrayOutputStream os = parseHtml2PdfWithCSS_OS(ifRotate, _url, cssFilePath);
		os.writeTo(new FileOutputStream(pdf));
    	os.close();
	}
	
	/**
	 * 支持图片的pdf生成方式
	 * 主要的生成方法
	 * @param ifRotate
	 * @param _url
	 * @param cssFilePath can be null
	 * @return
	 * @throws DocumentException
	 * @throws IOException
	 */
	public static ByteArrayOutputStream parseHtml2PdfWithCSS_OS(Boolean ifRotate, String _url, String cssFilePath)throws DocumentException, IOException{
	     // step 1
	    Document document = new Document();
	    
	    //setMargins(float marginLeft, float marginRight, float marginTop, float marginBottom) 
	    document.setMargins(35, 25, 20, 50);
	    //定义界面尺寸，默认A4
        if(ifRotate){
        	document.setPageSize(PageSize.A4.rotate());
        }
        
	    // step 2
	    ByteArrayOutputStream os = new ByteArrayOutputStream();
        //定义输出
        PdfWriter writer = PdfWriter.getInstance(document, os);
	    // step 3
	    document.open();
	    // step 4
	    URL url = new URL(_url); 
	    URLConnection conn = url.openConnection(); 
	    conn.setConnectTimeout(10000);
	    
	    XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
        fontProvider.register(FONT_SIMSUN, "simsun");
        fontProvider.register(FONT_SIMHEI, "simhei");
	    
	    CssFilesImpl cssFiles = new CssFilesImpl();
	    if(cssFilePath!=null){
	  	  CssFile cssFile = XMLWorkerHelper.getCSS(new FileInputStream(cssFilePath));
	  	  cssFiles.add(cssFile);
	    }
	    CSSResolver cssResolver = new StyleAttrCSSResolver(cssFiles);
	    
	    CssAppliers cssAppliers = new CssAppliersImpl(fontProvider);
	    HtmlPipelineContext htmlContext = new HtmlPipelineContext(cssAppliers);
	    htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
	    htmlContext.setImageProvider(new AbstractImageProvider() {
	        public String getImageRootPath() {
	            return IMG_PATH;
	        }
	    });
	    htmlContext.setLinkProvider(new LinkProvider() {
	        public String getLinkRoot() {
	            return RELATIVE_PATH;
	        }
	    });
	    
	    PdfWriterPipeline _pdf = new PdfWriterPipeline(document, writer);
	    HtmlPipeline html = new HtmlPipeline(htmlContext, _pdf);
	    CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);
	    
	    XMLWorker worker = new XMLWorker(css, true);
	    XMLParser p = new XMLParser(worker);
	    p.parse(conn.getInputStream(),Charset.forName("UTF-8"));
	    
	    
/*	    Image image = Image.getInstance ("devi.jpg");
	    document.add(new Paragraph("Roseindia"));
	    document.add(image); 
*/	    
	    // step 5	
	    document.close();
	   // System.out.println("image=============" + IMG_PATH);
        //System.out.println( "PDF Created! url ： "+_url );
        return os;
	}
	
	/**
	 * 支持图片的pdf生成方式 返回输入流 
	 * 提供给MergePdfUtil.concatPDFs
	 * @param ifRotate
	 * @param _url
	 * @param cssFilePath can be null
	 * @return
	 * @throws DocumentException
	 * @throws IOException
	 */
	public static InputStream parseHtml2PdfWithCSS_IS(Boolean ifRotate, String _url, String cssFilePath) throws DocumentException, IOException{
		ByteArrayOutputStream os = parseHtml2PdfWithCSS_OS(ifRotate, _url, cssFilePath);
		return new ByteArrayInputStream(os.toByteArray());
	}
	
	
	
	// ====================================================================================================
	// zhang xiang 20150303 end
	// ====================================================================================================
	
}
