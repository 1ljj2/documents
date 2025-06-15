package org.jit.sose.controller.file;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.jit.sose.domain.entity.*;
import org.jit.sose.domain.param.*;
import org.jit.sose.domain.vo.*;
import org.jit.sose.redis.RedisService;
import org.jit.sose.service.FileCategoryConService;
import org.jit.sose.service.FileInfoService;
import org.jit.sose.service.FileTermConService;
import org.jit.sose.util.EncryptionUtil;
import org.jit.sose.util.FileUtil;
import org.jit.sose.util.MD5Util;
import org.jit.sose.util.ResponseUtil;
import org.jit.sose.web.response.CommonRespT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;


@Api(tags = "文件接口")
@ApiSupport(author = "LJH")
@RestController
@RequestMapping("/file/file")
public class FileController {

    @Autowired
    private FileUtil fileUtil;

    @Autowired
    RedisService redisService;

    @Autowired
    FileInfoService fileInfoService;

    @Autowired
    FileCategoryConService fileCategoryConService;

    @Autowired
    FileTermConService fileTermConService;

    @ApiOperation(value = "课程和学期都选好时，获取匹配文档模板给档案模板参考")
    @PostMapping("/listMatchFile")
    public PageInfoVo<ListMatchFileTemVo> listMatchFile(@RequestBody JSONObject json) {
        Integer termId = json.getInteger("termId");
        Integer courseId = json.getInteger("courseId");
        Integer pageNum = json.getInteger("pageNum");
        Integer pageSize = json.getInteger("pageSize");
        return fileInfoService.listMatchFile(termId, courseId,pageNum,pageSize);
    }

    @ApiOperation(value = "编辑文件信息")
    @PostMapping("/editFileInfo")
    public void editFileInfo(@RequestBody FileInfoParam param) {
        fileInfoService.editFileInfo(param);
    }

    @ApiOperation(value = "根据【流程模板名称、学期、分类、分页参数】筛选文档模板列表对象")
    @PostMapping("/listFileTemByCondition")
    public PageInfoVo<ListFileTemVo> listFileTemByCondition(@RequestBody ListFileTemParam param) {
        return fileInfoService.listFileTemByCondition(param);
    }

    @ApiOperation(value = "根据【流程文档名称、学期、分类、创建者、分页参数】筛选我审核的文档列表对象")
    @PostMapping("/listMyAuditFile")
    public PageInfoVo<listMyAuditFileVo> listMyAuditFile(@RequestBody listMyAuditFileParam param,
                                                     @ApiIgnore @AuthenticationPrincipal SecurityUser securityUser) {
        param.setUserId(securityUser.getId());
        return fileInfoService.listMyAuditFile(param);
    }

    @ApiOperation(value = "根据【名称、学期、分类、分页参数】筛选我的文档")
    @PostMapping("/listMyFile")
    public PageInfoVo<ListMyFileVo> listFileTemByCondition(@RequestBody ListMyFileParam param,
                                                           @ApiIgnore @AuthenticationPrincipal SecurityUser securityUser) {
        // --- 开始打印 securityUser 信息 ---
        System.out.println("----------- Debugging SecurityUser -----------");
        if (securityUser != null) {
            System.out.println("SecurityUser object: " + securityUser.toString()); // 依赖 toString() 方法
            System.out.println("User ID: " + securityUser.getId());
            System.out.println("Username: " + securityUser.getUsername()); // 假设有 getUsername()
            // 打印你关心的其他字段，例如：
            // System.out.println("User Roles: " + securityUser.getAuthorities()); // Spring Security 通常有 getAuthorities()
            // System.out.println("User Email: " + securityUser.getEmail()); // 假设有 getEmail()
            // ...等等
        } else {
            System.out.println("SecurityUser is NULL!");
        }
        System.out.println("---------------------------------------------");
        // --- 打印结束 ---
        int []roleId=fileInfoService.selectRoleIdByUserId(securityUser.getId());
        param.setUserId(securityUser.getId());
        param.setRoleId(roleId);
        return fileInfoService.listMyFile(param);
    }

//    @ApiOperation(value = "根据【名称、学期、分类、分页参数，当前登录用户】筛选我的文档")
//    @PostMapping("/listMyFile")
//    public PageInfoVo<ListMyFileVo> listFileTemByConditionAndUser(@RequestBody ListMyFileParam param) {
//        return fileInfoService.listMyFile(param);
//    }


