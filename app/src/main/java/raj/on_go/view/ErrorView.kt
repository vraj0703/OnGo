package raj.on_go.view

import android.content.Context
import android.databinding.BindingAdapter
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import raj.on_go.databinding.LayoutErrorViewBinding


@BindingAdapter("errorImage")
fun setImageUrl(view: ErrorView, value: Int) {
    view.errorImage = value
}

class ErrorView(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : LinearLayout(context, attrs, defStyleAttr, defStyleRes) {
    constructor(context: Context?) : this(context, null, 0, 0)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    val binding: LayoutErrorViewBinding = LayoutErrorViewBinding.inflate(LayoutInflater.from(context), this, true)
    var errorImage: Int = 0
        set(value) {
            field = value
            binding.image = (if (field != 0) field else raj.on_go.R.drawable.ic_not_found).let { resources.getDrawable(it, context?.theme) }
        }

    init {
        context?.theme?.obtainStyledAttributes(
                attrs,
                raj.on_go.R.styleable.ErrorView,
                0, 0).apply {

            this!!
            try {
                binding.text = getText(raj.on_go.R.styleable.SelectableImageView_text)
                binding.image = (getResourceId(raj.on_go.R.styleable.SelectableImageView_image, raj.on_go.R.drawable.ic_not_found)).let { resources.getDrawable(it, context?.theme) }
            } finally {
                recycle()
            }
        }
    }

}

