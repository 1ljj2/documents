package org.jit.sose.util;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import lombok.extern.slf4j.Slf4j;
import org.jit.sose.domain.vo.QcloudSmsVo;
import org.jit.sose.enums.SmsCodeEnum;
import org.jit.sose.properties.QcloudSmsProperties;
import org.json.JSONException;

import java.io.IOException;

/**
 * 通过腾讯云 指定模板发送短信
 *
 * @author wangyue
 * @date 2019年4月18日 下午11:47:32
 */
@Slf4j
public class QcloudSmsUtil {

    /**
     * 图形验证码失效时间,5分钟
     */
    public static final Long SMS_CODE_EXPIRED_TIME = 60 * 5L;

    /**
     * 通过短信发送提醒
     * 模板：您目前有需要审批的流程，申请人为：{1}，请您尽快登录系统进行审批。
     *
     * @param phone        获取发送至用户的手机号码
     * @param userName     申请审核的用户名称
     * @return 0:成功；其他:失败
     */
    public static Integer sendMyFileQcloudSms(String phone, String userName) {
        SmsSingleSenderResult result = null; // 短信发送返回信息
        try {
            log.info("***********发送【【我的文档】-【送审】-提醒短信】的提醒短信***********");
            // 转为数组格式
            String[] phoneNumber = {phone};
            // params 为短信模板中{1}{2}的具体内容
            String[] params = {userName};
            SmsSingleSender msender = new SmsSingleSender(QcloudSmsProperties.appId, QcloudSmsProperties.appKey);
            /**
             * @param nationCode  国家码，如 86 为中国
             * @param phoneNumber 不带国家码的手机号
             * @param phoneTypeId 短信正文id
             * @param params      信息内容
             * @param params      模板参数列表，如模板 {1}...{2}...{3}，那么需要带三个参数
             * @param smsSign     签名，如果填空，系统会使用默认签名
             */
            result = msender.sendWithParam("86", phoneNumber[0], QcloudSmsProperties.auditSmsTemplateId, params,
                    QcloudSmsProperties.smsSign, "", "");
            log.info("发送结果：" + result);
            log.info("***********发送结束***********");
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
            return -1;
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
            return -2;
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
            return -3;
        }
        // 获取返回值
        return result.result;
    }

    /**
     * 通过短信发送提醒
     * 模板：您目前共有{1}个需要审批的流程，最近的需要审批的流程截止时间为{2}，请您尽快登录系统进行审批。
     *
     * @param phone   需要发送提醒的短信号码
     * @param size    待办事宜的总条数
     * @param cutTime 最近的一个的待办事宜的截至时间
     * @return 0:成功；其他:失败
     */
    public static Integer sendRemindBackLogQcloudSms(String phone, Integer size, String cutTime) {
        SmsSingleSenderResult result = null; // 短信发送返回信息
        try {
            log.info("***********发送【待办事宜】的提醒短信***********");
            // 转为数组格式
            String[] phoneNumber = {phone};
            // params 为短信模板中{1}{2}的具体内容
            String[] params = {size.toString(), cutTime};
            SmsSingleSender msender = new SmsSingleSender(QcloudSmsProperties.appId, QcloudSmsProperties.appKey);
            /**
             * @param nationCode  国家码，如 86 为中国
             * @param phoneNumber 不带国家码的手机号d
             * @param phoneTypeId 短信正文id
             * @param params      信息内容
             * @param params      模板参数列表，如模板 {1}...{2}...{3}，那么需要带三个参数
             * @param smsSign     签名，如果填空，系统会使用默认签名
             */
            result = msender.sendWithParam("86", phoneNumber[0], QcloudSmsProperties.backLogSmsTemplateIdId, params,
                    QcloudSmsProperties.smsSign, "", "");
            log.info("发送结果：" + result);
            log.info("***********发送结束***********");
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
            return -1;
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
            return -2;
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
            return -3;
        }
        // 获取返回值
        return result.result;
    }

    /**
     * 生成验证码在 redis 中的 key<br>
     * 格式为 smsCode:{codeType}:phone
     * 验证码类型
     * 获取验证码的手机
     *
     * @return 验证码在 redis 中的 key
     */
    public static Long getRedisExpiredTime(SmsCodeEnum phoneType) {
        long time = 0;
        switch (phoneType.toString()) {
            case "registerPhone":
                time = Integer.valueOf(QcloudSmsProperties.registerFailureTime) * 60L;
                break;
            case "forgetPwdPhone":
                time = Integer.valueOf(QcloudSmsProperties.forgetPwdFailureTime) * 60L;
                break;
            case "loginPhone":
                time = Integer.valueOf(QcloudSmsProperties.loginFailureTime) * 60L;
                break;
            default:
                break;
        }
        return time;
    }

