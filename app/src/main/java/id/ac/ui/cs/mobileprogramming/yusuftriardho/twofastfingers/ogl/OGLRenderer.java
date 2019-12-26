package id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.ogl;

import android.graphics.Color;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class OGLRenderer implements GLSurfaceView.Renderer {

    private int BLUE = Color.parseColor("#03A9F4");

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        GLES20.glClearColor(Color.red(BLUE) / 255.0f,
                Color.green(BLUE) / 255.0f,
                Color.blue(BLUE) / 255.0f,
                Color.alpha(BLUE) / 255.0f);
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int i, int i1) {

    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
    }
}

