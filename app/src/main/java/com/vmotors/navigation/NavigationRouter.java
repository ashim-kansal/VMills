package com.vmotors.navigation;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class NavigationRouter {

    private static final List<INavigator> navigators = new ArrayList<>();

    private final Activity activity;

    public NavigationRouter(final Activity activity) {
        this.activity = activity;
    }

    public static void registerNavigator(final INavigator navigator) {
        navigators.add(navigator);
    }

    public boolean navigateTo(final NavigationTarget navigationTarget) {
        for (INavigator navigator : navigators) {
            if (navigator.navigateTo(navigationTarget, activity)) {
                return true;
            }
        }
        return false;
    }

    public Observable<NavigationResult> navigateWithResult(final NavigationTarget navigationTarget) {
        for (INavigator navigator : navigators) {
            final Observable<NavigationResult> navigationResultObservable = navigator.navigateWithResult(navigationTarget, activity);
            if (navigationResultObservable != null) {
                return navigationResultObservable;
            }
        }

        return Observable.just(new NavigationResult());
    }
}
