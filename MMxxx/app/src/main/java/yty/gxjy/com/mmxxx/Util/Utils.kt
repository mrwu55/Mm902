package yty.gxjy.com.mmxxx.Util
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import yty.gxjy.com.mmxxx.R






@SuppressLint("StaticFieldLeak")
/**
 * Created by WuJingCheng on 2018/7/16.
 */
object Utils{
    private var toast: Toast?=null
    private var tv:TextView? = null
    fun logError(key:String,value:String){
             Log.e(key,value)
     }
    fun toast(context: Activity?, str:String ){
        if(toast==null){
            toast = Toast(context)
            val views = View.inflate(context, R.layout.toast_lay,null)
            toast?.view =views
            tv = views.findViewById(R.id.tv_toast_msg)
            val wm = context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            //获得屏幕的宽度
            val width = wm.defaultDisplay.width/2
            val layoutParams = LinearLayout.LayoutParams(width,
                    width/3)
            tv!!.setLayoutParams(layoutParams)
            toast?.setDuration(Toast.LENGTH_SHORT)
//            tv?.text = str
            toast?.setGravity(Gravity.CENTER,0,0);
        }else{
            toast?.setDuration(Toast.LENGTH_SHORT)
        }
        tv?.text = str
        toast?.show()
    }
}