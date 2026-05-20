package greenhouse;
import java.util.ArrayList;
import java.util.List;

public class THRTPDate extends THRTP implements Comparable<THRTPDate> {

    /**
     * Date in the yyyymmddd
     */
    protected String date;

    /**
     * Initializes the THRTPDate object
     * @param date the date in yyyymmdd format
     */
    THRTPDate(String date) {
        this.date = date;
    }

    /**
     * Deletes data following a date other than the date field from the sensorData
     * Calls super.clean()
     */
    protected void clean() {
        List<String> tokeep = new ArrayList<>();
        boolean keepingData = false;

        for(String item : this.sensorData) {
            if (item.length() == 8) {
                boolean isDate = true;
                for (int i = 0; i < 8; i++) {
                    if (!Character.isDigit(item.charAt(i))) {
                        isDate = false;
                        break;
                    }
                }
                if (isDate) {
                    keepingData = item.equals(this.date);
                    continue;
                }
            }
            if (keepingData) {
                tokeep.add(item);
            }
        }
        this.sensorData = tokeep;
        super.clean();
    }

    /**
     * @param o another object
     * @return true if the two dates of each object are true
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof THRTPDate) {
            THRTPDate other = (THRTPDate) o;
            if (this.date == null) return other.date == null;
            return this.date.equals(other.date);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.date != null ? this.date.hashCode() : 0;
    }

    /**
     * @param o the object to be compared.
     * @return what compareTo() on their date fields return
     */
    @Override
    public int compareTo(THRTPDate o) {
        if (o == null || o.date == null) return 1;
        if (this.date == null) return -1;
        return this.date.compareTo(o.date);
    }
}
