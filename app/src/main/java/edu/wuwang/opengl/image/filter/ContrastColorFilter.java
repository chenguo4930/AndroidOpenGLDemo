package edu.wuwang.opengl.image.filter;

import android.content.Context;
import android.opengl.GLES20;

/**
 * Created by wuwang on 2016/10/22
 */
public class ContrastColorFilter extends AFilter {

    private ColorFilter.Filter filter;

    private int hChangeType;
    private int hChangeColor;

    public ContrastColorFilter(Context context, ColorFilter.Filter filter) {
        super(context, "filter/half_color_vertex.vert", "filter/half_color_fragment.frag");
        this.filter = filter;
    }

    /**
     * 获取将图片处理的类型变量
     * @param mProgram
     */
    @Override
    public void onDrawCreatedSet(int mProgram) {
        hChangeType = GLES20.glGetUniformLocation(mProgram, "vChangeType");
        hChangeColor = GLES20.glGetUniformLocation(mProgram, "vChangeColor");
    }

    /**
     * 对底层变量进行赋值
     */
    @Override
    public void onDrawSet() {
        GLES20.glUniform1i(hChangeType, filter.getType());
        GLES20.glUniform3fv(hChangeColor, 1, filter.data(), 0);
    }
}
