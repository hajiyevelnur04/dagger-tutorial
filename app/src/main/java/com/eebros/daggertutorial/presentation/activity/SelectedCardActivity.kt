package com.eebros.daggertutorial.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.eebros.daggertutorial.R
import kotlinx.android.synthetic.main.activity_selected_card.*
import kotlinx.android.synthetic.main.custom_toolbar_layout_back.*

class SelectedCardActivity : AppCompatActivity() {

    private val name: String by lazy { intent.getStringExtra("name") }
    private val desc: String by lazy { intent.getStringExtra("desc") }
    private val type: String by lazy { intent.getStringExtra("type") }
    private val image: String by lazy { intent.getStringExtra("image") }
    private val race: String by lazy { intent.getStringExtra("race") }
    private val archetype: String by lazy { intent.getStringExtra("archetype") }
    private val id: String by lazy { intent.getStringExtra("id") }
    private val setName: String by lazy { intent.getStringExtra("setName") }
    private val setCode: String by lazy { intent.getStringExtra("setCode") }
    private val setPrice: String by lazy { intent.getStringExtra("setPrice") }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_card)

        toolbar_title.text = getString(R.string.selected)
        toolbar_back_button.setOnClickListener{
            onBackPressed()
        }

        nameT.text = name
        descT.text = desc
        typeT.text = type
        Glide.with(applicationContext).load(image).into(icon)
        raceT.text = race
        archetypeT.text = archetype
        setNameT.text = setName
        setCodeT.text = setCode
        setPriceT.text = setPrice

    }
}
