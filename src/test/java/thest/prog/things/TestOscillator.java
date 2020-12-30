package thest.prog.things;

import org.junit.Assert;
import org.junit.Test;
import th.prog.things.OscillatorModel;

public class TestOscillator {

    @Test
    public void testCreateOscillator()
    {
        OscillatorModel model = new OscillatorModel(440);
        Assert.assertTrue(model!=null);
    }

    @Test
    public void testPlayOscillator()
    {
        int i = 0;
        OscillatorModel model = new OscillatorModel(440);
        model.pluck();
        Byte b = model.tic();
        while(!model.isSilent( b))
        {
            System.out.println(model.tic());
            i++;
        }
    }
}
