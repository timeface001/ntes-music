package com.fs.ntes.utils;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.commons.codec.binary.Hex;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;

/**
 * @author fengsong
 * @description:一句话描述下类的功能
 * @time 2017-07-06 8:52
 **/
public class CaptureUtils {


    public static void searchSinger(String name) {
        try {
            //私钥，随机16位字符串（自己可改）
            String secKey = "cd859f54539b24b7";
            String text = "{\"username\": \"\", \"rememberLogin\": \"true\", \"password\": \"\"}";
            String modulus = "00e0b509f6259df8642dbc35662901477df22677ec152b5ff68ace615bb7b725152b3ab17a876aea8a5aa76d2e417629ec4ee341f56135fccf695280104e0312ecbda92557c93870114af6c9d05c4f7f0c3685b7a46bee255932575cce10b424d813cfe4875d3e82047b97ddef52741d546b8e289dc6935b3ece0462db0a22b8e7";
            String nonce = "0CoJUm6Qyw8W8jud";
            String pubKey = "010001";
            //2次AES加密，得到params
            String params = EncryptTools.encrypt(EncryptTools.encrypt(text, nonce), secKey);
            StringBuffer stringBuffer = new StringBuffer(secKey);
            //逆置私钥
            secKey = stringBuffer.reverse().toString();
            String hex = Hex.encodeHexString(secKey.getBytes());
            BigInteger bigInteger1 = new BigInteger(hex, 16);
            BigInteger bigInteger2 = new BigInteger(pubKey, 16);
            BigInteger bigInteger3 = new BigInteger(modulus, 16);
            //RSA加密计算
            BigInteger bigInteger4 = bigInteger1.pow(bigInteger2.intValue()).remainder(bigInteger3);
            String encSecKey = Hex.encodeHexString(bigInteger4.toByteArray());
            //字符填充
            encSecKey = EncryptTools.zfill(encSecKey, 256);
            //评论获取
            //Document document = Jsoup.connect("http://music.163.com/weapi/v1/resource/comments/R_SO_4_437859519/");
            //System.out.println("评论：" + document.text());
            Document response = Jsoup.connect("http://music.163.com/weapi/artist/top").cookie("appver", "1.5.2")
                    .header("Referer", "http://music.163.com/").data("params", params).data("encSecKey", encSecKey)
                    .ignoreContentType(true).post();
            System.out.println(response.text());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void commentAPI() throws Exception {
        //私钥，随机16位字符串（自己可改）
        String secKey = "cd859f54539b24b7";
        String text = "{\"username\": \"\", \"rememberLogin\": \"true\", \"password\": \"\"}";
        String modulus = "00e0b509f6259df8642dbc35662901477df22677ec152b5ff68ace615bb7b725152b3ab17a876aea8a5aa76d2e417629ec4ee341f56135fccf695280104e0312ecbda92557c93870114af6c9d05c4f7f0c3685b7a46bee255932575cce10b424d813cfe4875d3e82047b97ddef52741d546b8e289dc6935b3ece0462db0a22b8e7";
        String nonce = "0CoJUm6Qyw8W8jud";
        String pubKey = "010001";
        //2次AES加密，得到params
        String params = EncryptTools.encrypt(EncryptTools.encrypt(text, nonce), secKey);
        StringBuffer stringBuffer = new StringBuffer(secKey);
        //逆置私钥
        secKey = stringBuffer.reverse().toString();
        String hex = Hex.encodeHexString(secKey.getBytes());
        BigInteger bigInteger1 = new BigInteger(hex, 16);
        BigInteger bigInteger2 = new BigInteger(pubKey, 16);
        BigInteger bigInteger3 = new BigInteger(modulus, 16);
        //RSA加密计算
        BigInteger bigInteger4 = bigInteger1.pow(bigInteger2.intValue()).remainder(bigInteger3);
        String encSecKey = Hex.encodeHexString(bigInteger4.toByteArray());
        //字符填充
        encSecKey = EncryptTools.zfill(encSecKey, 256);
        //http://music.163.com/weapi/v1/resource/comments/R_SO_4_326904/
        //评论获取
        Document document = Jsoup.connect("http://music.163.com/weapi/artist/top/").cookie("appver", "1.5.2")
                .header("Referer", "http://music.163.com/").data("offset","10").data("params", params).data("encSecKey", encSecKey)
                .ignoreContentType(true).post();
        System.out.println("评论：" + document.text());
    }

    public static void loginAPI(String username, String password) throws Exception {
        password = EncryptTools.md5(password);
        // 私钥，随机16位字符串（自己可改）
        String secKey = "cd859f54539b24b7";
        String text = "{\"username\": \"" + username + "\", \"rememberLogin\": \"true\", \"password\": \"" + password
                + "\"}";
        String modulus = "00e0b509f6259df8642dbc35662901477df22677ec152b5ff68ace615bb7b725152b3ab17a876aea8a5aa76d2e417629ec4ee341f56135fccf695280104e0312ecbda92557c93870114af6c9d05c4f7f0c3685b7a46bee255932575cce10b424d813cfe4875d3e82047b97ddef52741d546b8e289dc6935b3ece0462db0a22b8e7";
        String nonce = "0CoJUm6Qyw8W8jud";
        String pubKey = "010001";
        // 2次AES加密，得到params
        String params = EncryptTools.encrypt(EncryptTools.encrypt(text, nonce), secKey);
        StringBuffer stringBuffer = new StringBuffer(secKey);
        // 逆置私钥
        secKey = stringBuffer.reverse().toString();
        String hex = Hex.encodeHexString(secKey.getBytes());
        BigInteger bigInteger1 = new BigInteger(hex, 16);
        BigInteger bigInteger2 = new BigInteger(pubKey, 16);
        BigInteger bigInteger3 = new BigInteger(modulus, 16);
        // RSA加密计算
        BigInteger bigInteger4 = bigInteger1.pow(bigInteger2.intValue()).remainder(bigInteger3);
        String encSecKey = Hex.encodeHexString(bigInteger4.toByteArray());
        // 字符填充
        encSecKey = EncryptTools.zfill(encSecKey, 256);
        // 登录请求
        Document document = Jsoup.connect("http://music.163.com/weapi/login/cellphone/").cookie("appver", "1.5.2")
                .header("Referer", "http://music.163.com/").data("params", params).data("encSecKey", encSecKey)
                .ignoreContentType(true).post();
        System.out.println("登录结果：" + document.text());
    }

    public static void testUserHttpUnit() throws FailingHttpStatusCodeException,
            MalformedURLException, IOException {

        /** HtmlUnit请求web页面 */
        WebClient wc = new WebClient(BrowserVersion.CHROME);
        wc.getOptions().setUseInsecureSSL(true);
        wc.getOptions().setJavaScriptEnabled(true); // 启用JS解释器，默认为true
        wc.getOptions().setCssEnabled(false); // 禁用css支持
        wc.getOptions().setThrowExceptionOnScriptError(false); // js运行错误时，是否抛出异常
        wc.getOptions().setTimeout(100000); // 设置连接超时时间 ，这里是10S。如果为0，则无限期等待
        wc.getOptions().setDoNotTrackEnabled(false);


        HtmlPage page = wc
                .getPage("http://music.163.com/#/search/m/?id=6452&s=%E5%91%A8%E6%9D%B0%E4%BC%A6&type=1");


        DomNodeList<DomElement> links = page.getElementsByTagName("b");

        for (DomElement link : links) {
            System.out
                    .println(link.asText() + "  " + link.getAttribute("title"));
        }
    }


    public static void main(String[] args) throws Exception {
        /*Document document = Jsoup.connect("http://music.163.com/api/search/get/").cookie("appver", "1.5.2")
                .header("Referer", "http://music.163.com/").data("s", "周杰伦").data("limit", "10").data("type","1")
                .ignoreContentType(true).post();
        System.out.println(document.body());*/
  //http://music.163.com/weapi/artist/top
       /*Document document = Jsoup.connect("http://music.163.com/api/song/lyric?os=pc&id=93920&lv=-1&kv=-1&tv=-1").cookie("appver", "1.5.2")
                .header("Referer", "http://music.163.com/")
                .ignoreContentType(true).get();
        System.out.println(document.body());
        Jsoup.parse("").getAllElements();*/
       //commentAPI();

       Document document = Jsoup.connect("http://music.163.com/api/artist/top?limit=1&offset=3&type=1000").cookie("appver", "1.5.2")
                .header("Referer", "http://music.163.com/")
                .ignoreContentType(true).get();
        System.out.println(document.body());
        Document documen1t = Jsoup.connect("http://music.163.com/api/artist/top?limit=1&offset=3&type=1").cookie("appver", "1.5.2")
                .header("Referer", "http://music.163.com/")
                .ignoreContentType(true).get();
        System.out.println(documen1t.body());


    }




}
