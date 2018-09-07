package yty.gxjy.com.mmxxx.Activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Handler
import android.os.Message
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.EditText
import yty.gxjy.com.mmxxx.Interface.MmClickListener
import yty.gxjy.com.mmxxx.R
import yty.gxjy.com.mmxxx.databinding.SearchClass
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView.OnEditorActionListener
import okhttp3.FormBody
import yty.gxjy.com.mmxxx.Bean.PicsBean
import yty.gxjy.com.mmxxx.Bean.SearchBean
import yty.gxjy.com.mmxxx.Constans
import yty.gxjy.com.mmxxx.Interface.RecyclerItemClick
import yty.gxjy.com.mmxxx.Util.OkHttpUtils
import yty.gxjy.com.mmxxx.Util.Utils
import yty.gxjy.com.mmxxx.View.AdapterWrapper
import yty.gxjy.com.mmxxx.View.SwipeToLoadHelper
import yty.gxjy.com.mmxxx.adapter.SearchRecyclerAdapter
import yty.gxjy.com.mmxxx.adapter.SwipeRecyclerAdapter


class SearchActivity : BaseActivity(),MmClickListener, RecyclerItemClick,
        SwipeToLoadHelper.LoadMoreListener{
    private var listData:MutableCollection<SearchBean.DataBean> = ArrayList()
    private  var swipeAdapter: SearchRecyclerAdapter? = null
    private  var searchMsg: String? = null
    private var adapterWrapper:AdapterWrapper? = null
    private var swipeRefresh:SwipeRefreshLayout? = null
    private var recycler:RecyclerView? = null
    private  var helper:SwipeToLoadHelper? = null
    private var page =1
    private var serachBean:SearchBean? = null
    val instance by lazy { this }
    private var handler : Handler = @SuppressLint("HandlerLeak")
    object : Handler(){
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            if(msg?.what == 2){
                serachBean = msg.obj as SearchBean
                val code:Int =  serachBean?.code!!
                helper?.setLoadMoreFinish()
                if(code==0){
//                    swipeAdapter?.setDatas(serachBean?.data,false)
//                    adapterWrapper?.notifyDataSetChanged()
                }else{
                    val errorMsg:String =serachBean?.msg!!
                    Utils.toast(this@SearchActivity,errorMsg)
                }
            }else{
                helper?.setLoadMoreFinish()
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
        swipeRefresh?.setEnabled(false)
        val pool = RecyclerView.RecycledViewPool()
        pool.setMaxRecycledViews(0, 10)
        recycler?.setRecycledViewPool(pool)
        recycler?.layoutManager = GridLayoutManager(this, 2)
        swipeAdapter = SearchRecyclerAdapter(listData,this)
        adapterWrapper = AdapterWrapper(swipeAdapter)
        helper = SwipeToLoadHelper(recycler,adapterWrapper)
        recycler?.adapter = adapterWrapper
        helper?.setLoadMoreListener(this)

    }

    override fun initData() {
        editSearch?.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                var imm:InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(this.getCurrentFocus()
                        .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS)
                val msg = v.text.toString()
                if(msg!=null){
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
    override fun onClick(position: Int) {
        val intent = Intent(this,LolPicActivity().javaClass)
        val picBean = serachBean?.data!!.pics[position]
        intent.putExtra("pdId",picBean.pdId)
        intent.putExtra("title",picBean.title)
        intent.putExtra("collectNum",picBean.collectNum)
        startActivity(intent)
    }
}
