package raj.on_go.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import raj.on_go.databinding.FragmentHistoryBinding
import raj.on_go.src.database.Word
import raj.on_go.utils.HistoryWordAdapter
import raj.on_go.view_model.MainViewModel

class HistoryFragment : Fragment() {

    companion object {
        fun newInstance(): HistoryFragment {
            return HistoryFragment()
        }
    }

    private lateinit var binding: FragmentHistoryBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHistoryBinding.inflate(LayoutInflater.from(activity), container, false)
        viewModel = activity?.let { ViewModelProviders.of(it).get(MainViewModel::class.java) }!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (binding.list.adapter == null) {
            binding.list.adapter = HistoryWordAdapter(activity as AppCompatActivity)
            binding.list.layoutManager = LinearLayoutManager(activity)
        }
        viewModel.words.observe(this, Observer<List<Word>> {
            val adapter = binding.list.adapter as HistoryWordAdapter
            adapter.update(it)
        })
    }
}