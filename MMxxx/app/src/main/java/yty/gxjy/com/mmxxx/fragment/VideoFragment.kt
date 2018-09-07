package yty.gxjy.com.mmxxx.fragment


import android.annotation.SuppressLint
import android.databinding.DataBindingUtil
import android.os.Handler
import android.os.Message
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shuyu.gsyvideoplayer.GSYVideoManager
import okhttp3.FormBody
import yty.gxjy.com.mmxxx.Bean.BaseBean
import yty.gxjy.com.mmxxx.Bean.VideoBean
import yty.gxjy.com.mmxxx.Constans
import yty.gxjy.com.mmxxx.Interface.VideoCollectListener
import yty.gxjy.com.mmxxx.R
import yty.gxjy.com.mmxxx.Util.OkHttpUtils
import yty.gxjy.com.mmxxx.Util.Utils
import yty.gxjy.com.mmxxx.View.AdapterWrapper
import yty.gxjy.com.mmxxx.View.SwipeToLoadHelper
import yty.gxjy.com.mmxxx.adapter.VideoRecyclerAdapter
import yty.gxjy.com.mmxxx.databinding.VideoBinding
import yty.gxjy.com.mmxxx.rollpager.RollPagerView



class VideoFragment : BaseFragment(),SwipeRefreshLayout.OnRefreshListener,
        SwipeToLoadHelper.LoadMoreListener, VideoCollectListener {


    private  var swipeAdapter: VideoRecyclerAdapter? = null
    private var adapterWrapper: AdapterWrapper? = null
    private var swipeRefresh: SwipeRefreshLayout? = null
    private var recycler: RecyclerView? = null
    private  var helper: SwipeToLoadHelper? = null
    private var page =1
    private var collectPosition =0
    private var listData:MutableCollection<VideoBean.DataBean> = ArrayList()

    private var _firstItemPosition = -1
    private var _lastItemPosition: Int = 0
    private var binding: VideoBinding? = null
    private var mViewPager: RollPagerView? = null
    private var mView:View? = null
    private var videoBean:VideoBean? = null
    private var isInit:Boolean = true
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
                        if(isInit){
                            swipeRefresh?.visibility = View.VISIBLE
                            isInit = false
                        }
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
                        if(code==1){
                            helper?.setSwipeToLoadEnabled(false)
                        }
                        val errorMsg:String =videoBean!!.msg
                        Utils.toast(activity,errorMsg)
                    }
                }
                3 ->{
                    val baseBean: BaseBean = msg.obj as BaseBean
                    val code = baseBean.code
                    if(code==0||code==1){
                        swipeAdapter?.setMapDatas(collectPosition,true)
                        val viewHolder = recycler?.findViewHolderForAdapterPosition(collectPosition)
                        if (viewHolder != null && viewHolder is VideoRecyclerAdapter.ViewHolder) {
                            val itemHolder:VideoRecyclerAdapter.ViewHolder =  viewHolder
                                    itemHolder.bindings.isCollect =true
                        }
                    }
                    val msg:String =baseBean.msg
                    Utils.toast(activity,msg)
                }
                4 ->{
                    val baseBean: BaseBean = msg.obj as BaseBean
                    val code = baseBean.code
                    if(code==0||code==1){
                        swipeAdapter?.setMapDatas(collectPosition,false)
                        val viewHolder = recycler?.findViewHolderForAdapterPosition(collectPosition)
                        if (viewHolder != null && viewHolder is VideoRecyclerAdapter.ViewHolder) {
                            val itemHolder:VideoRecyclerAdapter.ViewHolder =  viewHolder
                            itemHolder.bindings.isCollect =false
                        }
                    }
                    val msg:String =baseBean.msg
                    Utils.toast(activity,msg)
                }
                404 ->{
                    helper?.setSwipeToLoadEnabled(false)
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
        swipeRefresh?.visibility = View.GONE
        recycler = binding?.videoRecycler
        val pool = RecyclerView.RecycledViewPool()
        pool.setMaxRecycledViews(0, 10)
        recycler?.setRecycledViewPool(pool)
        recycler?.setLayoutManager(LinearLayoutManager(context))
        swipeAdapter = VideoRecyclerAdapter(listData,context,this)
        adapterWrapper = AdapterWrapper(swipeAdapter)
        helper = SwipeToLoadHelper(recycler,adapterWrapper)
        recycler?.adapter = adapterWrapper
        swipeRefresh?.setOnRefreshListener(this)
        helper?.setLoadMoreListener(this)
        recycler?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView?.layoutManager as LinearLayoutManager
                //获取最后一个可见view的位置
                val lastItemPosition = layoutManager.findLastVisibleItemPosition()
                //获取第一个可见view的位置
                val firstItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (GSYVideoManager.instance().playPosition >= 0) {
                    //当前播放的位置
                    val position = GSYVideoManager.instance().playPosition
                    //对应的播放列表TAG
                    if ((position < firstItemPosition || position > lastItemPosition)) {
                        if (GSYVideoManager.isFullState(activity)) {
                            return
                        }
                        Log.e("GSYVideoManager","滑出去了")
                        //如果滑出去了上面和下面就是否，和今日头条一样
                        GSYVideoManager.releaseAllVideos()
                        adapterWrapper?.notifyDataSetChanged()
                    }
//                    if (_firstItemPosition < firstItemPosition) {
//                        _firstItemPosition = firstItemPosition
//                        _lastItemPosition = lastItemPosition
//                        GSYVideoManager.releaseAllVideos()
//                        adapterWrapper?.notifyDataSetChanged()
//
//                    }else if  (_lastItemPosition > lastItemPosition){
//                        _firstItemPosition = firstItemPosition
//                        _lastItemPosition = lastItemPosition
//                        GSYVideoManager.releaseAllVideos()
//                        adapterWrapper?.notifyDataSetChanged()
//                    }
                }
            }
        })
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

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if(hidden){
            Log.e("onHiddenChanged","onHiddenChanged")
            GSYVideoManager.releaseAllVideos()
        }
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

    override fun onPause() {
        super.onPause()
        GSYVideoManager.onPause()
    }

    override fun onResume() {
        super.onResume()
        GSYVideoManager.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        GSYVideoManager.releaseAllVideos()
    }
    override fun onClick(dataBean: VideoBean.DataBean, position: Int,isCollect:Boolean) {
        collectPosition = position
        collectOrCancel(dataBean.vdId,isCollect)
    }
    fun collectOrCancel(viId:String,isCollect:Boolean){
        var url =Constans.collectVideo
        var code =3
        if(isCollect){
            url=Constans.cancelVideo
            code =4
        }
        OkHttpUtils.getInstance().getDataWithHandCode(activity, url,
                FormBody.Builder().add("vdId",viId).
                        build(), BaseBean().javaClass,handler,code)

    }
}
