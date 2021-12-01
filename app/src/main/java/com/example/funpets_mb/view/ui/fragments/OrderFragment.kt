package com.example.funpets_mb.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.funpets_mb.R
import com.example.funpets_mb.databinding.FragmentOrderBinding
import com.example.funpets_mb.model.Products
import com.example.funpets_mb.view.adapter.ProductsAdapter
import com.example.funpets_mb.view.adapter.ProductsListener
import com.example.funpets_mb.viewmodel.OrderViewModel


class OrderFragment : Fragment(), ProductsListener {

    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding!!

    private lateinit var productsAdapter: ProductsAdapter
    private lateinit var orderViewModel: OrderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentOrderBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        orderViewModel = ViewModelProvider(this).get(OrderViewModel::class.java)
        orderViewModel.refresh()

        productsAdapter = ProductsAdapter(this)

        binding.rvOrder.apply {
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
            adapter = productsAdapter
        }

        observeViewModel()
    }

    fun observeViewModel() {
        orderViewModel.listProducts.observe(viewLifecycleOwner, Observer<List<Products>> { products ->
            productsAdapter.updateData(products)
        })

        orderViewModel.isLoading.observe(viewLifecycleOwner, Observer<Boolean>{
            if (it != null)
                binding.rlBaseOrder.visibility = View.INVISIBLE
        })
    }

    override fun OnProductsClick(product: Products, position: Int) {
        val bundle = bundleOf("product" to product)
        findNavController().navigate(R.id.orderDetailFragmentDialog,bundle)
    }


}