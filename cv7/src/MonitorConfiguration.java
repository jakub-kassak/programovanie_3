public class MonitorConfiguration {
    private long contrast;
    private long frequency;
    private long brightness;

    public MonitorConfiguration(long contrast, long frequency, long brightness) {
        this.contrast = contrast;
        this.frequency = frequency;
        this.brightness = brightness;
    }

    public long getContrast() {
        return contrast;
    }

    public void setContrast(long contrast) {
        this.contrast = contrast;
    }

    public long getFrequency() {
        return frequency;
    }

    public void setFrequency(long frequency) {
        this.frequency = frequency;
    }

    public long getBrightness() {
        return brightness;
    }

    public void setBrightness(long brightness) {
        this.brightness = brightness;
    }

    public Memento saveToMemento(){
        return new Memento(this);
    }

    public void restoreFromMemento(Memento memento){
        contrast = memento.contrast;
        frequency = memento.frequency;
        brightness = memento.brightness;
    }

    public class Memento{
        private long contrast;
        private long frequency;
        private long brightness;

        private Memento(MonitorConfiguration mc){
            contrast = mc.contrast;
            frequency = mc.frequency;
            brightness = mc.brightness;
        }

    }
}
