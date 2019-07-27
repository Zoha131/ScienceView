package io.github.zoha131.scienceview.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import io.github.zoha131.scienceview.ScienceView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var scienceView: ScienceView<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scienceView = findViewById(R.id.scienceView)
    }

    override fun onResume() {
        super.onResume()

        scienceView.addCSS(R.raw.style)

        scienceView.setModel { model ->
            """
<form>
  <h1>ScinceView</h1>
  <label>
    <input type="radio" name="opinion"/>
    <i></i>
    <span>$model</span>
  </label>
  <label>
    <input type="radio" name="opinion"/>
    <i></i>
    <span>$model</span>
  </label>
</form>
            """.trimIndent()
        }

        scienceView.setScienceClickListener { tag, data ->
            Log.d("JSCLICKLISTENER", "$tag $data")
        }

        button.setOnClickListener {
            scienceView.setData("${'$'}${'$'}\\\\ce{x Na(NH4)HPO4 ->[\\\\Delta] (NaPO3)_x + x NH3 ^ + x H2O}${'$'}${'$'}")
        }
    }
}
