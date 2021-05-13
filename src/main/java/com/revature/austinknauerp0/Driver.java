package com.revature.austinknauerp0;

import com.revature.austinknauerp0.util.AppState;
import com.revature.austinknauerp0.util.structures.ArrayList;
import com.revature.austinknauerp0.util.structures.List;
import com.revature.austinknauerp0.util.structures.Stack;

public class Driver {

    private static AppState app = new AppState();

    public static void main(String[] args) {
       AppState app = new AppState();

        while (app.getIsRunning()) {
            app.getScreenRouter().getCurrentScreen().render();
        }



    }

    public static AppState getApp() {
        return app;
    }
}
