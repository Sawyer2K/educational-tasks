package ru.cft.focusstart.task6.client.model;

import ru.cft.focusstart.task6.client.view.ClientView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

class ViewNotifier {
    private final List<ClientView> clientViews = new ArrayList<>();

    void attachView(ClientView clientView) {
        clientViews.add(clientView);
    }

    void notifyViewsAboutServerAddressRequest() {
        for (ClientView clientView : clientViews) {
            clientView.renderServerAddressRequest();
        }
    }

    void notifyViewsAboutPortRequest() {
        for (ClientView clientView : clientViews) {
            clientView.renderPortRequest();
        }
    }

    void notifyViewsAboutNameRequest() {
        for (ClientView clientView : clientViews) {
            clientView.renderNameRequest();
        }
    }

    void notifyViewsAboutUsersUpdate(Set<String> allUserNames) {
        clientViews.forEach(clientView -> clientView.renderRefreshUsers(allUserNames));
    }

    void notifyViewsAboutNewMessage(String message) {
        clientViews.forEach(clientView -> clientView.renderRefreshMessages(message));
    }
}