package thest.prog.things;

import org.junit.Assert;

import org.junit.Test;
import th.prog.composer.Scaletor;


public class TestScales {
    @Test
    public void shouldCreateScale()
    {
        Scaletor scaletor = new Scaletor();
        Double[] scale = scaletor.createChromaticScale(220.0d, 2);
        Assert.assertTrue("octave should be "  + 440 +  " was "+ scale[12], Math.round(scale[12])==440.0d);
    }
}
