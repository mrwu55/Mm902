package yty.gxjy.com.mmxxx.Util;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import yty.gxjy.com.mmxxx.Constans;

/**
 * Created by WuJingCheng
 * on 2018/2/9.
 */
public class OkHttpUtils {
   public static OkHttpUtils okHttpUtils;
    public static OkHttpClient client ;
    private  OkHttpUtils(){
        client = new OkHttpClient();
        // 初始化okhttp 创建一个OKHttpClient对象，一个app里最好实例化一个此对象
        client.newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS);
    }
    public  static OkHttpUtils getInstance(){
        if (okHttpUtils==null){
            synchronized (OkHttpUtils.class){
                if (okHttpUtils==null){
                    okHttpUtils = new OkHttpUtils();
                }
            }
        }
        return okHttpUtils;
    }

    public  <T> void getData(final Activity activity, String url, RequestBody requestBody,
                                   final Class<T> tClass, final Handler handler){
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Content-Type","application/x-www-form-urlencoded; charset=utf-8")
                .addHeader("cookie",Constans.Session==null?"":Constans.Session)
                .post(requestBody)
                .build();
        Call call =client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
              activity.runOnUiThread(new Runnable() {
                  @Override
                  public void run() {
                      Utils.INSTANCE.toast(activity,"请求失败");
                  }
              });
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String msg = response.body().string();
                Log.e("response",msg);
                System.out.println(msg);
                if(response.isSuccessful()){
                    Message message = new Message();
                    message.what = 1;
                    message.obj = JsonUtil.getJsonBean(tClass,msg);
                    handler.sendMessage(message);
                    if(Constans.Session==null){//获取session的操作
                        try {
                            Headers headers = response.headers();
                            Log.d("info_headers", "header " + headers);
                            List<String> cookies = headers.values("Set-Cookie");
                            String session = cookies.get(0);
                            Log.d("info_cookies", "onResponse-size: " + cookies);
                            Constans.Session = session.substring(0, session.indexOf(";"));
                            Log.e("info_s", "session is  :" + Constans.Session);
                        }catch (Exception r){

                        }

                    }

                }else {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(activity,"请求错误",Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
    }
    public  <T> void getDataWithHandCode(final Activity activity, String url, RequestBody requestBody,
                                         final Class<T> tClass, final Handler handler, final int handlecode){
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Content-Type","application/x-www-form-urlencoded; charset=utf-8")
                .addHeader("cookie",Constans.Session==null?"":Constans.Session)
                .post(requestBody)
                .build();
        Call call =client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Utils.INSTANCE.toast(activity,"请求失败");
                    }
                });
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String msg = response.body().string();
                Log.e("response",msg);
                if(response.isSuccessful()){
                    Message message = new Message();
                    message.what = handlecode;
                    message.obj = JsonUtil.getJsonBean(tClass,msg);
                    handler.sendMessage(message);
                }else {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(activity,"请求错误",Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
    }
}
