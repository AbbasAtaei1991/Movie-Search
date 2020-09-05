package com.example.movie.ui.search

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.movie.MainActivity
import com.example.movie.R
import com.example.movie.utils.Resource
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber
import java.net.NetworkInterface
import java.util.*
import kotlin.collections.ArrayList


class SearchFragment : Fragment(), MovieAdapter.MovieItemListener {

    private val viewModel: SearchViewModel by viewModel()
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
        search_et.setOnEditorActionListener { _, actionId, event ->
            if ((event != null && (event.keyCode == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_GO)) {
                if (hasVpn()) {
                    if (search_et.text.length > 2) {
                        input_layout.error = null
                        input_layout.isErrorEnabled = false
                        viewModel.start(search_et.text.toString())
                        hideKeyboard()
                    } else {
                        input_layout.error = "At least 3 character"
                    }
                } else {
                    Toast.makeText(requireContext(), "check your vpn", Toast.LENGTH_SHORT).show()
                }
                true
            } else
                false
        }
    }

    private fun setupRecyclerView() {
        adapter = MovieAdapter(this)
        movie_list.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.movies.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    progress_bar.visibility = View.GONE
                    if (it.data != null) {
                        if (!it.data.results.isNullOrEmpty()
                        ) {
                            adapter.setItems(ArrayList(it.data.results))
                            no_result.visibility = View.GONE
                            movie_list.visibility = View.VISIBLE
                        } else {
                            no_result.visibility = View.VISIBLE
                            movie_list.visibility = View.INVISIBLE
                        }
                    }
                }
                Resource.Status.LOADING -> {
                    progress_bar.visibility = View.VISIBLE
                }
                Resource.Status.ERROR -> {
                    progress_bar.visibility = View.GONE
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun onMovieClick(movieId: String) {
        findNavController().navigate(R.id.action_searchFragment_to_movieFragment)
    }

    private fun hasVpn(): Boolean {
        val networkList: MutableList<String> = ArrayList()
        try {
            for (networkInterface in Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (networkInterface.isUp) networkList.add(networkInterface.getName())
            }
        } catch (ex: Exception) {
            Timber.d("isVpnUsing Network List didn't received")
        }

        return networkList.contains("tun0")
    }

    private fun hideKeyboard() {
        val activity = activity as MainActivity
        val view = activity.currentFocus
        if (view != null) {
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm!!.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}