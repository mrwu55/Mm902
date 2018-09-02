package yty.gxjy.com.mmxxx.Bean;

import android.databinding.BaseObservable;
import android.view.WindowManager;

/**
 * Created by WuJingCheng on 2018/7/16.
 */

public class MainBean extends BaseObservable {
    private String user;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
