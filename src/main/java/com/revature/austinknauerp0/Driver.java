package com.revature.austinknauerp0;

import com.revature.austinknauerp0.util.AppState;

public class Driver {

    private static AppState app = new AppState();

    public static void main(String[] args) {
        AppState app = new AppState();

        while (app.getIsRunning()) {
            app.getScreenRouter().route("/welcome");
        }
    }

    public static AppState getApp() {
        return app;
    }
}
