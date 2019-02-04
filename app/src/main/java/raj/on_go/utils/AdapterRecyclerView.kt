package raj.on_go.utils

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import raj.on_go.R
import raj.on_go.src.database.Word
import raj.on_go.databinding.LayoutSerachWordBinding
import raj.on_go.databinding.LayoutWordHistoryBinding
import raj.on_go.src.network.network_model.oxford.Converters
import raj.on_go.view_model.ListItem
import raj.on_go.view_model.MainViewModel


class ViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    fun getBinding(): ViewDataBinding {
        return binding
    }
}

abstract class AdapterRecyclerView : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(p0.context), p1, p0, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        updateBinding(p0.getBinding(), p1)
    }

    abstract override fun getItemViewType(position: Int): Int

    abstract override fun getItemCount(): Int

    abstract fun updateBinding(genericBinding: ViewDataBinding, pos: Int)

}

class AdapterRecyclerViewImpl : AdapterRecyclerView() {

    private var list: List<ListItem> = ArrayList()

    override fun getItemViewType(position: Int): Int {
        return list[position].layoutId
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun updateBinding(genericBinding: ViewDataBinding, pos: Int) {
        list[pos].updateBinding(genericBinding)
    }

    private fun isHeader(pos: Int): Boolean {
        return list[pos].layoutId == R.layout.layout_sticky_header
    }

    fun getHeaderPositionForItem(itemPosition: Int): Int {
        var headerPosition = 0
        for (i in itemPosition downTo 1) {
            if (isHeader(i)) {
                headerPosition = i
                return headerPosition
            }
        }
        return headerPosition
    }

    fun update(list: List<ListItem>) {
        this.list = list
        notifyDataSetChanged()
    }
}

class HistoryWordAdapter(activity: AppCompatActivity) : AdapterRecyclerView() {

    var list: List<Word>? = null
    private val viewModel = activity.let { ViewModelProviders.of(it).get(MainViewModel::class.java) }

    override fun getItemViewType(position: Int): Int {
        return R.layout.layout_word_history
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun updateBinding(genericBinding: ViewDataBinding, pos: Int) {
        genericBinding as LayoutWordHistoryBinding
        genericBinding.word = list?.get(pos)?.word
        genericBinding.root.setOnClickListener {
            viewModel.screen.postValue(Screen.Search)
            viewModel.word.postValue(list?.get(pos)?.word)
            viewModel.meaning.postValue(Converters.fromString(list?.get(pos)?.meaning))
        }
    }

    fun update(list: List<Word>?) {
        this.list = list
        notifyDataSetChanged()
    }
}

class SearchRecyclerView : AdapterRecyclerView() {

    var list: List<Word>? = null

    override fun getItemViewType(position: Int): Int {
        return R.layout.layout_serach_word
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun updateBinding(genericBinding: ViewDataBinding, pos: Int) {
        genericBinding as LayoutSerachWordBinding
        //genericBinding.word = list?.get(pos)?.word
    }

    fun update(list: List<Word>?) {
        this.list = list
        notifyDataSetChanged()
    }
}



