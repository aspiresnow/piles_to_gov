package com.piles.core.util;

import okhttp3.*;
import okhttp3.Request;
import org.apache.commons.collections.CollectionUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class OkHttpUtil {

    private static final OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build();

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private static final String CHARSET_NAME = "UTF-8";

    /**
     * 不会开启异步线程
     *
     * @param request
     * @return
     * @throws IOException
     */
    public static Response execute(Request request) throws IOException {
        return mOkHttpClient.newCall(request).execute();
    }

    /**
     * 开启异步线程访问网络
     *
     * @param request
     * @param responseCallback
     */
    public static void enqueue(Request request, Callback responseCallback) {
        mOkHttpClient.newCall(request).enqueue(responseCallback);
    }

    /**
     * 开启异步线程访问网络，且不在意返回结果（实现空callback）
     *
     * @param request
     */
    public static void enqueue(Request request) {
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }

    public static String getStringFromServer(String url) throws IOException {
        return getStringFromServer(url, null);
    }

    public static String getStringFromServer(String url, List<NameValuePair> headerList) throws IOException {
        Request.Builder builder = new Request.Builder().url(url);
        Request request = buildHeaderList(builder,headerList).build();
        Response response = execute(request);
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code" + response);
        }
    }

    /**
     * 这里使用了HttpClient的API，只是为了方便
     *
     * @param params
     * @return
     */
    public static String formatParams(List<BasicNameValuePair> params) {
        return URLEncodedUtils.format(params, CHARSET_NAME);
    }

    /**
     * 为HttpGet的url方便的添加多个name value参数
     *
     * @param url
     * @param params
     * @return
     */
    public static String attachHttpGetParam(String url, List<BasicNameValuePair> params) {
        return url + "?" + formatParams(params);
    }

    /**
     * 为HttpGet的url方法方便的添加1个name value参数
     *
     * @param url
     * @param name
     * @param value
     * @return
     */
    public static String attachHttpGetParam(String url, String name, String value) {
        return url + "?" + name + "=" + value;
    }

    public static String post(String url, String json, List<NameValuePair> headerList) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);

        Request.Builder builder = new Request.Builder()
                .url(url)
                .post(body);
        Request request = buildHeaderList(builder,headerList).build();

        Response response = mOkHttpClient.newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

    private static Request.Builder buildHeaderList(Request.Builder builder,List<NameValuePair> headerList){
        if (CollectionUtils.isNotEmpty(headerList)) {
            for (NameValuePair pair : headerList) {
                builder.header(pair.getName(), pair.getValue());
            }
        }
        return builder;
    }

    public static String post(String url, String json) throws IOException {
        return post(url, json, null);
    }

}
