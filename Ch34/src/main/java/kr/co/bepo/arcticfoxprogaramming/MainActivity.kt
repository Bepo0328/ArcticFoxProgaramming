package kr.co.bepo.arcticfoxprogaramming

import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import kr.co.bepo.arcticfoxprogaramming.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.activityMain.setOnTouchListener { _, m: MotionEvent ->
            handleTouch(m)
            true
        }
    }

    private fun handleTouch(m: MotionEvent) {
        val pointerCount = m.pointerCount
        for (i in 0 until pointerCount) {
            val x = m.getX(i)
            val y = m.getY(i)
            val id = m.getPointerId(i)
            val action = m.actionMasked
            val actionIndex = m.actionIndex
            val actionString: String =
                when (action) {
                        MotionEvent.ACTION_DOWN -> "DOWN"
                    MotionEvent.ACTION_UP -> "UP"
                    MotionEvent.ACTION_POINTER_DOWN -> "PNTR DOWN"
                    MotionEvent.ACTION_POINTER_UP -> "PNTR UP"
                    MotionEvent.ACTION_MOVE -> "MOVE"
                    else -> ""
                }

            val touchStatus =
                "Action: $actionString Index: $actionIndex ID: $id, X: $x, Y: $y"

            if (id == 0) binding.textView1.text = touchStatus
            else binding.textView2.text = touchStatus
        }
    }
}