    // 编辑我的文档
    @PostMapping("/editMyFile")
    public Integer editMyFile(@RequestBody FileInfo fileInfo) {
        //将一些原始信息进行更新
        fileInfoService.saveOrUpdate(fileInfo);
        //分类需要修改时
        if(!Objects.equals(fileInfo.getCategoryId(),null)){
            FileCategoryCon fileCategoryCon = new FileCategoryCon();
            fileCategoryCon.setFileId(fileInfo.getId());
            fileCategoryCon.setCategoryId(fileInfo.getCategoryId());
            UpdateWrapper<FileCategoryCon> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("file_id",fileInfo.getId());
            fileCategoryConService.saveOrUpdate(fileCategoryCon,updateWrapper);
        }
        //学期需要修改时
        if(!Objects.equals(fileInfo.getTermId(),null)){
            FileTermCon fileTermCon = new FileTermCon();
            fileTermCon.setFileId(fileInfo.getId());
            fileTermCon.setTermId(fileInfo.getTermId());
            UpdateWrapper<FileTermCon> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("file_id",fileInfo.getId());
            fileTermConService.saveOrUpdate(fileTermCon,updateWrapper);
        }
        return null;
    }

    @ApiOperation(value = "根据【流程模板名称、学期、分类、课程、分页参数】筛选文档监管列表对象")
    @PostMapping("/listChargingFileByCondition")
    public PageInfoVo<ListChargingFileVo> listChargingFileByCondition(@RequestBody ListChargingFileParam param) {
        return fileInfoService.listChargingFileByCondition(param);
    }

    @ApiOperation(value = "设置文件可用")
    @PostMapping("/enableFile")
    public CommonRespT<?> enableFile(@RequestBody Integer id) {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setId(id);
        fileInfo.setIsEnable(true);
        if (fileInfoService.updateById(fileInfo)) {
            return ResponseUtil.successT("success");
        } else {
            return ResponseUtil.successT("fail");
        }
    }

    @ApiOperation(value = "设置文件禁用")
    @PostMapping("/disableFile")
    public CommonRespT<?> disableFile(@RequestBody Integer id) {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setId(id);
        fileInfo.setIsEnable(false);
        if (fileInfoService.updateById(fileInfo)) {
            return ResponseUtil.successT("success");
        } else {
            return ResponseUtil.successT("fail");
        }
    }


