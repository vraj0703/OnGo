package raj.on_go.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import raj.on_go.databinding.FragmentSearchBinding
import raj.on_go.utils.AdapterRecyclerViewImpl
import raj.on_go.view_model.ListItem
import raj.on_go.view_model.MainViewModel

class SearchFragment : Fragment() {

    companion object {
        fun newInstance(): SearchFragment {
            return SearchFragment()
        }
    }

    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSearchBinding.inflate(LayoutInflater.from(activity), container, false)
        viewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (binding.list.adapter == null) {
            binding.list.adapter = AdapterRecyclerViewImpl()
            binding.list.layoutManager = LinearLayoutManager(activity)
        }
//        inflateRecyclerView()
        viewModel.meaning.observe(activity!!, Observer { inflateRecyclerView() })
        viewModel.loading.observe(activity!!, Observer { binding.loading = it })
        viewModel.error.observe(activity!!, Observer { binding.error = it })
    }

    private fun inflateRecyclerView() {
        val adapter = binding.list.adapter as AdapterRecyclerViewImpl
        adapter.update(getList())
    }

    private fun getList(): List<ListItem> {
        return viewModel.getSerachUiListItem(viewModel.meaning.value)
    }

    override fun onResume() {
        super.onResume()
        binding.list.smoothScrollToPosition(0)
    }
}