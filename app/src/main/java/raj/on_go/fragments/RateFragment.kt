package raj.on_go.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import raj.on_go.databinding.FragmentRateBinding

class RateFragment : Fragment() {

    companion object {
        fun newInstance(): RateFragment {
            return RateFragment()
        }
    }

    private lateinit var binding: FragmentRateBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentRateBinding.inflate(LayoutInflater.from(activity), container, false)
        return binding.root
    }
}