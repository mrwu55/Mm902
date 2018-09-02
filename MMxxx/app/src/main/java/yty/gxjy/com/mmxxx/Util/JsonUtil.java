package yty.gxjy.com.mmxxx.Util;

import com.alibaba.fastjson.JSON;

/**
 * Created by WuJingCheng
 * on 2018/2/9.
 */
public class JsonUtil {
    public static <T> T getJsonBean(Class<T> tClass, String response){
        if(response==null) return null;
        try {
            return  JSON.parseObject(response,tClass);

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
