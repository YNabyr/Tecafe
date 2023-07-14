package com.example.tecafe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tecafe.databinding.ActivityAddMenuBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddMenuActivity : AppCompatActivity() {

    // Declare variables
    private lateinit var binding: ActivityAddMenuBinding

    /* FirebaseDatabase is a class provided by the Firebase Realtime Database library,
    and it represents the entry point for accessing the Firebase Realtime Database service.*/
    private lateinit var firebaseDatabase: FirebaseDatabase

    /* DatabaseReference is a class provided by the Firebase Realtime Database library,
    and it represents a specific location in the Firebase Realtime Database.
    It can be used to read from or write to that particular location in the database.*/
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /* This line initializes the firebaseDatabase variable with an instance of FirebaseDatabase.
        The getInstance() method is a static method provided by the FirebaseDatabase class, and it returns a singleton instance of FirebaseDatabase.
        This singleton instance represents the entry point for accessing the Firebase Realtime Database service. */
        firebaseDatabase = FirebaseDatabase.getInstance()

        /* This line initializes the databaseReference variable with a DatabaseReference object obtained from the firebaseDatabase instance.
        The getReference() method is used to get a DatabaseReference to a specific location in the database.
        In this case, the location is "Menu", which represents a node or path within the database.
        You can replace "Menu" with the desired path or node in your database. */
        databaseReference = FirebaseDatabase.getInstance().getReference("Menu")

        binding.btnAddMenu.setOnClickListener {
            /* text is a property or method provided by the EditText view. It allows you to access the text content of the EditText.
            * toString(): The toString() method is used to convert the text content, which is of type Editable, to a String.
            * This ensures that the value stored in menuName is of type String.
            * By calling binding.edtMenu.text.toString(), you obtain the text entered by the user in the EditText view as a String.
            * This String value is then assigned to the variable menuName, allowing you to use it in subsequent parts of your code. */
            val menuName = binding.edtMenu.text.toString()
            val menuPrice = binding.edtPriceMenu.text.toString()
            val menuDesc = binding.edtDescMenu.text.toString()

            /*  checks if three variables (menuName, menuPrice, and menuDesc) are not empty before executing the code inside the if statement.  */
            if (menuName.isNotEmpty() && menuPrice.isNotEmpty() && menuDesc.isNotEmpty()) {

                /* The code val menuId = databaseReference.push().key!! generates a unique key for a new child node
                in the Firebase Realtime Database under the databaseReference location.

                push(): The push() method is used on a DatabaseReference to generate a unique key.
                When push() is called, it creates a new child node under the specified reference location and
                returns a DatabaseReference pointing to that newly generated child node.

                key: The key property is used on a DatabaseReference to retrieve the generated key for a child node.
                It returns the unique identifier/key associated with the child node.

                !!: The double exclamation mark is the Kotlin "non-null assertion operator". It asserts that the value on the left side is not null.
                In this case, it is used after key to assert that it is not null. If the value is null, a NullPointerException will be thrown.

                By calling databaseReference.push().key!!, a new child node is created under the specified databaseReference location,
                and a unique key is generated for that child node. The key is then assigned to the variable menuId,
                which can be used to uniquely identify the newly created child node in subsequent operations,
                such as storing data under that node or referencing it in other parts of the code.*/
                val menuId = databaseReference.push().key!!

                /* MejaModel: MejaModel is the name of a class or data model used in the code. It represents a model or structure that holds
                data related to a menu item or entity.

                (menuId, menuName, menuPrice, menuDesc): These are the arguments passed to the constructor of the MejaModel class.
                Based on the order of the arguments, it seems that the MejaModel class constructor expects four parameters:
                menuId, menuName, menuPrice, and menuDesc.

                menuId, menuName, menuPrice, menuDesc: These are variables that hold the corresponding values required to create an
                instance of the MejaModel class. The values are provided as arguments to the constructor.

                By calling MejaModel(menuId, menuName, menuPrice, menuDesc), a new instance of the MejaModel class is created
                with the provided values. The constructor of the MejaModel class initializes the instance with the passed values,
                allowing you to work with an object that represents a menu item or entity with the specified attributes.

                The resulting instance is then assigned to the variable menu, which can be used to reference and manipulate the
                properties and methods of the MejaModel class instance in subsequent parts of the code.*/
                val menu = MenuModel(menuId, menuName, menuPrice, menuDesc)

                /* The code databaseReference.child(menuId).setValue(menu) is used to store the menu object in the
                Firebase Realtime Database under a child node with the specified menuId

                child(menuId): The child() method is used on a DatabaseReference to create a reference to a child node under the current reference.
                In this case, it creates a child node with the name or identifier specified by menuId.
                This allows you to access or modify the child node under menuId.

                setValue(menu): The setValue() method is used to set the value of the specified menu object under the child node created in
                the previous step. It writes the value to the specified location in the Firebase Realtime Database.

                By calling databaseReference.child(menuId).setValue(menu), you are creating a child node with the specified
                menuId under the databaseReference location and setting its value to the menu object.
                This effectively stores the menu data in the Firebase Realtime Database under the child node with the given menuId.

                The menuId is typically a unique identifier generated for the menu item, and menu represents the data or
                object you want to store under that particular child node. This allows you to organize and retrieve the data in
                a structured manner within the Firebase Realtime Database.*/
                databaseReference.child(menuId).setValue(menu) // Create child using menuId
                    .addOnCompleteListener {
                        Toast.makeText(this, "Tambah Menu Sukses", Toast.LENGTH_SHORT).show()
                        binding.edtMenu.text?.clear()
                        binding.edtPriceMenu.text?.clear()
                        binding.edtDescMenu.text?.clear()
                    }.addOnFailureListener { err ->
                        Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Field Text cannot be empty", Toast.LENGTH_SHORT).show()
            }

        }

    }
}