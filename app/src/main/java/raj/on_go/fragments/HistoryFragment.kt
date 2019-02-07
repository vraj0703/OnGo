package raj.on_go.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import raj.on_go.databinding.FragmentHistoryBinding
import raj.on_go.src.database.Word
import raj.on_go.src.network.network_model.oxford.Converters
import raj.on_go.utils.AdapterRecyclerViewImpl
import raj.on_go.utils.RecyclerViewItemClickListener
import raj.on_go.utils.Screen
import raj.on_go.view_model.HistoryWord
import raj.on_go.view_model.ListItem
import raj.on_go.view_model.MainViewModel

class HistoryFragment : Fragment(), RecyclerViewItemClickListener {
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
            binding.list.adapter = AdapterRecyclerViewImpl(this)
            binding.list.layoutManager = LinearLayoutManager(activity)
        }
        viewModel.words.observe(this, Observer<List<Word>> {
            val adapter = binding.list.adapter as AdapterRecyclerViewImpl
            adapter.update(getList(it))
        })
    }

    override fun onRecyclerViewItemClicked(obj: Any?) {
        obj as Word
        viewModel.screen.postValue(Screen.Search)
        viewModel.word.postValue(obj.word)
        viewModel.meaning.postValue(Converters.fromString(obj.meaning))
    }


    private fun getList(it: List<Word>?): List<ListItem> {
        val list = ArrayList<ListItem>()
        for (item in it!!) {
            list.add(HistoryWord(item.word, item))
        }
        return list.reversed()
    }

    override fun onResume() {
        super.onResume()
        binding.list.smoothScrollToPosition(0)
    }
}