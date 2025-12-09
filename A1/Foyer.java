/**
 * Represents the foyer that manages admission and discharge of patients.
 *
 * @author Luyun Li (ID: 1586333)
 */

public class Foyer {
    // the Foyer may contain one newly admitted Patient and
    // one Patient waiting to be discharged at the same time
    private Patient admitted;
    private Patient discharged;

    // we use the following diagram to illustrate four methods of Foyer
    // and how a patient interacts with Foyer
    // (the numbers (1)-(4) indicate the ordering)


    //                     (1)              _________         (2)
    // [producer]  --- arriveAtED()   ---> |         |  --- leave() ---> [triage]
    //                                     |  Foyer  |
    // [consumer] <--- departFromED() ---  |_________| <--- enter() ---  [triage or treatment]
    //                     (4)                                (3)


    // a patient `p` arrives at ED to be admitted
    public synchronized void arriveAtED(Patient p) {
        // the producer has to wait if there is a patient admitted already
        while (this.admitted != null) {
            tryWait();
        }
        this.admitted = p;
        System.out.println(this.admitted.toString() + " admitted to ED.");
        notifyAll();
    }

    // the patient ready to be discharged departs from ED
    public synchronized void departFromED() {
        // the consumer has to wait if there is no patient discharged or
        // the patient hasn't been released by their nurse
        while (this.discharged == null || this.discharged.allocated) {
            tryWait();
        }
        System.out.println(this.discharged + " discharged from ED.");

        this.discharged = null;
        notifyAll();
    }

    // return the admitted patient
    public synchronized Patient getAdmittedPatient() {
        // the nurse has to wait if there is no patient admitted or
        // the patient has been allocated to another nurse
        while (this.admitted == null || this.admitted.allocated) {
            tryWait();
        }
        notifyAll();
        return this.admitted;
    }

    // the admitted patient leaves the foyer
    public synchronized void leave() {
        while (this.admitted == null) {
            tryWait();
        }
        Patient p = this.admitted;
        this.admitted = null;
        System.out.println(p + " leaves Foyer.");
        notifyAll();
    }

    // a patient `p` enters the foyer to be discharged
    public synchronized void enter(Patient p) {
        while (this.discharged != null) {
            tryWait();
        }
        this.discharged = p;
        System.out.println(this.discharged.toString() + " enters Foyer.");
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
