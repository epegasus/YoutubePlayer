package dev.pegasus.youtubeplayer

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dev.pegasus.youtubeplayer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private var youTubeUrl = "https://www.youtube.com/embed/o9EBm4-9isI"

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        val regexYoutube = Regex("^(http(s)?://)?((w){3}.)?youtu(be|.be)?(\\.com)?/.+")

        val frameVideo = "<html><body><iframe width=\"720\" height=\"320\" src=\"https://www.youtube.com/embed/gTzSw_xVCWI\" title=\"Alec Benjamin - Must Have Been The Wind (Lyrics)\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe></body></html>"

        if (youTubeUrl.matches(regexYoutube)) {

            //setting web client
            binding.webview.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                    return false
                }
            }
            //web settings for JavaScript Mode
            val webSettings = binding.webview.settings
            webSettings.javaScriptEnabled = true
            webSettings.domStorageEnabled = true
            binding.webview.loadData(frameVideo, "text/html", "utf-8")
        } else {
            Toast.makeText(
                this@MainActivity, "This is other video", Toast.LENGTH_SHORT
            ).show()
        }
    }
}