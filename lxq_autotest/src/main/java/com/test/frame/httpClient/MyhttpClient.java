package com.test.frame.httpClient;


import com.google.common.base.Preconditions;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *  以下所有接口来自于json文件：
 *  startupWithCookies.json
 *  运行时先运行jar包
 *
 *  cd /Users/lvxueqing/OnlyMyCode/autoTest/AutoTest/lxq_autotest/src/main/java/com/test/frame/httpClient
 *  java -jar ./moco-runner-0.11.0-standalone.jar http -p 8899 -c startupWithCookies.json
 *
 */

public class MyhttpClient {

    /**
     * get 方法
     */

    @Test
    public void test1() throws IOException {
        String result; //存放结果
        HttpGet get = new HttpGet("http://www.baidu.com");
        HttpClient client = new DefaultHttpClient(); //用来执行get方法
        HttpResponse response = client.execute(get);
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
    }

    /**
     * get 之后 获取 cookies
     *
     * 使用配置文件存url
     */

    private String url;
    private ResourceBundle bundle;

    @BeforeMethod
    public void beforeMethod(){
        //会自动识别resource下的配置文件
        bundle = ResourceBundle.getBundle("httpClient", Locale.CHINA);
        url = bundle.getString("test.url");

    }

    @Test
    public void test2_getCookies() throws IOException {
        String result;
        HttpGet get = new HttpGet(this.url + bundle.getString("getCookies.uri"));

        //HttpClient client = new DefaultHttpClient(); HttpClient 本身是不能获取cookies信息的， 所以修改一下
        DefaultHttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(get);
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);

        //获取cookies信息
        //CookieStore store = client.getCookieStore();
        this.store = client.getCookieStore();
        List<Cookie> cookieList = store.getCookies();
        for (Cookie ancookie : cookieList){
            String name = ancookie.getName();
            String value = ancookie.getValue();
            System.out.println("cookie name =" + name);
            System.out.println("cookie value =" + value);
        }

    }

    /**
     * get时 传递cookies
     */

    private CookieStore store; // 用来存储cookies信息的变量

    @Test(dependsOnMethods = {"test2_getCookies"})
    public void getWithCookies() throws IOException {
        String result;
        HttpGet get = new HttpGet(this.url + bundle.getString("getWithCookies.uri"));
        DefaultHttpClient client = new DefaultHttpClient();
        //设置cookies信息
        client.setCookieStore(this.store);

        HttpResponse response = client.execute(get);

        //获取响应状态码
        int status = response.getStatusLine().getStatusCode();
        System.out.println("返回状态码 " + status);
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(result);

    }


    /**
     * post 方法
     */


    @Test(dependsOnMethods = {"test2_getCookies"})
    public void post() throws IOException {
        String testurl = this.url + bundle.getString("postWithCookies.uri");
        /**
         * 1。 声明一个client对象， 用来进行方法的执行
         * 2。 声明一个post方法
         * 3。 添加参数
         * 4。 设置请求信息 请求头，参数信息添加到方法
         * 5。 声明对象 进行响应结果的存储
         * 6。 设置cookies信息
         * 7。 执行post
         * 8。 获取响应结果
         * 9。 处理结果
         */

        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(testurl);
        JSONObject param = new JSONObject();
        param.put("name","huhansan");
        param.put("age","18");

        post.setHeader("Content-Type","application/json");
        StringEntity stringEntity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(stringEntity);

        String result;
        client.setCookieStore(this.store);
        HttpResponse response = client.execute(post);
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);

        JSONObject resultjson = new JSONObject(result);
        Preconditions.checkArgument(resultjson.getString("huhansan").equals("success"),"返回值不正确");




    }

}
