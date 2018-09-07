package yty.gxjy.com.mmxxx.fragment


import android.annotation.SuppressLint
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Handler
import android.os.Message
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import okhttp3.FormBody
import yty.gxjy.com.mmxxx.Activity.LolPicActivity
import yty.gxjy.com.mmxxx.Bean.PicsBean
import yty.gxjy.com.mmxxx.Constans
import yty.gxjy.com.mmxxx.Interface.PicClickListener
import yty.gxjy.com.mmxxx.R
import yty.gxjy.com.mmxxx.Util.OkHttpUtils
import yty.gxjy.com.mmxxx.Util.Utils
import yty.gxjy.com.mmxxx.View.AdapterWrapper
import yty.gxjy.com.mmxxx.View.SwipeToLoadHelper
import yty.gxjy.com.mmxxx.adapter.SwipeRecyclerAdapter
import yty.gxjy.com.mmxxx.databinding.HotBinding

/**
 * A simple [Fragment] subclass.
 */
class HotFragment : BaseFragment(),PicClickListener,SwipeRefreshLayout.OnRefreshListener, SwipeToLoadHelper.LoadMoreListener {
    override fun onClick(dataBean: PicsBean.DataBean, position: Int) {
        val intent = Intent(activity,LolPicActivity().javaClass)
        intent.putExtra("pdId",dataBean.pdId)
        intent.putExtra("title",dataBean.title)
        intent.putExtra("collectNum",dataBean.collectNum)
        activity?.startActivity(intent)
    }

    private  var swipeAdapter: SwipeRecyclerAdapter? = null
    private var adapterWrapper: AdapterWrapper? = null
    private var swipeRefresh: SwipeRefreshLayout? = null
    private var recycler: RecyclerView? = null
    private var  picsBean:PicsBean? = null
    private  var helper: SwipeToLoadHelper? = null
    private var page =1
    private var listData:MutableCollection<PicsBean.DataBean> = ArrayList()
    val instance by lazy { this } //这里使用了委托，表示只有使用到instance才会执行该段代码
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
                    picsBean = msg.obj as PicsBean
                    val code:Int =  picsBean!!.code
                    if(code==0){
                        swipeAdapter?.setDatas(picsBean?.data!!,true)
                        adapterWrapper?.notifyDataSetChanged()
                    }else{
                        val errorMsg:String =picsBean!!.msg
                        Utils.toast(activity,errorMsg)
                    }
                }
                2 ->{
                    picsBean = msg.obj as PicsBean
                    val code:Int =  picsBean!!.code
                    swipeRefresh?.setEnabled(true)
                    if(code==0){
                        swipeAdapter?.setDatas(picsBean?.data!!,false)
                        adapterWrapper?.notifyDataSetChanged()
                        helper?.setLoadMoreFinish()
                    }else{
                        val errorMsg:String =picsBean!!.msg
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
    var binding: HotBinding? = null
    override fun setLayout(inflater: LayoutInflater?, container: ViewGroup?): View?{
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_hot, container, false)
        return binding?.root
    }

    override fun initView() {
        swipeRefresh = binding?.swipeRefreshHot
        recycler = binding?.hotRecycler
        val pool = RecyclerView.RecycledViewPool()
        pool.setMaxRecycledViews(0, 10)
        recycler?.setRecycledViewPool(pool)
        recycler?.layoutManager = GridLayoutManager(activity, 2)
        swipeAdapter = SwipeRecyclerAdapter(listData,this)
        adapterWrapper = AdapterWrapper(swipeAdapter)
        helper = SwipeToLoadHelper(recycler,adapterWrapper)
        recycler?.adapter = adapterWrapper
        swipeRefresh?.setOnRefreshListener(this)
        helper?.setLoadMoreListener(this)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
    }
    override fun initData() {
        getData("1",true)
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
    fun getData(pageNo:String,isRefresh:Boolean){
        var code =2
        if(isRefresh){
            code=1
        }
        OkHttpUtils.getInstance().getDataWithHandCode(activity, Constans.getPics,
                FormBody.Builder().add("orderType","2").add("tagName","").add("pageNo",pageNo).
                        build(), PicsBean().javaClass,handler,code)
    }
}
