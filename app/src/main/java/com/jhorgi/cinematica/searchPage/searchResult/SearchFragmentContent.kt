package com.jhorgi.cinematica.searchPage.searchResult

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.jhorgi.cinematica.databinding.FragmentSearchContentBinding
import com.jhorgi.cinematica.searchPage.SearchPageViewModel
import com.jhorgi.cinematica.searchPage.adapter.SearchResultSectionsPagerAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.androidx.viewmodel.ext.android.viewModel

@FlowPreview
@ExperimentalCoroutinesApi
class SearchFragmentContent : Fragment() {

    private val searchPageViewModel: SearchPageViewModel by viewModel()

    private var _binding: FragmentSearchContentBinding? =null
    private  val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSearchContentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvContent.layoutManager=layoutManager

        val index = arguments?.getInt(SearchResultSectionsPagerAdapter.ARG_SECTION_NUMBER,0)
        if(index==1){
//            searchPageViewModel.searchResult.observe(viewLifecycleOwner) {
//                Log.d("index 1", "here")
//                lifecycleScope.launch {
//                    it.collect { data ->
//                        when (data) {
//                            is Resource.Success -> {
//                                Log.d("index 1 data", data.data.toString())
//                                val adapter = SearchResultAdapter(data.data) { clickMovie ->
//                                    val intent = Intent(activity, DetailsActivity::class.java)
//                                    intent.putExtra(DetailsActivity.EXTRA_DATA, clickMovie.movieId)
//                                    intent.putExtra(
//                                        DetailsActivity.TYPE_DATA,
//                                        DetailsActivity.MOVIE_TYPE
//                                    )
//                                    startActivity(intent)
//                                }
//
//                                binding.rvContent.adapter = adapter
//
//                            }
//
//                            is Resource.Error -> {
////                            Toast.makeText(context,"enter error", Toast.LENGTH_LONG).show()
//                            }
//
//                        }
//                    }
//                }
//            }

            Log.d("index 1", "here")
        }
        if(index==2){
            Log.d("index 2", "index 1")
        }

    }

}