    /*
     * 文件上传接口
     * */
    @PostMapping("/uploadFile")
    public CommonRespT<?> uploadFile(@RequestBody FileInfo fileInfo,
                                     @ApiIgnore/*上传时这个参数可以忽略，相当于值有FileInfo fileInfo这个参数*/
                                     @AuthenticationPrincipal/*相当于Authentication.getPrincipal()*/
                                                  SecurityUser securityUser) throws Exception {

        //获取用户信息userId
        Integer userId = securityUser.getId();
        //从redis中 获取存储的私钥  解密 被非对称加密 加密的 对称加密秘钥
        String aesKey = EncryptionUtil
                .rsaDecrypt(fileInfo.getAesKeyEncrypt(),/*要解密的信息*/
                           (String) redisService.get(String.valueOf(userId))/*redis中的私钥*/);
        // 拿到解密的对称加密密钥之后，对文件base64编码解密
        String base64 = EncryptionUtil.aesDecrypt(fileInfo.getFileCodeStr(), aesKey);
        //设置文件base64编码
        fileInfo.setBase64(base64);
        //获取文件base64编码哈希值
        fileInfo.setHashCode(MD5Util.getMD5(base64));
        //文件上传者的userId
        fileInfo.setUserId(userId);
        //上传文件是否成功返回的标志 大于0即成功
        int flag = 0;

        //"E"上传的是签名文件
        if (fileInfo.getParameters().get(0).equals("E")) {
            fileInfo.setType("E");
            flag = fileInfoService.insertSignature(fileInfo);
            if (flag > 0) {
                return ResponseUtil.successT("success");
            } else {
                return ResponseUtil.successT("fail");
            }
        }

        //"F"上传的是验真文件
        else if (fileInfo.getParameters().get(0).equals("F")) {
            System.err.println(fileInfo.getHashCode());
            flag = fileInfoService.fileVerification(fileInfo.getHashCode());//用于记录数据库中相同文件的数量
            Map result = new HashMap();
            result.put("fileName", fileInfo.getFileName());
            if (flag > 0) {
                result.put("result", "success");
                return ResponseUtil.successT(result);
            } else {
                result.put("result", "fail");
                return ResponseUtil.successT(result);
            }
        }

        //"A"上传的是文件模板
        else if (fileInfo.getParameters().get(0).equals("A")) {
            fileInfo.setType("A");
            flag = fileInfoService.insertFileTem(fileInfo);
            if (flag > 0) {
                // return ResponseUtil.successT("success");
                return ResponseUtil.successT(flag);  //返回文件的id
            } else {
                return ResponseUtil.successT("fail");
            }
        }

        //"C1"上传的是个人文件（原始文件，未归档）
        else if (fileInfo.getParameters().get(0).equals("C1")) {
            fileInfo.setType("C1");
            flag = fileInfoService.insertMyFile(fileInfo);
            if (flag > 0) {
                return ResponseUtil.successT("success");
            } else {
                return ResponseUtil.successT("fail");
            }
        }

        //"C2"上传的是个人文件（原始文件，归档）
        else if (fileInfo.getParameters().get(0).equals("C2")) {
            fileInfo.setType("C2");
            flag = fileInfoService.insertMyArchivFile(fileInfo);
            if (flag > 0) {
                return ResponseUtil.successT("success");
            } else {
                return ResponseUtil.successT("fail");
            }
        }

        //"M"上传的是消息文档
        else if (fileInfo.getParameters().get(0).equals("M")) {
            fileInfo.setType("M");
            int fileId = 0;
            fileId = fileInfoService.insertMessFile(fileInfo);
            if (fileId > 0) {
                return ResponseUtil.successT(fileId);
            } else {
                return ResponseUtil.successT("fail");
            }
        }

        //"N"上传的是公告文档
        else if (fileInfo.getParameters().get(0).equals("N")) {
            fileInfo.setType("N");
            int fileId = 0;
            fileId = fileInfoService.insertNoticeFile(fileInfo);
            if (fileId > 0) {
                return ResponseUtil.successT(fileId);
            } else {
                return ResponseUtil.successT("fail");
            }
        }

        //"Y"  临时上传的文件
        else if (fileInfo.getParameters().get(0).equals("Y")) {
            fileInfo.setType("Y");
            int fileId = 0;
            fileId = fileInfoService.insertTempFile(fileInfo);
            if (fileId > 0) {
                return ResponseUtil.successT(fileId);
            } else {
                return ResponseUtil.successT("fail");
            }
        }

        return ResponseUtil.successT("success");

    }

