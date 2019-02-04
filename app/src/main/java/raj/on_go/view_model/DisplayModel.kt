package raj.on_go.view_model

import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import raj.on_go.R
import raj.on_go.databinding.LayoutMeaningBinding
import raj.on_go.databinding.LayoutStickyHeaderBinding

abstract class ListItem(@LayoutRes open val layoutId: Int) {
    abstract fun updateBinding(genericBinding: ViewDataBinding)
}

class HeaderItem(val text: CharSequence, val audioUrl: String) : ListItem(R.layout.layout_sticky_header) {
    override fun updateBinding(genericBinding: ViewDataBinding) {
        genericBinding as LayoutStickyHeaderBinding
        genericBinding.title = text
    }
}

class MeaningItem(val meaning: CharSequence, val example: CharSequence) : ListItem(R.layout.layout_meaning) {
    override fun updateBinding(genericBinding: ViewDataBinding) {
        genericBinding as LayoutMeaningBinding
        genericBinding.example = example
        genericBinding.meaning = meaning
    }
}

class HistoryWord(val word: String)