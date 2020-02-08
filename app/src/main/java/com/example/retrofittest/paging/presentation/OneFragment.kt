package com.example.retrofittest.paging.presentation

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import com.example.retrofittest.databinding.FragmentOneBinding
import com.example.retrofittest.paging.data.remote.MovieItem
import com.example.retrofittest.paging.data.repository.MovieRepository
import kotlinx.android.synthetic.main.fragment_one.*


class OneFragment : Fragment(), SearchView.OnQueryTextListener {

    private val viewModel: OneViewModel by lazy {
        ViewModelProviders.of(this, OneViewModel.Factory(MovieRepository()))
            .get(OneViewModel::class.java)
    }

    private lateinit var searchView: SearchView

    private lateinit var databinding: FragmentOneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        databinding = DataBindingUtil.inflate(
            inflater,
            com.example.retrofittest.R.layout.fragment_one,
            container,
            false
        )
        return databinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = OneAdapter()
        my_recycler_view.apply {
            this.adapter = adapter
            setHasFixedSize(true)
        }

        viewModel.moveList.observe(this, Observer<PagedList<MovieItem>> {
            adapter.submitList(it)
        })

        viewModel.isEmpty.observe(this, Observer {
            empty_textview.visibility = if (it) View.VISIBLE else View.GONE
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(com.example.retrofittest.R.menu.tab_top, menu)
        val searchItem = menu.findItem(com.example.retrofittest.R.id.action_search)
        searchView = searchItem.actionView as SearchView
        searchView.run {
            maxWidth = Int.MAX_VALUE
            setOnQueryTextListener(this@OneFragment)
        }

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let {
            viewModel.searchKey.value = it
        }

        if (::searchView.isInitialized) {
            searchView.clearFocus()
        }

        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }
}