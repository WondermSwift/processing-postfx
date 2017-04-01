package ch.bildspur.postfx.pass;

import ch.bildspur.postfx.Supervisor;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.opengl.PShader;

import java.nio.file.Paths;

/**
 * Created by cansik on 27.03.17.
 */
public class BasePass implements Pass {
    PShader shader;

    public BasePass(PApplet sketch, String passName)
    {
        shader = sketch.loadShader(Paths.get("shader", passName + ".glsl").toString());
    }

    @Override
    public void prepare(Supervisor supervisor) {}

    @Override
    public void apply(Supervisor supervisor) {
        PGraphics pass = supervisor.getNextPass();
        supervisor.clearPass(pass);

        pass.beginDraw();
        pass.shader(shader);
        pass.image(supervisor.getCurrentPass(), 0, 0);
        pass.endDraw();
    }

    public PShader getShader() {
        return shader;
    }
}
