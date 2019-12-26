package id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.ogl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

public class OGLView extends GLSurfaceView {
    public OGLView(Context context) {
        super(context);
        init();
    }

    public OGLView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setEGLContextClientVersion(2);
        setPreserveEGLContextOnPause(true);
        setRenderer(new OGLRenderer());
    }
}
