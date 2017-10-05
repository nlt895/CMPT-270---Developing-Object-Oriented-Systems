/**
 * Kristine Trinh
 * nlt895
 * 11190412
 */
public class Main {

    public static void main(String[] args) {
        Params params = new Params(true, false, false, false, false, false, true, false, State.INITIALSTATE);
        DVDSimulationDisplay dvdSimulationDisplay = new DVDSimulationDisplay(params);
        DVDPlayer dvdPlayer = new DVDPlayer(dvdSimulationDisplay, params);
        RemoteControl remoteControl = new RemoteControl(dvdPlayer);
    }
}
