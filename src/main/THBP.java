public class THBP extends THTemplate{

    /**
     * Initialize the THBP through super
     * */
    public THBP() {
        super();
    }

    /**
     * method called after the data collection process is completed (none in THBP)
     */
    @Override
    protected void postCollect() {}

    /**
     * method called before initiating a query
     */
    @Override
    protected void preQuery() {
        clean();
        parse();
    }
}
