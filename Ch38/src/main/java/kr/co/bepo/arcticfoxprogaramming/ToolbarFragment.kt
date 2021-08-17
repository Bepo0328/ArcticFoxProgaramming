package kr.co.bepo.arcticfoxprogaramming

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import kr.co.bepo.arcticfoxprogaramming.databinding.FragmentToolbarBinding

class ToolbarFragment : Fragment(), SeekBar.OnSeekBarChangeListener {

    private var _binding: FragmentToolbarBinding? = null
    private val binding get() = _binding!!

    var seekValue = 10
    var activityCallback: ToolbarFragment.ToolbarListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentToolbarBinding.inflate(inflater, container, false)
        .also { _binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.seekBar1.setOnSeekBarChangeListener(this)
        binding.button1.setOnClickListener { v: View -> buttonClicked(v) }
    }

    interface ToolbarListener {
        fun onButtonClick(position: Int, text: String)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            activityCallback = context as ToolbarListener
        } catch (e: ClassCastException) {
            throw ClassCastException(context.toString()
                                    + " must implement ToolbarListener")
        }
    }

    private fun buttonClicked(view: View) {
        activityCallback?.onButtonClick(seekValue,
                binding.editText1.text.toString())
    }

    override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        seekValue = progress
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {
    }

    override fun onStopTrackingTouch(p0: SeekBar?) {
    }
}