    /**
     * 获取文件base64编码
     * 获取文件编码 下载预览文件
     */
    @PostMapping(value = "/getFileCodeById")
    public FileInfo getFileCodeById(@RequestBody FileInfo f,
                                    @ApiIgnore/*上传时这个参数可以忽略，相当于值有FileInfo fileInfo这个参数*/
                                    @AuthenticationPrincipal/*相当于Authentication.getPrincipal()*/
                                                 SecurityUser securityUser) throws Exception {

        //通过参数的文件id获取到该id对应的文件信息
        FileInfo fileInfo = fileInfoService.getFileCodeById(f.getId());
        //从redis中 获取存储的私钥  解密 被非对称加密 加密的 对称加密秘钥
        String aesKey = EncryptionUtil
                .rsaDecrypt(f.getAesKeyEncrypt(),/*要解密的信息*/
                           (String) redisService.get(String.valueOf(securityUser.getId())));//解密被非对称加密加密的对称加密秘钥
        //对文件base64编码进行ase对称加密
        String fileBase64Encrypt = EncryptionUtil
                .encryptAes(fileInfo.getPrefix() + /*获取前缀+ 文件编码*/
                                Base64.getEncoder().encodeToString(fileInfo.getFileCode()),
                            aesKey);
        //把加密后的base64编码返回
        fileInfo.setBase64(fileBase64Encrypt);
        //把查询出来的文件编码清空 不能把这个文件信息返回
        fileInfo.setFileCode(null);
        return fileInfo;
    }


    @ApiOperation(value = "微信小程序获取文件编码【下载文件】")
    @PostMapping(value = "/getWXFileCodeById")//用于微信小程序在 “本地” 预览文件
    public String getWXFileCodeById(@RequestBody FileInfo f, HttpServletResponse response) throws IOException {
        FileInfo fileInfo = fileInfoService.getFileCodeById(f.getId());
        String fileCode = Base64.getEncoder().encodeToString(fileInfo.getFileCode());
        String fileBase64;
        if(fileInfo.getSuffix().equals("png") || fileInfo.getSuffix().equals("jpg")){
             fileBase64 = fileCode;
        }
        else{
             fileBase64 = fileCode + fileInfo.getPrefix();//文件的base64编码
        }

        String filename = fileInfo.getFileName().split("\\.")[0];
        String suffix = fileInfo.getSuffix();
        String reallyPath = filename+ "." +suffix;
        //文件的base64编码 转文件
        File dir = new File("D://temp");
        if (!dir.exists()){
            dir.mkdirs();
        }
        File file = new File(dir+"//"+reallyPath);

        byte[] buffer = new BASE64Decoder().decodeBuffer(fileBase64);
        FileOutputStream out = new FileOutputStream(file);
        out.write(buffer);
        out.close();
        return reallyPath;
    }

