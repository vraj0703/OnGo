package raj.on_go.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.widget.LinearLayout
import raj.on_go.R
import raj.on_go.databinding.SelectableImageViewBinding

class SelectableImageView(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : LinearLayout(context, attrs, defStyleAttr, defStyleRes) {
    constructor(context: Context?) : this(context, null, 0, 0)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    val binding: SelectableImageViewBinding = SelectableImageViewBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        context?.theme?.obtainStyledAttributes(
                attrs,
                R.styleable.SelectableImageView,
                0, 0).apply {

            this!!
            try {
                binding.text = getText(R.styleable.SelectableImageView_text)
                binding.selected = getBoolean(R.styleable.SelectableImageView_selected, false)
                binding.image = (getResourceId(R.styleable.SelectableImageView_image, R.drawable.ic_beer)).let { resources.getDrawable(it, context?.theme) }
            } finally {
                recycle()
            }
        }
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            binding.selected = !binding.selected
        }
        return super.onTouchEvent(event)
    }

    fun selected(): Boolean {
        return binding.selected
    }

}