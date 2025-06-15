package org.jit.sose.util;


import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;

/**
 * Date:2021-03-17
 * Time:14:16
 */
public class CheckEnvironmentUtil {
    private static final String path = "src/ServerInformation.txt";


    //验证方法 UiVNx/r2AE
    public static boolean checkEnvironment(){
        if(!isExists()){
            return false;
        }
        //判断受否存在文件 如果没有返回false
        File file = new File(path);
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader br = null;// 构造一个BufferedReader类来读取文件
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
//            e.printStackTrace();
            return false;
        }
        String s = null;
        while (true) {
            try {
                if (!((s = br.readLine()) != null)) break;
            } catch (IOException e) {
//                e.printStackTrace();
                return false;
            }// 使用readLine方法，一次读一行
            stringBuilder.append(s);
        }
        try {
            br.close();
        } catch (IOException e) {
//            e.printStackTrace();
            return false;
        }
        //文件存储的基础加密串
        String baseCode = stringBuilder.toString();
        if (baseCode.length()==0){
            return false;
        }
        String[] EncryptedInformation = baseCode.split("@");
        String beforeComputerInformation = EncryptedInformation[0];
        String privateKey = EncryptedInformation[1];
        String afterRsaPublicKeyInformation = EncryptedInformation[2];
        //解密这个加密串
           //获取aes密钥
        String aesKey = null;
        try {
            aesKey = EncryptionUtil.rsaDecrypt(afterRsaPublicKeyInformation,privateKey);
        } catch (Exception e) {
//            e.printStackTrace();
            return false;
        }
        //解密信息
        String nowComputerInformation = EncryptionUtil.aesDecrypt(beforeComputerInformation, aesKey);
        String[] allInformation = nowComputerInformation.split("\\s");
//        for (String s1:allInformation){
//            System.out.println(s1);
//        }
        //获取串中的时间进行比对
        String Deadline = allInformation[2]+" "+allInformation[3];
        if(!isDateBefore(Deadline)){
            return false;
        }
        //获取其他参数进行比对
        if(!getIpAddress().equals(allInformation[0])){
            System.out.println("文件提供："+allInformation[0]);
            System.out.println("代码提供："+getIpAddress());
            return false;
        }
        if(!GetCpuID().equals(allInformation[1])){
            System.out.println("文件提供："+allInformation[1]);
            System.out.println("代码提供："+GetCpuID());
            return false;
        }

        //如果都通过返回true
        return true;
    }


    //获取服务器id
    private static String getIpAddress(){
        try {
            InetAddress address = InetAddress.getLocalHost();
            String hostAddress = address.getHostAddress();
            return hostAddress;
        } catch (UnknownHostException e) {
            return null;
        }
    }


    //获取服务器机器码  例如：   078BFBFF00830F10
    private static String GetCpuID() {
        try {
            Process process = Runtime.getRuntime().exec(new String[]{"wmic", "cpu", "get", "ProcessorId"});
            process.getOutputStream().close();
            Scanner sc = new Scanner(process.getInputStream());
            String before = sc.next();
            String serial = sc.next();
            return serial;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    //读取是否有本地文件
    private static boolean isExists(){
        File file = new File(path);
        return file.exists();
    }

    /**判断时间是否过期
      *时间格式：yyyy-mm-DD hh:mm:ss 例如：4040-09-09 12:12:12
      */
    private static boolean isDateBefore(String date2){
        try{
            Date date1 = new Date();
            DateFormat df = DateFormat.getDateTimeInstance();
            return date1.before(df.parse(date2));
        }catch(ParseException e){
            return false;
        }
    }


    public static void main(String[] args) throws Exception{

        //原信息 非对称加密私钥+" "+非对称公钥加密对称加密密钥+" "+服务器ip地址+" "+服务器机器码+" "+过期时间
//        String beforeMsg = "106.55.37.222"+" "+"078BFBFF00830F10"+" "+"4040-09-09 12:12:12";
        String beforeMsg = "192.168.1.111"+" "+"BFEBFBFF000A0652"+" "+"4040-09-09 12:12:12";
        //生成对称加密密钥
        String AesKey=EncryptionUtil.getAesKey(16);

        //对称加密加密信息
        String InformationAfterAsc  = EncryptionUtil.encryptAes(beforeMsg, AesKey);

        //非对称加密 密钥对
        Map<Integer, String> rsaKeyPair = EncryptionUtil.getRsaKeyPair();

        //获取非对称公钥
        String publicKey = rsaKeyPair.get(0);
        //获取非对称私钥
        String privateKey = rsaKeyPair.get(1);

        //非对称公钥加密 对称加密 密钥
        String InformationAfterDescPublic = EncryptionUtil.rsaEncrypt(AesKey, publicKey);

        BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(new File(path)));
        bufferedWriter.write(InformationAfterAsc+"@"+privateKey+"@"+InformationAfterDescPublic);
        bufferedWriter.flush();
        bufferedWriter.close();

    }
}
