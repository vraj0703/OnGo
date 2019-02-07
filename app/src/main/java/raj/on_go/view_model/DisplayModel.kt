package raj.on_go.view_model

import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import raj.on_go.R
import raj.on_go.databinding.*

abstract class ListItem(@LayoutRes open val layoutId: Int, val payload: Any?) {
    constructor(@LayoutRes layoutId: Int) : this(layoutId, null)

    abstract fun updateBinding(binding: ViewDataBinding)
}

class HeaderItem(val text: CharSequence, val audioUrl: String) : ListItem(R.layout.layout_sticky_header) {
    override fun updateBinding(binding: ViewDataBinding) {
        binding as LayoutStickyHeaderBinding
        binding.title = text
    }
}

class MeaningItem(val meaning: CharSequence, val example: CharSequence) : ListItem(R.layout.layout_meaning) {
    override fun updateBinding(binding: ViewDataBinding) {
        binding as LayoutMeaningBinding
        binding.example = example
        binding.meaning = meaning
    }
}

class HistoryWord(val word: String, load: Any?) : ListItem(R.layout.layout_word_history, load) {
    override fun updateBinding(binding: ViewDataBinding) {
        binding as LayoutWordHistoryBinding
        binding.word = word
    }

}

class LexicalCategory(private val category: String?) : ListItem(R.layout.layout_serach_word) {
    override fun updateBinding(binding: ViewDataBinding) {
        binding as LayoutSerachWordBinding
        binding.entry = category
    }
}

class Etymologies(private val value: String?) : ListItem(R.layout.layout_etmology) {
    override fun updateBinding(binding: ViewDataBinding) {
        binding as LayoutEtmologyBinding
        binding.entry = value
    }
}

class Defination(private val value: String?) : ListItem(R.layout.layout_defination) {
    override fun updateBinding(binding: ViewDataBinding) {
        binding as LayoutDefinationBinding
        binding.entry = value
    }
}


class Example(private val value: String?) : ListItem(R.layout.layout_example) {
    override fun updateBinding(binding: ViewDataBinding) {
        binding as LayoutExampleBinding
        binding.entry = value
    }
}