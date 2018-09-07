package yty.gxjy.com.mmxxx;

import android.util.Log;

/**
 * Created by WuJingCheng
 * on 2018/8/26.
 */

public class Constans {
    public static final String URL = "http://39.108.155.228/mztAppMng/";
    public static final String LOGIN = URL+"users/usersLogin";
    public static final String Exit = URL+"users/signOut";
    public static final String getPics = URL+"users/getPics";
    public static final String getPicsDetails = URL+"users/getPicDetails";
    public static final String getVideo= URL+"users/getVideos";
    public static final String collectionPic= URL+"users/collectionPic";
    public static final String cancelCollectionPic= URL+"users/cancelCollectionPic";
    public static final String getSearch= URL+"users/getSearchResult";
    public static final String updatePass= URL+"users/updatePass";
    public static final String getCollect= URL+"users/getCollectionResult";
    public static final String collectVideo= URL+"users/collectionVideo";
    public static final String cancelVideo= URL+"users/cancelCollectionVideo";
    public static String Session = null;
    public static String uName =null;
    public static String vipName =null;
    public static void showLogCompletion(String log,int showCount) {
        if (log.length() > showCount) {
            String show = log.substring(0, showCount);
//          System.out.println(show);
            Log.e("TAG", show + "");
            if ((log.length() - showCount) > showCount) {//剩下的文本还是大于规定长度
                String partLog = log.substring(showCount, log.length());
                showLogCompletion(partLog, showCount);
            } else {
                String surplusLog = log.substring(showCount, log.length());
//              System.out.println(surplusLog);
                Log.e("TAG", surplusLog + "");
            }

        } else {
//          System.out.println(log);
            Log.e("TAG", log + "");
        }
    }
}
