package com.github.cementovoz.jpodder;


import com.github.cementovoz.jpodder.events.Initialize;
import com.github.cementovoz.jpodder.events.Initialized;
import com.github.cementovoz.jpodder.events.Start;
import com.github.cementovoz.jpodder.events.Stop;
import com.github.cementovoz.jpodder.rx.FXScheduler;
import com.github.cementovoz.jpodder.window.MainWindow;
import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.controlsfx.dialog.ExceptionDialog;
import rx.functions.Action1;

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
        eventBus.post(new Initialize());
    }

    private void showError(Throwable e) {
        ExceptionDialog dialog = new ExceptionDialog(e);
        dialog.setResizable(true);
        dialog.show();
        dialog.setOnHidden(it -> Platform.exit());
    }

    @Override
    public void stop() throws Exception {
        eventBus.post(new Stop());
    }

    @Override
    public void start(Stage stage) throws Exception {
        MainWindow mainWindow = injector.getInstance(MainWindow.class);
        mainWindow.show(stage);
        eventBus.observable(Initialized.class).subscribe(it -> {
            eventBus.post(new Start());
        });
    }

    public static void main(String[] args) {
        Application.launch(Launcher.class);
    }
}
