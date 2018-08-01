package formers.factory;

import formers.boundary.ui.presenter.FormersPresenter;
import formers.boundary.ui.presenter.FormersPresenterImpl;
import formers.core.database.Database;
import formers.core.database.DatabaseImpl;
import formers.core.users.AdminCore;

public class ObjectsFactory {

    public static Database getDatabase() {
        return new DatabaseImpl();
    }

    public static AdminCore getAdminCore() {
        return new AdminCore(getDatabase());
    }

    public static FormersPresenter getFormsPresenter() {
        return new FormersPresenterImpl(getAdminCore());
    }
}
