package client.states;

import client.Client;

public abstract class State {

    protected Client client;

    public State(Client client) {
        this.client = client;
    }

    public void handleResult(String result) {

    }
}
