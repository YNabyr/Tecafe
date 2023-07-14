package com.example.tecafe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/* The code class MejaAdapter(private val mejaList: ArrayList<MejaModel>) : RecyclerView.Adapter<MejaAdapter.ViewHolder>() defines a
custom adapter class called MejaAdapter that extends the RecyclerView.Adapter class.

class MejaAdapter: This line declares the MejaAdapter class.

private val mejaList: ArrayList<MejaModel>: It declares a private property mejaList of type ArrayList<MejaModel>.
This property holds the data that the adapter will display in the RecyclerView.
The ArrayList<MejaModel> type indicates that mejaList will store objects of the MejaModel class.

RecyclerView.Adapter<MejaAdapter.ViewHolder>(): This part indicates that MejaAdapter is a subclass of RecyclerView.Adapter.
It specifies the type of the ViewHolder class that the adapter will use.

The RecyclerView.Adapter is a base class for adapting data to be displayed in a RecyclerView.
By extending this class, you can create a custom adapter that binds your data to the views within the RecyclerView.

The MejaAdapter class, in particular, takes the mejaList as a constructor parameter, allowing you to pass the data
to the adapter when creating an instance of the adapter. The adapter can then use this data to populate the views in the RecyclerView.*/

class MenuAdapter(private val menuList: ArrayList<MenuModel>) :
    RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    /* private lateinit var mListener: onItemClickListener:
    This line declares a private property mListener of type onItemClickListener.
    onItemClickListener is an interface that is defined within the same class. */
    private lateinit var mListener: onItemClickListener

    /* interface onItemClickListener: This code declares an interface named onItemClickListener.
    It defines a single method onItemClick(position: Int).
     This interface serves as a contract for handling item click events in the adapter.*/
    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    /*f un setOnItemClickListener(clickListener: onItemClickListener):
    This is a setter method that allows setting an instance of onItemClickListener as the listener for item clicks in the adapter.*/
    fun setOnItemClickListener(clickListener: onItemClickListener) {

        /* mListener = clickListener: This line assigns the provided clickListener to the mListener property.
        By doing this, the adapter class can store a reference to the listener instance.*/
        mListener = clickListener
    }


    /* fun onCreateViewHolder(parent: ViewGroup, viewType: Int): This line declares the onCreateViewHolder method. It takes two parameters:
   parent: It represents the parent ViewGroup into which the created ViewHolder will be added.
   viewType: It represents the type of the view, which can be useful if the adapter needs to support multiple view types.
   ViewHolder: This specifies the return type of the method, which is the ViewHolder class defined within the MejaAdapter class.*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        /* val itemView = LayoutInflater.from(parent.context).inflate(R.layout.menu_list_item, parent, false):
        This line inflates a layout XML file (R.layout.menu_list_item) to create the item view for the RecyclerView.
        The LayoutInflater.from(parent.context) obtains a LayoutInflater instance from the parent's context,
        and inflate() is called to inflate the layout XML file. The resulting view is assigned to the itemView variable.

        return ViewHolder(itemView, mListener): This line returns a new instance of the ViewHolder class,
        passing the itemView and mListener as parameters. The ViewHolder is responsible for holding references to
        the views within each item of the RecyclerView.*/
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.menu_list_item, parent, false)
        return ViewHolder(itemView, mListener)
    }


    /* override fun onBindViewHolder(holder: ViewHolder, position: Int):
    This line indicates that you are overriding the onBindViewHolder() method inherited from the RecyclerView.Adapter class.
    The method takes two parameters:
    holder: It is an instance of the ViewHolder class that represents the view holder for the current item.
    position: It is the position of the item in the data list that needs to be bound to the view holder. */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        /* The code val currentMenu = menuList[position] retrieves the data item at the specified position from the menuList ArrayList.
        *
        * menuList: It is assumed to be an ArrayList that holds objects of some type, likely representing menu items.
        *
        * position: It is the index of the item in the ArrayList that you want to retrieve.
        *
        * currentMenu: It declares a new variable currentMenu to store the data item at the specified position.
        *
        * By accessing the element at the given position in the menuList ArrayList, you can retrieve the corresponding menu item object.
        * The retrieved object is then assigned to the currentMenu variable. */
        val currentMenu = menuList[position]

        // Get the Text View of currentMenu and Set Text View to menu_list_item
        holder.tvMenuName.text = currentMenu.menuName
        holder.tvMenuPrice.text = currentMenu.menuPrice
        holder.tvMenuDesc.text = currentMenu.menuDesc
        holder.btnEdit
        holder.btnTambah
    }

    override fun getItemCount(): Int {


        return menuList.size
    }

    class ViewHolder(itemView: View, clickListener: onItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        // Initialize menu_list_item
        val tvMenuName: TextView = itemView.findViewById(R.id.tvMenuName)
        val tvMenuPrice: TextView = itemView.findViewById(R.id.tvMenuPrice)
        val tvMenuDesc: TextView = itemView.findViewById(R.id.tvMenuDesc)
        val btnEdit: Button = itemView.findViewById(R.id.btnEdtMenu)
        val btnTambah: Button = itemView.findViewById(R.id.btnDeleteMenu)


        init {
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }
    }
}

