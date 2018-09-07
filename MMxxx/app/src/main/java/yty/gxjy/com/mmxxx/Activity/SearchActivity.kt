package yty.gxjy.com.mmxxx.Activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Handler
import android.os.Message
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView.OnEditorActionListener
import com.shuyu.gsyvideoplayer.GSYVideoManager
import okhttp3.FormBody
import yty.gxjy.com.mmxxx.Bean.BaseBean
import yty.gxjy.com.mmxxx.Bean.SearchBean
import yty.gxjy.com.mmxxx.Constans
import yty.gxjy.com.mmxxx.Interface.MmClickListener
import yty.gxjy.com.mmxxx.Interface.SearchClickListener
import yty.gxjy.com.mmxxx.Interface.SearchCollectlistener
import yty.gxjy.com.mmxxx.R
import yty.gxjy.com.mmxxx.Util.OkHttpUtils
import yty.gxjy.com.mmxxx.Util.Utils
import yty.gxjy.com.mmxxx.View.AdapterWrapper
import yty.gxjy.com.mmxxx.View.SwipeToLoadHelper
import yty.gxjy.com.mmxxx.adapter.SearchRecyclerAdapter
import yty.gxjy.com.mmxxx.databinding.SearchClass


class SearchActivity : BaseActivity(),MmClickListener, SearchClickListener,
        SwipeToLoadHelper.LoadMoreListener, SearchCollectlistener {



    private var listData:MutableCollection<SearchBean.DataBean> = ArrayList()
    private  var swipeAdapter: SearchRecyclerAdapter? = null
    private  var searchMsg: String? = null
    private var adapterWrapper:AdapterWrapper? = null
    private var swipeRefresh:SwipeRefreshLayout? = null
    private var recycler:RecyclerView? = null
    private  var helper:SwipeToLoadHelper? = null
    private var page =1
    private var serachBean:SearchBean? = null
    private var isInit:Boolean = true
    private var collectPosition =0
    val instance by lazy { this }
    private var handler : Handler = @SuppressLint("HandlerLeak")
    object : Handler(){
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            if(page!=1){
               helper?.setLoadMoreFinish()
            }
            when(msg?.what){
                2 ->{
                    serachBean = msg.obj as SearchBean
                    val code:Int =  serachBean?.code!!
                    if(code==0){
                        swipeAdapter?.setDatas(serachBean?.data!!,page==1)
                        if(page==1) {recycler?.scrollToPosition(0)}
                        adapterWrapper?.notifyDataSetChanged()
                        if(isInit){
                            swipeRefresh?.visibility = View.VISIBLE
                            isInit = false
                        }
                    }else{
                        if(code==1){
                            searchMsg = null
                            helper?.setSwipeToLoadEnabled(false)
                        }
                        val errorMsg:String =serachBean?.msg!!
                        Utils.toast(this@SearchActivity,errorMsg)
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
                    Utils.toast(this@SearchActivity,msg)
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
                    Utils.toast(this@SearchActivity,msg)
                }
            }

        }
    }
    private var editSearch:EditText? = null
    override fun onClick(view: View) {
    }
    private var binding: SearchClass?=null
    override fun initView() {
        binding = DataBindingUtil.
                setContentView(this, R.layout.activity_search)
        binding?.listener = this
        editSearch = binding?.editSearch
        recycler = binding?.recyclerSearch
        swipeRefresh = binding?.swipeRefreshSearch
        swipeRefresh?.visibility = View.GONE
        swipeRefresh?.setEnabled(false)
        val pool = RecyclerView.RecycledViewPool()
        pool.setMaxRecycledViews(0, 10)
        recycler?.setRecycledViewPool(pool)
        recycler?.setLayoutManager(LinearLayoutManager(this))
//        recycler?.layoutManager = GridLayoutManager(this, 2)
        swipeAdapter = SearchRecyclerAdapter(listData,this,this,this)
        adapterWrapper = AdapterWrapper(swipeAdapter)
        helper = SwipeToLoadHelper(recycler,adapterWrapper)
        recycler?.adapter = adapterWrapper
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
                        if (GSYVideoManager.isFullState(this@SearchActivity)) {
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
        editSearch?.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                var imm:InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(this.getCurrentFocus()
                        .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS)
                val msg = v.text.toString()
                if(msg!=null){
                    page=1
                    searchMsg = msg
                    getData("1")
                }
                true
            }
            false
        })
    }

    private fun getData(s: String) {
        if(searchMsg==null) return
        OkHttpUtils.getInstance().getDataWithHandCode(this, Constans.getSearch,
                FormBody.Builder().add("searchType","3").
                        add("keyWords",searchMsg).add("pageNo",s).
                        build(), SearchBean().javaClass,handler,2)
    }

    override fun onLoad() {
        page++
        getData(page.toString())
    }
    override fun onClick(dataBean: SearchBean.DataBean, position: Int) {
        val intent = Intent(this,LolPicActivity().javaClass)
        intent.putExtra("pdId",dataBean.id)
        intent.putExtra("title",dataBean.title)
        intent.putExtra("collectNum",dataBean.collectNum)
        startActivity(intent)
    }

    override fun onStop() {
        super.onStop()
        GSYVideoManager.releaseAllVideos()
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
        OkHttpUtils.getInstance().getDataWithHandCode(this, url,
                FormBody.Builder().add("vdId",viId).
                        build(), BaseBean().javaClass,handler,code)

    }
}
