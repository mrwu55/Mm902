package yty.gxjy.com.mmxxx.fragment
import android.annotation.SuppressLint
import android.databinding.DataBindingUtil
import android.os.Handler
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import okhttp3.FormBody
import yty.gxjy.com.mmxxx.Bean.PicsBean
import yty.gxjy.com.mmxxx.Constans
import yty.gxjy.com.mmxxx.R
import yty.gxjy.com.mmxxx.Util.OkHttpUtils
import yty.gxjy.com.mmxxx.Util.Utils
import yty.gxjy.com.mmxxx.databinding.CollectBinding
class CollectFragment : BaseFragment() {
    var binding: CollectBinding? = null
    private var handler : Handler = @SuppressLint("HandlerLeak")
    object : Handler(){
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            if(msg?.what == 1){
//                picsBean = msg.obj as PicsBean
//                val code:Int =  picsBean!!.code
//                if(code==0){
//                    binding?.data =picsBean
//                    binding?.listener =instance
//                }else{
//                    val errorMsg:String =picsBean!!.msg
//                    Utils.toast(activity,errorMsg)
//                }
            }else{
                Utils.toast(activity,"请求错误！")
            }
        }
    }

    override fun setLayout(inflater: LayoutInflater?, container: ViewGroup?): View?{
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_collect, container, false)
        return binding?.root
    }
    override fun initView() {
        var list = listOf<Int>(R.drawable.c1, R.drawable.c2,
                R.drawable.c3, R.drawable.c1, R.drawable.c2, R.drawable.c3,
                R.drawable.c1, R.drawable.c2)
        binding?.data =list
    }
    override fun initData() {
        OkHttpUtils.getInstance().getData(activity, Constans.getVideo,
                FormBody.Builder().add("orderType","1").add("tagName","").
                        build(), PicsBean().javaClass,handler)
    }
}