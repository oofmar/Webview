package com.example.codingpractice

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.codingpractice.databinding.FragmentWebViewBinding

class WebViewFragment : Fragment() {
    private var binding: FragmentWebViewBinding? = null
    private val args: WebViewFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentWebViewBinding.inflate(inflater, container, false)
        return binding?.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.webView?.apply {
            settings.javaScriptEnabled = true
            webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    Log.d("WebView", "Page started loading: $url")
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    Log.d("WebView", "Page finished loading: $url")
                }

                override fun onReceivedError(view: WebView?, errorCode: Int, description: String?, failingUrl: String?) {
                    Log.e("WebView", "Error loading page $failingUrl: $errorCode $description")
                }
            }
            Log.d("WebViewFragment", "Loading URL: ${args.url}")
            loadUrl(args.url)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
