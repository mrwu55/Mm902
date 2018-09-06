package yty.gxjy.com.mmxxx.fragment


import android.annotation.SuppressLint
import android.databinding.DataBindingUtil
import android.os.Handler
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_video.view.*
import kotlinx.android.synthetic.main.video_pager_lay.view.*
import okhttp3.FormBody
import yty.gxjy.com.mmxxx.Bean.PicsBean
import yty.gxjy.com.mmxxx.Bean.VideoBean
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
    private var videoBean:VideoBean? = null
    private var handler : Handler = @SuppressLint("HandlerLeak")
    object : Handler(){
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            if(msg?.what == 1){
                videoBean = msg.obj as VideoBean
                val code:Int =  videoBean!!.code
                if(code==0){
                    binding?.videoBean =videoBean
                }else{
                    val errorMsg:String =videoBean!!.msg
                    Utils.toast(activity,errorMsg)
                }
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
        var view = layoutInflater.inflate(R.layout.video_pager_lay,null)
        mViewPager = view.video_rollpager
        mViewPager?.setAdapter(VideoPagerAdapter(mViewPager!!,listImg))
    }
    override fun initData() {
        OkHttpUtils.getInstance().getData(activity, Constans.getVideo,
                FormBody.Builder().add("orderType","1").add("tagName","").add("pageNo","1").
                        build(), VideoBean().javaClass,handler)
    }

}
