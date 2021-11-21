package be.faxifin.androidtv.presentation.presenters

import androidx.leanback.widget.ListRowPresenter
import androidx.leanback.widget.Presenter
import androidx.leanback.widget.PresenterSelector

class ShadowRowPresenterSelector : PresenterSelector() {
    //    private val shadowEnabledRowPresenter = ListRowPresenter()
    private val shadowDisabledRowPresenter = ListRowPresenter(1)

    init {
//        shadowEnabledRowPresenter.setNumRows(1)
        shadowDisabledRowPresenter.shadowEnabled = false

    }

    override fun getPresenter(item: Any?): Presenter {
        return shadowDisabledRowPresenter

//        if (item !is CardListRow) {
//            return shadowDisabledRowPresenter
//        }
//        val row = item.mCardRow
//        return if (row.useShadow()) shadowEnabledRowPresenter
//        else shadowDisabledRowPresenter
    }
}