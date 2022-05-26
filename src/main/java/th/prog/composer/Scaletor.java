package th.prog.composer;

public class Scaletor {

    private double[] createScale(double lowerOctave, double upperOctave, int divisor, int octaves) {
        double[] notes = new double[divisor*octaves];
        if (Math.round(upperOctave / lowerOctave) != 2) {
            throw new MusicalTheoryException("an octave is defined as a requency with an exact factor 2 difference, your octave does not compute");
        }
        notes[0] = lowerOctave + ((upperOctave*octaves)-lowerOctave)/(divisor*octaves);
        for(int i=1;i<divisor*octaves;i++){
            notes[i] = notes[i-1] + (((upperOctave*octaves) - notes[i-1])/(divisor*octaves));
        }
        return notes;
    }

    public double[] createChromaticScale(double lowerOctave, double upperOctave, int octaves){
        return createScale(lowerOctave, upperOctave, 12, octaves);
    }


    public double[] createChinesePentatonicScale(double lowerOctave, double upperOctave,int octaves){
        return createScale(lowerOctave, upperOctave, 5, octaves);
    }
}
