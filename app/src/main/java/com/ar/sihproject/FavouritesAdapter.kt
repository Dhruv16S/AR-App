package com.ar.sihproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView

class FavouritesAdapter : RecyclerView.Adapter<FavouritesAdapter.FavViewHolder> {
        var list = ArrayList<String>()


        constructor(
            list: ArrayList<String>,
        ) {
            this.list = list
        }

        class FavViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            var monumentName : TextView = itemView.findViewById(R.id.mName)
            var button : Button = itemView.findViewById(R.id.button)
            var cardView : CardView = itemView.findViewById(R.id.cardView)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
        //define the card design we made aka which design is displayed
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.card_design, parent, false)
        return FavViewHolder(view)

    }

    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
        holder.monumentName.text = list.get(position)
        }

    override fun getItemCount(): Int {
        return list.size
    }
}


