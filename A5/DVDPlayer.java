/**
 * Kristine Trinh
 * nlt895
 * 11190412
 */
public class DVDPlayer {
    private DVDSimulationDisplay dvdSimulationDisplay;

    private Params params;

    public DVDPlayer(DVDSimulationDisplay dvdSimulationDisplay, Params params) {
        this.params = params;
        this.dvdSimulationDisplay = dvdSimulationDisplay;
        dvdSimulationDisplay.displayCmd("Welcome to DVD Simulation Display\n", params);
    }

    private String turnOn() {
        params.state = State.HOMESTATE;
        return "DVD Player turned on";
    }

    private String turnOff() {
        params = new Params(true, false, false, false, false, false, true, false, State.INITIALSTATE);
        return "DVD Player turned off";
    }

    private String navRightBtn() {
        return "Move the cursor display to the right";
    }

    private String navLeftBtn() {
        return "Move the cursor display to the left";
    }

    private String navUpBtn() {
        return "Move the cursor up one line";
    }

    private String navDownBtn() {
        return "Move the cursor display to the right";
    }

    private String playDVD() {
        params.isPaused = false;
        params.isDVD_Playing = true;
        params.isDrawer_Open = false;
        params.state = State.DVDMODE;
        return "Playing DVD";
    }

    private String stopDVD() {
        params.isDVD_Playing = false;
        params.isPaused = false;
        return "DVD stopped";
    }

    private String pauseDVD() {
        params.isPaused = true;
        return "DVD paused";
    }

    private String pauseMovie() {
        params.isPaused = true;
        return "Movie paused";
    }

    private String ffwd() {
        return "Fast forward";
    }

    private String frev() {
        return "Fast reverse";
    }

    private String skipSceneFwd() {
        return "Skip scene forward";
    }

    private String skipSceneRev() {
        return "Skip scene reverse";
    }

    private String closeDrawer() {
        params.isDrawer_Open = false;
        return "Close drawer";
    }

    private String openDrawer() {
        params.isDrawer_Open = true;
        return "Open drawer";
    }

    private String accept() {
        return "The highlighted item has been accepted";
    }

    private String home() {
        params.isStreaming_Movie = false;
        params.isDVD_Playing = false;
        params.isPaused = false;
        params.isInetConnected = false;
        params.state = State.HOMESTATE;
        return "Home Screen";
    }

    private String internet() {
        if (!params.isInetAvailable) return "No internet service is available - the connection can't be established";
        params.isInetConnected = true;
        params.state = State.INTERNETMODE;
        if (!params.isLastVisited) {
            params.isLastVisited = true;
            return "'List of sites' screen has been shown.\nConnected to the selected site";
        }
        return "Connected to the last visited site";
    }

    private String streamMovie() {
        params.isStreaming_Movie = true;
        params.isPaused = false;
        return "Streaming movie";
    }

    private String stopMovie() {
        params.isPaused = false;
        params.isStreaming_Movie = false;
        return "Movie stopped";
    }

    private String playFFwd() {
        params.isPaused = false;
        return "Playing fast forward";
    }

    private String playFRev() {
        params.isPaused = false;
        return "Playing fast reverse";
    }

    public void sendPlayerCMD(String command) {
        String result = processRemoteCMD(command);
        dvdSimulationDisplay.displayCmd(result, params);
    }

    private String processRemoteCMD(String command) {
        String result = null;
        switch (params.state) {
            case INITIALSTATE:
                switch (command) {
                    case "PowerBtn":
                        result = turnOn();
                        return result;
                    default:
                        return result;
                }
            case HOMESTATE:
                switch (command) {
                    case "PowerBtn":
                        return turnOff();
                    case "PlayBtn":
                        if (params.isDVD_Inserted) {
                            return playDVD();
                        }
                        break;
                    case "NavRightBtn":
                        return navRightBtn();
                    case "NavLeftBtn":
                        return navLeftBtn();
                    case "NavUpBtn":
                        return navUpBtn();
                    case "NavDownBtn":
                        return navDownBtn();
                    case "InternetBtn":
                        if (params.isInetAvailable) return internet();
                        break;
                }
                break;
            case DVDMODE:
                switch (command) {
                    case "HomeBtn":
                        return home();
                    case "PowerBtn":
                        return turnOff();
                    case "NavRightBtn":
                        return navRightBtn();
                    case "NavLeftBtn":
                        return navLeftBtn();
                    case "NavUpBtn":
                        return navUpBtn();
                    case "NavDownBtn":
                        return navDownBtn();
                    case "PlayBtn":
                        if (params.isDVD_Inserted) {
                            return playDVD();
                        }
                        break;
                    case "StopBtn":
                        if (params.isDVD_Playing) return stopDVD();
                        break;
                    case "PauseBtn":
                        if (params.isDVD_Playing && !params.isPaused) return pauseDVD();
                        break;
                    case "FFwdBtn":
                        return ffwd();
                    case "FRevBtn":
                        return frev();
                    case "SkipSceneFwdBtn":
                        return skipSceneFwd();
                    case "SkipSceneRevBtn":
                        return skipSceneRev();
                    case "EjectBtn":
                        if (params.isDVD_Inserted) return openDrawer();
                        break;
                    case "CloseDrawerBtn":
                        if (params.isDrawer_Open) return closeDrawer();
                        break;
                    case "OpenDrawerBtn":
                        if (!params.isDrawer_Open) return openDrawer();
                        break;
                }
                break;
            case INTERNETMODE:
                switch (command) {
                    case "HomeBtn":
                        return home();
                    case "PowerBtn":
                        return turnOff();
                    case "NavRightBtn":
                        return navRightBtn();
                    case "NavLeftBtn":
                        return navLeftBtn();
                    case "NavUpBtn":
                        return navUpBtn();
                    case "NavDownBtn":
                        return navDownBtn();
                    case "PlayBtn":
                        if (!params.isInetConnected) {
                            return playDVD();
                        }
                        else {
                            return streamMovie();
                        }
                    case "StopBtn":
                        if (params.isStreaming_Movie) return stopMovie();
                        break;
                    case "PauseBtn":
                        if (params.isStreaming_Movie && !params.isPaused) return pauseMovie();
                        break;
                    case "FFwdBtn":
                        return ffwd();
                    case "FRevBtn":
                        return frev();
                    case "SkipSceneFwdBtn":
                        if (params.isStreaming_Movie) return playFFwd();
                        break;
                    case "SkipSceneRevBtn":
                        if (params.isStreaming_Movie) return playFRev();
                        break;
                    case "EjectBtn":
                        if (params.isDVD_Inserted) return openDrawer();
                        break;
                    case "CloseDrawerBtn":
                        if (params.isDrawer_Open) return closeDrawer();
                        break;
                    case "OpenDrawerBtn":
                        if (!params.isDrawer_Open) return openDrawer();
                        break;
                    case "AcceptBtn":
                        return accept();
                }

                break;
        }
        return result;
    }

}
