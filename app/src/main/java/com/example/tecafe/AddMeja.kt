package com.example.tecafe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tecafe.databinding.ActivityAddMejaBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddMeja : AppCompatActivity() {

    private lateinit var binding: ActivityAddMejaBinding
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMejaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Init

        firebaseDatabase = FirebaseDatabase.getInstance()

        databaseReference = FirebaseDatabase.getInstance().getReference("Meja")

        binding.btnAddMeja.setOnClickListener {
            val mejaName = binding.edtMeja.text.toString()
            val mejaPrice = binding.edtPriceMeja.text.toString()
            val mejaDesc = binding.edtDescMeja.text.toString()

            if (mejaName.isNotEmpty() && mejaPrice.isNotEmpty() && mejaDesc.isNotEmpty()) {
                val mejaId = databaseReference.push().key!!
                val meja = MejaModel(mejaId, mejaName, mejaPrice, mejaDesc)
                databaseReference.child(mejaId).setValue(meja) // Create child using menuId
                    .addOnCompleteListener {
                        Toast.makeText(this, "Tambah Meja Sukses", Toast.LENGTH_SHORT).show()
                        binding.edtMeja.text?.clear()
                        binding.edtPriceMeja.text?.clear()
                        binding.edtDescMeja.text?.clear()
                    }.addOnFailureListener { err ->
                        Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Field Text cannot be empty", Toast.LENGTH_SHORT).show()
            }

        }
    }
}