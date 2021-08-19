package kr.co.bepo.arcticfoxprogaramming

import android.content.ClipData
import android.os.Bundle
import android.view.DragEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.CollapsingToolbarLayout
import kr.co.bepo.arcticfoxprogaramming.databinding.FragmentWebsiteDetailBinding
import kr.co.bepo.arcticfoxprogaramming.placeholder.PlaceholderContent

class WebsiteDetailFragment : Fragment() {

    private var item: PlaceholderContent.PlaceholderItem? = null

    lateinit var itemDetailTextView: WebView
    private var toolbarLayout: CollapsingToolbarLayout? = null

    private var _binding: FragmentWebsiteDetailBinding? = null

    private val binding get() = _binding!!

    private val dragListener = View.OnDragListener { v, event ->
        if (event.action == DragEvent.ACTION_DROP) {
            val clipDataItem: ClipData.Item = event.clipData.getItemAt(0)
            val dragData = clipDataItem.text
            item = PlaceholderContent.ITEM_MAP[dragData]
            updateContent()
        }
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_ITEM_ID)) {
                item = PlaceholderContent.ITEM_MAP[it.getString(ARG_ITEM_ID)]
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentWebsiteDetailBinding.inflate(inflater, container, false)
        val rootView = binding.root

        updateContent()
        rootView.setOnDragListener(dragListener)

        return rootView
    }

    private fun updateContent() {
        toolbarLayout?.title = item?.website_name

        item?.let {
            val rootView = binding.root
            val webView: WebView = rootView.findViewById(R.id.website_detail)

            webView.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView, request: WebResourceRequest): Boolean {
                    return super.shouldOverrideUrlLoading(view, request)
                }
            }

            webView.loadUrl(it.website_url)
        }
    }

    companion object {
        const val ARG_ITEM_ID = "item_id"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}