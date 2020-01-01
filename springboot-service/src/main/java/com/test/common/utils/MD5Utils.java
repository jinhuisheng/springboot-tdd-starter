package com.test.common.utils;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MD5Utils {

    public static String md5(String string) {
        if(string==null||string.trim().length()==0){
            return null;
        }
        try {
            return getMD5(string.getBytes("GBK"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getMD5(byte[] source) {
        String s = null;
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            md.update(source);
            byte tmp[] = md.digest();
            char str[] = new char[16 * 2];
            int k = 0;
            for (int i = 0; i < 16; i++) {
                byte byte0 = tmp[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            s = new String(str);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }


    public static void main(String[] args) {

        Map<String, String> paramsMap = new HashMap<String, String>();
        //paramsMap.put("partnerId", configUtil.getMdfrontServerPartnerId()) ;
        paramsMap.put("device_no", "4111170899070441");
        paramsMap.put("auth_code", "134560561123581652");
        paramsMap.put("total_fee", String.valueOf(1));
        paramsMap.put("goods_desc", "goods desc");
        paramsMap.put("pp_trade_no", "17a241534308b412f");
        paramsMap.put("bill_create_ip", "192.168.43.14");
        paramsMap.put("nonce_str", "123456");
        String signKey ="aaaf476d424fb60906e9be01fa69a992";
        System.out.println("md5String="+getMD5String(paramsMap,signKey));
    }

    public static String getMDFrontServerSign(Map<String, String> params, String signKey){
        if(params == null || params.isEmpty()){
            return null;
        }

        Map<String,String> map = new TreeMap<String,String>(params);
        String querys = "";
        for(String key : map.keySet()){
            String value = map.get(key);
            if(value == null){
                value = "";
            }
            querys += (key + "=" + value + "&");

        }
        if(querys.endsWith("&")){
            querys = querys.substring(0, querys.length()-1);
        }

        if(querys.equals("")){
            return "";
        }

        querys+=signKey;

        return md5(querys).toUpperCase();
    }

    public static String getMD5String(Map<String, String> params, String signKey){
        if(params == null || params.isEmpty()){
            return null;
        }

        Map<String,String> map = new TreeMap<String,String>(params);
        String querys = "";
        for(String key : map.keySet()){
            String value = map.get(key);
            if(value == null){
                value = "";
            }
            querys += (key + "=" + value + "&");
        }
        if(querys.endsWith("&")){
            querys = querys.substring(0, querys.length()-1);
        }
        if(querys.equals("")){
            return "";
        }

        querys+="&key=" + signKey;
        return md5(querys).toUpperCase();
    }
}
