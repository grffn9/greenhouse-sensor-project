import org.junit.Test;

import static org.junit.Assert.*;

public class Examples {
    @Test
    public void testBPtor1(){
        THSensible th = new THBP();
        th.collect("20250407 T 68.0 H 30.0 T 71.0 Err H 45.0 T 81.5 H 50.0 Err");
        assertEquals(3, th.tempsOutsideRange(0.0, 50.0));
    }

    @Test
    public void testBPtor2(){
        THSensible th = new THBP();
        th.collect("20250407 T 40.0");
        th.collect("20250407 H 5.0");
        assertEquals(0, th.tempsOutsideRange(0.0, 50.0));
    }

    @Test
    public void testBPtor3(){
        THSensible th = new THBP();
        th.collect("20250407 T 40.0");
        th.collect("20250407 Err");
        assertEquals(0, th.tempsOutsideRange(0.0, 50.0));
    }

    @Test
    public void testAvgHumidity1() {
        THSensible th = new THBP();
        th.collect("20250407 T 40.0");
        th.collect("20250407 T 5.0");
        assertEquals(0, th.avgHumidity(), 0.01);
    }

    @Test
    public void testAvgHumidity2() {
        THSensible th = new THBP();
        th.collect("20250407 H 20.0");
        th.collect("20250407 H 5.0");
        assertEquals(12.5, th.avgHumidity(), 0.01);
    }

    @Test
    public void maxHumidity1() {
        THSensible th = new THBP();
        th.collect("20250407 H 20.0");
        assertEquals(20.0, th.maxHumidity(), 0.01);
    }

    @Test
    public void maxHumidity2() {
        THSensible th = new THBP();
        th.collect("20250407 T 20.0");
        assertEquals(0.0, th.maxHumidity(), 0.01);
    }

    @Test
    public void minHumidity1() {
        THSensible th = new THBP();
        th.collect("20250407 H 20.0");
        assertEquals(20.0, th.minHumidity(), 0.01);
    }

    @Test
    public void minHumidity2() {
        THSensible th = new THBP();
        th.collect("20250407 T 20.0");
        assertEquals(0.0, th.minHumidity(), 0.01);
    }

    @Test
    public void testCollectTiming1() {
        THSensible bp = new THBP();
        THSensible rtp = new THRTP();
        long preBP = System.nanoTime();
        bp.collect("20250407 T 73.0 H 26.5 T 12.3 Err H 15.3 T 82.9 H 40.0 Err");
        long postBP = System.nanoTime();
        long deltaBP = postBP - preBP;
        long preRTP = System.nanoTime();
        rtp.collect("20250407 T 73.0 H 26.5 T 12.3 Err H 15.3 T 82.9 H 40.0 Err");
        long postRTP = System.nanoTime();
        long deltaRTP = postRTP - preRTP;
        assertTrue(deltaBP < deltaRTP);
    }

    @Test
    public void testCollectTiming2(){
        THSensible bp = new THBP();
        THSensible rtp = new THRTP();
        long preBP = System.nanoTime();
        bp.collect("20250407 T 73.0 H 26.5 T 12.3 Err H 15.3 T 82.9 H 40.0 Err");
        long postBP = System.nanoTime();
        long deltaBP = postBP - preBP;
        long preRTP = System.nanoTime();
        rtp.collect("20250407 T 73.0 H 26.5 T 12.3 Err H 15.3 T 82.9 H 40.0 Err");
        long postRTP = System.nanoTime();
        long deltaRTP = postRTP - preRTP;
        assertTrue(deltaBP < deltaRTP);
    }

    @Test
    public void testCollectTiming3(){
        THSensible bp = new THBP();
        THSensible rtp = new THRTP();
        long preBP = System.nanoTime();
        bp.collect("20250407 T 73.0 H 26.5 T 12.3 Err H 15.3 T 82.9 H 40.0 Err");
        long postBP = System.nanoTime();
        long deltaBP = postBP - preBP;
        long preRTP = System.nanoTime();
        rtp.collect("20250407 T 73.0 H 26.5 T 12.3 Err H 15.3 T 82.9 H 40.0 Err");
        long postRTP = System.nanoTime();
        long deltaRTP = postRTP - preRTP;
        assertTrue(deltaBP < deltaRTP);
    }

    @Test
    public void testCollectTiming4(){
        THSensible bp = new THBP();
        THSensible rtp = new THRTP();
        long preBP = System.nanoTime();
        bp.collect("20250407 T 73.0");
        long postBP = System.nanoTime();
        long deltaBP = postBP - preBP;
        long preRTP = System.nanoTime();
        rtp.collect("20250407 T 73.0 H 26.5 T 12.3 Err H 15.3 T 82.9 H 40.0 Err");
        long postRTP = System.nanoTime();
        long deltaRTP = postRTP - preRTP;
        assertTrue(deltaBP < deltaRTP);
    }

    @Test
    public void testAuditor(){
        THBPAuditor th = new THBPAuditor();
        th.collect("20250407 T 40.0");
        assertEquals(0, th.getErrorCount(), 0.01);
    }

    @Test
    public void testDate(){
        THRTPDate date1 = new THRTPDate("20250407");
        THRTPDate date2 = new THRTPDate("20250407");

        date1.collect("20250407 T 73.0 H 26.5");
        date1.collect("20250408 T 75.0 H 30.0");

        date2.collect("20250407 T 73.0 H 26.5");
        date2.collect("20250408 T 75.0 H 30.0");

        assertTrue(date1.equals(date2));
    }

}
