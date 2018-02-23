/*
 *
 * FGLView.java
 * 
 * Created by Wuwang on 2016/9/29
 */
package edu.wuwang.opengl.render;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

/**
 * Description:
 *
 * @author Chengguo
 */
public class FGLView extends GLSurfaceView {

    private FGLRender renderer;

    public FGLView(Context context) {
        this(context, null);
    }

    public FGLView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        //The EGLContext client version to choose. Use 2 for OpenGL ES 2.0
        setEGLContextClientVersion(2);
        //the renderer to use to perform OpenGL drawing.
        setRenderer(renderer = new FGLRender(this));
        /*Render的mode可以设为两种模式，一种是自动循环模式，也就是说GL线程以一 定的时间间隔自动的循环调用用户实现的onDrawFrame（）
          方法进行一帧一帧的绘制，还有一种的“脏”模式，也就是说当用户需要重绘的时候，主动 “拉”这个重绘过程，有点类似于Canvas中的invalidate（）
         具体的调用方法是在GLSurfaceView中
         a.自动模式
         setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
         b."脏"模式
         .setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
         当需要重绘时，调用
         GLSurfaceView.requestRender()
         一般情况下使用脏模式，这样可以有效降低cpu负载。测试结果表明，OpenGL真正绘图时一般会占到30%以上的cpu
         */
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    public void setShape(Class<? extends Shape> clazz) {
        try {
            renderer.setShape(clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
