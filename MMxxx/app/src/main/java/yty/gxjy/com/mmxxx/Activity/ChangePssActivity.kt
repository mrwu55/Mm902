package yty.gxjy.com.mmxxx.Activity

import android.annotation.SuppressLint
import android.databinding.DataBindingUtil
import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.EditText
import okhttp3.FormBody
import yty.gxjy.com.mmxxx.Bean.BaseBean
import yty.gxjy.com.mmxxx.Constans
import yty.gxjy.com.mmxxx.Interface.MmClickListener
import yty.gxjy.com.mmxxx.R
import yty.gxjy.com.mmxxx.Util.OkHttpUtils
import yty.gxjy.com.mmxxx.Util.Utils
import yty.gxjy.com.mmxxx.databinding.ChangePssClass

class ChangePssActivity : BaseActivity(),MmClickListener {
    private var mEditOldPs:EditText? = null
    private var mEditNewPs:EditText? = null
    private var mEditAgainPs:EditText? = null
    private var binding: ChangePssClass?=null

    private var handler : Handler = @SuppressLint("HandlerLeak")
    object : Handler(){
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            if(msg?.what == 1){
//                val loginBean: LoginBean = msg.obj as LoginBean
//                val code:Int =  loginBean.code
//                if(code==0){
//                    Constans.uName = loginBean.data.uName
//                    Constans.vipName = loginBean.data.mlName
//                    startActivity(Intent(this@SearchActivity, MainActivity().javaClass))
//                    finish()
//                }else{
//                    val errorMsg:String =loginBean.msg
//                    Utils.toast(this@SearchActivity,errorMsg)
//                    startActivity(Intent(this@SearchActivity, MainActivity().javaClass))
//                    finish()
//                }
            }

        }
    }

    override fun initView() {
        binding = DataBindingUtil.
                setContentView(this, R.layout.activity_change_pss)
        binding?.listener = this
        mEditOldPs = binding?.editChangepsOld
        mEditNewPs = binding?.editChangepsNew
        mEditAgainPs = binding?.editChangepsAgain
    }
    override fun initData() {

    }
    override fun onClick(view: View) {
        when(view.id){
            R.id.btn_changeps ->{
                val psOld = mEditOldPs?.text.toString()
                val psNew = mEditNewPs?.text.toString()
                val psAgain = mEditNewPs?.text.toString()
                if(psOld==null){
                    Utils.toast(this,"请输入旧密码!")
                    return
                }
                if(psNew==null){
                    Utils.toast(this,"请输入新密码!")
                    return
                }
                if(psAgain==null){
                    Utils.toast(this,"请再次输入新密码!")
                    return
                }
                if(!psNew.equals(psAgain)){
                    Utils.toast(this,"两次输入不一致!")
                    return
                }
                OkHttpUtils.getInstance().getData(this, Constans.updatePass,
                                    FormBody.Builder().add("oldPass",psOld).
                                    add("newPass",psNew).
                                    build(), BaseBean().javaClass,handler)
            }
            R.id.re_change_back ->{
                finish()
            }
        }
    }
}
