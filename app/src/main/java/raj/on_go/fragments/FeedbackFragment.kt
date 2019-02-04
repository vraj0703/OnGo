package raj.on_go.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import raj.on_go.databinding.FragmentFeedbackBinding
import raj.on_go.utils.KeyboardUtils


class FeedbackFragment : Fragment() {

    companion object {
        fun newInstance(): FeedbackFragment {
            return FeedbackFragment()
        }
    }

    lateinit var binding: FragmentFeedbackBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFeedbackBinding.inflate(LayoutInflater.from(activity), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        KeyboardUtils.showKeyboardForced(activity)
    }
}