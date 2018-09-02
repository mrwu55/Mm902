package yty.gxjy.com.mmxxx.fragment
import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import yty.gxjy.com.mmxxx.R
import yty.gxjy.com.mmxxx.databinding.CollectBinding
class CollectFragment : BaseFragment() {
    var binding: CollectBinding? = null
    override fun setLayout(inflater: LayoutInflater?, container: ViewGroup?): View?{
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_collect, container, false)
        return binding?.root
    }
    override fun initView() {
        var list = listOf<Int>(R.drawable.c1, R.drawable.c2,
                R.drawable.c3, R.drawable.c1, R.drawable.c2, R.drawable.c3,
                R.drawable.c1, R.drawable.c2)
        binding?.data =list
    }
    override fun initData() {
    }
}