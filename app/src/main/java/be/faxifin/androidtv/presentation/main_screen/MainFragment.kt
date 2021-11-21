package be.faxifin.androidtv.presentation.main_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.leanback.app.BackgroundManager
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.*

class MainFragment : BrowseSupportFragment() {
    private lateinit var rowsAdapter: ArrayObjectAdapter
    private lateinit var mBackgroundManager: BackgroundManager

    private val MOVIES_ID: Long = 1
    private val SERIES_ID: Long = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupUI()
        loadData()
        mBackgroundManager = BackgroundManager.getInstance(activity)
        mBackgroundManager.attach(activity?.window)
        mainFragmentRegistry.registerFragment(
            PageRow::class.java,
            PageRowFragmentFactory(mBackgroundManager)
        )

    }

    private class PageRowFragmentFactory(private val mBackgroundManager: BackgroundManager) :
        FragmentFactory<Fragment>() {
//        @Inject
//        lateinit var jellyfinUsecases: JellyfinUsecases

        override fun createFragment(row: Any?): Fragment {
            row as Row
            mBackgroundManager.drawable = null
            if (row.headerItem.id == 1L) {
                return TestRowsFragment()
            }
            if (row.headerItem.id == 2L) {
                return TestRowsFragment()
            }

            throw IllegalArgumentException(String.format("Invalid row %s", row))
        }
    }

    private fun loadData() {
        rowsAdapter = ArrayObjectAdapter(ListRowPresenter())
        adapter = rowsAdapter
        createRows()
        startEntranceTransition() // TODO get prepareEntranceTransition working as well
    }

    private fun createRows() {
        val headerItem1 = HeaderItem(MOVIES_ID, "test1")
        val pageRow1 = PageRow(headerItem1)
        rowsAdapter.add(pageRow1)

        val headerItem2 = HeaderItem(SERIES_ID, "test2")
        val pageRow2 = PageRow(headerItem2)
        rowsAdapter.add(pageRow2)
    }

    private fun setupUI() {
//        title = "Faxifin"
//        headersState = HEADERS_DISABLED
//        brandColor = getColor(resources, R.color.lb_grey, null) // TODO experiment with themes
    }

    /**
     * TODO figure out; Why does this work like this?
     */
    class anynamehere(fragment: TestFragment?) : MainFragmentAdapter<TestFragment?>(fragment)
}