/**
 * Represents a nurse who takes a patient from one location to another.
 *
 * @author Luyun Li (ID: 1586333)
 */

public class Nurse extends Thread {

    // the unique ID for the nurse
    private int id;

    // locations to which the nurse takes the patient to and from
    private Foyer foyer;
    private Triage triage;
    private Treatment treatment;

    // the nurse recruits orderlies to assist in transferring patients
    private Orderlies orderlies;

    // constructor
    Nurse(int id, Foyer foyer, Triage triage, Orderlies orderlies, Treatment treatment) {
        this.id = id;
        this.foyer = foyer;
        this.triage = triage;
        this.orderlies = orderlies;
        this.treatment = treatment;
    }

    // repeatedly taking patients through the ED
    public void run() {
        while (true) {
            // allocate the patient, who is already admitted at the foyer, to a nurse
            Patient patient = foyer.getAdmittedPatient();
            patient.allocated = true;
            System.out.println(patient + " allocated to " + this);

            // take the patient from foyer to triage,
            // with the assistance of the orderlies

            orderlies.recruit(this);
            foyer.leave();
            this.trySleep(Params.TRANSFER_TIME);

            triage.enter(patient);
            orderlies.release(this);
            this.trySleep(Params.TRIAGE_TIME);

            // take the patient from triage to treatment room
            // first, or to the foyer directly,
            // depending on the patient's severity,
            // with the assistance of the orderlies

            orderlies.recruit(this);
            triage.leave();
            this.trySleep(Params.TRANSFER_TIME);

            if (patient.Severe()) {
                treatment.enter(patient);
                orderlies.release(this);

                orderlies.recruit(this);
                treatment.leave();
                this.trySleep(Params.TRANSFER_TIME);
            }

            foyer.enter(patient);
            orderlies.release(this);

            System.out.println(this + " releases " + patient);
            patient.allocated = false;
        }
    }

    // extract wrapping sleep in a try-catch block into a function to avoid code duplication
    private void trySleep(long duration) {
        try {
            sleep(duration);
        } catch (InterruptedException e) {
            this.interrupt();
        }
    }

    public String toString() {
        return "Nurse " + this.id;
    }
}
