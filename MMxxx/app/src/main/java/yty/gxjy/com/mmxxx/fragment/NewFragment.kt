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
import yty.gxjy.com.mmxxx.Activity.LolPicActivity
import yty.gxjy.com.mmxxx.Bean.PicsBean
import yty.gxjy.com.mmxxx.Constans
import yty.gxjy.com.mmxxx.Interface.RecyclerItemClick
import yty.gxjy.com.mmxxx.R
import yty.gxjy.com.mmxxx.Util.OkHttpUtils
import yty.gxjy.com.mmxxx.Util.Utils
import yty.gxjy.com.mmxxx.databinding.NewBinding

class NewFragment : BaseFragment() ,RecyclerItemClick{

    private var  picsBean:PicsBean? = null
    override fun onClick(position: Int) {
        val intent = Intent(activity,LolPicActivity().javaClass)
        intent.putExtra("pdId",picsBean?.data!![position].pdId)
        intent.putExtra("title",picsBean?.data!![position].title)
        intent.putExtra("collectNum",picsBean?.data!![position].collectNum)
        activity.startActivity(intent)
    }
    val instance by lazy { this } //这里使用了委托，表示只有使用到instance才会执行该段代码
    private var handler : Handler = @SuppressLint("HandlerLeak")
    object : Handler(){
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            if(msg?.what == 1){
                 picsBean = msg.obj as PicsBean
                val code:Int =  picsBean!!.code
                if(code==0){
                    binding?.data =picsBean
                    binding?.listener =instance
                }else{
                    val errorMsg:String =picsBean!!.msg
                    Utils.toast(activity,errorMsg)
                }
            }else{
                Utils.toast(activity,"请求错误！")
            }
        }
    }

    var binding: NewBinding? = null
    override fun setLayout(inflater: LayoutInflater?, container: ViewGroup?): View?{
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_new, container, false)
        return binding?.root
    }
    override fun initView() {
//        var list = listOf<Int>(R.drawable.p1, R.drawable.p2,
//                R.drawable.p3, R.drawable.p4, R.drawable.p1, R.drawable.p2,
//                R.drawable.p3, R.drawable.p4)
//        binding?.data =list
    }
    override fun initData() {
        OkHttpUtils.getInstance().getData(activity, Constans.getPics,
                FormBody.Builder().add("orderType","1").add("tagName","").add("pageNo","1").
                        build(), PicsBean().javaClass,handler)
    }

}
