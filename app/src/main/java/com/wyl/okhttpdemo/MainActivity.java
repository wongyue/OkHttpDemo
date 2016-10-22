package com.wyl.okhttpdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.wyl.okhttpdemo.http.OKhttpUtils;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
        String url = "http://api2.hichao.com/stars?category=%E5%85%A8%E9%83%A8&pin=&ga=%2Fstars&flag=&gv=63&access_token=&gi=862949022047018&gos=5.2.3&p=2013022&gc=xiaomi&gn=mxyc_adr&gs=720x1280&gf=android&page=2";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
        }

        public void getMsg(View view) {
                OKhttpUtils.getInstance().OKForJsonOb(url, new OKhttpUtils.JsonObCallback() {
                        @Override
                        public void result(int code, JSONObject result) {

                        }

                        @Override
                        public void result(JSONObject result) {
                                TestUtils.sys(result.toString());
                        }
                });
        }

}
