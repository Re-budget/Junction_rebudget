package nordea.junction.rebudget.di.module;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import nordea.junction.rebudget.App;
import nordea.junction.rebudget.api.RestApi;
import nordea.junction.rebudget.di.qualifier.BaseContext;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Dependency Injection Dagger2 Application Module.
 *
 * Created by Bel on 24/11/2017.
 */

@Module
public class AppModule {

    private App mApp;

    private static final String BASE_URL = "https://app.lunni360.fi/";
    private static final String PREFERENCES_NAME = "PREFERENCES";

    public AppModule(App app){
        mApp = app;
    }

    @Provides
    @Singleton
    App providesApp(){
        return mApp;
    }

    @Provides
    @BaseContext
    Context providesContext(){
        return mApp.getBaseContext();
    }


    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(App context) {
        return context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                //converts Retrofit response into Observable
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    Cache provideHttpCache(App application) {
        int cacheSize = 10 * 1024 * 1024;
        return new Cache(application.getCacheDir(), cacheSize);
    }

    @Provides
    @Singleton
    public OkHttpClient provideHttpClient(Cache cache) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.cache(cache);
        return client.build();
    }

    @Provides
    public RestApi provideItemService(Retrofit retrofit) {
        return retrofit.create(RestApi.class);
    }

}
