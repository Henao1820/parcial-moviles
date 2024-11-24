package com.myapp11;

import android.app.Application;
import com.google.firebase.FirebaseApp;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Inicializa Firebase una vez al inicio de la aplicación
        FirebaseApp.initializeApp(this);
    }
}
