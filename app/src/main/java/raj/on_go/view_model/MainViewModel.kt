package raj.on_go.view_model

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.view.View
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import raj.on_go.repsoitory.Repository
import raj.on_go.src.database.AppDatabase
import raj.on_go.src.database.Word
import raj.on_go.src.network.ApiHelper
import raj.on_go.src.network.ApiService
import raj.on_go.src.network.network_model.oxford.Converters
import raj.on_go.src.network.network_model.oxford.Meaning
import raj.on_go.src.network.network_model.oxford.models.Pronunciation
import raj.on_go.utils.AudioPlayer
import raj.on_go.utils.Screen
import raj.on_go.utils.UiError
import raj.on_go.view.SelectableImageView
import kotlin.coroutines.CoroutineContext

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    private val repository: Repository
    val words: LiveData<List<Word>>
    val screen: MutableLiveData<Screen>
    val word: MutableLiveData<CharSequence>
    val meaning: MutableLiveData<Meaning>
    val audioFile: MutableLiveData<String>
    val loading: MutableLiveData<Boolean>
    val error: MutableLiveData<UiError>
    val donation: MutableLiveData<Int>

    init {
        val dao = AppDatabase.db(application).dao
        repository = Repository(dao)
        words = repository.words
        screen = MutableLiveData<Screen>().apply { postValue(Screen.Home) }
        word = MutableLiveData()
        meaning = MutableLiveData()
        audioFile = MutableLiveData()
        loading = MutableLiveData()
        error = MutableLiveData<UiError>().apply { postValue(UiError.NoError) }
        donation = MutableLiveData<Int>().apply { postValue(0) }
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }

    fun sendFeedback(name: String, feedback: String) = scope.launch(Dispatchers.IO) {
        val service = ApiService.makeFeedbackService()
        val request = service.sendFeedbackAsync(name, feedback)
        try {
            val response = request.await()
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }

    fun changeWord(value: CharSequence) {
        word.postValue(value)
        meaning.postValue(null)
        audioFile.postValue(null)
    }

    fun searchWord(word: CharSequence?) = scope.launch(Dispatchers.IO) {
        if (eligible(word)) {
            val savedWord = repository.find(word.toString())
            val result = savedWord.let {
                if (it != null) Converters.fromString(it.meaning)
                else ApiHelper().search(word)
            }
            if (!checkError(result)) {
                insert(word.toString(), Converters.toString(result))
                meaning.postValue(result)
                if (result?.lexicalEntries != null)
                    audioFile.postValue(getAudioFile(result.lexicalEntries!![0].pronunciations))
                else
                    audioFile.postValue("")
            }
            loading.postValue(false)
        }
    }

    fun getList(result: Meaning?): List<ListItem> {
        val list: MutableList<ListItem> = ArrayList()
        if (result != null) {
            for (lexicalEntry in result.lexicalEntries!!) {
                val headerItem = HeaderItem(lexicalEntry.lexicalCategory!!, getAudioFile(lexicalEntry.pronunciations))
                list.add(headerItem)
                val entry = lexicalEntry.entries!![0]
                var added = false
                for (senses in entry.senses!!) {
                    if (senses.definitions != null) {
                        added = true
                        var example = ""
                        if (senses.examples != null) example = senses.examples!![0].text!!
                        list.add(MeaningItem(senses.definitions!![0], example))
                    }
                }
                if (!added)
                    list.remove(headerItem)
            }
        }
        return list
    }

    private fun checkError(result: Meaning?): Boolean {
        return false
    }

    private fun getAudioFile(pronunciations: List<Pronunciation>?): String {
        if (pronunciations != null)
            for (pronunciation in pronunciations) {
                if (pronunciation.audioFile != null)
                    return pronunciation.audioFile!!
            }
        return ""
    }

    private fun eligible(word: CharSequence?): Boolean {
        return true
    }

    private fun insert(rawWord: String, jsonMeaning: String) = scope.launch(Dispatchers.IO) {
        val savedWord = repository.find(rawWord)
        if (savedWord == null) {
            val word = Word(rawWord, jsonMeaning)
            repository.insert(word)
        }
    }

    fun changeScreen(value: Screen) {
        screen.postValue(value)
    }

    fun pronounce() {
        AudioPlayer.playAudio(audioFile.value)
    }

    fun evaluateDonation(view: View) {
        val v = view as SelectableImageView
        val value: Int = if (v.selected()) donation.value?.plus((v.tag.toString().toInt()))!! else donation.value?.minus((v.tag.toString().toInt()))!!
        donation.postValue(value)
    }

    fun donate() {

    }
}