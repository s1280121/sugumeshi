package com.example.every_restautant

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.every_restautant.data.Restaurant
import com.example.every_restautant.data.Term
import com.example.every_restautant.databinding.ActivityDetailBinding


class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private var isSelected = false
    private lateinit var term: Term
    private lateinit var shop: Restaurant

    companion object {
        const val SEARCH_TERM = "term"
        const val RESTAURANT_DATA = "restaurantData"
    }


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.title = "店舗詳細"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        shop = intent.getSerializableExtra(SearchActivity.RESTAURANT_DATA) as Restaurant
        term = intent.getSerializableExtra(SearchActivity.SEARCH_TERM) as Term


        binding.tvGenre.text = HtmlCompat.fromHtml("<u>${shop.genre.name}</u>", HtmlCompat.FROM_HTML_MODE_COMPACT)
        binding.tvName.text = shop.name
        binding.tvAddress.text = shop.address

        binding.tvCatch.text = shop.catch
        if(binding.tvCatch.text.isEmpty()) binding.tvCatch.visibility = View.GONE

        binding.tvOpenTime.text = shop.open
        binding.tvCloseTime.text = shop.close
        if(binding.tvOpenTime.text.isEmpty() && binding.tvCloseTime.text.isEmpty()) binding.card1.visibility = View.GONE

        binding.tvAccess.text = shop.access
        if(binding.tvAccess.text.isEmpty()) binding.card2.visibility = View.GONE

        binding.tvCard.text = shop.card
        if(binding.tvCard.text.isEmpty()) binding.card3.visibility = View.GONE

        binding.tvSmoking.text = shop.non_smoking
        if(binding.tvSmoking.text.isEmpty()) binding.card4.visibility = View.GONE

        binding.tvParking.text = shop.parking
        if(binding.tvParking.text.isEmpty()) binding.card5.visibility = View.GONE

        binding.tvBudget.text = shop.budget.average
        if(binding.tvBudget.text.isEmpty()) binding.card6.visibility = View.GONE

        Glide.with(this)
            .load(shop.photo.pc.l)
            .into(binding.ivPhoto)


        binding.titleLayout1.setOnClickListener {
            binding.expandableLayout1.toggle()
            isSelected = !isSelected
            val expand_arrow1 : ImageView = findViewById(R.id.expand_arrow1)
            expand_arrow1.startAnimation(animation())
        }
        binding.titleLayout2.setOnClickListener {
            binding.expandableLayout2.toggle()
            isSelected = !isSelected
            val expand_arrow2 : ImageView = findViewById(R.id.expand_arrow2)
            expand_arrow2.startAnimation(animation())
        }
        binding.titleLayout3.setOnClickListener {
            binding.expandableLayout3.toggle()
            isSelected = !isSelected
            val expand_arrow3 : ImageView = findViewById(R.id.expand_arrow3)
            expand_arrow3.startAnimation(animation())
        }
        binding.titleLayout4.setOnClickListener {
            binding.expandableLayout4.toggle()
            isSelected = !isSelected
            val expand_arrow4 : ImageView = findViewById(R.id.expand_arrow4)
            expand_arrow4.startAnimation(animation())
        }
        binding.titleLayout5.setOnClickListener {
            binding.expandableLayout5.toggle()
            isSelected = !isSelected
            val expand_arrow5 : ImageView = findViewById(R.id.expand_arrow5)
            expand_arrow5.startAnimation(animation())
        }
        binding.titleLayout6.setOnClickListener {
            binding.expandableLayout6.toggle()
            isSelected = !isSelected
            val expand_arrow6 : ImageView = findViewById(R.id.expand_arrow6)
            expand_arrow6.startAnimation(animation())
        }
    }

    private fun animation(): Animation {
        if(isSelected) {
            return RotateAnimation(
                0f, 180f, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF,
                0.5f
            ).apply {
                duration = 300
                fillAfter = true }
        } else {
            return RotateAnimation(
                180f, 360f, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF,
                0.5f
            ).apply {
                duration = 300
                fillAfter = true }
        }
    }


    //ActionBarの戻るボタン
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
