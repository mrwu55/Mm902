package yty.gxjy.com.mmxxx.Activity

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import yty.gxjy.com.mmxxx.R
import yty.gxjy.com.mmxxx.Util.SystemBarTintManager

abstract class BaseActivity : AppCompatActivity(){
    private var tintManager: SystemBarTintManager? = null
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true)
        }
        tintManager = SystemBarTintManager(this)
        tintManager?.setStatusBarTintEnabled(true)
        tintManager?.setNavigationBarTintEnabled(true)
        tintManager?.setTintResource(R.color.f13c5d)
        initView()
        initData()
    }
    abstract fun initView() //初始化view
    abstract fun initData() //数据操作
    @TargetApi(19)
    private fun setTranslucentStatus(on: Boolean) {
        val win = window
        val winParams = win.attributes
        val bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }
}
