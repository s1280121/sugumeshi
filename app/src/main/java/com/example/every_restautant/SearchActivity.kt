package com.example.every_restautant

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.every_restaurant.viewModel.ReataurantViewModel
import com.example.every_restautant.adapter.RestaurantListAdapter
import com.example.every_restautant.data.Term
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


class SearchActivity : AppCompatActivity() {
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
        if (isGranted) {
            fusedLocation()
        } else {
            Toast.makeText(this, "設定から位置情報をオンにしてください", Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        supportActionBar?.title = "店舗一覧"

        val loadingText : TextView = findViewById(R.id.loadingText)
        val progressBar: ProgressBar = findViewById(R.id.ProgressBar)
        val linearLayout : LinearLayout = findViewById(R.id.linearLayout)
        val restaurantRecyclerView : RecyclerView = findViewById(R.id.restaurantRecyclerView)

        progressBar.max= 100
        progressBar.progress = 75
        progressBar.min = 0

        progressBar.visibility = View.VISIBLE
        loadingText.visibility = View.VISIBLE
        linearLayout.visibility = View.GONE
        restaurantRecyclerView.visibility = View.GONE

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissionLauncher.launch(
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        } else {
            fusedLocation()
        }
    }

    private val term = Term(0.0, 0.0,"", 0)
    private val viewModel by viewModels<ReataurantViewModel>()
    companion object {
        const val SEARCH_TERM = "term"
        const val RESTAURANT_DATA = "restaurantData"
    }

    private fun fusedLocation() {
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        val fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        fusedLocationClient.lastLocation
            .addOnSuccessListener(this) { location ->
                if (location == null) {
                    //新宿駅の緯度経度を検索データへ格納
//                    term.lat = 35.690921
//                    term.lng = 139.70025799999996
                    term.lat = 37.52393597142831
                    term.lng = 139.93747178250905
                }
                else{
                    //現在地の緯度経度を検索データへ格納
                    term.lat = location.latitude
                    term.lng = location.longitude
                }

                val loadingText : TextView = findViewById(R.id.loadingText)
                val progressBar: ProgressBar = findViewById(R.id.ProgressBar)
                val linearLayout : LinearLayout = findViewById(R.id.linearLayout)
                val restaurantRecyclerView : RecyclerView = findViewById(R.id.restaurantRecyclerView)

                progressBar.visibility = View.GONE
                linearLayout.visibility = View.VISIBLE
                restaurantRecyclerView.visibility = View.VISIBLE

                loadingText.text = "キーワードや範囲を選び、\n検索ボタンを押してください"
                val spinner : Spinner = findViewById(R.id.spinner)

                val spinnerAdapter = ArrayAdapter(
                    this,
                    R.layout.custom_spinner,
                    resources.getStringArray(R.array.spinnerItems)
                )
                spinnerAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown)
                spinner.adapter = spinnerAdapter

                spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    // 項目が選択された時に呼ばれる
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long,
                    ) {
                        when (parent?.selectedItem as String) {
                            "300m以内" -> { term.range = 1 }
                            "500m以内" -> { term.range = 2 }
                            "1000m以内" -> { term.range = 3 }
                            "2000m以内" -> { term.range = 4 }
                            "3000m以内" -> { term.range = 5 }
                        }
                    }
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }
                }

                val reAdapter = RestaurantListAdapter(restaurantList= emptyList(), clickListener=null)
                restaurantRecyclerView.adapter = reAdapter
                restaurantRecyclerView.layoutManager = LinearLayoutManager(this)
                //restaurantRecyclerView.layoutManager = GridLayoutManager(this, 2) // 1列2店

                val searchButton: ImageButton = findViewById(R.id.searchButton)

                searchButton.setOnClickListener {
                    val keywordField : EditText = findViewById(R.id.keyword)
                    term.keyword = keywordField.text.toString()


                    viewModel.getReataurantData(term)
                    viewModel.restaurantDataList.observe(this)  {
                        reAdapter.setRestaurantList(it)
                        if(it.isNotEmpty()){ loadingText.visibility = View.GONE }
                        var rangeText = ""
                        if(it.isEmpty()) {
                            loadingText.visibility = View.VISIBLE
                            when (term.range) {
                                1 -> { rangeText = "300m以内" }
                                2 -> { rangeText = "500m以内" }
                                3 -> { rangeText = "1000m以内" }
                                4 -> { rangeText = "2000m以内" }
                                5 -> { rangeText = "3000m以内" }
                            }
                        }
                        loadingText.text = "${rangeText}に該当する\nレストランはありません"
                    }
                }
                reAdapter.setOnClickListener {
                    val intent = Intent(this, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.SEARCH_TERM, term)
                    intent.putExtra(DetailActivity.RESTAURANT_DATA, it)
                    startActivity(intent)
                }
            }
    }
}

