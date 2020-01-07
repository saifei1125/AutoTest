package com.taxchina.autotest.crmnew.service.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 编码转换
 */
public class EncodeConvert<T> {
    //转成application/x-www-form-urlencoded
    public String urlEncoder(T encodeStr, String charset){
        String encoderStr =null;
        try {
            if(encodeStr!=null)
            encoderStr = URLEncoder.encode(encodeStr.toString(), charset);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encoderStr;
    }
    //application/x-www-form-urlencoded解码
    public  String urlDecoder(T decodeStr,String charset){
        String decoderStr = null;
        try {
            if(decodeStr!=null)
            decoderStr = URLDecoder.decode(decodeStr.toString(), charset);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return decoderStr;
    }

}
