package yty.gxjy.com.mmxxx

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import io.vov.vitamio.Vitamio

/**
 * Created by WuJingCheng on 2018/7/16.
 */

class App : Application() {
    private var width = 0
    companion object {
        @JvmStatic lateinit var app: App
            private set
    }
    override fun onCreate() {
        super.onCreate()
        app = this
        Vitamio.initialize(this)
        var resources = this.getResources()
        var dm = resources.getDisplayMetrics()
        var density = dm.density
        width=dm.widthPixels

    }
    fun getwidth():Int{
        return width
    }
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }
}
