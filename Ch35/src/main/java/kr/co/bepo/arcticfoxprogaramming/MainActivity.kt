package kr.co.bepo.arcticfoxprogaramming

import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import kr.co.bepo.arcticfoxprogaramming.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    var gDetector: GestureDetectorCompat? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        this.gDetector = GestureDetectorCompat(this, this)
        gDetector?.setOnDoubleTapListener(this)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        this.gDetector?.onTouchEvent(event)
        return super.onTouchEvent(event)
    }

    override fun onDown(event: MotionEvent?): Boolean {
        binding.getstureStatusText.text = "onDown"
        return true
    }

    override fun onShowPress(event: MotionEvent?) {
        binding.getstureStatusText.text = "onShowPress"
    }

    override fun onSingleTapUp(event: MotionEvent?): Boolean {
        binding.getstureStatusText.text = "onSingleTapUp"
        return true
    }

    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
        binding.getstureStatusText.text = "onScroll"
        return true
    }

    override fun onLongPress(event: MotionEvent?) {
        binding.getstureStatusText.text = "onScroll"
    }

    override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
        binding.getstureStatusText.text = "onFling"
        return true
    }

    override fun onSingleTapConfirmed(event: MotionEvent?): Boolean {
        binding.getstureStatusText.text = "onSingleTapConfirmed"
        return true
    }

    override fun onDoubleTap(event: MotionEvent?): Boolean {
        binding.getstureStatusText.text = "onDoubleTap"
        return true
    }

    override fun onDoubleTapEvent(event: MotionEvent?): Boolean {
        binding.getstureStatusText.text = "onDoubleTapEvent"
        return true
    }
}