    /**
     * 生成验证码在 redis 中的 key<br>
     * 格式为 smsCode:{phoneType}:phone
     *
     * @param phoneType 验证码类型
     * @param phone     获取验证码的手机
     * @return 验证码在 redis 中的 key
     */
    public static String getRedisKey(SmsCodeEnum phoneType, String phone) {
        String key = "smsCode:" + phoneType + ":" + phone;
        return key;
    }

    /**
     * 获取自定义长度的验证码随机数
     *
     * @return 验证码
     */
    public static String smsCode(Integer smsCodeLength) {
        String code = "";
        for (int i = 0; i < smsCodeLength; i++) {
            code += (int) (Math.random() * 9 + 1);
        }
        return code;
    }

    /**
     * 指定模板ID单发登录，注册，忘记密码短信
     *
     * @param phone     不带国家码的手机号
     * @param phoneType 短信发送类型:registerPhone forgetPwdPhone loginPhone
     * @param code      短信验证码
     * @return 返回错误码 返回0代表成功
     */
    public static QcloudSmsVo sendQcloudSms(String phone, SmsCodeEnum phoneType, String code) {

        String[] qcloudPhones = new String[]{phone};

        log.info("短信模板：" + phoneType);
        // 短信模板ID，需要在短信应用中申请
        // 真实的模板ID需要在短信控制台中申请
        int phoneTypeId = getPhoneTypeIdByPhoneType(phoneType);

        // 自定义短信失效时间
        String qMsgFailureTime = getQMsgFailureTimeByPhoneType(phoneType);

        return sendSms(code, qMsgFailureTime, qcloudPhones, phoneTypeId);
    }

    private static QcloudSmsVo sendSms(String code, String qMsgFailureTime, String[] phoneNumbers,
                                       Integer phoneTypeId) {
        SmsSingleSenderResult result = null; // 短信发送返回信息
        try {
            // params 为短信模板中{1}{2}的具体内容
            String[] params = {code, qMsgFailureTime};
            SmsSingleSender msender = new SmsSingleSender(QcloudSmsProperties.appId, QcloudSmsProperties.appKey);
            /**
             * @param nationCode  国家码，如 86 为中国
             * @param phoneNumber 不带国家码的手机号d
             * @param phoneTypeId 短信正文id
             * @param params      信息内容
             * @param params      模板参数列表，如模板 {1}...{2}...{3}，那么需要带三个参数
             * @param smsSign     签名，如果填空，系统会使用默认签名
             */
            result = msender.sendWithParam("86", phoneNumbers[0], phoneTypeId, params, QcloudSmsProperties.smsSign, "",
                    "");
            log.info("短信发送结果：" + result.toString());
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
            return new QcloudSmsVo(false, -1, "短信发送失败");
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
            return new QcloudSmsVo(false, -2, "短信发送失败");
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
            return new QcloudSmsVo(false, -3, "短信发送失败");
        }
        // 获取返回码
        int resultCode = result.result;
        // 0表示成功
        if (0 == resultCode) {
            return new QcloudSmsVo(true, 0, "短信发送成功");
        } else {
            return getErrorCode(resultCode);
        }

    }

    /**
     * 根据不同的错误码返回对的结果
     */
    private static QcloudSmsVo getErrorCode(Integer resultCode) {
        // 其他表示失败
        switch (resultCode) {
            case 1016:
                return new QcloudSmsVo(false, 1016, "手机号格式错误");
            default:
                return new QcloudSmsVo(false, -4, "短信发送失败");
        }

    }

    /**
     * 获取短信模板ID
     */
    private static Integer getPhoneTypeIdByPhoneType(SmsCodeEnum phoneType) {
        int phoneTypeId = 0;
        switch (phoneType.toString()) {
            case "registerPhone":
                phoneTypeId = QcloudSmsProperties.registerTemplateId;
                break;
            case "forgetPwdPhone":
                phoneTypeId = QcloudSmsProperties.forgetPwdTemplateId;
                break;
            case "loginPhone":
                phoneTypeId = QcloudSmsProperties.loginTemplateId;
                break;
            default:
                break;
        }
        return phoneTypeId;
    }

    /**
     * 自定义短信失效时间
     */
    private static String getQMsgFailureTimeByPhoneType(SmsCodeEnum phoneType) {
        String qMsgFailureTime = "0";

        switch (phoneType.toString()) {
            case "registerPhone":
                qMsgFailureTime = QcloudSmsProperties.registerFailureTime;
                break;
            case "forgetPwdPhone":
                qMsgFailureTime = QcloudSmsProperties.forgetPwdFailureTime;
                break;
            case "loginPhone":
                qMsgFailureTime = QcloudSmsProperties.loginFailureTime;
                break;
            default:
                break;
        }
        return qMsgFailureTime;
    }

}