package yty.gxjy.com.mmxxx.fragment


import android.annotation.SuppressLint
import android.databinding.DataBindingUtil
import android.os.Handler
import android.os.Message
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
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
import yty.gxjy.com.mmxxx.View.AdapterWrapper
import yty.gxjy.com.mmxxx.View.SwipeToLoadHelper
import yty.gxjy.com.mmxxx.adapter.SwipeRecyclerAdapter
import yty.gxjy.com.mmxxx.adapter.VideoPagerAdapter
import yty.gxjy.com.mmxxx.adapter.VideoRecyclerAdapter
import yty.gxjy.com.mmxxx.databinding.VideoBinding
import yty.gxjy.com.mmxxx.rollpager.RollPagerView

class VideoFragment : BaseFragment(),SwipeRefreshLayout.OnRefreshListener, SwipeToLoadHelper.LoadMoreListener {

    private  var swipeAdapter: VideoRecyclerAdapter? = null
    private var adapterWrapper: AdapterWrapper? = null
    private var swipeRefresh: SwipeRefreshLayout? = null
    private var recycler: RecyclerView? = null
    private  var helper: SwipeToLoadHelper? = null
    private var page =1

    private var listData:MutableCollection<VideoBean.DataBean> = ArrayList()

    private var binding: VideoBinding? = null
    private var mViewPager: RollPagerView? = null
    private var mView:View? = null
    private var videoBean:VideoBean? = null
    private var handler : Handler = @SuppressLint("HandlerLeak")
    object : Handler(){
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            when(msg?.what){
                1 ->{
                    if(swipeRefresh?.isRefreshing!!){
                        swipeRefresh?.isRefreshing = false
                    }
                    helper?.setSwipeToLoadEnabled(true)
                    videoBean = msg.obj as VideoBean
                    val code:Int =  videoBean!!.code
                    if(code==0){
                        swipeAdapter?.setDatas(videoBean?.data!!,true)
                        adapterWrapper?.notifyDataSetChanged()
                    }else{
                        val errorMsg:String =videoBean!!.msg
                        Utils.toast(activity,errorMsg)
                    }
                }
                2 ->{
                    videoBean = msg.obj as VideoBean
                    val code:Int =  videoBean!!.code
                    swipeRefresh?.setEnabled(true)
                    helper?.setLoadMoreFinish()
                    if(code==0){
                        swipeAdapter?.setDatas(videoBean?.data!!,false)
                        adapterWrapper?.notifyDataSetChanged()
                    }else{
                        val errorMsg:String =videoBean!!.msg
                        Utils.toast(activity,errorMsg)
                    }
                }
                404 ->{
                    swipeRefresh?.isRefreshing = false
                    helper?.setLoadMoreFinish()
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
//        var listImg = listOf(R.drawable.v1,R.drawable.v2,R.drawable.v3)
//        var view = layoutInflater.inflate(R.layout.video_pager_lay,null)
//        mViewPager = view.video_rollpager
//        mViewPager?.setAdapter(VideoPagerAdapter(mViewPager!!,listImg))


        swipeRefresh = binding?.swipeRefreshVideo
        recycler = binding?.videoRecycler
        val pool = RecyclerView.RecycledViewPool()
        pool.setMaxRecycledViews(0, 10)
        recycler?.setRecycledViewPool(pool)
        recycler?.setLayoutManager(LinearLayoutManager(context))
        swipeAdapter = VideoRecyclerAdapter(listData,activity)
        adapterWrapper = AdapterWrapper(swipeAdapter)
        helper = SwipeToLoadHelper(recycler,adapterWrapper)
        recycler?.adapter = adapterWrapper
        swipeRefresh?.setOnRefreshListener(this)
        helper?.setLoadMoreListener(this)

    }
    override fun initData() {

        getData("1",true)

    }

    private fun getData(s: String, b: Boolean) {
        var code =2
        if(b){
            code=1
        }
        OkHttpUtils.getInstance().getDataWithHandCode(activity, Constans.getVideo,
                FormBody.Builder().add("orderType","1").add("tagName","").add("pageNo",s).
                        build(), VideoBean().javaClass,handler,code)
    }
    //刷新
    override fun onRefresh() {
        helper?.setSwipeToLoadEnabled(false)
        getData("1",true)
    }
    //加载更多
    override fun onLoad() {
        page++
        swipeRefresh?.setEnabled(false)
        getData(page.toString(),false)
    }
}
