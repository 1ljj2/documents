package org.jit.sose.controller.signature;

import com.alibaba.fastjson.JSONObject;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.jit.sose.domain.entity.FileInfo;
import org.jit.sose.domain.entity.SecurityUser;
import org.jit.sose.domain.vo.PageInfoVo;
import org.jit.sose.service.FileInfoService;
import org.jit.sose.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Api(tags = "公章签名接口")
@ApiSupport(author = "LJH")
@RestController
@RequestMapping("/signature/signature")
public class SignatureController {

    @Autowired
    FileInfoService fileInfoService;

//    @Autowired
//    private SignatureService signatureService;
//
//    @Autowired
//    RedisService redisService;
//
//    @Resource
//    private RedisTemplate<String, Object> redisTemplate;
//
//    @ApiResponses({@ApiResponse(code = 200, message = "Request Success"),
//            @ApiResponse(code = 401, message = "Authentication Failure"),
//            @ApiResponse(code = 402, message = "Login Information Invalid"),
//            @ApiResponse(code = 403, message = "No Permission"), @ApiResponse(code = 500, message = "Request fail")})
//    @ApiOperation(value = "上传公章签名", notes = "增加公章签名")
//    @PostMapping("/uploadSignature")
//    public String uploadSignature(@RequestBody JSONObject json, @ApiIgnore @AuthenticationPrincipal SecurityUser securityUser) throws Exception {
//
//        System.out.println("securityUser"+securityUser);
//        String aesKey = json.getString("aesKey");//对称加密秘钥
//        String aesKey2 = EncryptionUtil.rsaDecrypt(aesKey, (String) redisService.get(String.valueOf(securityUser.getId())));//解密被非对称加密加密的对称加密秘钥
//        String fileStr = json.getString("fileStr");//对称加密后的文件
//        String storeWay = json.getString("storeWay");//文件存储类型
//        String signatureName = json.getString("signatureName");//文件名称
//        String base64 = EncryptionUtil.aesDecrypt(fileStr, aesKey2);//文件base64编码解密
////        System.out.println("base64"+base64);
//        Integer userId = securityUser.getId();//用户id，用于唯一标识每一位用户
//
//        MultipartFile file = BASE64DecodedMultipartFile.base64ToMultipart(base64);
//        if (file == null) {//判断文件是否为空
//            return null;
//        }
//        Integer signatureInsert = signatureService.insertSignature(file, storeWay, signatureName, userId);
//        return ResponseUtil.success("success");
////        return 1;
//    }
//
//
    @ApiResponses({@ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 401, message = "Authentication Failure"),
            @ApiResponse(code = 402, message = "Login Information Invalid"),
            @ApiResponse(code = 403, message = "No Permission"), @ApiResponse(code = 500, message = "Request fail")})
    @ApiOperation(value = "过滤查询个人公章签名", notes = "根据签名名称、用户id过滤查询")
    @PostMapping(value = "/listPageInfo")
    public PageInfoVo<FileInfo> listPageInfo(@RequestBody JSONObject json, @ApiIgnore @AuthenticationPrincipal SecurityUser securityUser) {
//        System.out.println("securityUser"+securityUser);
        Integer userId = securityUser.getId();//用户id，用于唯一标识每一位用户
        FileInfo fileInfo = new FileInfo();
        // 过滤查询条件
        String fileName = json.getString("fileName");
        List<Integer> roleId = (List<Integer>) json.get("roleId");
        System.out.println("roleId"+roleId);
        //设置过滤条件
        fileInfo.setFileName(StringUtil.isEmpty(fileName) ? null : fileName);
        fileInfo.setUserId(userId);
        fileInfo.setRoleIdList(roleId);
        // 当前页码
        Integer pageNum = json.getIntValue("pageNum");
        // 每页条数
        Integer pageSize = json.getIntValue("pageSize");
        return fileInfoService.signatureListPageInfo(fileInfo, pageNum, pageSize);
//        return null;
    }







//
//    @ApiResponses({@ApiResponse(code = 200, message = "success"),
//            @ApiResponse(code = 401, message = "Authentication Failure"),
//            @ApiResponse(code = 402, message = "Login Information Invalid"),
//            @ApiResponse(code = 403, message = "No Permission"), @ApiResponse(code = 500, message = "Request fail")})
//    @ApiOperation(value = "过滤查询个人公章签名（不分页）", notes = "根据用户id过滤查询")
//    @GetMapping(value = "/listSignature")
//    public List<Signature> listSignature(@ApiIgnore @AuthenticationPrincipal SecurityUser securityUser) {
//        Integer userId = securityUser.getId();//用户id，用于唯一标识每一位用户
//        Signature signature = new Signature();
//        //设置过滤条件
//        signature.setUserId(userId);
//        return signatureService.listSignature(signature);
//    }
//
//    @ApiResponses({@ApiResponse(code = 200, message = "success"),
//            @ApiResponse(code = 401, message = "Authentication Failure"),
//            @ApiResponse(code = 402, message = "Login Information Invalid"),
//            @ApiResponse(code = 403, message = "No Permission"), @ApiResponse(code = 500, message = "Request fail")})
//    @ApiOperation(value = "逻辑删除个人公章签名（不分页）", notes = "根据公章签名id删除")
//    @PostMapping(value = "/deleteSignature")
//    public void deleteSignature(@RequestBody Integer id) {
//        System.out.println("id:"+id);
//        signatureService.deleteSignature(id);
//    }
}
