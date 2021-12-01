package com.example.funpets_mb.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.funpets_mb.R
import com.example.funpets_mb.databinding.FragmentComentsBinding
import com.example.funpets_mb.model.Comments
import com.example.funpets_mb.view.adapter.CommentsAdapter
import com.example.funpets_mb.viewmodel.CommentsViewModel

class ComentsFragment : Fragment() {

    private lateinit var commentsAdapter: CommentsAdapter
    private lateinit var commentsViewModel: CommentsViewModel
    private var _binding: FragmentComentsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentComentsBinding.inflate(inflater,container,false)
        var view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        commentsViewModel = ViewModelProvider(this).get(CommentsViewModel::class.java)
        commentsViewModel.refresh()

        commentsAdapter = CommentsAdapter()

        binding.rvComments.apply {
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
            adapter = commentsAdapter
        }

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.commentsDetailFragmentDialog)
        }

        observeViewModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun observeViewModel() {
        commentsViewModel.listComments.observe(viewLifecycleOwner, Observer<List<Comments>> { comments ->
            commentsAdapter.updateData(comments)
        })

        commentsViewModel.isLoading.observe(viewLifecycleOwner, Observer<Boolean>{
            if (it != null)
                binding.rlBaseComments.visibility = View.INVISIBLE
        })
    }

}