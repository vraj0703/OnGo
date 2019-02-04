package raj.on_go.utils

import android.support.v4.app.Fragment
import raj.on_go.fragments.*

enum class Screen(val value: Int, val fragment: Fragment?, val tag: String) {
    Home(0, null, "home"),
    Search(1, SearchFragment.newInstance(), "search"),
    History(2, HistoryFragment.newInstance(), "history"),
    Feedback(3, FeedbackFragment.newInstance(), "feedback"),
    Donate(4, DonateFragment.newInstance(), "donate"),
    Rate(5, RateFragment.newInstance(), "rate")
}

enum class UiError(val value: Boolean) {

    NoError(false),
    NoInternet(true),
    NoData(true),
    ApiError(true)
}