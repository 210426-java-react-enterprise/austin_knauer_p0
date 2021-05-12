package com.revature.austinknauerp0.util;

import com.revature.austinknauerp0.screens.Screen;

public class ScreenRouter {

    // to be updated with better data structure; array is just for getting app working

    private Screen[] screens;

    ScreenRouter() {
        screens = new Screen[9];
    }

    public ScreenRouter add(Screen screen) {
        for(int i = 0; i < screens.length; i++) {
            if (screens[i] == null) {
                screens[i] = screen;
                i = screens.length;
            }
        }

        return this;
    }

    public void route(String newRoute) {
        for (int i = 0; i < screens.length; i++) {
            if (screens[i].route == newRoute) {
                screens[i].render();
            }
        }
    }
}
