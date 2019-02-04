package raj.on_go

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import raj.on_go.databinding.ActivityOngoBinding
import raj.on_go.src.network.network_model.oxford.Meaning
import raj.on_go.utils.AdapterRecyclerViewImpl
import raj.on_go.view_model.HeaderItem
import raj.on_go.view_model.MainViewModel


class OnGoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOngoBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFinishOnTouchOutside(false)
        setWindow()
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ongo)

        viewModel.word.observe(this, Observer { searchWord(it) })
        viewModel.meaning.observe(this, Observer { inflateMeaning(it) })
        viewModel.audioFile.observe(this, Observer { binding.noAudio = it == null || it.isEmpty() })
        viewModel.loading.observe(this, Observer {
            if (it!!) binding.loading = true else {
                binding.loading = false
                binding.notFound = viewModel.meaning.value?.lexicalEntries == null
            }
        })

        val word = intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT)
        viewModel.word.postValue(word)
    }

    private fun searchWord(word: CharSequence?) {
        binding.word = word
        viewModel.loading.postValue(true)
        viewModel.searchWord(word)
    }

    private fun inflateMeaning(result: Meaning?) {
        if (result != null) {
            val list = viewModel.getList(result)
            val adapter = AdapterRecyclerViewImpl()
            adapter.update(list)
            val layoutManager = LinearLayoutManager(this)
            binding.list.adapter = adapter
            binding.list.layoutManager = layoutManager
            binding.list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    val firstVisibleItem = layoutManager.findLastVisibleItemPosition()
                    val headerPosition = adapter.getHeaderPositionForItem(firstVisibleItem)
                    val headerItem = list[headerPosition] as HeaderItem
                    binding.header = headerItem.text
                    viewModel.audioFile.postValue(headerItem.audioUrl)
                }
            })
        }
    }


    fun pronounce(v: View?) {
        viewModel.pronounce()
    }

    fun close(v: View?) {
        finish()
    }

    fun more(v: View?) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("Word", viewModel.word.value)
        startActivity(intent)
        close(null)
    }

    private fun setWindow() {
        val param = window.attributes
        param.gravity = Gravity.BOTTOM or Gravity.END
        window.attributes = param
        window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
    }
}
