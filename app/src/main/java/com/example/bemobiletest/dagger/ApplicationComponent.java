package com.example.bemobiletest.dagger;

import com.example.bemobiletest.screens.detail.Detail;
import com.example.bemobiletest.screens.main.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, NetworkingModule.class})
public interface ApplicationComponent {

    void inject (MainActivity mainActivity);

    void inject (Detail detail);
}
