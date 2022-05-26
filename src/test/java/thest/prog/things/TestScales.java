package thest.prog.things;

import org.junit.Assert;

import org.junit.Test;
import th.prog.composer.Scaletor;


public class TestScales {
    @Test
    public void shouldCreateScale()
    {
        Scaletor scaletor = new Scaletor();
        double[] scale = scaletor.createChromaticScale(220.0d, 440.0d, 2);
        Assert.assertTrue(Math.round(scale[11])==440.0d);
    }
}
