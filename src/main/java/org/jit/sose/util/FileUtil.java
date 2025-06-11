package org.jit.sose.util;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

/**
 * 文件操作工具类
 *
 * @author: dylan
 */
@Data
@Slf4j
@Component
public class FileUtil {

    /**
     * 虚拟目录
     */
    @Value("${file.staticAccessPath}")
    private String staticAccessPath;

    /**
     * 文件上传路径
     */
    @Value("${file.uploadFolder}")
    private String uploadPath;


    /**
     * 获取自定义文件路径(不包含基础上传路径，文件父目录+自定义文件名称)
     *
     * @return 文件名
     */
    public String getTemplateUrl(String originalFileNameString) {
        String fileName = null;
        // 获取当前时间字符串
        String fileDateCode = DateFormatUtil.formatCode(new Date());
        //设置父目录
        String pDirectory = "templateUsedByProcess";
        // 文件上传目录
        String fileParentDir = pDirectory + File.separator;
        fileName = fileParentDir + fileDateCode + "_" + originalFileNameString;
        log.warn("fileName:" + fileName);
        return fileName;
    }

    /**
     * 获取自定义文件路径(不包含基础上传路径，文件父目录+自定义文件名称)
     *
     * @return 文件名
     */
    public String getEditedFileUrl(String originalFileNameString) {
        String fileName = null;
        // 获取当前时间字符串
        String fileDateCode = DateFormatUtil.formatCode(new Date());
        //设置父目录
        String pDirectory = "editedFile";
        // 文件上传目录
        String fileParentDir = pDirectory + File.separator;
        fileName = fileParentDir + fileDateCode + "_" + originalFileNameString;
        log.warn("fileName:" + fileName);
        return fileName;
    }

    /**
     * 获取自定义文件路径(不包含基础上传路径，文件父目录+自定义文件名称)
     *
     * @return 文件名
     */
    public String getSignatureUrl(String originalFileNameString) {
        String fileName = null;
        // 获取当前时间字符串
        String fileDateCode = DateFormatUtil.formatCode(new Date());
        //设置父目录
        String pDirectory = "signature";
        // 文件上传目录
        String fileParentDir = pDirectory + File.separator;
        fileName = fileParentDir + fileDateCode + "_" + originalFileNameString;
        log.warn("fileName:" + fileName);
        return fileName;
    }

    /**
     * 根据文件名获取文件对象
     *
     * @param fileName
     * @return
     */
    public File getNewFile(String fileName) {
        log.warn("uploadPath:" + uploadPath);
        // 根据上传路径和文件名创建 文件对象
        File newFile = new File(uploadPath, fileName);
        // 获取新对象的父目录对象
        File parentFile = newFile.getParentFile();
        // 判断父目录对象是否存在
        boolean isParentFileExist = parentFile.exists();
        // 父目录不存在，创建父目录对象
        if (!isParentFileExist) {
            parentFile.mkdirs();
        }
        // 判断新对象是否存在
        boolean isNewFileExist = newFile.exists();
        // 新对象不存在，创建新对象
        if (!isNewFileExist) {
            try {
                boolean isCreateNewFile = newFile.createNewFile();
                if (!isCreateNewFile) {
                    log.error("文件创建失败");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return newFile;
    }

    /**
     * 根据自定义文件路径(不包含基础上传路径) 获取页面访问路径
     *
     * @param templateUrl 自定义文件路径
     * @return
     */
    public String getAccessUrl(String templateUrl) {
        log.warn("staticAccessPath:" + staticAccessPath);
        return staticAccessPath + "/" + templateUrl.replace(File.separator, "/");
    }

    /**
     * 判断文件后缀是否在自定义后缀数组中
     *
     * @param suffix
     * @param allowPicSuffix
     * @return
     */
    public boolean isHasSuffix(String suffix, String allowPicSuffix[]) {
        // 后缀不为空
        if (suffix == null || "".equals(suffix) || suffix.trim().length() == 0) {
            return false;
        }
        // 判断数据中书否存在后缀。使用Arrays类中asList()方法将数组转化为List()列表
        return Arrays.asList(allowPicSuffix).contains(suffix);
    }

    /**
     * <p>将文件转成base64 字符串</p>
     *
     * @param path 文件路径
     * @return
     * @throws Exception
     */
    public String encodeBase64File(String path) throws Exception {

        String realPath = path.replace(staticAccessPath, uploadPath);
//        System.out.println(realPath);
        File file = new File(realPath);

//        File file = new File(path);
        FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[(int) file.length()];
        inputFile.read(buffer);
        inputFile.close();
        return new BASE64Encoder().encode(buffer);
//        return "test";
    }



}
