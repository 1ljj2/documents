package org.jit.sose.util;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Data
@Component
//@PropertySource("classpath:application.properties")
public class TXTUtil {




    public List<String> getSystemInfoFromUrl() {
        List<String> result = new ArrayList<>();
        String path = "D:\\proj\\document\\systemInfo.txt";
        File file = new File(path);
//        System.out.println("systemInfoUrl:" + systemInfoUrl);
//        File file = new File(systemInfoUrl);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = null;
            while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
                result.add(s);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 本地读取文件转成一行字符串
     *
     * @param file
     * @return
     */
    public static String txt2String(File file) {
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
                result.append(System.lineSeparator() + s);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    /**
     * 本地读取文件转成List集合
     *
     * @param file
     * @return
     */
    public static List<String> txt2List(File file) {
        List<String> result = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
                result.add(s);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 从网络位置读取文件转成一行字符串
     *
     * @param filePath
     * @return
     * @throws Exception
     */
    public static String txt2String(String filePath) throws Exception {
        StringBuffer strB = new StringBuffer();   //strB用来存储txt文件里的内容
        String str = "";
        URL url = null;
        try {
            url = new URL(filePath);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream is = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, TXTUtil.resolveCode(filePath));
            BufferedReader br = new BufferedReader(isr);
            while ((str = br.readLine()) != null) {
                strB.append(str).append("<br>");   //将读取的内容放入strB
            }
            br.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strB.toString();
    }


    /*
     * 从网络位置获取文件转成List集合
     * @param filePath
     * @return
     * @throws Exception
     */
    public static List<String> txt2List(String filePath) throws Exception {
        List<String> result = new ArrayList<>();
        String str = "";
        URL url = null;
        try {
            url = new URL(filePath);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream is = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, TXTUtil.resolveCode(filePath));
            BufferedReader br = new BufferedReader(isr);
            while ((str = br.readLine()) != null) {
                result.add(str);   //将读取的内容放入strB
            }
            br.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 传入文件路径将TXT转成List集合
     *
     * @param filePath
     * @return
     * @throws Exception
     */
    public static List<String> localTxt2List(String filePath) throws Exception {
        List<String> result = new ArrayList<>();
        File file = new File(filePath);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
                result.add(s);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 解析编码格式
     *
     * @param filePath
     * @return
     * @throws Exception
     */
    public static String resolveCode(String filePath) throws Exception {
        byte[] head = new byte[8];
        URL url = new URL(filePath);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        InputStream is = connection.getInputStream();
        is.read(head);
        String code = "gb2312";  //或GBK  
        if (head[0] == -1 && head[1] == -2) {
            code = "UTF-16";
        } else if (head[0] == -2 && head[1] == -1) {
            code = "Unicode";
        } else if (head[0] == -17 && head[1] == -69 && head[2] == -65) {
            code = "UTF-8";
        }

//        System.out.println("文本编码格式:"+code);  
        is.close();
        return code;
    }

    /**
     * 判断网络文件是否存在
     *
     * @param httpPath
     * @return
     * @throws IOException
     */
    public static Boolean existHttpPath(String httpPath) throws IOException {
        URL pathUrl = new URL(httpPath);
        HttpURLConnection urlcon = (HttpURLConnection) pathUrl.openConnection();
        if (urlcon.getResponseCode() >= 400) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 写入TXT文件
     *
     * @param filePath
     * @param list
     */
    public static void writeTXT(String filePath, List<String> list) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(filePath);//创建文本文件
            int i = 0;
            for (String item : list) {//循环写入

                fileWriter.write(item + "\r\n");//写入 \r\n换行
                i++;
            }
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
