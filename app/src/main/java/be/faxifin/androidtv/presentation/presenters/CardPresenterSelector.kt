package be.faxifin.androidtv.presentation.presenters

import android.content.Context
import androidx.leanback.widget.Presenter
import androidx.leanback.widget.PresenterSelector
import java.lang.RuntimeException

class CardPresenterSelector(val mContext: Context) : PresenterSelector() {
    override fun getPresenter(item: Any?): Presenter {
        if (item !is Card) {
            throw RuntimeException("The PresenterSelector only supports data items of type ${Card::class.simpleName}")
        }

        return ImageCardViewPresenter(mContext)
    }
}