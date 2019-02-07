package raj.on_go.utils

import android.support.annotation.DrawableRes
import android.support.v4.app.Fragment
import raj.on_go.R
import raj.on_go.fragments.*

enum class Screen(val value: Int, val fragment: Fragment?, val tag: String) {
    Home(0, null, "home"),
    Search(1, SearchFragment.newInstance(), "search"),
    History(2, HistoryFragment.newInstance(), "history"),
    Feedback(3, FeedbackFragment.newInstance(), "feedback"),
    Donate(4, DonateFragment.newInstance(), "donate"),
    Rate(5, RateFragment.newInstance(), "rate")
}

enum class UiError(val value: Boolean, @DrawableRes val image: Int) {

    NoError(false, 0),
    NoInternet(true, R.drawable.ic_no_network),
    NoData(true, R.drawable.ic_no_data),
    ApiError(true, R.drawable.ic_not_found),
    WrongInput(true, R.drawable.ic_wrong_input)
}