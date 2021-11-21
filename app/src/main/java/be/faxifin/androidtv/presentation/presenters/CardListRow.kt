package be.faxifin.androidtv.presentation.presenters

import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ObjectAdapter

class CardListRow(headerItem: HeaderItem, adapter: ObjectAdapter, cardRow: CardRow) :
    ListRow(headerItem, adapter) {

    var mCardRow: CardRow = cardRow
}