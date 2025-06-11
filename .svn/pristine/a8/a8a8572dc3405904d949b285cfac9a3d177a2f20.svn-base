package org.jit.sose.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import org.junit.Test;
import sun.misc.BASE64Encoder;

import java.io.*;


/**
 * @author 小红
 * @Date 2020/7/23 0:25
 */
public class FileUtilTest {


    @Test
    public void encodeBase64FileTest() throws Exception {
//        String path = "/upload_document/templateUsedByProcess/%E5%AE%BF%E8%88%8D%E8%AF%B7%E5%81%87%E5%81%87%E6%9D%A1%E6%A8%A1%E6%9D%BF.doc";
//        String path = "D:/proj/document/file/templateUsedByProcess/宿舍请假假条模板.doc";
//        String path = "D:/proj/document/file/templateUsedByProcess/优秀教职工竞选表模板.doc";
//        String path = "D:/proj/document/file/templateUsedByProcess/20200712213747_测试2.png";/
//        C:/FruitCake/proj/document/file/editedFile/20200821132008_admin假条.doc
//        /upload_document/editedFile/20200821132008_admin假条.doc
//        String path = "D:/proj/document/file/templateUsedByProcess/2.1命题逻辑.pdf";
        String path = "D:/proj/document/file/templateUsedByProcess/2.1命题逻辑.pdf";

        //方法一
//        String path = "/upload_document/templateUsedByProcess/宿舍请假假条模板.doc";
//        String realPath=path.replace("/upload_document","D:/proj/document/file/");
//        System.out.println(realPath);
//        File file = new File(realPath);
//        FileInputStream inputFile = new FileInputStream(file);
//        byte[] buffer = new byte[(int)file.length()];
//        inputFile.read(buffer);
//        inputFile.close();
//        String base64 = new BASE64Encoder().encode(buffer);
//        System.out.println(base64);

        //方法二
        byte[] data = null;
        // 读取图片字节数组
        try {
            InputStream in = new FileInputStream(path);
            System.out.println("文件大小（字节）=" + in.available());
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组进行Base64编码，得到Base64编码的字符串
        BASE64Encoder encoder = new BASE64Encoder();
        String base64Str = encoder.encode(data);
        System.out.println(base64Str);

//        System.out.println(fileUtil.encodeBase64File(path));
    }

    @Test
    public void signatureTest() throws IOException, DocumentException {
        // 模板文件路径
//        String templatePath = "f:/template.pdf";
        String templatePath = "D:/proj/document/file/templateUsedByProcess/2.1命题逻辑.pdf";
        String staticAccess = "/upload_document/templateUsedByProcess/2.1命题逻辑.pdf";
        // 生成的文件路径
        String targetPath = "D:/proj/document/file/templateUsedByProcess/result.pdf";
        // 图片路径
        String imagePath = "D:/proj/document/file/templateUsedByProcess/20200712213747_测试2.png";
        // 读取模板文件
        FileInputStream input = new FileInputStream(new File(templatePath));
        PdfReader reader = new PdfReader(input);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(targetPath));
        Document document = new Document();// 创建文档实例
        // 通过域名获取所在页和坐标，左下角为起点
        float x = document.getPageSize().getWidth() - 440;
        float y = document.getPageSize().getHeight() - 480;
        float x2 = document.getPageSize().getWidth();
        float y2 = document.getPageSize().getHeight();
        System.out.println("x:" + x + "y:" + y);
        System.out.println("x2:" + x2 + "y2:" + y2);
        // 读图片
        Image image = Image.getInstance(imagePath);
        int pages = reader.getNumberOfPages();//获取源pdf的总页数
        // 获取操作的页面
        PdfContentByte under = stamper.getOverContent(pages);
        // 根据域的大小缩放图片
        image.scaleToFit(document.getPageSize().getWidth() - 350, document.getPageSize().getHeight() - 290);
        // 添加图片
        image.setAbsolutePosition(x, y);
        under.addImage(image);

        stamper.close();
        reader.close();
    }

//    @Test
//    public void wordToPDF() {
//        //        C:/FruitCake/proj/document/file/editedFile/20200821132008_admin假条.doc
////        /upload_document/editedFile/20200821132008_admin假条.doc
////        String path = "D:/proj/document/file/templateUsedByProcess/2.1命题逻辑.pdf";
//        String staticAccessPath = "/upload_document/";
////        String uploadPath = "D:/proj/document/file";
//        String uploadPath = "C:\\FruitCake\\proj\\document\\file\\";
////        String startFile = "/upload_document/templateUsedByProcess/(19-20-2)-0809512118-人工智能导论-试卷A的答题纸.doc";
////        String startFile = "/upload_document/editedFile/20200821132008_admin假条.doc";
//        String startFile = "/upload_document/editedFile/20200821125906_test.doc";
//        // 转换前的文件路径
//        String startRealPath = startFile.replace(staticAccessPath, uploadPath);
//        System.out.println("startRealPath" + startRealPath);
//        // 转换后的文件路劲
//        String overRealFile = startRealPath.replace(".doc", ".pdf");
//        System.out.println("overRealFile" + overRealFile);
//
//
//        int wdFormatPDF = 17;// 指定17为转成pdf格式
//        ActiveXComponent app = null;
//        Dispatch doc = null;
//        try {
//            app = new ActiveXComponent("Word.Application");
//            app.setProperty("Visible", new Variant(false));
//            Dispatch docs = app.getProperty("Documents").toDispatch();
//
////            //转换前的文件路径
////            String startFile = "F:\\新建文件夹\\我是word版本" + ".doc";
////            //转换后的文件路劲
////            String overFile =  "F:\\新建文件夹\\我是转换后的pdf文件"  + ".pdf";
//
//            doc = Dispatch.call(docs, "Open", startRealPath).toDispatch();
//            File tofile = new File(overRealFile);
//            if (tofile.exists()) {
//                tofile.delete();
//            }
//            Dispatch.call(doc, "SaveAs", overRealFile, wdFormatPDF);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        } finally {
//            Dispatch.call(doc, "Close", false);
//            if (app != null)
//                app.invoke("Quit", new Variant[]{});
//        }
//        //结束后关闭进程
//        ComThread.Release();
//
//
////        int wdFormatPDF = 17;// PDF 格式
////        ActiveXComponent app = null;
////        Dispatch doc = null;
////        try {
////            app = new ActiveXComponent("Word.Application");
////            app.setProperty("Visible", new Variant(false));
////            Dispatch docs = app.getProperty("Documents").toDispatch();
////            doc = Dispatch.call(docs, "Open", startFile).toDispatch();
////            File tofile = new File(overRealFile);
////            if (tofile.exists()) {
////                tofile.delete();
////            }
////            Dispatch.call(doc, "SaveAs", overRealFile, wdFormatPDF);
////        } catch (Exception e) {
////            System.out.println(e.getMessage());
////        } finally {
////            Dispatch.call(doc, "Close", false);
////            if (app != null)
////                app.invoke("Quit", new Variant[] {});
////        }
////        // 结束后关闭进程
////        ComThread.Release();
////        String overPath=overRealFile.replace(uploadPath,staticAccessPath);
////        System.out.println("overPath"+overPath);
//
//
//    }
}
