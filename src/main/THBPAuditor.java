public class THBPAuditor extends THBP {

    /**
     * The amount of errors in sensorData
     */
    protected int errors;

    /**
     * Initializing the object and setting errors to 0
     */
    THBPAuditor() {
        errors = 0;
    }

    /**
     * counting up the amount of "Err"ors in sensorData
     * call super.clean()
     */
    @Override
    protected void clean() {
        for(String item : this.sensorData) {
            if (item.equals("Err")) {
                errors++;
            }
        }
        super.clean();

    }

    /**
     * call preQuery
     * @return the number of errors
     */
    public int getErrorCount() {
        preQuery();
        return errors;
    }
}
