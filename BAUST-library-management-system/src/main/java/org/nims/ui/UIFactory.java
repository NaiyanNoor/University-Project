package org.nims.ui;

import org.nims.commands.Commander;
import org.nims.library.Library;

public class UIFactory {
    private final Library library;
    private final Commander commander;

    public UIFactory(Library library, Commander commander) {
        this.library = library;
        this.commander = commander;
    }
    public UIContract getUI(UI ui, Commander commander) {
        if (ui == UI.CLI) return new CLI(library, commander);
        else return new GUI(library, commander);
    }
}