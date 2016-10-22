package com.wyl.okhttpdemo;

import android.support.annotation.NonNull;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串处理的工具类
 * Created by Administrator .
 */
public class StringUtils {

        /**
         * 判断字符串是否为空
         *
         * @param str
         * @return
         */
        public static boolean strIsNull(String str) {
                return str == null || str.isEmpty() || str.equals("null");
        }


        /**
         * 判断字符串是否为空
         *
         * @param str
         * @return
         */
        public static boolean strIsNull(CharSequence str) {
                return str == null || str.length() == 0 || str.equals("null");
        }

        /**
         * 判断是否是邮箱
         *
         * @param email
         * @return
         */
        public static boolean isEmail(String email) {
                String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
                Pattern p = Pattern.compile(str);
                Matcher m = p.matcher(email);

                return m.matches();
        }


        /**
         * 判断是否全是数字
         *
         * @param str
         * @return
         */
        public static boolean isNumeric(String str) {
                Pattern pattern = Pattern.compile("[0-9]*");
                Matcher isNum = pattern.matcher(str);
                return isNum.matches();
        }

        /**
         * //判断手机格式是否正确
         *
         * @param mobiles
         * @return
         */

        public static boolean isMobileNO(String mobiles) {
                Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(17[0-9])|(18[0-9]))\\d{8}$");
                Matcher m = p.matcher(mobiles);

                return m.matches();
        }

        /**
         * 判断是否是正确的经纬度
         *
         * @param str 需要判断的字符串
         * @return
         */
        public static boolean isLatlng(String str) {
                Pattern pattern = Pattern.compile("^\\d+\\.\\d+\\,\\d+\\.\\d+$");
                Matcher matcher = pattern.matcher(str);
                return matcher.matches();

        }

        /**
         * 得到系统的时间
         *
         * @return 返回的时间的格式 年-月-日
         */
        public static String getDataByCus(long time) {
                SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd", Locale.CHINESE);
                String d = format.format(time);
                return d;
        }

        /**
         * 将字符串转换为int的数据
         * 如果输入的str为空或者不为数字就返回0
         *
         * @param str
         * @return
         */
        public static int getIntByString(String str) {
                if (strIsNull(str)) {
                        return 0;
                }

                if (!StringUtils.isNumeric(str)) {
                        return 0;
                }

                return Integer.valueOf(str);


        }

        /**
         * 获取系统当前时间
         */
        public static String getSysTime() {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                Date curDate = new Date(System.currentTimeMillis());//获取当前时间
                return formatter.format(curDate);
        }

        /**
         * 获取系统当前时间
         */
        public static String getSysDay() {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                Date curDate = new Date(System.currentTimeMillis());//获取当前时间
                return formatter.format(curDate);
        }

