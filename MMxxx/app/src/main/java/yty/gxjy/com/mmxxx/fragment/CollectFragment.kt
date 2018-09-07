package yty.gxjy.com.mmxxx.fragment
import android.annotation.SuppressLint
import android.databinding.DataBindingUtil
import android.os.Handler
import android.os.Message
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import okhttp3.FormBody
import yty.gxjy.com.mmxxx.Bean.PicsBean
import yty.gxjy.com.mmxxx.Bean.SearchBean
import yty.gxjy.com.mmxxx.Constans
import yty.gxjy.com.mmxxx.R
import yty.gxjy.com.mmxxx.Util.OkHttpUtils
import yty.gxjy.com.mmxxx.Util.Utils
import yty.gxjy.com.mmxxx.View.AdapterWrapper
import yty.gxjy.com.mmxxx.View.SwipeToLoadHelper
import yty.gxjy.com.mmxxx.adapter.CollectAdapter
import yty.gxjy.com.mmxxx.adapter.SearchRecyclerAdapter
import yty.gxjy.com.mmxxx.databinding.CollectBinding
class CollectFragment : BaseFragment(), SwipeToLoadHelper.LoadMoreListener{
    private var listData:MutableCollection<PicsBean.DataBean> = ArrayList()
    private  var swipeAdapter: CollectAdapter? = null
    private var adapterWrapper: AdapterWrapper? = null
    private var swipeRefresh: SwipeRefreshLayout? = null
    private var recycler: RecyclerView? = null
    private  var helper:SwipeToLoadHelper? = null
    private var page =1
    private var picBean:PicsBean? = null
    var binding: CollectBinding? = null
    private var handler : Handler = @SuppressLint("HandlerLeak")
    object : Handler(){
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            helper?.setLoadMoreFinish()
            if(msg?.what == 1){
                picBean = msg.obj as PicsBean
                val code:Int =  picBean?.code!!
                if(code==0){
//                    swipeAdapter?.setDatas(serachBean?.data,false)
//                    adapterWrapper?.notifyDataSetChanged()
                }else{
                    val errorMsg:String =picBean?.msg!!
                    Utils.toast(activity,errorMsg)
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
        swipeRefresh?.setEnabled(false)
        val pool = RecyclerView.RecycledViewPool()
        pool.setMaxRecycledViews(0, 10)
        recycler?.setRecycledViewPool(pool)
        recycler?.layoutManager = GridLayoutManager(activity, 2)
        swipeAdapter = CollectAdapter(listData)
        adapterWrapper = AdapterWrapper(swipeAdapter)
        helper = SwipeToLoadHelper(recycler,adapterWrapper)
        recycler?.adapter = adapterWrapper
        helper?.setLoadMoreListener(this)

    }
    override fun initData() {
        getData("1")

    }

    private fun getData(s: String) {
        OkHttpUtils.getInstance().getData(activity, Constans.getCollect,
                FormBody.Builder().add("pageNo",s).
                        build(), PicsBean().javaClass,handler)
    }


    override fun onLoad() {
        page++
        getData(page.toString())
    }

}