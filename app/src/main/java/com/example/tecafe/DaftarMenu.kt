package com.example.tecafe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class DaftarMenu : AppCompatActivity() {

    private lateinit var menuRecyclerView: RecyclerView
    private lateinit var menuList: ArrayList<MenuModel>
    private lateinit var dbRef: DatabaseReference
    private lateinit var tvLoadingData : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar_menu)

        tvLoadingData = findViewById(R.id.tvLoadingData)

        /* The code snippet you provided is used to configure a RecyclerView in an Android application.
        By using these lines of code, you initialize and configure the RecyclerView. You assign it to the mejaRecyclerView variable,
        set a layout manager to specify how the items should be arranged, and indicate whether the RecyclerView's size is fixed
        or can change dynamically..
        * mejaRecyclerView = findViewById(R.id.rvMeja): This line retrieves the RecyclerView from the layout XML file by using its ID (R.id.rvMeja). */
        menuRecyclerView = findViewById(R.id.rvMenu)

        /* mejaRecyclerView.layoutManager = LinearLayoutManager(this): This line sets the layout manager for the RecyclerView.
        In this case, it sets a LinearLayoutManager as the layout manager. The LinearLayoutManager arranges the items in a vertical
        or horizontal linear list, depending on the orientation specified. The this parameter refers to the current activity or context. */
        menuRecyclerView.layoutManager = LinearLayoutManager(this)

        /* mejaRecyclerView.setHasFixedSize(true): This line sets the fixed size property of the RecyclerView.
        When you set it to true, it means that the RecyclerView has a fixed size and won't change dynamically.
        This can improve performance if the RecyclerView's size is not affected by the content. However, if the size of the
        RecyclerView can change, you should set it to false. */
        menuRecyclerView.setHasFixedSize(true)

        /* The code mejaList = arrayListOf<MejaModel>() initializes an empty ArrayList of MejaModel objects and assigns it to the variable mejaList
        *
        * arrayListOf<MejaModel>(): This is a function that creates a new instance of an ArrayList.
        * In this case, the ArrayList is specifically declared to hold objects of the MejaModel class.
        * The MejaModel class represents a model or structure for a menu item or entity.
        *
        * mejaList: mejaList is the variable to which the newly created ArrayList is assigned.
        * It is assumed that mejaList is declared as a variable of type ArrayList<MejaModel>

        * By calling arrayListOf<MejaModel>(), you create a new ArrayList object that can hold MejaModel objects.
        * This ArrayList is then assigned to the mejaList variable, allowing you to add, remove, or modify MejaModel objects within the ArrayList.*/
        menuList = arrayListOf<MenuModel>()


        getMenuData()
    }

    private fun getMenuData() {

        tvLoadingData.visibility = View.VISIBLE
        menuRecyclerView.visibility = View.GONE


        dbRef = FirebaseDatabase.getInstance().getReference("Menu")

        /* The code snippet you provided sets up a ValueEventListener to listen for changes in data at a specific DatabaseReference (dbRef)
        *
        * addValueEventListener: This method is used to add a ValueEventListener to the DatabaseReference.
        * It registers the listener to listen for changes in the data at the specified location.
        *
        * object : ValueEventListener { ... }: This is an anonymous object declaration that implements the ValueEventListener interface.
        * It allows you to override the necessary callback methods defined by the interface*/
        dbRef.addValueEventListener(object : ValueEventListener {

            /* onDataChange(snapshot: DataSnapshot): This is one of the callback methods provided by the ValueEventListener interface.
            It is triggered when the data at the specified DatabaseReference changes. */
            override fun onDataChange(snapshot: DataSnapshot) {
                /* The code mejaList.clear() clears all the elements from the mejaList ArrayList.
                *
                * By calling mejaList.clear(), you remove all the elements from the mejaList ArrayList, resulting in an empty list.
                * This operation is useful when you want to clear the existing data in the list and start fresh or
                * when you need to update the list with new data retrieved from a data source.*/
                menuList.clear()

                /* The code if (snapshot.exists()) checks whether the snapshot object exists or contains data.
                *
                * snapshot: It refers to the DataSnapshot object passed as a parameter to the onDataChange() method of a ValueEventListener.
                * The DataSnapshot object represents a particular location's data in the Firebase Realtime Database at the time of the event. */
                if (snapshot.exists()) {  // Snapshot means Data

                    /* The code for (mejaSnap in snapshot.children) is a loop that iterates over the child nodes of a snapshot object.
                    *
                    * children: children is a property provided by the DataSnapshot class in Firebase.
                    * It returns an Iterable<DataSnapshot> object representing the child nodes of the current snapshot.
                    *
                    * mejaSnap: It is a variable that represents each child node (DataSnapshot) iterated in the loop. You can choose any variable name you prefer.
                    *
                    * By using the for loop with mejaSnap as the loop variable and snapshot.children as the iterable, you can iterate over each child node under the specified snapshot object.*/
                    for (menuSnap in snapshot.children) { // mengambil data dari Menu child

                        /* The code val mejaData = mejaSnap.getValue(MejaModel::class.java) retrieves the data from a specific
                        DataSnapshot object (mejaSnap) and maps it to an instance of the MejaModel class.

                        mejaSnap: It represents an individual child node DataSnapshot obtained from iterating over the child nodes of a snapshot.

                        getValue(MejaModel::class.java): getValue() is a method provided by the DataSnapshot class in Firebase.
                        It is used to retrieve the value of the DataSnapshot and convert it into a specific class type.
                        In this case, MejaModel::class.java is used to specify that the retrieved data should be mapped to an
                        instance of the MejaModel class.

                        mejaData: It is a variable of type MejaModel that holds the mapped data from the mejaSnap object.

                        By calling mejaSnap.getValue(MejaModel::class.java), you extract the data from the mejaSnap object and attempt to convert
                        it into an instance of the MejaModel class. The MejaModel class should have a corresponding structure and properties that
                        match the data stored in the mejaSnap.*/
                        val menuData = menuSnap.getValue(MenuModel::class.java) // referencing class MenuModel untuk mendapat semua data dari kelas tersebut

                        /* The code mejaList.add(mejaData!!) adds the mejaData object to the mejaList ArrayList.

                        add(mejaData!!): add() is a method provided by the ArrayList class in Kotlin. It is used to add an element to the ArrayList.
                        In this case, the element being added is the mejaData object.

                        mejaData!!: The !! is the Kotlin "non-null assertion" operator. It is used to assert that the mejaData object is not null.
                        If mejaData is null, a NullPointerException will be thrown.

                        By calling mejaList.add(mejaData!!), the mejaData object is appended to the mejaList ArrayList, effectively adding it as
                        a new element at the end of the list. This operation allows you to store and accumulate MejaModel objects within the ArrayList,
                        enabling you to keep track of multiple MejaModel instances or data retrieved from the Firebase Realtime Database.*/
                        menuList.add(menuData!!) // !! untuk menghindari null data
                    }
                    val mAdapter = MenuAdapter(menuList)
                    menuRecyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : MenuAdapter.onItemClickListener {
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@DaftarMenu, MenuDetails::class.java)

                            // put extras
                            intent.putExtra("menuId", menuList[position].menuId)
                            intent.putExtra("menuName", menuList[position].menuName)
                            intent.putExtra("menuPrice", menuList[position].menuPrice)
                            intent.putExtra("menuDesc", menuList[position].menuDesc)
                            startActivity(intent)

                        }

                    })

                    tvLoadingData.visibility = View.GONE
                    menuRecyclerView.visibility = View.VISIBLE

                }


            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}