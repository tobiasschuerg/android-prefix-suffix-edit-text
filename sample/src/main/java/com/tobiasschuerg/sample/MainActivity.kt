package com.tobiasschuerg.sample

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.edit_text_dollar
import kotlinx.android.synthetic.main.activity_main.edit_text_euro

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // just a prefix:
        edit_text_dollar.prefix = "$"

        // a suffix with custom color
        edit_text_euro.suffix = "€"
        edit_text_euro.setHintTextColor(Color.GREEN)
    }
}
