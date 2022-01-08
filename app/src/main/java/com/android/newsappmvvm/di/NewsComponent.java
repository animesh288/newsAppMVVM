package com.android.newsappmvvm.di;

import com.android.newsappmvvm.home.viewmodel.MainActivityViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NewsModule.class})
public interface NewsComponent {

    public void inject(MainActivityViewModel mainActivityViewModel);

}
