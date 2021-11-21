package be.faxifin.androidtv.presentation.presenters

import android.content.Context
import android.view.ContextThemeWrapper
import androidx.leanback.widget.ImageCardView
import be.faxifin.androidtv.R

class ImageCardViewPresenter : AbstractCardPresenter<ImageCardView> {

    constructor(context: Context, cardThemeResId: Int) : super(ContextThemeWrapper(context, cardThemeResId))
    constructor(context: Context) : this(context, R.style.DefaultCardTheme)

    override fun onCreateView(): ImageCardView {
        val imageCardView = ImageCardView(context)
        return imageCardView
    }

    override fun onBindViewHolder(card: Card?, cardView: ImageCardView) {
        cardView.tag = card
        cardView.cardType = ImageCardView.CARD_TYPE_FLAG_IMAGE_ONLY
//        cardView.titleText = card?.title
//        cardView.contentText = card?.description

    }

}