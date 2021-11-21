package be.faxifin.androidtv.presentation.main_screen

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.app.BrowseSupportFragment.MainFragmentAdapterProvider
import androidx.leanback.app.RowsSupportFragment
import androidx.leanback.widget.*
import be.faxifin.androidtv.presentation.presenters.*
import dagger.hilt.android.AndroidEntryPoint

class TestRowsFragment : RowsSupportFragment(), MainFragmentAdapterProvider {
    var mRowsAdapter: ArrayObjectAdapter = ArrayObjectAdapter(ShadowRowPresenterSelector())

    init {
        adapter = mRowsAdapter
        onItemViewClickedListener =
            OnItemViewClickedListener { _, _, _, _ ->
                Toast.makeText(activity, "Implement click handler", Toast.LENGTH_SHORT).show()
            }
    }

    private val mMainFragmentAdapter =
        object : BrowseSupportFragment.MainFragmentAdapter<Fragment?>(this) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createRows()
        mMainFragmentAdapter.fragmentHost.notifyDataReady(mMainFragmentAdapter)
    }

    private fun createRows() {
        for (i in 1..2) {
            val cards: MutableList<Card> = ArrayList()
            for (j in 1..8) {
                cards.add(Card("title$j", "description$j", "extraText$j"))
            }
            mRowsAdapter.add(createCardListRow(CardRow(title = "cardRow1", cards = cards)))
        }
    }

    private fun createCardListRow(cardRow: CardRow): Row {
        val presenterSelector = CardPresenterSelector(requireActivity())
        val adapter = ArrayObjectAdapter(presenterSelector)
        for(card in cardRow.cards) {
            adapter.add(card)
        }

        return CardListRow(HeaderItem("headerItem"), adapter, cardRow)
    }

    override fun getMainFragmentAdapter(): BrowseSupportFragment.MainFragmentAdapter<*> {
        return mMainFragmentAdapter;
    }

}