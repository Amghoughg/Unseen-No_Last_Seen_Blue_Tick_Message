package com.example.unseenmessagesfha.Models;

import io.realm.RealmObject;

public class Apps extends RealmObject {
    private int Messenger;
    private int WhatsApp;
    private int Instagram;
    private int Imo;
    private int Skype;
    private int Viber;

    public int getViber() {
        return Viber;
    }

    public void setViber(int viber) {
        Viber = viber;
    }

    public Apps() {
    }

    public int getImo() {
        return Imo;
    }

    public void setImo(int imo) {
        Imo = imo;
    }

    public int getSkype() {
        return Skype;
    }

    public void setSkype(int skype) {
        Skype = skype;
    }

    public int getMessenger() {
        return Messenger;
    }

    public void setMessenger(int messenger) {
        Messenger = messenger;
    }

    public int getWhatsApp() {
        return WhatsApp;
    }

    public void setWhatsApp(int whatsApp) {
        WhatsApp = whatsApp;
    }

    public int getInstagram() {
        return Instagram;
    }

    public void setInstagram(int instagram) {
        Instagram = instagram;
    }
}
