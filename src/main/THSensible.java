public interface THSensible {

    /** Split and store sensor data */
    void collect(String data);

    /** @return counts temps outside range exclusively */
    int tempsOutsideRange(double lo, double hi);

    /** @return the lowest humidity % (or 0 if none) */
    double minHumidity();

    /** @return the largest humidity % (or 0 if none) */
    double maxHumidity();

    /** @return the avg humidity % (or 0 if none) */
    double avgHumidity();
}
