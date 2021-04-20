package ru.dmisb.sample.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ru.dmisb.sample.R
import ru.dmisb.sample_view.SampleView

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private lateinit var infoView: SampleView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        infoView = findViewById(R.id.info_view)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.generalInfo.observe(this, Observer { generalInfo ->
            generalInfo?.let {
                infoView.bind(
                    it.currentQuantity,
                    it.forBurningQuantity,
                    it.dateBurning
                )
            }

        })
    }
}