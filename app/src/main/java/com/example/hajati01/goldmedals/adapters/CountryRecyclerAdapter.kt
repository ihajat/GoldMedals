package com.example.hajati01.goldmedals.adapters

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.ahmadrosid.svgloader.SvgLoader
import com.example.hajati01.goldmedals.Country
import com.example.hajati01.goldmedals.R
import com.example.hajati01.goldmedals.utils.Constants


class CountryRecyclerAdapter(countries: ArrayList<Country>, listener: OnItemClickListener, act: Activity) : RecyclerView.Adapter<CountryRecyclerAdapter.RecyclerViewHolder>() {

    private var listCountries: List<Country> = countries

    private val activity: Activity = act

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
        val goldsCountry = currentCountry.golds
        val silversCountry = currentCountry.silvers
        val bronzesCountry = currentCountry.bronzes
        val imageUrl = currentCountry.flag

        holder?.apply{
            mName.text = nameCountry
            mGolds.text = goldsCountry.toString()
            mSilvers.text = silversCountry.toString()
            mBronzes.text = bronzesCountry.toString()

            if(!holder.mFlag.equals(Constants.file_not_found)) {
                SvgLoader.pluck()
                        .with(activity)
                        .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                        .load(imageUrl, holder.mFlag)
            }

            bind(currentCountry, listenerContact)
        }

    }

    fun addCountries(listCountries: List<Country>) {
        this.listCountries = listCountries
        notifyDataSetChanged()
    }

    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mName = itemView.findViewById<TextView>(R.id.name_country)
        val mGolds = itemView.findViewById<TextView>(R.id.golds_country)
        val mSilvers = itemView.findViewById<TextView>(R.id.silvers_country)
        val mBronzes = itemView.findViewById<TextView>(R.id.bronzes_country)
        val mFlag = itemView.findViewById<ImageView>(R.id.country_flag_image_view)

        fun bind(country: Country, listener: OnItemClickListener) {
            itemView.setOnClickListener {
                listener.onItemClick(country)
            }
        }

    }
}