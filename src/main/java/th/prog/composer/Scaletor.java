package th.prog.composer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Scaletor {

    private Double[] createScale(double lowerOctave, double divisor, int octaves) {
        Double[] notes = new Double[((Double) divisor).intValue() * octaves];
        double factor = Math.pow(2, 1/divisor);
        notes[0] = lowerOctave;
        for (int i = 1; i < notes.length; i++) {
                notes[i] = notes[i - 1] * factor;
            }
        return notes;
    }

    public Double[] createChromaticScale(double lowerOctave, int octaves) {
        return createScale(lowerOctave, 12, octaves);
    }

    public Double[] createDiatonicScale(double lowerOctave, double upperOctave, int octaves) {
        Double[] firstOctave = createChromaticScale(lowerOctave,  1);
        List<Double> resultingScale = new ArrayList<>();
        for(int  i=1;i<=octaves;i++) {
            Double[] firstDiatonicOctave = selectTonesForDiatonicScale(firstOctave, i);
            resultingScale.addAll(Arrays.asList(firstDiatonicOctave));
        }
        return (Double[]) resultingScale.toArray(new Double[]{});
    }


    public Double[] createChinesePentatonicScale(double lowerOctave, double upperOctave, int octaves) {
        return createScale(lowerOctave, 5, octaves);
    }

    private Double[] selectTonesForDiatonicScale(Double[] chromaticScaleOneOctave, int factor){
        return new Double[]{factor*chromaticScaleOneOctave[0], factor*chromaticScaleOneOctave[2], factor*chromaticScaleOneOctave[4], factor*chromaticScaleOneOctave[5], factor*chromaticScaleOneOctave[7], factor*chromaticScaleOneOctave[9], factor*chromaticScaleOneOctave[11]};
    }
}
