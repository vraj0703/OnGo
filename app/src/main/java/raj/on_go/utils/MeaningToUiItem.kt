package raj.on_go.utils

import raj.on_go.src.network.network_model.oxford.Meaning
import raj.on_go.src.network.network_model.oxford.models.Pronunciation
import raj.on_go.view_model.HeaderItem
import raj.on_go.view_model.ListItem
import raj.on_go.view_model.MeaningItem

abstract class MeaningToUiItemParent {
    protected fun getAudioFile(pronunciations: List<Pronunciation>?): String {
        if (pronunciations != null)
            for (pronunciation in pronunciations) {
                if (pronunciation.audioFile != null)
                    return pronunciation.audioFile!!
            }
        return ""
    }
}

class MeaningToPopUpUiItem : MeaningToUiItemParent() {
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
}

