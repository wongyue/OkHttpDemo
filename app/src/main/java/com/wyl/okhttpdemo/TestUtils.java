package com.wyl.okhttpdemo;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;


import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Log信息和Toast信息的工具类
 * Created by Administrator on 2016/6/2.
 */
public class TestUtils {
        private static boolean islog = true;

        public static void sys(String className, String parmes, String str) {
                if (islog) {
                        Log.i(className, "--------" + parmes + "-------->" + str);
                }
        }

        public static void sys(Class aClass, String str) {
                if (islog) {
                        Log.i(aClass.getSimpleName(), "---------------->" + str);
                }
        }

        public static void sys(String str) {
                if (islog) {
                        Log.i("HoTime", "----------->" + str);
                }
        }

        public static void sys(List<?> list) {
                if (islog) {
                        for (int i = 0; i < list.size(); i++) {
                                Log.i("HoTime", "----------->" + list.get(i).toString());
                        }
                }
        }


        /**
         * 记录当前是否正在显示系统的提示框
         */
        public static boolean isToast = false;

        /**
         * 系统自带的提示框 10秒内不显示提示框
         */
        public static void toastLong(Context context, String s) {
                if (StringUtils.strIsNull(s)) {
                        return;
                }
                if (!isToast) {
                        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
                        isToast = true;
                        new Timer().schedule(new TimerTask() {
                                @Override
                                public void run() {
                                        isToast = false;
                                }
                        }, 3000);
                }
        }


        /**
         * 系统自带的提示框
         */
        public static void toastShort(Context context, String s) {
                Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
        }


}
