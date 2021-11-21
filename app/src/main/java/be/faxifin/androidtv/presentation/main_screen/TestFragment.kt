package be.faxifin.androidtv.presentation.main_screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.Toast.LENGTH_LONG
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.leanback.app.BrowseSupportFragment
import be.faxifin.androidtv.R
import be.faxifin.androidtv.common.Resource
import be.faxifin.androidtv.data.jellyfin.model.JellyfinLibraryDto
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TestFragment : Fragment(),
    BrowseSupportFragment.MainFragmentAdapterProvider {
    private val TAG = "TestFragment"


    private val viewModel: MainViewModel by viewModels()
    private var libraries: List<JellyfinLibraryDto> = emptyList()
//    lateinit var adapter: ArrayAdapter<String>

    private val mMainFragmentAdapter: BrowseSupportFragment.MainFragmentAdapter<*> =
        object : BrowseSupportFragment.MainFragmentAdapter<Fragment?>(this) {}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        subscribeToObservers()
        return inflater.inflate(R.layout.fragment_test, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        subscribeToObservers()


        val listView: ListView = view.findViewById(R.id.testListView)
        val mylist = arrayOf("123", "345", "567")
        val adapter = context?.let {
            ArrayAdapter(it, R.layout.test, R.id.myTestTextView, mylist)
        }!!
        listView.adapter = adapter
//        adapter.notifyDataSetChanged()
        mylist[0] = "TEST"
        mMainFragmentAdapter.fragmentHost.notifyDataReady(mMainFragmentAdapter)

    }

    private fun subscribeToObservers() {
        Log.i("testing", "Object: " + viewModel.libraryState.value)
        viewModel.libraryState.observe(viewLifecycleOwner, { result ->
            Log.i("testing", "Object: $result")
            when (result) {
                is Resource.Success -> {
                    libraries = result.data!!
                }
                is Resource.Loading -> {
                    Log.i(TAG, "Loading shit")
                    Toast.makeText(context, "LOADING LIBRARIES!", LENGTH_LONG).show()
                }
                is Resource.Error -> {
                    Toast.makeText(context, result.message, LENGTH_LONG).show()
                }
            }
            Log.i("testing", "Libraries: $libraries")
        })
    }

    override fun getMainFragmentAdapter(): BrowseSupportFragment.MainFragmentAdapter<*> {
        return mMainFragmentAdapter
    }
}