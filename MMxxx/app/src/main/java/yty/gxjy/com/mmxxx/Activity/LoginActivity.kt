package yty.gxjy.com.mmxxx.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Handler
import android.os.Message
import android.view.View
import okhttp3.FormBody
import yty.gxjy.com.mmxxx.Bean.LoginBean
import yty.gxjy.com.mmxxx.Constans
import yty.gxjy.com.mmxxx.Interface.MmClickListener
import yty.gxjy.com.mmxxx.R
import yty.gxjy.com.mmxxx.Util.OkHttpUtils
import yty.gxjy.com.mmxxx.Util.Utils
import yty.gxjy.com.mmxxx.databinding.LoginClass

class LoginActivity : BaseActivity(), MmClickListener {
    private var binding : LoginClass? = null
    private var handler :Handler = @SuppressLint("HandlerLeak")
    object : Handler(){
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            if(msg?.what == 1){
                val loginBean:LoginBean = msg.obj as LoginBean
                val code:Int =  loginBean.code
                if(code==0){
                    Constans.uName = loginBean.data.uName
                    Constans.vipName = loginBean.data.mlName
                    startActivity(Intent(this@LoginActivity, MainActivity().javaClass))
                    finish()
                }else{
                    val errorMsg:String =loginBean.msg
                    Utils.toast(this@LoginActivity,errorMsg)
                    startActivity(Intent(this@LoginActivity, MainActivity().javaClass))
                    finish()
                }
            }else{
                startActivity(Intent(this@LoginActivity, MainActivity().javaClass))
                finish()
            }

        }
    }
    override fun onClick(view: View) {
        val phone:String = binding?.loginEditPhone!!.text.toString()
        val pss:String = binding?.loginEditPss!!.text.toString()
        OkHttpUtils.getInstance().getData(this, Constans.LOGIN,
                FormBody.Builder().add("uName",phone).add("uPass",pss).build(), LoginBean().javaClass,handler)
    }

    override fun initData() {
    }

    override fun initView() {
        binding = DataBindingUtil.
                setContentView(this, R.layout.activity_login)
        binding?.listener = this
    }

}
