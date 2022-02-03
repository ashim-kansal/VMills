package com.vmotors;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import com.vmotors.deps.ApplicationModule;
import com.vmotors.deps.DaggerDeps;
import com.vmotors.deps.Deps;
import com.vmotors.networking.NetworkModule;

import java.io.File;

public class BaseFragment extends Fragment {


    Deps deps;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        File cacheFile = new File(getActivity().getCacheDir(), "responses");
        deps = DaggerDeps.builder()
                .applicationModule(new ApplicationModule(getActivity().getApplication()))
                .networkModule(new NetworkModule(cacheFile)).build();

    }

    public Deps getDeps() {
        return deps;
    }

}
