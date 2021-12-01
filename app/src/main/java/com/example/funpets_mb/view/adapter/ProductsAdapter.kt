package com.example.funpets_mb.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.funpets_mb.R
import com.example.funpets_mb.model.Products
import com.squareup.picasso.Picasso

class ProductsAdapter(val productsListener: ProductsListener) : RecyclerView.Adapter<ProductsAdapter.ViewHolder> () {

    var listProducts = ArrayList<Products>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_product,parent,false))

    override fun onBindViewHolder(holder: ProductsAdapter.ViewHolder, position: Int) {
        val products = listProducts[position]
        holder.tvNameProduct.text = products.name
        holder.tvPriceProduct.text = products.price
        Picasso.get().load(products.url).into(holder.ivItemProduct)

        holder.itemView.setOnClickListener {
            productsListener.OnProductsClick(products, position)
        }
    }

    override fun getItemCount()= listProducts.size

    fun updateData(data: List<Products>) {
        listProducts.clear()
        listProducts.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNameProduct = itemView.findViewById<TextView>(R.id.tvItemNameProduct)
        val tvPriceProduct = itemView.findViewById<TextView>(R.id.tvItemPriceProduct)
        val ivItemProduct = itemView.findViewById<ImageView>(R.id.ivItemProduct)
    }
}