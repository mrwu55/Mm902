package yty.gxjy.com.mmxxx.fragment


import android.annotation.SuppressLint
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Handler
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import okhttp3.FormBody
import yty.gxjy.com.mmxxx.Activity.*
import yty.gxjy.com.mmxxx.Bean.LoginBean
import yty.gxjy.com.mmxxx.Constans
import yty.gxjy.com.mmxxx.Interface.MmClickListener
import yty.gxjy.com.mmxxx.R
import yty.gxjy.com.mmxxx.Util.OkHttpUtils
import yty.gxjy.com.mmxxx.Util.Utils
import yty.gxjy.com.mmxxx.databinding.MineBinding

class MineFragment : BaseFragment(), MmClickListener {
    var binding: MineBinding? = null
    private var handler : Handler = @SuppressLint("HandlerLeak")
    object : Handler(){
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            if(msg?.what == 1){
                val loginBean: LoginBean = msg.obj as LoginBean
                val code:Int =  loginBean.code
                if(code==0){
                    Constans.uName = loginBean.data.uName
                    Constans.vipName = loginBean.data.mlName
                    startActivity(Intent(activity, MainActivity().javaClass))
                }else{
                    val errorMsg:String =loginBean.msg
                    Utils.toast(activity,errorMsg)
                }
            }else{

            }

        }
    }
    override fun setLayout(inflater: LayoutInflater?, container: ViewGroup?): View?{
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_mine, container, false)
        return binding?.root
    }

    override fun initView() {
        binding?.listener = this
    }

    override fun initData() {
        if(Constans.uName!=null){binding?.mineName!!.text = Constans.uName}
        if(Constans.vipName!=null){
            binding?.mineVipName!!.text = "会员名："+Constans.vipName
            binding?.mineVipName!!.visibility = View.VISIBLE
            binding?.llRecharge!!.visibility = View.GONE
        }
    }
    override fun onClick(view: View) {
        when(view.id){
            R.id.mine_person ->{
                activity.startActivity(Intent(activity,PersonDataActivity().javaClass))
            }
            R.id.mine_tv_exit ->{
                OkHttpUtils.getInstance().getData(activity, Constans.Exit,
                        FormBody.Builder().build(), LoginBean().javaClass,handler)
            }
            R.id.mine_watched ->{
                activity.startActivity(Intent(activity,WatchedActivity().javaClass))
            }
            R.id.mine_updatesoft ->{
                activity.startActivity(Intent(activity,UpdateSoftActivity().javaClass))
            }
            R.id.mine_jihuocode ->{
                activity.startActivity(Intent(activity,InputCodeActivity().javaClass))
            }
            R.id.mine_changeps ->{
                activity.startActivity(Intent(activity,ChangePssActivity().javaClass))
            }
            R.id.mine_connect ->{
                activity.startActivity(Intent(activity,CustomerActivity().javaClass))
            }
            R.id.mine_callback ->{
                activity.startActivity(Intent(activity,CallbackActivity().javaClass))
            }
        }
    }

}
