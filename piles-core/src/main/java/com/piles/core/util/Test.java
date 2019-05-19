package com.piles.core.util;

import lombok.Data;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class Test {
    /*
    参数签名采用HMAC-MD5算法，采用MD5	作为散列函数，通过签名密钥（SigSecret）对整个消息主体进行加密，
    然后采用Md5信息摘要的方式形成新的密文，参数签名要求大写。严格区分大小写。
    参数签名顺序按照消息体顺序拼接后执行，拼接顺序为运营商标识（operatorID）、参数内容（Data）、时间戳（TimeStamp）、自增序列（Seq）。
     */

    /*
        1)	在签名密钥（SigSecret）后面添加0来创建一个长为64字节的字符型(str)；
        2)	将上一步生成的字符型(str)与ipad(0x36)做异或运算，形成结果字符型(istr)；
        3)将消息内容data附加到第二步的结果字符型(istr)的末尾；
        4)做md5运算于第三步生成的数据流(istr)；
        5)	将第一步生成的字符型(str)与opad(0x5c)做异或运算，形成结果字符型(ostr)；
        6)再将第四步的结果(istr)附加到第五步的结果字符型(ostr)的末尾；
        7)做md5运算于第六步生成的数据流(ostr)，输出最终结果(out)。
         */

    /*
    签名密钥：1234567890abcdef(不传)
    运营商标识（operatorID）：123456789
    参数信息（data）：{"lastQueryTime":"2017-04-01 12:23:21"}
    时间戳（timeStamp）：2017050142400
    自增序列（seq）：0001
    签名（sig）：84D78B494560B1347DD209F948946DB0
     */
    /*
    {
      "operatorID": "123456789",
      "data": {
        "lastQueryTime": “2017-04-01 12:23:21”
      },
      "timeStamp": "2017050142400",
      "seq": "0001",
      "sig": "84D78B494560B1347DD209F948946DB0"
    }

     */

    public static void main(String[] args) throws IllegalAccessException {
        String secretKey ="1234567890abcdef";
        Request<Map<String, String>> request = new Request<Map<String, String>>();
        request.setOperatorID("123456789");
        Map map = new HashMap();
        map.put("lastQueryTime", "2017-04-01 12:23:21");
        request.setData(map);
        request.setTimeStamp("2017050142400");
        request.setSeq("0001");
        JSONUtil jsonUtil = new JSONUtil();
        JSONObject jsonObject = jsonUtil.obj2JSONObj(request);
        String data = jsonObject.toString();
        String hmacMd5Str = HMacMD5.getHmacMd5Str(secretKey, data);
        System.out.println(hmacMd5Str);
    }
    /*public static void main(String[] args) throws Exception {
        String secretKey ="1234567890abcdef";
        String str = StringUtils.rightPad(secretKey, 64, "0");
        int ipad = 0x36;
        String istr = xor(str, ipad);
        String data = "{\"operatorID\": \"123456789\",\"data\": {\"lastQueryTime\": “2017-04-01 12:23:21”},\"timeStamp\": \"2017050142400\",\"seq\": \"0001\"}";
        String hmacMd5Str = HMacMD5.getHmacMd5Str(secretKey, data);
//        String sign = SignUtils.sign(data, str);
        istr = istr.concat(data);
        istr = md5(istr);

        int opad = 0x5c;
        String ostr = xor(str, opad);
        ostr = ostr.concat(istr);
        String out = md5(ostr);
        System.out.println(out);


    }
*/
    public static String xor(String para,int key){
        char[] charArray = para.toCharArray();
        for(int i =0;i<charArray.length;i++){
            charArray[i]=(char)(charArray[i]^key);
        }
        return String.valueOf(charArray);
    }
    public static String md5(String plainText) {
        //67429bb2fd66b9f6631950e185441c0d
        //67429bb2fd66b9f6631950e185441c0d
        //定义一个字节数组
        byte[] secretBytes = null;
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            //对字符串进行加密
            md.update(plainText.getBytes());
            //获得加密后的数据
            secretBytes = md.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有md5这个算法！");
        }
        //将加密后的数据转换为16进制数字
        String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
        // 如果生成数字未满32位，需要前面补0
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }
}

@Data
class Request<T>{
    private String operatorID;
    private T data;
    private String timeStamp;
    private String seq;
//    private String sig;
}
