/**
 * Represents the orderlies to be recruited by nurses.
 *
 * @author Luyun Li (ID: 1586333)
 */

public class Orderlies {
    // the number of orderlies available
    private volatile int count;

    public Orderlies() {
        this.count = Params.ORDERLIES;
    }

    // recruited by `nurse`
    public synchronized void recruit(Nurse nurse) {
        // the nurse has to wait if there is insufficient number of orderlies
        while (count < Params.TRANSFER_ORDERLIES) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        // update the number of orderlies available
        this.count -= Params.TRANSFER_ORDERLIES;
        System.out.println(nurse.toString() + " recruits " + Params.TRANSFER_ORDERLIES +
                " orderlies (" + this.count + " free).");
        notifyAll();
    }

    // released by `nurse`
    public synchronized void release(Nurse nurse) {
        // update the number of orderlies available
        this.count += Params.TRANSFER_ORDERLIES;
        // the total number cannot exceed the total
        assert this.count <= Params.ORDERLIES;
        System.out.println(nurse.toString() + " releases " + Params.TRANSFER_ORDERLIES +
                " orderlies (" + this.count + " free).");
        notifyAll();
    }
}
