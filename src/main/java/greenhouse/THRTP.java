public class THRTP extends THTemplate{

    /**
     * Initialize the THBP through super
     */
    public THRTP() {
        super();
    }

    /**
     * method called after the data collection process is completed
     */
    protected void postCollect() {
        clean();
        parse();
    }

    /**
     * method called after the data collection process is completed (none in THRTP)
     */
    @Override
    protected void preQuery() {}
}
