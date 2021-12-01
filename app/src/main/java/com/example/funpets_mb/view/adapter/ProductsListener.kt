package com.example.funpets_mb.view.adapter

import com.example.funpets_mb.model.Products

interface ProductsListener {
    fun OnProductsClick(product: Products, position: Int)
}