package raj.on_go

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import raj.on_go.databinding.ActivityMainBinding
import raj.on_go.fragments.FeedbackFragment
import raj.on_go.utils.KeyboardUtils
import raj.on_go.utils.Screen
import raj.on_go.view_model.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding = (R.layout.activity_main).let { layout -> DataBindingUtil.setContentView(this, layout) }
        binding.viewModel = viewModel

        viewModel.screen.observe(this, Observer<Screen> {
            binding.screen = it
            if (it?.fragment != null) {
                addFragment(it.fragment, it.tag)
            } else {
                binding.search.text.clear()
            }
        })

        viewModel.word.observe(this, Observer {
            binding.search.setText(it)
            searchWord()
        })

        binding.search.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.word.postValue(binding.search.text)
                return@OnEditorActionListener true
            }
            false
        })

        val word = intent.getCharSequenceExtra("Word")
        if (word != null) {
            viewModel.word.postValue(word)
        } else showMenu()
    }

    override fun onBackPressed() {
        if (viewModel.screen.value == Screen.Home) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
            viewModel.screen.postValue(Screen.Home)
        }
    }

    fun sendFeedback(v: View?) {
        val fragment = supportFragmentManager.findFragmentByTag("feedback")
        fragment as FeedbackFragment
        val feedback = fragment.binding.feedback.text.toString()
        val name = fragment.binding.name.text.toString()
        if (feedback.isEmpty()) {
            Snackbar.make(
                    binding.root, // Parent view
                    "Kindly fill feedback.", // Message to show
                    Snackbar.LENGTH_SHORT // How long to display the message.
            ).show()
        } else {
            viewModel.sendFeedback(name, feedback)
        }
    }

    private fun addFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.popBackStack()
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, fragment, tag)
                .commit()
    }

    private fun searchWord() {
        KeyboardUtils.hideKeyboard(binding.search, this)
        viewModel.screen.postValue(Screen.Search)
        viewModel.loading.postValue(true)
        viewModel.searchWord(viewModel.word.value)
    }

    private fun showMenu() {
        viewModel.screen.postValue(Screen.Home)
    }

    fun fabClicked(v: View) {
        KeyboardUtils.showKeyboard(binding.search, this)
    }
}




