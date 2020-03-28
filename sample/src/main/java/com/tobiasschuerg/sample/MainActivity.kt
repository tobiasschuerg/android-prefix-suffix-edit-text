package com.tobiasschuerg.sample

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tobiasschuerg.prefixsuffix.PrefixSuffixEditText

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val editDollar: PrefixSuffixEditText = findViewById(R.id.edit_text_dollar)
        val editEuro: PrefixSuffixEditText = findViewById(R.id.edit_text_euro)

        // just a prefix:
        editDollar.prefix = "$"

        // a suffix with custom color
        editEuro.suffix = "€"
        editEuro.setHintTextColor(Color.GREEN)
    }
}
