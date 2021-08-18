package kr.co.bepo.arcticfoxprogaramming.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kr.co.bepo.arcticfoxprogaramming.DemoOwner
import kr.co.bepo.arcticfoxprogaramming.R

private lateinit var demoOwner: DemoOwner

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        demoOwner = DemoOwner()
        demoOwner.startOwner()
        demoOwner.stopOwner()
    }

}