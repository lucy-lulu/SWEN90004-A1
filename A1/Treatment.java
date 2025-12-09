/**
 * Represents the treatment room where each patient is treated.
 *
 * @author Luyun Li (ID: 1586333)
 */

public class Treatment {
    // the patient in the treatment room
    private Patient patient;

    // a patient `p` enters treatment room
    public synchronized void enter(Patient p) {
        while (this.patient != null) {
            tryWait();
        }
        this.patient = p;
        System.out.println(this.patient.toString() + " enters treatment room.");
        notifyAll();
    }

    // the patient leaves treatment room after being treated
    public synchronized void leave() {
        while (this.patient == null) {
            tryWait();
        }
        Patient p = this.patient;
        this.patient = null;
        System.out.println(p + " leaves treatment room.");
        notifyAll();
    }

    // treat the patient
    public synchronized void treat() {
        while (this.patient == null) {
            tryWait();
        }

        System.out.println(this.patient + " treatment started.");

        try {
            Thread.sleep(Params.TREATMENT_TIME);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println(patient + " treatment complete.");

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
