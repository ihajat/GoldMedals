package com.example.hajati01.goldmedals.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.hajati01.goldmedals.Country
import com.example.hajati01.goldmedals.R

class CountryRecyclerAdapter(countries: ArrayList<Country>, listener: OnItemClickListener) : RecyclerView.Adapter<CountryRecyclerAdapter.RecyclerViewHolder>() {

    private var listCountries: List<Country> = countries

    private val listenerContact: OnItemClickListener = listener

    interface OnItemClickListener {
        fun onItemClick(country: Country)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_list, parent, false))
    }

    override fun getItemCount(): Int {
        return listCountries.size
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder?, position: Int) {
        val currentCountry: Country = listCountries[position]

        val nameCountry = currentCountry.name
        val numberCountry = currentCountry.golds

        holder!!.mName.text = nameCountry
        holder.mNumber.text = numberCountry.toString()

        holder.bind(currentCountry, listenerContact)

    }

    fun addCountries(listCountries: List<Country>) {
        this.listCountries = listCountries
        notifyDataSetChanged()
    }

    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mName = itemView.findViewById<TextView>(R.id.name_country)
        val mNumber = itemView.findViewById<TextView>(R.id.golds_country)

        fun bind(country: Country, listener: OnItemClickListener) {
            itemView.setOnClickListener {
                listener.onItemClick(country)
            }
        }

    }
}