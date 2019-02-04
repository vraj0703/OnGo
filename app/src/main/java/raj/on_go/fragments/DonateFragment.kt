package raj.on_go.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import raj.on_go.databinding.FragmentDonteBinding
import raj.on_go.view_model.MainViewModel

class DonateFragment : Fragment() {

    companion object {
        fun newInstance(): DonateFragment {
            return DonateFragment()
        }
    }

    private lateinit var binding: FragmentDonteBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDonteBinding.inflate(LayoutInflater.from(activity), container, false)
        viewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.donation.observe(activity!!, Observer { binding.value = it.toString() })
        viewModel.donation.postValue(0)
    }
}