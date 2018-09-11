package com.tobiasschuerg.prefixpostfix

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edit_text_prefix.setPrefix("$")
        edit_text_suffix.setSuffix("€")

        edit_text_both.setPrefix("Age: ")
        edit_text_both.setSuffix(" years")
    }
}
