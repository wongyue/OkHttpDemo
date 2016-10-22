package com.wyl.okhttpdemo.http;

import android.os.Handler;
import android.os.Looper;

import com.wyl.okhttpdemo.StringUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created on 16/10/22.
 * 简单封装一个okhttp的联网请求类
 *
 * @auther Mr.Wong
 */
public class OKhttpUtils {

        private OkHttpClient client;
        private volatile static OKhttpUtils maneger;
        private Handler mHandler;

        //提交json数据(发起请求的请求体是Json字符串)
        private static final MediaType JSON = MediaType.parse("application/json;charset=utf-8");
        //提交字符串（发起请求的请求体为字符串）
        private static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown;charset=utf-8");

        private OKhttpUtils() {
                client = new OkHttpClient();
                mHandler = new Handler(Looper.getMainLooper());
        }

        /**
         * 采用单例模式获取对象
         *
         * @return
         */
        public static OKhttpUtils getInstance() {
                OKhttpUtils instance = null;
                if (maneger == null) {
                        synchronized (OKhttpUtils.class) {
                                if (instance == null) {
                                        instance = new OKhttpUtils();
                                        maneger = instance;
                                }
                        }
                }
                return instance;
        }
        /***********************************************************************************************************************/

        /**
         * 带参数请求结果为 json字符串
         *
         * @param url
         * @param params   请求的参数map
         * @param callback
         */
        public void OKForJsonStr(String url, Map<String, String> params, final JsonStrCallback callback) {
                //表单对象，包含以input开始的对象，以html表单为主
                FormBody.Builder builder = new FormBody.Builder();
                if (params != null && !params.isEmpty()) {
                        for (Map.Entry<String, String> entry : params.entrySet()) {
                                builder.add(entry.getKey(), entry.getValue());
                        }
                }

                RequestBody requestBody = builder.build();
                // 创建请求
                final Request request = new Request.Builder().url(url).patch(requestBody).build();
                client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                                e.printStackTrace();
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                                //此处需要请求返回的结果进行判断
                                if (response != null && response.isSuccessful()) {
                                        //调用方法
                                        resultJSONStr(response.body().string(), callback);
                                }
                        }
                });
        }

        /**
         * 无参数请求结果为 json字符串
         *
         * @param url
         * @param callback
         */
        public void OKForJsonStr(String url, final JsonStrCallback callback) {
                Request request = new Request.Builder().url(url).build();
                client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                                e.printStackTrace();
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                                if (response != null && response.isSuccessful()) {
                                        resultJSONStr(response.body().string(), callback);
                                }
                        }
                });
        }
        /***********************************************************************************************************************/

        /**
         * 带参数请求结果为 json对象
         *
         * @param url
         * @param params   请求的参数map
         * @param callback
         */
        public void OKForJsonOb(String url, Map<String, String> params, final JsonObCallback callback) {
                //表单对象，包含以input开始的对象，以html表单为主
                FormBody.Builder builder = new FormBody.Builder();
                if (params != null && !params.isEmpty()) {
                        for (Map.Entry<String, String> entry : params.entrySet()) {
                                builder.add(entry.getKey(), entry.getValue());
                        }
                }

                RequestBody requestBody = builder.build();
                // 创建请求
                final Request request = new Request.Builder().url(url).patch(requestBody).build();
                client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                                e.printStackTrace();
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                                //此处需要请求返回的结果进行判断
                                if (response != null && response.isSuccessful()) {
                                        //调用方法
                                        resultJSONOb(response.body().string(), callback);
                                }
                        }
                });
        }

        /**
         * 无参数请求结果为 json对象
         *
         * @param url
         * @param callback
         */
        public void OKForJsonOb(String url, final JsonObCallback callback) {
                Request request = new Request.Builder().url(url).build();
                client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                                e.printStackTrace();
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                                if (response != null && response.isSuccessful()) {
                                        resultJSONOb(response.body().string(), callback);
                                }
                        }
                });
        }
        /***********************************************************************************************************************/

        /**
         * 带参数请求结果为 json数组
         *
         * @param url
         * @param params   请求的参数map
         * @param callback
         */
        public void OKForJsonArr(String url, Map<String, String> params, final JsonArrCallback callback) {
                //表单对象，包含以input开始的对象，以html表单为主
                FormBody.Builder builder = new FormBody.Builder();
                if (params != null && !params.isEmpty()) {
                        for (Map.Entry<String, String> entry : params.entrySet()) {
                                builder.add(entry.getKey(), entry.getValue());
                        }
                }

                RequestBody requestBody = builder.build();
                // 创建请求
                final Request request = new Request.Builder().url(url).patch(requestBody).build();
                client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                                e.printStackTrace();
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                                //此处需要请求返回的结果进行判断
                                if (response != null && response.isSuccessful()) {
                                        //调用方法
                                        resultJSONArr(response.body().string(), callback);
                                }
                        }
                });
        }

        /**
         * 无参数请求结果为 json数组
         *
         * @param url
         * @param callback
         */
        public void OKForJsonArr(String url, final JsonArrCallback callback) {
                Request request = new Request.Builder().url(url).build();
                client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                                e.printStackTrace();
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                                if (response != null && response.isSuccessful()) {
                                        resultJSONArr(response.body().string(), callback);
                                }
                        }
                });
        }

        /***********************************************************************************************************************/

        /**
         * 向服务器提交String请求
         * @param url
         * @param content
         * @param callBack
         */
        public void sendStringByPostMethod(String url,String content,final JsonStrCallback callBack){
                Request request = new Request.Builder().url(url).post(RequestBody.create(MEDIA_TYPE_MARKDOWN,content)).build();
                client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                                e.printStackTrace();
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                                if (response!=null&&response.isSuccessful()){
                                        resultJSONStr(response.body().string(),callBack);
                                }
                        }
                });
        }

        /***********************************************************************************************************************/

        /**
         * 返回值为Json‘字符串的回调
         */
        public interface JsonStrCallback {
                void result(int code, String result);
        }

        /**
         * 返回值为JSONObject的回调
         */
        public interface JsonObCallback {
                void result(int code, JSONObject result);

                void result(JSONObject result);
        }

        /**
         * 返回值为JSONOArray的回调
         */
        public interface JsonArrCallback {
                void result(int code, JSONArray result);
        }

        /**
         * 请求返回结果为Json字符串
         *
         * @param s
         * @param callback
         */
        private void resultJSONStr(final String s, final JsonStrCallback callback) {
                mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                                if (callback != null) {
                                        JSONObject object = getJsobjectByJsString(s);
                                        // 字段用 statu  和 result 是由于我这边的接口 的返回字段是这样，可以根据接口做相应的变化
                                        callback.result(getIntByName("statu", object), getValueByName("result", object));
                                }
                        }
                });
        }

        /**
         * 请求返回结果为Json对象
         *
         * @param s
         * @param callback
         */
        private void resultJSONOb(final String s, final JsonObCallback callback) {
                mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                                if (callback != null) {
                                        JSONObject object = getJsobjectByJsString(s);
//                                        callback.result(getIntByName("statu", object), getJSONObject("result", object));
                                        callback.result(object);
                                }
                        }
                });
        }


        /**
         * 请求返回结果为Json数组
         *
         * @param s
         * @param callback
         */
        private void resultJSONArr(final String s, final JsonArrCallback callback) {
                mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                                if (callback != null) {
                                        JSONObject object = getJsobjectByJsString(s);
                                        callback.result(getIntByName("statu", object), getJSONArray("result", object));
                                }
                        }
                });
        }


        /**
         * 将json字符串转换为jsobject
         *
         * @param js
         * @return
         */
        private JSONObject getJsobjectByJsString(String js) {
                if (StringUtils.strIsNull(js)) {
                        return null;
                }
                try {
                        return new JSONObject(js);
                } catch (JSONException e) {
                        return null;
                }

        }

        /**
         * 根据给定的名字充jsobject中解析出对应的int值
         *
         * @param name
         * @param object
         * @return
         */
        private int getIntByName(String name, JSONObject object) {
                if (StringUtils.strIsNull(name) || object == null) {
                        return -1;
                }
                try {
                        return object.getInt(name);
                } catch (JSONException e) {
                        return -1;
                }
        }


        /**
         * 根据给定的名字充jsobject中解析出对应的值
         *
         * @param name
         * @param object
         * @return
         */
        private String getValueByName(String name, JSONObject object) {
                if (StringUtils.strIsNull(name) || object == null) {
                        return null;
                }
                try {
                        return object.getString(name);
                } catch (JSONException e) {
                        return null;
                }
        }


        /**
         * 根据给定的名字 object中的jsobject.
         *
         * @param name
         * @param object
         * @return
         */
        public JSONObject getJSONObject(String name, JSONObject object) {
                if (object == null || StringUtils.strIsNull(name)) {
                        return null;
                }
                try {
                        return object.getJSONObject(name);
                } catch (JSONException e) {
                        return null;
                }

        }


        /**
         * 充jsobject中解析出对应名字的jsarray
         *
         * @param name   jsarray的名字
         * @param object jsarray所在的jaonobject
         * @return
         */
        public JSONArray getJSONArray(String name, JSONObject object) {
                if (StringUtils.strIsNull(name) || object == null) {
                        return null;
                }
                try {
                        return object.getJSONArray(name);
                } catch (JSONException e) {
                        return null;
                }

        }

}
