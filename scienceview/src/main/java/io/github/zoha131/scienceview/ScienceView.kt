package io.github.zoha131.scienceview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.webkit.JavascriptInterface
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
class ScienceView<T> (context: Context?= null, attrs: AttributeSet? = null) : WebView(context, attrs), LifecycleObserver{

    private val scienceViewScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    private var isLoaded = false
    private var model: ((T)->String)? = null
    private var scienceClickListener: ((String, String)->Unit)? = null

    init {
        settings.javaScriptEnabled = true
        //todo-me add custom config support
        addJavascriptInterface(MathJaxConfig(), "BridgeConfig")
        addJavascriptInterface(this, "Bridge")
        loadUrl("file:///android_asset/science_view.html")

        webViewClient = object : WebViewClient(){
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                isLoaded = true
            }
        }

        (context as LifecycleOwner).lifecycle.addObserver(this)
    }

    fun addCSS(@RawRes css: Int){
        scienceViewScope.launch {

            while (!isLoaded) delay(500)

            val file = context.resources.openRawResource(css)

            val strFile = file.bufferedReader().use { it.readText() }

            loadUrl("javascript:loadCSS(`$strFile`);")
        }

    }

    //todo-me add custom js support
    /*fun addJS(@RawRes js: Int){
        scienceViewScope.launch {
            while (!isLoaded) delay(500)

            val file = context.resources.openRawResource(js)
            val strFile = file.bufferedReader().use { it.readText() }
            loadUrl("javascript:loadJS(`$strFile`);")
        }
    }*/

    /*
    * function loadJS(jsScript) {

            BridgeConfig.backCss(jsScript);

            var oScript = document.createElement("script");
            var oScriptText = document.createTextNode(jsScript);
            oScript.appendChild(oScriptText);
            document.body.appendChild(oScript);
          }
          * */

    fun setModel(model: (T)-> String){
        this.model = model
    }

    fun setData( data: T){

        if(model == null) throw NullPointerException("model has not been initialized")

        scienceViewScope.launch {

            while (!isLoaded) delay(500)
            loadUrl("javascript:loadHTML(`${model?.invoke(data)}`);")
        }
    }

    fun setScienceClickListener(scienceClickListener: (tag: String, data: String)->Unit){
        this.scienceClickListener = scienceClickListener
    }

    @JavascriptInterface
    fun jsOnClick(tag: String, data: String){
        scienceClickListener?.invoke(tag, data)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stop(){
        scienceViewScope.cancel()
    }
}