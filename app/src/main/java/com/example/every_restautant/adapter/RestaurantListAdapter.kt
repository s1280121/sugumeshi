package com.example.every_restautant.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.every_restautant.R
import com.example.every_restautant.data.Restaurant

//RecyclerViewのAdapter

class RestaurantListAdapter(private var restaurantList: List<Restaurant>, var clickListener: ((Restaurant) -> Unit)?) : RecyclerView.Adapter<RestaurantListAdapter.RestaurantListHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RestaurantListHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.restautant_list, parent, false)
        return RestaurantListHolder(view)
    }

    override fun onBindViewHolder(holder: RestaurantListHolder, position: Int) {
        holder.bind(restaurantList.get(position))
    }

    override fun getItemCount(): Int {
        return restaurantList.size
    }

    fun setOnClickListener(listener: (Restaurant) -> Unit) {
        this.clickListener = listener
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setRestaurantList(restaurantList: List<Restaurant>) {
        this.restaurantList = restaurantList
        notifyDataSetChanged()
    }

    inner class RestaurantListHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var ivThum: ImageView = view.findViewById(R.id.ivThum)
        private var tvThumName: TextView = view.findViewById(R.id.tvThumName)
        private var tvThumAccess: TextView = view.findViewById(R.id.tvThumAccess)

        fun bind(shop: Restaurant) {
            tvThumName.text = shop.name
            tvThumAccess.text = shop.access

            Glide.with(ivThum.context)
                .load(shop.photo.pc.l)
                .into(ivThum)

            itemView.setOnClickListener {
                clickListener?.invoke(shop)
            }
        }

    }
}

