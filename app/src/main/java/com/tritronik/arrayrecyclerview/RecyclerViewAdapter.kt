package com.tritronik.arrayrecyclerview

import android.content.Context

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide

// Untuk menambahkan onClik Listener :
//  > tambahkan parameter berupa Unit dari data class Item pada RecyclerViewAdapter & bindItem
//  > Parameter tersebut ( private val listener: (item) -> unit) diakses dari dalam fungsi setOnClickListener
class RecyclerViewAdapter(private val context: Context, private val items: List<Item>, private val listener: (Item) -> Unit )
    : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

 /*   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener) // Tambah listener untuk onClick
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val name = view.findViewById<TextView>(R.id.name)
        private val image = view.findViewById<ImageView>(R.id.image)

        // Tambah listener: (Item) -> Unit
        fun bindItem(items: Item, listener: (Item) -> Unit) {
            name.text = items.name
            Glide.with(itemView.context).load(items.image).into(image)

            // Tambah fungsi setOnClickListener
            itemView.setOnClickListener{ listener(items)}
        }

        /* Note : Selanjutnya setelah membuat listener untuk handle onClick di recyclerview
         edit file MainActivity.kt --> tambah kode dibawah ini :

         list.adapter = RecyclerViewAdapter(this, items){
         val toast = Toast.makeText(applicationContext, it.name, Toast.LENGTH_SHORT)
         toast.show()

         */
    }
}