package loli.ball.easyplayer2.surface

import android.content.Context
import android.graphics.PixelFormat
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.ViewGroup
import androidx.media3.exoplayer.ExoPlayer

/**
 * Created by heyanlin on 2024/6/12.
 */
class EasySurfaceViewV2: EasySurfaceView,  SurfaceHolder.Callback {

    private var exoPlayer: ExoPlayer? = null
    private var surfaceHolder: SurfaceHolder? = null

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val measuredSize: IntArray = measureHelper.doMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(measuredSize[0], measuredSize[1])
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        exoPlayer?.setVideoSurfaceHolder(holder)
        this.surfaceHolder = holder
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        exoPlayer?.setVideoSurfaceHolder(holder)
        this.surfaceHolder = holder
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        exoPlayer?.clearVideoSurfaceHolder(holder)
        this.surfaceHolder = holder
    }

    fun attachPlayer(player: ExoPlayer){
        exoPlayer = player
        if(surfaceHolder != null){
            exoPlayer?.setVideoSurfaceHolder(surfaceHolder)
        }
    }

    fun detachPlayer(player: ExoPlayer){
        if (exoPlayer == player) {
            exoPlayer?.clearVideoSurface()
            exoPlayer = null
        }
    }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr)

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    init {
        holder.setFormat(PixelFormat.RGBA_8888)
        holder.addCallback(this)
        layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    }
}