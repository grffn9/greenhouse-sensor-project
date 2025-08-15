import java.util.*;
import static java.util.Arrays.asList;

public abstract class THTemplate implements THSensible {

    /**
     * Data is collected in a String with the format:
     * yyyymmdd date followed by a space
     * Has 0+ space-separated entries which is one of:
     *  T, followed by a space and a decimal number from -100 to +200 (Measured in Degrees Fahrenheit)
     *  H, followed by a space and a decimal percent from 0.0 to 100.0 percent
     *  Err, followed by a space if the sensor had an error
     */
    protected List<String> sensorData;

    /** List of all temperatures retrieved from sensorData */
    protected List<Double> temperatures;

    /** List of all humidities retrieved from sensorData */
    protected List<Double> humidities;

    /** Initializes the template assigning new ArrayLists to each field */
    public THTemplate() {
        this.sensorData = new ArrayList<>();
        this.temperatures = new ArrayList<>();
        this.humidities = new ArrayList<>();
    }

    /**
     * adds the data to the sensorData and then calls postCollect()
     * @param data sensor data
     */
    public void collect(String data) {
        List<String> collection = asList(data.split(" "));
        sensorData.addAll(sensorData.size() , collection);
        postCollect();
    }

    /**
     * @param lo temp
     * @param hi temp
     * @return the number of temperatures below lo or above hi
     */
    public int tempsOutsideRange(double lo, double hi) {
        preQuery();
        int counter = 0;
        for(double temp : this.temperatures) {
            if(temp < lo || temp > hi) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * @return the lowest humidity in the list of humidities
     */
    public double minHumidity() {
        preQuery();
        if (this.humidities.isEmpty()) {
            return 0.0;
        }
        double min = Double.MAX_VALUE;
        for(double humid : this.humidities) {
            if(humid < min) {
                min = humid;
            }
        }
        return min;
    }

    /**
     * @return the highest humidity in the list of humidities
     */
    public double maxHumidity() {
        preQuery();
        if (this.humidities.isEmpty()) {
            return 0.0;
        }
        double max = Double.MIN_VALUE;
        for(double humid : this.humidities) {
            if(humid > max) {
                max = humid;
            }
        }
        return max;
    }

    /**
     * @return the average humidity from the list of humidities
     */
    public double avgHumidity() {
        preQuery();
        if (this.humidities.isEmpty()) {
            return 0.0;
        }
        double avg = 0.0;
        for(double humid : this.humidities) {
            avg += humid;
        }
        return avg / this.humidities.size();
    }

    /**
     * removes dates and errors from sensorData
     */
    protected void clean() {
        if (!this.sensorData.isEmpty()) {
            this.sensorData.remove(0);
            List<String> nonErr = new ArrayList<>();
            for (String item : this.sensorData) {
                if (!Objects.equals(item, "Err")) {
                    nonErr.add(item);
                }
            }
            this.sensorData = nonErr;
        }
    }

    /**
     * moves the data from sensorData to temperatures and humidities
     * sorts temperatures and humidities
     * clears sensorData
     */
    protected void parse() {
        for(int i = 0; i < sensorData.size(); i++) {
            if(sensorData.get(i).equals("T")) {
                this.temperatures.add(Double.parseDouble(sensorData.get(i + 1)));
            } else if(sensorData.get(i).equals("H")) {
                this.humidities.add(Double.parseDouble(sensorData.get(i + 1)));
            }
        }
        Collections.sort(this.temperatures);
        Collections.sort(this.humidities);
        this.sensorData.clear();
    }

    /**
     * method called after the data collection process is completed
     */
    protected abstract void postCollect();

    /**
     * method called before initiating a query
     */
    protected abstract void preQuery();
}
