package yty.gxjy.com.mmxxx

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.tencent.bugly.Bugly
import com.tencent.bugly.beta.Beta

/**
 * Created by WuJingCheng on 2018/7/16.
 */

class App : Application() {
    companion object {
        @JvmStatic lateinit var app: App
            private set
    }
    override fun onCreate() {
        super.onCreate()
        app = this
        var resources = this.getResources()
        var dm = resources.getDisplayMetrics()
        var density = dm.density
        var widht=dm.widthPixels

        Bugly.init(this, "8d339c122f", false)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(base)
        // 安装tinker
        Beta.installTinker()
    }
}
