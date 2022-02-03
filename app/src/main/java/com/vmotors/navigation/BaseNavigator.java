package com.vmotors.navigation;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

public abstract class BaseNavigator implements INavigator {

    private final List<NavigationRoute> navigationRoutes = new ArrayList<>();

    protected NavigationRoute forTarget(final String target) {
        NavigationRoute route = new NavigationRoute(target);
        navigationRoutes.add(route);
        return route;
    }

    @Override
    public boolean navigateTo(final NavigationTarget navigationTarget, final Activity activity) {
        for (NavigationRoute navigationRoute : navigationRoutes) {
            if (navigationRoute.matches(navigationTarget)) {
                navigationRoute.navigateTo(navigationTarget, activity);
                return true;
            }
        }
        return false;
    }

    @Override
    public Observable<NavigationResult> navigateWithResult(final NavigationTarget navigationTarget, final Activity activity) {
//        for (NavigationRoute navigationRoute : navigationRoutes) {
//            if (navigationRoute.matches(navigationTarget)) {
//                return navigationRoute.navigateWithResult(navigationTarget, activity);
//            }
//        }
        return null;
    }

    protected class NavigationRoute {

        private final String target;

        private Class<? extends Activity> activityClass;

        private boolean useId = false;

        private String idExtraKey = null;

        private final Map<String, Object> params = new HashMap<>();

        public NavigationRoute(final String target) {
            this.target = target;
        }

        public NavigationRoute withId() {
            useId = true;
            return this;
        }

        public NavigationRoute openActivity(final Class<? extends Activity> activityClass) {
            this.activityClass = activityClass;
            return this;
        }

        public NavigationRoute putIdAsExtra(final String key) {
            idExtraKey = key;
            return this;
        }

        public NavigationRoute withParam(final String name, final String value) {
            params.put(name, value);
            return this;
        }

        public NavigationRoute withParam(final String name, final boolean value) {
            params.put(name, value);
            return this;
        }

        public NavigationRoute withParam(final String name, final long value) {
            params.put(name, value);
            return this;
        }

        public NavigationRoute withParam(final String name, final int value) {
            params.put(name, value);
            return this;
        }

        public NavigationRoute withParam(final String name, final Parcelable value) {
            params.put(name, value);
            return this;
        }

        public NavigationRoute withParam(final String name, final Serializable value) {
            params.put(name, value);
            return this;
        }

        boolean matches(final NavigationTarget navigationTarget) {
            return navigationTarget.getTarget().equals(target);
        }

        void navigateTo(final NavigationTarget navigationTarget, final Activity activity) {
            final Intent intent = createIntent(navigationTarget, activity);
            activity.startActivity(intent);
        }

        void putParams(Bundle bundle) {
            for (String key : params.keySet()) {
                Object obj = params.get(key);
                if (obj instanceof String) {
                    bundle.putString(key, (String) obj);
                } else if (obj instanceof Long) {
                    bundle.putLong(key, (Long) obj);
                } else if (obj instanceof Integer) {
                    bundle.putInt(key, (Integer) obj);
                } else if (obj instanceof Parcelable) {
                    bundle.putParcelable(key, (Parcelable) obj);
                } else if (obj instanceof Boolean) {
                    bundle.putBoolean(key, (Boolean) obj);
                } else if (obj instanceof Serializable) {
                    bundle.putSerializable(key, (Serializable) obj);
                }
            }
        }

        Bundle getParams() {
            Bundle bundle = new Bundle();
            putParams(bundle);
            return bundle;
        }

//        Observable<NavigationResult> navigateWithResult(final NavigationTarget navigationTarget, final Activity activity) {
//            final BaseApp baseActivity = (BaseApp) activity;
//            final Intent intent = createIntent(navigationTarget, activity);
//            return baseActivity
//                    .startActivityForResult(intent)
//                    .map(activityResult -> new NavigationResult(activityResult.first, activityResult.second));
//        }

        Intent createIntent(final NavigationTarget navigationTarget, final Activity activity) {
            Intent intent = new Intent(activity, activityClass);
            if (useId && navigationTarget.getId() != null && idExtraKey != null) {
                intent.putExtra(idExtraKey, navigationTarget.getId());
            }

            if (navigationTarget.shouldPassAllData()) {
                final Intent previousIntent = activity.getIntent();
                if (previousIntent != null) {
                    intent.putExtras(previousIntent);
                }
            }

            intent.putExtras(navigationTarget.getParams());
            intent.putExtras(getParams());

            if (navigationTarget.shouldNewTask()) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            }

            if (navigationTarget.shouldClearStack()) {
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            }
            if (navigationTarget.shouldClearTopWithSingleTop()) {
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            }
            if (navigationTarget.shouldClearTop()) {
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            }

            return intent;
        }
    }
}
