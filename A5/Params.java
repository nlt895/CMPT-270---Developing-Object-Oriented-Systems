/**
 * Kristine Trinh
 * nlt895
 * 11190412
 */
public class Params {
    boolean isDVD_Inserted;
    boolean isDVD_Playing;
    boolean isPaused;
    boolean isDrawer_Open;
    boolean isStreaming_Movie;
    boolean isInetConnected;
    boolean isInetAvailable;
    boolean isLastVisited;
    State state;

    public Params(boolean isDVD_Inserted, boolean isDVD_Playing, boolean isPaused, boolean isDrawer_Open, boolean isStreaming_Movie, boolean isInetConnected, boolean isInetAvailable, boolean isLastVisited, State state) {
        this.state = state;
        this.isDVD_Inserted = isDVD_Inserted;
        this.isDVD_Playing = isDVD_Playing;
        this.isPaused = isPaused;
        this.isDrawer_Open = isDrawer_Open;
        this.isStreaming_Movie = isStreaming_Movie;
        this.isInetConnected = isInetConnected;
        this.isInetAvailable = isInetAvailable;
        this.isLastVisited = isLastVisited;
    }
}
