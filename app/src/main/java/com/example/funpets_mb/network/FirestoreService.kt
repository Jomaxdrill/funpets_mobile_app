package com.example.funpets_mb.network

import com.google.firebase.firestore.FirebaseFirestore
import com.example.funpets_mb.model.Comments
import com.example.funpets_mb.model.Products

const val PRODUCTS_COLLECTION_NAME = "products"
const val COMMENTS_COLLECTION_NAME = "comments"

class FirestoreService {
    val firebaseFirestore = FirebaseFirestore.getInstance()

    fun getProducts(callback: Callback<List<Products>>){
        firebaseFirestore.collection(PRODUCTS_COLLECTION_NAME)
                .get()
                .addOnSuccessListener { result ->
                    for (doc in result) {
                        val list = result.toObjects(Products::class.java)
                        callback.onSuccess(list)
                        break
                    }
                }
    }

    fun getComments(callback: Callback<List<Comments>>) {
        firebaseFirestore.collection(COMMENTS_COLLECTION_NAME)
                .get()
                .addOnSuccessListener { result ->
                    for (doc in result) {
                        val list = result.toObjects(Comments::class.java)
                        callback.onSuccess(list)
                        break
                    }
                }
    }
}