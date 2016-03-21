package com.github.cementovoz.jpodder;

import com.github.cementovoz.jpodder.db.Connector;
import com.github.cementovoz.jpodder.window.MainWindow;
import com.github.cementovoz.jpodder.window.panels.ContentPanel;
import com.github.cementovoz.jpodder.window.panels.LeftPanel;
import com.github.cementovoz.jpodder.window.panels.MenuPanel;
import com.github.cementovoz.jpodder.window.panels.StatusPanel;
import com.google.inject.AbstractModule;


public class JPodderModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(EventBus.class).asEagerSingleton();
        bind(MainWindow.class);
        bind(LeftPanel.class);
        bind(ContentPanel.class);
        bind(MenuPanel.class);
        bind(StatusPanel.class);
        bind(Connector.class).asEagerSingleton();
    }
}
