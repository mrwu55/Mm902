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
import yty.gxjy.com.mmxxx.Constans
import yty.gxjy.com.mmxxx.Util.OkHttpUtils
import yty.gxjy.com.mmxxx.Util.Utils


class SearchActivity : BaseActivity(),MmClickListener {

    private var handler : Handler = @SuppressLint("HandlerLeak")
    object : Handler(){
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            if(msg?.what == 1){
//                val loginBean: LoginBean = msg.obj as LoginBean
//                val code:Int =  loginBean.code
//                if(code==0){
//                    Constans.uName = loginBean.data.uName
//                    Constans.vipName = loginBean.data.mlName
//                    startActivity(Intent(this@SearchActivity, MainActivity().javaClass))
//                    finish()
//                }else{
//                    val errorMsg:String =loginBean.msg
//                    Utils.toast(this@SearchActivity,errorMsg)
//                    startActivity(Intent(this@SearchActivity, MainActivity().javaClass))
//                    finish()
//                }
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
                            FormBody.Builder().add("pdId",pdId).
                                    build(), BaseBean().javaClass,handler,2)
                    Utils.toast(this,v.text.toString())
                }
                true
            }
            false
        })
    }

}
