/**
 * Represents the triage where each patient is assessed.
 *
 * @author Luyun Li (ID: 1586333)
 */

public class Triage {
    // the patient in triage
    private Patient patient;

    // a patient `p` enters triage
    public synchronized void enter(Patient p) {
        while (this.patient != null) {
            tryWait();
        }
        this.patient = p;
        System.out.println(this.patient.toString() + " enters triage.");
        notifyAll();
    }

    // the patient leaves triage after being assessed
    public synchronized void leave() {
        while (this.patient == null) {
            tryWait();
        }
        Patient p = this.patient;
        this.patient = null;
        System.out.println(p + " leaves triage.");
        notifyAll();
    }

    // extract wrapping wait in a try-catch block into a function to avoid code duplication
    private void tryWait() {
        try {
            wait();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
