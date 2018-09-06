package yty.gxjy.com.mmxxx.Activity

import android.databinding.DataBindingUtil
import android.view.View
import yty.gxjy.com.mmxxx.Interface.MmClickListener
import yty.gxjy.com.mmxxx.R
import yty.gxjy.com.mmxxx.View.Dialog
import yty.gxjy.com.mmxxx.databinding.InputCodeClass

class InputCodeActivity : BaseActivity(),MmClickListener, Dialog.OnDialogClick {
    override fun onDialogClick() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private var binding: InputCodeClass?=null
    private var dialog:Dialog?=null
    override fun initView() {
        binding = DataBindingUtil.
                setContentView(this, R.layout.activity_input_code)
        binding?.listener = this
    }

    override fun initData() {
    }
    override fun onClick(view: View) {
        when(view.id){
            R.id.re_code_back ->{
                finish()
            }
            R.id.btn_code_sure ->{
                if(dialog==null){
                    dialog = Dialog(this,"激活成功！",this)
                }
                dialog?.show()
            }
        }
    }
}
