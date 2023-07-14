package com.example.tecafe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.tecafe.databinding.ActivityMejaDetailsBinding
import com.google.firebase.database.FirebaseDatabase

class MejaDetails : AppCompatActivity() {

    private lateinit var binding: ActivityMejaDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMejaDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setValuesToViews()

        binding.btnUpdateMeja.setOnClickListener{
            openUpdateDialog(
                intent.getStringExtra("mejaId").toString(),
                intent.getStringExtra("mejaName").toString()
            )
        }

        binding.btnDeleteMeja.setOnClickListener{
            deleteRecord(
                intent.getStringExtra("mejaId").toString(),
            )
        }

    }

    private fun deleteRecord(
        id:String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Meja").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this,"Meja Data Deleted", Toast.LENGTH_LONG).show()
            val intent = Intent(this, DaftarMeja::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener {error ->
            Toast.makeText(this,"Deleting Err ${error.message}", Toast.LENGTH_LONG).show()

        }
    }
    
    private fun setValuesToViews() {

        binding.tvMejaId.text = intent.getStringExtra("mejaId")
        binding.tvMejaName2.text = intent.getStringExtra("mejaName")
        binding.tvMejaPrice2.text = intent.getStringExtra("mejaPrice")
        binding.tvMejaDesc2.text = intent.getStringExtra("mejaDesc")

    }


    fun openUpdateDialog(mejaId: String, mejaName: String) {
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.update_meja_dialog, null)

        mDialog.setView(mDialogView)

        val etMejaName = mDialogView.findViewById<EditText>(R.id.etMejaName)
        val etMejaPrice = mDialogView.findViewById<EditText>(R.id.etMejaPrice)
        val etMejaDesc = mDialogView.findViewById<EditText>(R.id.etMejaDesc)

        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnUpdateMeja)

        etMejaName.setText(intent.getStringExtra("mejaName").toString())
        etMejaPrice.setText(intent.getStringExtra("mejaPrice").toString())
        etMejaDesc.setText(intent.getStringExtra("mejaDesc").toString())

        mDialog.setTitle("Updating $mejaName Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener {
            updateMejaData(
                mejaId,
                etMejaName.text.toString(),
                etMejaPrice.text.toString(),
                etMejaDesc.text.toString()
            )

            Toast.makeText(applicationContext, "Meja Data Updated", Toast.LENGTH_LONG).show()

            //
            binding.tvMejaName2.text = etMejaName.text.toString()
            binding.tvMejaPrice2.text = etMejaPrice.text.toString()
            binding.tvMejaDesc2.text = etMejaDesc.text.toString()

            alertDialog.dismiss()
        }


    }

    private fun updateMejaData(
        id: String,
        name: String,
        price: String,
        desc: String
    ) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Meja").child(id)
        val mejaInfo = MejaModel(id, name, price, desc)
        dbRef.setValue(mejaInfo)
    }
}