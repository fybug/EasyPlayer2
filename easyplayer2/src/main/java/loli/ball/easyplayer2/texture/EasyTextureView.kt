package loli.ball.easyplayer2.texture

import android.content.Context
import android.graphics.PixelFormat
import android.util.AttributeSet
import android.view.TextureView
import android.view.ViewGroup
import loli.ball.easyplayer2.utils.MeasureHelper

/**
 * Created by heyanlin on 2024/6/11.
 */
class EasyTextureView : TextureView {

    private val measureHelper: MeasureHelper = MeasureHelper()

    fun setVideoSize(width: Int, height: Int) {
        if (width > 0 && height > 0) {
            measureHelper.setVideoSize(width, height)
            requestLayout()
        }
    }

    fun setVideoRotation(degree: Int) {
        measureHelper.setVideoRotation(degree)
        requestLayout()
    }

    fun setScaleType(scaleType: Int) {
        measureHelper.setScreenScale(scaleType)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val measuredSize: IntArray = measureHelper.doMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(measuredSize[0], measuredSize[1])
    }

    init {
        layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    }

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
}