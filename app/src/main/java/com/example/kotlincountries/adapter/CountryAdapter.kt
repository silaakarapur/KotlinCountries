package com.example.kotlincountries.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlincountries.R
import com.example.kotlincountries.databinding.CountryItemBinding
import com.example.kotlincountries.model.Country
import com.example.kotlincountries.util.downloadFromUrl
import com.example.kotlincountries.util.placeholderProgressBar
import com.example.kotlincountries.view.FeedFragmentDirections
import kotlinx.android.synthetic.main.country_item.view.*

class CountryAdapter(val countryList: ArrayList<Country>) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>(),CountryClickListener {
    class CountryViewHolder(val view: CountryItemBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        //val view = inflater.inflate(R.layout.country_item, parent, false)
        val view=DataBindingUtil.inflate<CountryItemBinding>(inflater,R.layout.country_item,parent,false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {

        holder.view.country=countryList[position]
        holder.view.listener=this

//        holder.view.name.text = countryList[position].countryName
//        holder.view.region.text = countryList[position].countryRegion
//
//        holder.view.setOnClickListener {
//            val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment()
//            action.countryUuid = countryList[position].uuid
//            Navigation.findNavController(it).navigate(action)
//        }
//        holder.view.imageView.downloadFromUrl(
//            countryList[position].imageUrl,
//            placeholderProgressBar(holder.view.context)
//        )
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    fun updateCountryList(newCountryList: List<Country>) {
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }

    override fun onCountryClicked(v: View) {
        val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment()
       action.countryUuid =v.countryUUIDText.text.toString().toInt()
  Navigation.findNavController(v).navigate(action)
    }
}