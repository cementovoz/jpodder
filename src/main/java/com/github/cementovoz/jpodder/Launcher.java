package com.github.cementovoz.jpodder;


import com.github.cementovoz.jpodder.db.Connector;
import com.github.cementovoz.jpodder.events.StartEvent;
import com.github.cementovoz.jpodder.events.StopEvent;
import com.github.cementovoz.jpodder.rx.FXScheduler;
import com.github.cementovoz.jpodder.window.MainWindow;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.controlsfx.dialog.ExceptionDialog;

public class Launcher extends Application {

    private Injector injector;
    private EventBus eventBus;

    @Override
    public void init() throws Exception {
        injector = Guice.createInjector(new JPodderModule());

        eventBus = injector.getInstance(EventBus.class);
        eventBus.observable(Exception.class)
                .observeOn(FXScheduler.instance())
                .subscribe(this::showError);
        eventBus.observable().onErrorReturn(it -> null);
        Connector connector = injector.getInstance(Connector.class);
        connector.initialize();
    }

    private void showError(Throwable e) {
        ExceptionDialog dialog = new ExceptionDialog(e);
        dialog.setResizable(true);
        dialog.show();
        dialog.setOnHidden(it -> Platform.exit());
    }

    @Override
    public void stop() throws Exception {
        eventBus.post(new StopEvent());
    }

    @Override
    public void start(Stage stage) throws Exception {
        MainWindow mainWindow = injector.getInstance(MainWindow.class);
        mainWindow.show(stage);
        eventBus.post(new StartEvent());
    }

    public static void main(String[] args) {
        LauncherImpl.launchApplication(Launcher.class, PreloaderImpl.class, args);
    }
}
