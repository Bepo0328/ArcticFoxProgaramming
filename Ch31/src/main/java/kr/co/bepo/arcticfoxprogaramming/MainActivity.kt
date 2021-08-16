package kr.co.bepo.arcticfoxprogaramming

import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        configureLayout()
    }

    private fun configureLayout() {
        val myButton = Button(this).apply {
            text = getString(R.string.press_me)
            setBackgroundColor(Color.YELLOW)
            id = R.id.myButton
        }

        val myEditText = EditText(this).apply {
            id = R.id.myEditText
            width = convertToPx(200)
        }

        val myLayout = ConstraintLayout(this).apply {
            setBackgroundColor(Color.BLUE)
        }

        myLayout.addView(myButton)
        myLayout.addView(myEditText)

        setContentView(myLayout)

        val set = ConstraintSet().apply {
            constrainHeight(myButton.id, ConstraintSet.WRAP_CONTENT)
            constrainWidth(myButton.id, ConstraintSet.WRAP_CONTENT)

            connect(myButton.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 0)
            connect(myButton.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 0)
            connect(myButton.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0)
            connect(myButton.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0)
        }

        set.apply {
            constrainHeight(myEditText.id, ConstraintSet.WRAP_CONTENT)
            constrainWidth(myEditText.id, ConstraintSet.WRAP_CONTENT)

            connect(myEditText.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 0)
            connect(myEditText.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 0)
            connect(myEditText.id, ConstraintSet.BOTTOM, myButton.id, ConstraintSet.TOP, 70)
        }

        set.applyTo(myLayout)
    }

    private fun convertToPx(value: Int): Int{
        val r = resources
        val px = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, value.toFloat(),
            r.displayMetrics
        ).toInt()

        return px
    }
}