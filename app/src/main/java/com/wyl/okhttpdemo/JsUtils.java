package com.wyl.okhttpdemo;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 处理与js字符串相关操作的类
 * Created by Administrator on 2016/3/30.
 */
public class JsUtils {

        /**
         * 将js字符串转换为jsobject
         *
         * @param js
         * @return
         */
        public static JSONObject getJsobjectByJsString(String js) {
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
         * 根据给定的positon 解析jsarray中的jsobject.
         *
         * @param jsonArray
         * @param position
         * @return
         */
        public static JSONObject getJsobjectByPosition(JSONArray jsonArray, int position) {
                if (jsonArray == null || position < 0) {
                        return null;
                }

                try {
                        return jsonArray.getJSONObject(position);
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
        public static JSONObject getJsobjectByName(String name, JSONObject object) {
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
         * 根据给定的名字充jsobject中解析出对应的值
         *
         * @param name
         * @param object
         * @return
         */
        public static String getValueByName(String name, JSONObject object) {
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
         * 根据给定的名字充jsobject中解析出对应的值
         *
         * @param name
         * @param object
         * @return
         */
        public static String getPhoneValueByName(String name, JSONObject object) {
                String values = JsUtils.getValueByName(name, object);
                if (StringUtils.strIsNull(values)) {
                        return null;
                }

                if (values.length() != 11) {
                        return null;
                }

                return values.substring(0, 3) + "****" + values.substring(7);
        }


        /**
         * 根据给定的名字充jsobject中解析出对应的int值
         *
         * @param name
         * @param object
         * @return
         */
        public static int getIntByName(String name, JSONObject object) {
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
         * 根据给定的jsarray返回其长度。如果jsarray为null就返回0；
         *
         * @param jsonArray
         * @return
         */
        public static int getSizeFromJsArray(JSONArray jsonArray) {

                if (jsonArray == null) {
                        return 0;
                }

                return jsonArray.length();

        }


        /**
         * 该方法用于向jsobject中添加字符串的数据
         *
         * @param object
         * @param name
         * @param value
         */
        public static void putJsobjectString(JSONObject object, String name, String value) {
                if (object == null || StringUtils.strIsNull(name) || StringUtils.strIsNull(value)) {
                        return;
                }

                try {
                        object.put(name, value);
                } catch (JSONException e) {

                }

        }


        /**
         * 该方法用于向jsobject中添加字符串的数据
         *
         * @param object
         * @param name
         * @param value
         */
        public static void putJsobjectLong(JSONObject object, String name, long value) {
                if (object == null || StringUtils.strIsNull(name)) {
                        return;
                }

                try {
                        object.put(name, value);
                } catch (JSONException e) {

                }

        }


        /**
         * 该方法用于向jsobject中添加字符串的数据
         *
         * @param object
         * @param name
         * @param value
         */
        public static void putJsobjectInt(JSONObject object, String name, int value) {
                if (object == null || StringUtils.strIsNull(name)) {
                        return;
                }

                try {
                        object.put(name, value);
                } catch (JSONException e) {

                }

        }


        /**
         * 根据输入的类型获取到result
         *
         * @param s    js字符串
         * @param type result的类型  jsobject 为 ob  jsarray 为 arr string 为 s
         */
        public static Object getResult(String s, String type) {
                if (StringUtils.strIsNull(s) || StringUtils.strIsNull(type)) {
                        return null;
                }
                JSONObject object = JsUtils.getJsobjectByJsString(s);
                if (object == null) {
                        return null;
                }

                int code = JsUtils.getIntByName("statu", object);
                if (code < 0) {
                        return null;
                }
                if (code > 0) {
                        return null;
                }
                switch (type) {
                        case "ob":
                                return JsUtils.getJsobjectByName("result", object);

                        case "arr":
                                return JsUtils.getjsonArrayByName("result", object);

                        case "s":
                                return JsUtils.getValueByName("result", object);

                        default:
                                break;
                }

                return null;
        }


        /**
         * 根据输入的类型获取到result,用reListener返回数据
         *
         * @param s          js字符串
         * @param type       result的类型  jsobject 为 ob  jsarray 为 arr string 为 s
         * @param reListener 回调
         * @return
         */
/*    public static void getResult(String s, String type, ReListener reListener) {
        if (reListener == null) {
            return;
        }

        if (StringUtils.strIsNull(s)) {
            reListener.result(HttpConfig.weberr, HttpConfig.weberrmsg);
            return;
        }
        if (StringUtils.strIsNull(type)) {
            reListener.result(0, s);
            return;
        }

        JSONObject object = JsUtils.getJsobjectByJsString(s);
        if (object == null) {
            reListener.result(HttpConfig.weberr, HttpConfig.weberrmsg);
            return;
        }

        int code = JsUtils.getIntByName("statu", object);
        if (code < 0) {
            reListener.result(HttpConfig.weberr, HttpConfig.weberrmsg);
            return;
        }
        if (code > 0) {
            reListener.result(code, JsUtils.getJsobjectByName("result", object));
            reListener.cacheResult(code, null,s);
            return;
        }

        switch (type) {
            case "ob":
                reListener.result(code, JsUtils.getJsobjectByName("result", object));
                break;
            case "arr":
                reListener.result(code, JsUtils.getjsonArrayByName("result", object));
                break;
            case "s":
                reListener.result(code, JsUtils.getValueByName("result", object));
                break;
            default:
                break;
        }
        reListener.cacheResult(code, null,s);


    }*/


        /**
         * 根据输入的类型获取到result,用reListener返回数据
         *
         * @param s          js字符串
         * @param type       result的类型  jsobject 为 ob  jsarray 为 arr string 为 s
         * @param reListener 回调
         * @return
         */
/*    public static boolean getCacheResult(String s, String type,ReListener reListener) {
        if (reListener == null) {
            return false;
        }

        if (StringUtils.strIsNull(s)) {
            return reListener.resultCache(HttpConfig.weberr, HttpConfig.weberrmsg);
        }
        if (StringUtils.strIsNull(type)) {

            return reListener.resultCache(0, s);
        }

        JSONObject object = JsUtils.getJsobjectByJsString(s);
        if (object == null) {

            return reListener.resultCache(HttpConfig.weberr, HttpConfig.weberrmsg);
        }

        int code = JsUtils.getIntByName("statu", object);
        if (code < 0) {

            return reListener.resultCache(HttpConfig.weberr, HttpConfig.weberrmsg);
        }
        if (code > 0) {

            return reListener.resultCache(code, JsUtils.getValueByName("result", object));
        }
        boolean b = false;
        switch (type) {
            case "ob":
                b = reListener.resultCache(code, JsUtils.getJsobjectByName("result", object));
                break;
            case "arr":
                b = reListener.resultCache(code, JsUtils.getjsonArrayByName("result", object));
                break;
            case "s":
                b = reListener.resultCache(code, JsUtils.getValueByName("result", object));
                break;
            default:
                break;
        }
       return b;

    }*/


        /**
         * 充jsobject中解析出对应名字的jsarray
         *
         * @param name   jsarray的名字
         * @param object jsarray所在的jaonobject
         * @return
         */
        public static JSONArray getjsonArrayByName(String name, JSONObject object) {
                if (StringUtils.strIsNull(name) || object == null) {
                        return null;
                }

                try {
                        return object.getJSONArray(name);
                } catch (JSONException e) {
                        return null;
                }

        }


        /**
         * 根据下标得到对应的字符串
         *
         * @param array
         * @param position
         * @return
         */
        public static String getStringByJsArryPosition(JSONArray array, int position) {
                if (array == null || position < 0) {
                        return null;
                }

                if (position >= array.length()) {

                        return null;
                }
                String path = null;

                try {
                        path = String.valueOf(array.get(position));
                } catch (JSONException e) {
                        return path;
                }

                return path;

        }


        /**
         * 根据传递的S返回用于保存到数据库的jsarray。
         *
         * @param s
         * @return
         */
        public static JSONArray getWebJsarrayByString(String url, String s) {
                JSONArray jsonArray = new JSONArray();
                JSONObject object = new JSONObject();
                try {
                        object.put(url, s);
                        object.put("time", System.currentTimeMillis());
                        jsonArray.put(object);
                } catch (JSONException e) {
                        return null;
                }

                return jsonArray;

        }

        /**
         * 把通过接口中的得到的jsonArray，全部添加到 List<JSONObject> list 中，准备进行后续的数据添加到布局中
         *
         * @param jsonArray
         * @return
         */
        public static List<JSONObject> changJsArrayToList(JSONArray jsonArray) {
                if (jsonArray == null) {
                        return null;
                }
                List<JSONObject> list = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = JsUtils.getJsobjectByPosition(jsonArray, i);
                        if (object != null) {
                                list.add(object);
                        }
                }

                return list;
        }


        /**
         * 得到long型数据
         *
         * @param name
         * @param jsonObject
         * @return
         */
        public static Long getLongByName(String name, JSONObject jsonObject) {
                if (jsonObject == null) {
                        return -1L;
                }
                try {

                        return jsonObject.getLong(name);

                } catch (JSONException e) {

                        return -1L;

                }
        }

        /**
         * 根据传递的数据提取出正常的数据
         *
         * @param jsonArray
         * @return
         */
        public static JSONArray getNormalArray(String[] par, JSONArray jsonArray) {
                JSONArray array = new JSONArray();
                if (par == null || jsonArray == null) {
                        return array;
                }
                for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = JsUtils.getJsobjectByPosition(jsonArray, i);
                        if (object == null) {
                                continue;
                        }

                        for (String aPar : par) {

                                String values = JsUtils.getValueByName(aPar, object);
                                if (StringUtils.strIsNull(values)) {
                                        object = null;
                                        break;
                                }

                        }

                        if (object != null) {
                                array.put(object);
                        }

                }

                return array;
        }

        /**
         * 将jsonArray1添加到jsonarray
         *
         * @param jsonArray  添加的目标
         * @param jsonArray1 需要添加的项目
         */
        public static void putJsonArrayToJsonArray(JSONArray jsonArray, JSONArray jsonArray1) {
                if (jsonArray1 == null) {
                        return;
                }
                if (jsonArray == null) {
                        return;
                }

                for (int i = 0; i < jsonArray1.length(); i++) {
                        JSONObject object = JsUtils.getJsobjectByPosition(jsonArray1, i);
                        if (object != null) {
                                jsonArray.put(object);
                        }
                }
        }

        /**
         * 将addJSONArray添加到JSONArray中
         *
         * @param addJSONArray 需要添加的array
         * @param JSONArray    添加到的array
         */
        public static void addJSONArrayToJSONArray(JSONArray addJSONArray, JSONArray JSONArray) {
                if (addJSONArray == null || JSONArray == null) {
                        return;
                }

                for (int i = 0; i < addJSONArray.length(); i++) {
                        JSONObject object = JsUtils.getJsobjectByPosition(addJSONArray, i);
                        if (object == null) {
                                continue;
                        }
                        JSONArray.put(object);
                }
        }


        /**
         * 将jsonarray中的key值转换为list中的数据
         *
         * @param jsonObject
         * @return
         */
        public static List<String> getKeyToList(JSONObject jsonObject) {
                List<String> list = new ArrayList<>();
                if (jsonObject == null) {
                        return list;
                }
                Iterator<String> iterator = jsonObject.keys();
                while (iterator.hasNext()) {
                        list.add(iterator.next());
                }
                return list;
        }

        /**
         * 将字符串转换为jsonarry
         *
         * @param strrp
         * @return
         */
        public static JSONArray getjsonArrayByString(String strrp) {
                if (strrp == null) {
                        return null;
                }

                try {
                        return new JSONArray(strrp);
                } catch (JSONException e) {
                        return null;
                }
        }
}
