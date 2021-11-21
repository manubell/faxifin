package be.faxifin.androidtv.presentation.main_screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import be.faxifin.androidtv.R
import be.faxifin.androidtv.common.Resource
import be.faxifin.androidtv.data.jellyfin.model.JellyfinLibraryDto
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels()
    private var libraries: List<JellyfinLibraryDto> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        subscribeToObservers()
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    private fun subscribeToObservers() {
        Log.i("testing", "Object: " + viewModel.libraryState.value)
        viewModel.libraryState.observe(viewLifecycleOwner, { result ->
            Log.i("testing", "Object: $result")
            when (result) {
                is Resource.Success -> {
                    libraries = result.data!!
                    val textView: TextView = view!!.findViewById(R.id.myTestTextView)
                    textView.text = libraries.joinToString { t -> t.name }
                }
                is Resource.Loading -> {
                    Log.i("testing", "Loading shit")
                    Toast.makeText(context, "LOADING LIBRARIES!", Toast.LENGTH_LONG).show()
                }
                is Resource.Error -> {
                    Toast.makeText(context, result.message, Toast.LENGTH_LONG).show()
                }
            }
            Log.i("testing", "Libraries: $libraries")
        })
    }
}