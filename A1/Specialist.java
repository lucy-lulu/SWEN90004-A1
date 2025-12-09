/**
 * Represents the specialist who treats severe patients.
 *
 * @author Luyun Li (ID: 1586333)
 */

public class Specialist extends Thread {
    private Treatment treatment;

    Specialist(Treatment treatment) {
        this.treatment = treatment;
    }

    // repeatedly treating severe patients while
    // leaving treatment room in between treating each patient
    public void run() {
        while (true) {
            System.out.println("Specialist enters treatment room.");

            treatment.treat();

            System.out.println("Specialist leaves treatment room.");

            try {
                sleep(Params.SPECIALIST_AWAY_TIME);
            } catch (InterruptedException e) {
                this.interrupt();
            }
        }
    }
}
