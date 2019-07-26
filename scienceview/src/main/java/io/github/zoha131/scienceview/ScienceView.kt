package io.github.zoha131.scienceview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.IntegerRes
import androidx.annotation.RawRes
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import kotlinx.coroutines.*

@SuppressLint("SetJavaScriptEnabled")
class ScienceView (context: Context?= null, attrs: AttributeSet? = null) : WebView(context, attrs), LifecycleObserver{

    private val scienceViewScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    private var isLoaded = false

    init {
        settings.javaScriptEnabled = true

        // render MathJax once page finishes loading

        webViewClient = object : WebViewClient(){
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                isLoaded = true
            }
        }

        addJavascriptInterface(MathJaxConfig(), "BridgeConfig")
        loadUrl("file:///android_asset/science_view.html")

        (context as LifecycleOwner).lifecycle.addObserver(this)
    }

    fun addCSS(@RawRes css: Int){
        scienceViewScope.launch(Dispatchers.Main) {

            while (!isLoaded) delay(500)

            val file = context.resources.openRawResource(css)

            val strFile = file.bufferedReader().use { it.readText() }

            loadUrl("javascript:loadCSS(`$strFile`);")
        }

    }

    /*fun addJS(@RawRes js: Int){
        val file = context.resources.openRawResource(js)

        val strFile = file.bufferedReader().use { it.readText() }

        loadUrl("javascript:loadJS(`$strFile`);")

    }*/

    fun setHTML(data: String){
        scienceViewScope.launch(Dispatchers.Main) {

            while (!isLoaded) delay(500)
            loadUrl("javascript:loadHTML(`$data`);")
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stop(){
        scienceViewScope.cancel()
        Log.d("ONSTOPCALLED", "Coroutines has been stopped")
    }


}