//        /**
//         * 返回间隔day天后的时间
//         *
//         * @param day
//         * @return
//         */
//        public static String getDateByAddDay(int day) {
//                Date date = new Date();
//                //格式化时间工具类
//                SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
//                //万年历获取对象
//                Calendar calendar = Calendar.getInstance();
//                //设置时间为传进来的参数
//                calendar.setTime(date);
//                //+100天
//                calendar.add(Calendar.DAY_OF_MONTH, day);
//                //获取+100天后的日期
//                Date tmp = calendar.getTime();
//                //转换为指定下格式
//                String day100 = sdf.format(tmp);
//                //返回
//                return day100;
//
//        }

        /**
         * 将以分为单位的价格的字符串转换为double类型的数据
         *
         * @param price
         * @return
         */
        public static double getPriceByStringZero(String price) {
                if (StringUtils.strIsNull(price)) {
                        return 0d;
                }
                try {
                        return Double.valueOf(price);
                } catch (Exception e) {
                        return 0d;
                }

        }


        /**
         * 将double数据转换为保留两位小数的字符串
         *
         * @param b
         * @return
         */
        public static String getTwoPriceByDouble(double b) {
                java.text.DecimalFormat df = new java.text.DecimalFormat("#.##");
                if (b == 0) {
                        return df.format(b);
                }
                return df.format(b / 100);
        }

        /**
         * 将double数据转换为保留两位小数的字符串
         *
         * @param b
         * @return
         */
        public static String getTwoWayByDouble(double b) {
                java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
                if (b == 0) {
                        return df.format(b);
                }
                return df.format(b);
        }


        /**
         * 判断字符串是不是doouble类型
         *
         * @param string
         * @return
         */
        public static boolean isStringIsDouble(String string) {
                boolean b;
                try {
                        Double.valueOf(string);
                        b = true;
                } catch (Exception e) {
                        b = false;
                }
                return b;
        }


        /**
         * 获取跳转的url
         * 如 拦截的urlhttp://tll.tlf61.com/myapp/venuewap/venue_detail.html?vid=1
         * 跳转的url就为
         * /myapp/venuewap/venue_detail.html
         *
         * @param mainurl app的主链接
         * @param url
         * @return
         */
        public static String getJumpUrl(String mainurl, String url) {

                String jumpString = "";
                if (url.contains(mainurl)) {
                        jumpString = url.substring(mainurl.length());
                }

                if (jumpString.contains("?")) {
                        jumpString = jumpString.substring(0, jumpString.indexOf("?"));
                }

                if (StringUtils.strIsNull(jumpString)) {
                        jumpString = url;
                }

                return jumpString;
        }

        /**
         * 验证身份证号是否符合规则
         *
         * @param text 身份证号
         * @return
         */
        public static boolean isIdCard(String text) {
                String regx = "[0-9]{17}x";
                String reg1 = "[0-9]{15}";
                String regex = "[0-9]{18}";
                return text.matches(regx) || text.matches(reg1) || text.matches(regex);
        }

        /**
         * 将时间戳转换成为常见的时间格式
         *
         * @param s
         * @param x 标记，1：yyyy-MM-dd 2：yyyy-MM-dd HH:mm 3 ：yyyy-MM-dd HH:mm:ss
         * @return
         */
        public static String changeTimeType(String s, int x) {
                String result = null;
                long time;
                try {
                        time = Long.valueOf(s);
                } catch (NumberFormatException e) {
                        return null;
                }

                SimpleDateFormat formatter = null;

                if (x == 1) {
                        formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                }
                if (x == 2) {
                        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
                }
                if (x == 3) {
                        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ", Locale.getDefault());
                }
                if (formatter != null) {
                        result = formatter.format(new Date(time * 1000L));
                }
                return result;
        }


        /**
         * 将传入的url码转换为utf-8的文字
         *
         * @param url 需要转换的url
         * @return 解析后的文字或者解析失败返回null
         */
        public static String urlDecodingUtf8(String url) {
                try {
                        return URLDecoder.decode(url, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                        return "";
                }
        }

        /**
         * 判断url是否包含服务器的主ip，如果不包含就添加主ip.
         *
         * @param mainurl app项目的主链接
         * @param url     需要处理的ip
         * @return null表示url为null, 否则就返回一个包含主ip的url.
         */
        public static String getAllUrl(String mainurl, String url) {
                if (strIsNull(url)) {
                        return null;
                }

                if (url.contains(mainurl)) {
                        return url;
                }
                int first = url.charAt(0);
                int c = '/';
                if (first == c) {
                        return mainurl + url;
                }

                return mainurl + "/" + url;
        }


        /**
         * 检查输入的字符串是否是版本号的格式，版本号的格式为x.x.x
         *
         * @param str 需要检查的字符串
         * @return 返回true表示字符串是版本号的格式 false则表示不是版本号的格式
         */
        public static boolean isVersion(String str) {
                Pattern pattern = Pattern.compile("^\\d+\\.\\d+\\.\\d+$");
                Matcher matcher = pattern.matcher(str);
                return matcher.matches();
        }


        /**
         * MD5加密 截取16位
         */
        public static String getMD5Str(String str) {
                MessageDigest messageDigest = null;

                try {
                        messageDigest = MessageDigest.getInstance("MD5");

                        messageDigest.reset();

                        messageDigest.update(str.getBytes("UTF-8"));
                } catch (NoSuchAlgorithmException e) {
                        System.out.println("NoSuchAlgorithmException caught!");
                        System.exit(-1);
                } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                }

                byte[] byteArray = messageDigest.digest();

                StringBuffer md5StrBuff = new StringBuffer();

                for (int i = 0; i < byteArray.length; i++) {
                        if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
                        else
                                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
                }
                //16位加密，从第9位到25位
                return md5StrBuff.substring(8, 24).toString().toUpperCase();
        }

        /**
         * MD5 加密   32位数
         *
         * @param string
         * @return
         */
        @NonNull
        public static String md5(String string) {
                byte[] hash;
                try {
                        hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
                } catch (NoSuchAlgorithmException e) {
                        throw new RuntimeException("Huh, MD5 should be supported?", e);
                } catch (UnsupportedEncodingException e) {
                        throw new RuntimeException("Huh, UTF-8 should be supported?", e);
                }

                StringBuilder hex = new StringBuilder(hash.length * 2);
                for (byte b : hash) {
                        if ((b & 0xFF) < 0x10) hex.append("0");
                        hex.append(Integer.toHexString(b & 0xFF));
                }
                return hex.toString();
        }

        /**
         * 如果url不包含http://就添加
         *
         * @param url
         * @return
         */
        public static String getHttpUrl(String url) {
                if (strIsNull(url)) {
                        return null;
                }
                if (url.contains("http://") || url.contains("https://")) {
                        return url;
                }
                return "http://" + url;
        }


        /**
         * 将str转换为中间为*的电话号码显示的形式
         *
         * @param str
         * @return
         */
        public static String getPhoneStr(String str) {
                if (StringUtils.strIsNull(str)) {
                        return null;
                }

                if (!StringUtils.isMobileNO(str)) {
                        return null;
                }

                return str.substring(0, 3) + "****" + str.substring(7);
        }
}
