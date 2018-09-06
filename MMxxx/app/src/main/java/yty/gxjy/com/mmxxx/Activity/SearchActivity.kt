package yty.gxjy.com.mmxxx.Activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Handler
import android.os.Message
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
import yty.gxjy.com.mmxxx.Bean.BaseBean
import yty.gxjy.com.mmxxx.Bean.LoginBean
import yty.gxjy.com.mmxxx.Bean.SearchBean
import yty.gxjy.com.mmxxx.Constans
import yty.gxjy.com.mmxxx.Interface.RecyclerItemClick
import yty.gxjy.com.mmxxx.Util.OkHttpUtils
import yty.gxjy.com.mmxxx.Util.Utils


class SearchActivity : BaseActivity(),MmClickListener, RecyclerItemClick {
    override fun onClick(position: Int) {
        val intent = Intent(this,LolPicActivity().javaClass)
        val picBean = serachBean?.data!!.pics[position]
        intent.putExtra("pdId",picBean.pdId)
        intent.putExtra("title",picBean.title)
        intent.putExtra("collectNum",picBean.collectNum)
        startActivity(intent)
    }

    private var serachBean:SearchBean? = null
    val instance by lazy { this }
    private var handler : Handler = @SuppressLint("HandlerLeak")
    object : Handler(){
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            if(msg?.what == 2){
                serachBean = msg.obj as SearchBean
                val code:Int =  serachBean?.code!!
                if(code==0){
                    binding?.data = serachBean
                    binding?.listener = instance
                    binding?.listenerItem =instance
                }else{
                    val errorMsg:String =serachBean?.msg!!
                    Utils.toast(this@SearchActivity,errorMsg)
                }
            }

        }
    }
    private var editSearch:EditText? = null
    private var recyclerSearch:RecyclerView? =null
    override fun onClick(view: View) {
    }

    private var binding: SearchClass?=null
    override fun initView() {
        binding = DataBindingUtil.
                setContentView(this, R.layout.activity_search)
        binding?.listener = this
        editSearch = binding?.editSearch
        recyclerSearch = binding?.recyclerSearch

    }

    override fun initData() {
        editSearch?.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                var imm:InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(this.getCurrentFocus()
                        .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS)
                val msg = v.text.toString()
                if(msg!=null){
                    OkHttpUtils.getInstance().getDataWithHandCode(this, Constans.getSearch,
                            FormBody.Builder().add("searchType","3").
                                    add("keyWords",msg).add("pageNo","1").
                                    build(), SearchBean().javaClass,handler,2)
                }
                true
            }
            false
        })
    }


}
