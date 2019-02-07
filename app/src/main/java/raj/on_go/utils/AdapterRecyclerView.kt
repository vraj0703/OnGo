package raj.on_go.utils

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import raj.on_go.R
import raj.on_go.view_model.ListItem


class ViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

interface RecyclerViewItemClickListener {
    fun onRecyclerViewItemClicked(obj: Any?)
}

abstract class AdapterRecyclerView : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(p0.context), p1, p0, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        updateBinding(p0.binding, p1)
    }

    abstract override fun getItemViewType(position: Int): Int

    abstract override fun getItemCount(): Int

    abstract fun updateBinding(binding: ViewDataBinding, pos: Int)

}

class AdapterRecyclerViewImpl(private val clickListener: RecyclerViewItemClickListener?) : AdapterRecyclerView() {
    constructor() : this(null)

    private var list: List<ListItem> = ArrayList()

    override fun getItemViewType(position: Int): Int {
        return list[position].layoutId
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun updateBinding(binding: ViewDataBinding, pos: Int) {
        list[pos].updateBinding(binding)
        if (clickListener != null)
            binding.root.setOnClickListener { clickListener.onRecyclerViewItemClicked(list[pos].payload) }
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




