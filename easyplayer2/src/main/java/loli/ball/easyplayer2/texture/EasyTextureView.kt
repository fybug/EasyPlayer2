package loli.ball.easyplayer2.texture

import android.content.Context
import android.graphics.PixelFormat
import android.graphics.SurfaceTexture
import android.util.AttributeSet
import android.view.Surface
import android.view.TextureView
import android.view.ViewGroup
import androidx.media3.exoplayer.ExoPlayer
import loli.ball.easyplayer2.utils.MeasureHelper

/**
 * Created by heyanlin on 2024/6/11.
 */
class EasyTextureView : TextureView, TextureView.SurfaceTextureListener {

    private val measureHelper: MeasureHelper = MeasureHelper()
    private var mSurface: Surface? = null
    private var mSurfaceTexture: SurfaceTexture? = null
    private var exoPlayer: ExoPlayer? = null

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
        surfaceTextureListener = this
    }

    override fun onSurfaceTextureAvailable(surface: SurfaceTexture, width: Int, height: Int) {
        mSurfaceTexture = surface
        mSurface = Surface(surface)
        exoPlayer?.setVideoSurface(mSurface)
    }

    override fun onSurfaceTextureSizeChanged(surface: SurfaceTexture, width: Int, height: Int) {
        mSurfaceTexture = surface
        mSurface = Surface(surface)
        exoPlayer?.setVideoSurface(mSurface)
    }

    override fun onSurfaceTextureDestroyed(surface: SurfaceTexture): Boolean {
        exoPlayer?.clearVideoSurface(mSurface)
        return true
    }

    override fun onSurfaceTextureUpdated(surface: SurfaceTexture) {
        mSurfaceTexture = surface
        mSurface = Surface(surface)
        exoPlayer?.setVideoSurface(mSurface)
    }

    fun attachPlayer(player: ExoPlayer){
        exoPlayer = player
        if(mSurfaceTexture != null){
            exoPlayer?.setVideoSurface(mSurface)
        }
    }

    fun detachPlayer(player: ExoPlayer){
        if (exoPlayer == player) {
            exoPlayer?.clearVideoSurface(mSurface)
            exoPlayer = null
        }

    }

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
}