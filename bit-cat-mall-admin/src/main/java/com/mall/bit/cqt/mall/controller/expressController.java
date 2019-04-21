package com.mall.bit.cqt.mall.controller;

import com.mall.bit.cqt.mall.entity.Express;
import com.mall.bit.cqt.mall.service.ExpressService;
import com.mall.bit.cqt.mall.service.ItemService;
import com.mall.bit.cqt.mall.utils.HttpUtils;
import com.mall.bit.cqt.mall.utils.MD5Utils;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = {"/exp"})
@Api(tags = "快递查询模块")
public class expressController {

    @Resource
    private ExpressService expressService;

    public static byte[] decodeBase64(String input) throws Exception{
        Class clazz=Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
        Method mainMethod= clazz.getMethod("decode", String.class);
        mainMethod.setAccessible(true);
        Object retObj=mainMethod.invoke(input);
        return (byte[])retObj;
    }
    //跳转快递页面
    @GetMapping("list")
    public String list(){
        return "/udai_mail_query";
    }
    @GetMapping("show")
    public String exp(){
        return "/exp";
    }
    /**
     * 返回后台快递物流信息
     * @param expKind 快递种类
     * @param expNum  快递单号
     * @return
     */
    @ResponseBody
    @PostMapping(value = {"express"})
    public String express(String expKind,String expNum){
        expressController expressController = new expressController();
        String res = null;
        try {
            String result = expressController.getOrderTracesByJson(expKind, expNum);
            res = result;

        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.print(res);
        return res;
    }

    /**
     * 根据用户id查询相应快递信息
     * @param uid
     * @return
     */
    @ResponseBody
    @PostMapping(value = {"getMsg"})
    public Express getMsg(String uid){
        int uids = Integer.parseInt(uid);
        return expressService.getById(uids);
    }

    //电商ID
    private String EBusinessID="1436451";
    //电商加密私钥，快递鸟提供，注意保管，不要泄漏
    private String AppKey="9b4c6b10-84c6-4223-b1fe-6be2f0277f85";
    //请求url
    private String ReqURL="http://api.kdniao.com/Ebusiness/EbusinessOrderHandle.aspx";

    /**
     * Json方式 查询订单物流轨迹
     * @throws Exception
     */
    public String getOrderTracesByJson(String expCode, String expNo) throws Exception{
        String requestData= "{'OrderCode':'','ShipperCode':'" + expCode + "','LogisticCode':'" + expNo + "'}";

        Map<String, String> params = new HashMap<String, String>();
        params.put("RequestData", urlEncoder(requestData, "UTF-8"));
        params.put("EBusinessID", EBusinessID);
        params.put("RequestType", "1002");
        String dataSign=encrypt(requestData, AppKey, "UTF-8");
        params.put("DataSign", urlEncoder(dataSign, "UTF-8"));
        params.put("DataType", "2");

        //String result=sendPost(ReqURL, params);
        String result = HttpUtils.post(ReqURL,params);

        //根据公司业务处理返回的信息......

        return result;
    }
    /**
     * base64编码
     * @param str 内容
     * @param charset 编码方式
     * @throws UnsupportedEncodingException
     */
    private String base64(String str, String charset) throws UnsupportedEncodingException{
        //String encoded = base64Encode(str.getBytes(charset));
        String encoded = Base64.getEncoder().encodeToString(str.getBytes(charset));
        return encoded;
    }

    @SuppressWarnings("unused")
    private String urlEncoder(String str, String charset) throws UnsupportedEncodingException{
        String result = URLEncoder.encode(str, charset);
        return result;
    }

    /**
     * 电商Sign签名生成
     * @param content 内容
     * @param keyValue Appkey
     * @param charset 编码方式
     * @throws UnsupportedEncodingException ,Exception
     * @return DataSign签名
     */
    @SuppressWarnings("unused")
    private String encrypt (String content, String keyValue, String charset) throws UnsupportedEncodingException, Exception
    {
        if (keyValue != null)
        {
            return base64(MD5Utils.MD5(content + keyValue, charset), charset);
        }
        return base64(MD5Utils.MD5(content, charset), charset);
    }


}
