package com.revature.austinknauerp0.util;

import com.revature.austinknauerp0.screens.Screen;
import com.revature.austinknauerp0.util.structures.ArrayList;

public class ScreenRouter {

    // to be updated with better data structure; array is just for getting app working

    private ArrayList<Screen> screens;
    private Screen currentScreen;

    ScreenRouter() {
        screens = new ArrayList<>();
    }

    public ScreenRouter add(Screen screen) {
        screens.add(screen);

        return this;
    }

    public void setStartingScreen(Screen screen) {
        if (currentScreen == null) {
            currentScreen = screen;
        }
    }

    public Screen getCurrentScreen() {
        return currentScreen;
    }

    public void route(String newRoute) {
        for (int i = 0; i < screens.size(); i++) {
            if (screens.get(i).route == newRoute) {
                currentScreen = screens.get(i);
            }
        }
    }
}
