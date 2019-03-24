package net.azarquiel.pokedex.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.rowpokemon.view.*
import net.azarquiel.pokedex.model.Results

class CustomAdapter(val context: Context,
                    val layout: Int,
                    val dataList: List<Results>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewlayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewlayout, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Results){
            // itemview es el item de diseño
            // al que hay que poner los datos del objeto dataItem
            itemView.tvnombreRow.text=dataItem.name
            var numero=dataItem.url.substring(42,dataItem.url.length-1)
            itemView.tvnumeroRow.text=numero
            // Si la foto viene de Internet
            // implementation 'com.squareup.picasso:picasso:2.5.2'
            Picasso.with(context).load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$numero.png").into(itemView.ivrow)
            itemView.setTag(dataItem)
        }

    }
}