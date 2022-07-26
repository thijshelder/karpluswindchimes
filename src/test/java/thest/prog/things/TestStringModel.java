package thest.prog.things;

import org.junit.Assert;
import org.junit.Test;
import th.prog.things.StringModel;

public class TestStringModel {

    @Test
    public void testCreateString()
    {
        StringModel model = new StringModel(10);
        model.pluck();
        Assert.assertTrue(model.getTimes()==0);
    }

    @Test
    public void testPluckString()
    {
        StringModel model = new StringModel(10);
        model.pluck();
        //ert.assertTrue(model.tic().doubleValue()!=0.0 &&model.tic().doubleValue()!=0.6);

    }

    @Test
    public void itIsReallyEndless()
    {
        StringModel model = new StringModel(new Byte[]{5,8,9,2,7,3,2,4,8});

        Byte s = model.tic();
        while( model.isActive())
        {
            System.out.println(model.tic());
        }
    }
}
