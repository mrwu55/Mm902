package yty.gxjy.com.mmxxx.fragment
import android.annotation.SuppressLint
import android.content.Intent
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
import yty.gxjy.com.mmxxx.Activity.LolPicActivity
import yty.gxjy.com.mmxxx.Bean.BaseBean
import yty.gxjy.com.mmxxx.Bean.SearchBean
import yty.gxjy.com.mmxxx.Constans
import yty.gxjy.com.mmxxx.Interface.SearchClickListener
import yty.gxjy.com.mmxxx.Interface.SearchCollectlistener
import yty.gxjy.com.mmxxx.R
import yty.gxjy.com.mmxxx.Util.OkHttpUtils
import yty.gxjy.com.mmxxx.Util.Utils
import yty.gxjy.com.mmxxx.View.AdapterWrapper
import yty.gxjy.com.mmxxx.View.SwipeToLoadHelper
import yty.gxjy.com.mmxxx.adapter.SearchRecyclerAdapter
import yty.gxjy.com.mmxxx.databinding.CollectBinding
class CollectFragment : BaseFragment(), SwipeToLoadHelper.LoadMoreListener, SearchClickListener,
        SwipeRefreshLayout.OnRefreshListener, SearchCollectlistener {
    private var listData:MutableCollection<SearchBean.DataBean> = ArrayList()
    private  var swipeAdapter: SearchRecyclerAdapter? = null
    private var adapterWrapper: AdapterWrapper? = null
    private var swipeRefresh: SwipeRefreshLayout? = null
    private var recycler: RecyclerView? = null
    private  var helper:SwipeToLoadHelper? = null
    private var page =1
    private var serachBean:SearchBean? = null
    private var isInit:Boolean = true
    var binding: CollectBinding? = null
    private var collectPosition =0
    private var handler : Handler = @SuppressLint("HandlerLeak")
    object : Handler(){
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            if(page!=1){
                helper?.setLoadMoreFinish()
            }
            when(msg?.what){
                1 ->{
                    if(swipeRefresh?.isRefreshing!!){
                        swipeRefresh?.isRefreshing = false
                    }
                    helper?.setSwipeToLoadEnabled(true)
                    serachBean = msg.obj as SearchBean
                    val code:Int =  serachBean?.code!!
                    if(code==0){
                        swipeAdapter?.setDatas(serachBean?.data!!,true)
                        adapterWrapper?.notifyDataSetChanged()
                        if(isInit){
                            swipeRefresh?.visibility = View.VISIBLE
                            isInit = false
                        }
                    }else{
                        if(code==1){
                            page = -1
                        }
                        val errorMsg:String =serachBean?.msg!!
                        Utils.toast(activity,errorMsg)
                    }
                }
                2 ->{
                    swipeRefresh?.setEnabled(true)
                    helper?.setLoadMoreFinish()
                    serachBean = msg.obj as SearchBean
                    val code:Int =  serachBean?.code!!
                    if(code==0){
                        swipeAdapter?.setDatas(serachBean?.data!!,false)
                        adapterWrapper?.notifyDataSetChanged()
                    }else{
                        if(code==1){
                            helper?.setSwipeToLoadEnabled(false)
                        }
                        val errorMsg:String =serachBean?.msg!!
                        Utils.toast(activity,errorMsg)
                    }
                }

                3 ->{
                    val baseBean: BaseBean = msg.obj as BaseBean
                    val code = baseBean.code
                    if(code==0||code==1){
                        swipeAdapter?.setMapDatas(collectPosition,true)
                        val viewHolder = recycler?.findViewHolderForAdapterPosition(collectPosition)
                        if (viewHolder != null && viewHolder is SearchRecyclerAdapter.ViewHolderVideo) {
                            val itemHolder: SearchRecyclerAdapter.ViewHolderVideo =  viewHolder
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
                        if (viewHolder != null && viewHolder is SearchRecyclerAdapter.ViewHolderVideo) {
                            val itemHolder: SearchRecyclerAdapter.ViewHolderVideo =  viewHolder
                            itemHolder.bindings.isCollect =false
                        }
                    }
                    val msg:String =baseBean.msg
                    Utils.toast(activity,msg)
                }

            }


        }
    }

    override fun setLayout(inflater: LayoutInflater?, container: ViewGroup?): View?{
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_collect, container, false)
        return binding?.root
    }
    override fun initView() {
        recycler = binding?.collectRecycler
        swipeRefresh = binding?.swipeRefreshCollect
        swipeRefresh?.visibility = View.GONE
        val pool = RecyclerView.RecycledViewPool()
        pool.setMaxRecycledViews(0, 10)
        recycler?.setRecycledViewPool(pool)
        recycler?.setLayoutManager(LinearLayoutManager(activity))
        swipeAdapter = SearchRecyclerAdapter(listData,this,context!!,this)
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
//
                }
            }
        })
    }
    override fun initData() {
        getData("1",true)
    }
    private fun getData(s: String,isRefresh:Boolean) {
        var code =2
        if(isRefresh){
            code=1
        }
        OkHttpUtils.getInstance().getDataWithHandCode(activity, Constans.getCollect,
                FormBody.Builder().add("pageNo",s).
                        build(), SearchBean().javaClass,handler,code)
    }
    override fun onRefresh() {
        helper?.setSwipeToLoadEnabled(false)
        getData("1",true)
    }
    override fun onLoad() {
        page++
        swipeRefresh?.setEnabled(false)
        getData(page.toString(),false)
    }
    override fun onClick(dataBean: SearchBean.DataBean, position: Int) {
        val intent = Intent(activity, LolPicActivity().javaClass)
        intent.putExtra("pdId",dataBean.id)
        intent.putExtra("title",dataBean.title)
        intent.putExtra("collectNum",dataBean.collectNum)
        startActivity(intent)
    }
    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if(hidden){
            GSYVideoManager.releaseAllVideos()
        }
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
    override fun onClick(dataBean: SearchBean.DataBean, position: Int, isCollect: Boolean) {
        collectPosition = position
        collectOrCancel(dataBean.id,isCollect)
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