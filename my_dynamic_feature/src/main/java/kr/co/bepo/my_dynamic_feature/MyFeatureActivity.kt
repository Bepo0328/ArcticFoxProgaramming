package kr.co.bepo.my_dynamic_feature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.play.core.splitcompat.SplitCompat
import kr.co.bepo.my_dynamic_feature.databinding.ActivityMyFeatureBinding

class MyFeatureActivity : AppCompatActivity() {

    private val binding: ActivityMyFeatureBinding by lazy { ActivityMyFeatureBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SplitCompat.install(this)
        setContentView(binding.root)
    }
}