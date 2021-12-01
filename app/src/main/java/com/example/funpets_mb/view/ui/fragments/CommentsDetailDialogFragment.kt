package com.example.funpets_mb.view.ui.fragments

import android.content.ContentValues
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.example.funpets_mb.R
import com.example.funpets_mb.databinding.FragmentCommentsDetailDialogBinding

class CommentsDetailDialogFragment : DialogFragment() {

    private var _binding: FragmentCommentsDetailDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCommentsDetailDialogBinding.inflate(inflater,container,false)
        var view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val spinner: Spinner = binding.spinnerScores
        ArrayAdapter.createFromResource(requireActivity(),R.array.scores,android.R.layout.simple_spinner_item).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        binding.btSaveComment.setOnClickListener {
            val auth = FirebaseAuth.getInstance()
            val db = Firebase.firestore
            val textErrorInternet = resources.getString(R.string.failSendIssue)
            val duration = Toast.LENGTH_LONG
            val comment = binding.etComments.text

            if (auth.currentUser != null) {
                var user = auth.currentUser!!.displayName.toString()
                if (TextUtils.isEmpty(user)) {
                    user = resources.getString(R.string.userAnon)
                }

                val data = hashMapOf(
                    "comment" to comment.toString(),
                    "user" to user,
                    "score" to spinner.selectedItem.toString()+" / 5"
                )
                db.collection("comments").document()
                    .set(data)
                    .addOnSuccessListener {
                        Log.d(ContentValues.TAG, "Message received successfully!")
                        dismiss()
                    }
                    .addOnFailureListener { e ->
                        Log.w(ContentValues.TAG, "Error writing message forum", e)
                        Toast.makeText(context, textErrorInternet, duration).show()
                    }
            } else {
                Toast.makeText(context, textErrorInternet, duration).show()
            }
        }
    }
}