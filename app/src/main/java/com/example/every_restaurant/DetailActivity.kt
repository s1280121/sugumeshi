package com.example.every_restaurant

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
//import androidx.data-binding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.every_restaurant.data.Restaurant
import com.example.every_restaurant.data.Term
import com.example.every_restaurant.databinding.ActivityDetailBinding


class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private var isSelected = false
    private var isSelected2 = false
    private var isSelected3 = false
    private var isSelected4 = false
    private var isSelected5 = false
    private var isSelected6 = false

    private lateinit var term: Term
    private lateinit var shop: Restaurant

    companion object {
        const val SEARCH_TERM = "term"
        const val RESTAURANT_DATA = "restaurantData"
    }


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_detail)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.title = "店舗詳細"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

//        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

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
            val expandArrow1 : ImageView = findViewById(R.id.expand_arrow1)
            if(isSelected) expandArrow1.startAnimation(expandAnimation())
            else expandArrow1.startAnimation(closeAnimation())
        }
        binding.titleLayout2.setOnClickListener {
            binding.expandableLayout2.toggle()
            isSelected2 = !isSelected2
            val expandArrow2 : ImageView = findViewById(R.id.expand_arrow2)
            if(isSelected2) expandArrow2.startAnimation(expandAnimation())
            else expandArrow2.startAnimation(closeAnimation())
        }
        binding.titleLayout3.setOnClickListener {
            binding.expandableLayout3.toggle()
            isSelected3 = !isSelected3
            val expandArrow3 : ImageView = findViewById(R.id.expand_arrow3)
            if(isSelected3) expandArrow3.startAnimation(expandAnimation())
            else expandArrow3.startAnimation(closeAnimation())
        }
        binding.titleLayout4.setOnClickListener {
            binding.expandableLayout4.toggle()
            isSelected4 = !isSelected4
            val expandArrow4 : ImageView = findViewById(R.id.expand_arrow4)
            if(isSelected4) expandArrow4.startAnimation(expandAnimation())
            else expandArrow4.startAnimation(closeAnimation())
        }
        binding.titleLayout5.setOnClickListener {
            binding.expandableLayout5.toggle()
            isSelected5 = !isSelected5
            val expandArrow5 : ImageView = findViewById(R.id.expand_arrow5)
            if(isSelected5) expandArrow5.startAnimation(expandAnimation())
            else expandArrow5.startAnimation(closeAnimation())
        }
        binding.titleLayout6.setOnClickListener {
            binding.expandableLayout6.toggle()
            isSelected6 = !isSelected6
            val expandArrow6 : ImageView = findViewById(R.id.expand_arrow6)
            if(isSelected6) expandArrow6.startAnimation(expandAnimation())
            else expandArrow6.startAnimation(closeAnimation())
        }
    }

    private fun expandAnimation(): Animation {
        return RotateAnimation(
            0f, 180f, Animation.RELATIVE_TO_SELF,
            0.5f, Animation.RELATIVE_TO_SELF,
            0.5f
        ).apply {
            duration = 300
            fillAfter = true }
    }
    private fun closeAnimation(): Animation {
        return RotateAnimation(
            180f, 360f, Animation.RELATIVE_TO_SELF,
            0.5f, Animation.RELATIVE_TO_SELF,
            0.5f
        ).apply {
            duration = 300
            fillAfter = true }
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