    @ApiOperation(value = "微信小程序获取文件编码【下载文件并转成图片】")
    @PostMapping(value = "/getWXFileCodeByIdToPic")//用于微信小程序在 “小程序端” 预览文件
    public  List<Map<String, String>> getWXFileCodeByIdToPic(@RequestBody FileInfo f) throws IOException {
        System.out.println("进入小程序图片转换"+f.getId());

        //获取数据库的文件的编码
        FileInfo fileInfo = fileInfoService.getFileCodeById(f.getId());
        String fileCode = Base64.getEncoder().encodeToString(fileInfo.getFileCode());
        String fileBase64 = fileInfo.getPrefix()+fileCode;//文件的base64编码
        //文件的base64编码 转文件
        File dir = new File("D://temp");
        if (!dir.exists()){
            dir.mkdirs();
        }
        File file1 = new File(dir+"//"+ fileInfo.getFileName().split("\\.")[0]+"."+fileInfo.getSuffix());
        byte[] buffer = new BASE64Decoder().decodeBuffer(fileBase64);
        FileOutputStream out = new FileOutputStream(file1);
        out.write(buffer);//数据库文件写入服务器本地
        out.close();

        //文件转图片
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> resultMap = null;
        File file = null;
        PDDocument pdDocument = null;
        int dpi = 300;/* dpi越大转换后越清晰，相对转换速度越慢 */
        file = new File(file1.getPath());
        pdDocument = PDDocument.load(file);
        PDFRenderer renderer = new PDFRenderer(pdDocument);
        int pageCount = pdDocument.getNumberOfPages();


        for (int i = 0; i < pageCount; i++) {
            resultMap = new HashMap<>();
            String fileName = fileInfo.getFileName().split("\\.")[0]+ "-" + i+".png";
            //String fileName = new Random(1000)+ "-" + i+".png";
            String filePath = "D:\\temp\\"+fileName;
            File dstFile = new File(filePath);
            BufferedImage image = renderer.renderImageWithDPI(i, dpi);
            ImageIO.write(image, "jpg", dstFile);
            InputStream in = null;
            byte[] data = null;
            //读取图片字节数组
            in = new FileInputStream(dstFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
            //对字节数组Base64编码
           // BASE64Encoder encoder = new BASE64Encoder();
           // String emm =  encoder.encode(data);//返回Base64编码过的
            resultMap.put("fileName", fileName);
            //resultMap.put("filePath", filePath);
            //resultMap.put("tp",emm);
            list.add(resultMap);
        }


        // 这里需要关闭PDDocument，不然如果想要删除pdf文件时会提示文件正在使用，无法删除的情况
        pdDocument.close();

        System.out.println("end");
        return list;
    }

    @ApiOperation(value = "微信小程序实现文件盖章")
    @PostMapping("/ensureSgin")
    public void ensureSgin(@RequestBody JSONObject json) throws Exception {
        System.out.println("盖章名称"+json.getString("sginName"));
        System.out.println("文件名称"+json.getString("fileName"));
        System.out.println(json.getString("x")+"w时");
        System.out.println(json.getString("y")+"y时");
        Document document=null;
        PdfStamper stamper=null;
        PdfReader reader=null;
        try {

            // 模板文件路径
            String templatePath = "D:\\temp\\"+json.getString("fileName");
            // 生成的文件路径
            String targetPath = "D:\\temp\\"+json.getString("fileName");
            // 书签名
            String fieldName = "texts";
            // 图片路径
            String imagePath = "D:\\temp\\"+json.getString("sginName");




            // 读取模板文件
            InputStream input = new FileInputStream(new File(templatePath));
            reader = new PdfReader(input);
            //获取页数
            int pagecount= reader.getNumberOfPages();



            stamper = new PdfStamper(reader, new FileOutputStream(
                    targetPath));

            document = new Document(reader.getPageSize(1));

            // 获取页面宽度
            float width = document.getPageSize().getWidth();
            // 获取页面高度
            float height = document.getPageSize().getHeight();
            System.out.println("width = "+width+", height = "+height);

            // 读图片
            Image image = Image.getInstance(imagePath);
            // 根据域的大小缩放图片
            image.scaleToFit(150, 150);
            image.setAbsolutePosition((float) (Double.parseDouble(json.getString("x"))*1.58),
                                       (float) (Double.parseDouble(json.getString("y"))*1.58));




            //for (int i=1;i<=pagecount;i++) {
            // 获取操作的页面
           // PdfContentByte under = stamper.getOverContent(pagecount);
            PdfContentByte under = stamper.getOverContent(json.getInteger("page"));
            // 添加图片
            under.addImage(image);
            // }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(document!=null){
                document.close();
            }
            if(stamper!=null) {
                stamper.close();
            }
            if(reader!=null) {
                reader.close();
            }
        }
    }

    /**
     * 文件逻辑删除 通过修改文件的state属性由 A 变成 X
     */
    @PostMapping(value = "/delete")
    public int delete(@RequestBody Integer id) {
        return fileInfoService.updateStateToX(id);
    }

    /**
     * 文件批量逻辑删除 通过修改文件的state属性由 A 变成 X
     */
    @PostMapping(value = "/deleteMyFileSelected")
    public int deleteMyFileSelected(@RequestBody List<Integer> idList) {
        return fileInfoService.updateSelectedStateToX(idList);
    }


    /**
     * 文件分享
     */
    @PostMapping(value = "/shareFile")
    public int shareFile(@RequestBody shareFile shareFile) {
        return fileInfoService.shareFile(shareFile);
    }


}
