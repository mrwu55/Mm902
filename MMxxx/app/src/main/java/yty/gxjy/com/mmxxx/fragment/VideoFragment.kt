package yty.gxjy.com.mmxxx.fragment


import android.annotation.SuppressLint
import android.databinding.DataBindingUtil
import android.os.Handler
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_video.view.*
import okhttp3.FormBody
import yty.gxjy.com.mmxxx.Bean.PicsBean
import yty.gxjy.com.mmxxx.Constans
import yty.gxjy.com.mmxxx.R
import yty.gxjy.com.mmxxx.Util.OkHttpUtils
import yty.gxjy.com.mmxxx.Util.Utils
import yty.gxjy.com.mmxxx.adapter.VideoPagerAdapter
import yty.gxjy.com.mmxxx.databinding.VideoBinding
import yty.gxjy.com.mmxxx.rollpager.RollPagerView

class VideoFragment : BaseFragment() {
    private var binding: VideoBinding? = null
    private var mViewPager: RollPagerView? = null
    private var mView:View? = null
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
                R.layout.fragment_video, container, false)
        mView = binding?.root
        return mView
    }
    override fun initView(){
        var listImg = listOf(R.drawable.v1,R.drawable.v2,R.drawable.v3)
        mViewPager = mView?.video_rollpager
        mViewPager?.setAdapter(VideoPagerAdapter(mViewPager!!,listImg))
        Utils.logError("onResume","走了")
    }
    override fun initData() {
        OkHttpUtils.getInstance().getData(activity, Constans.getVideo,
                FormBody.Builder().add("orderType","1").add("tagName","").add("pageNo","1").
                        build(), PicsBean().javaClass,handler)
    }

}
