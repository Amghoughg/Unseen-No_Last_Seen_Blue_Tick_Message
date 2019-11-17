package com.example.unseenmessagesfha.Configurations;


import io.realm.RealmConfiguration;

public class RealmConfig {

    public RealmConfig() {

    }

    public RealmConfiguration MessengerConfig()
    {

        return new RealmConfiguration.Builder()
                .name("Messenger.realm")
                .schemaVersion(42)
                .build();
    }
    public RealmConfiguration WhatsappConfig()
    {

        return new RealmConfiguration.Builder()
                .name("Whatsapp.realm")
                .schemaVersion(42)
                .build();
    }
    public RealmConfiguration InstagramConfig()
    {

        return new RealmConfiguration.Builder()
                .name("Instagram.realm")
                .schemaVersion(42)
                .build();
    }
    public RealmConfiguration ImoConfig()
    {

        return new RealmConfiguration.Builder()
                .name("Imo.realm")
                .schemaVersion(42)
                .build();
    }
    public RealmConfiguration SkypeConfig()
    {

        return new RealmConfiguration.Builder()
                .name("Skype.realm")
                .schemaVersion(42)
                .build();
    }
    public RealmConfiguration AppsConfig()
    {
        return new RealmConfiguration.Builder()
        .name("Tabs.realm")
        .schemaVersion(42)
        .build();
    }
    public RealmConfiguration AllChat()
    {
        return new RealmConfiguration.Builder()
                .name("AllChat.realm")
                .schemaVersion(42)
                .build();
    }
    public RealmConfiguration ViberConfig()
    {
        return new RealmConfiguration.Builder()
                .name("Viber.realm")
                .schemaVersion(42)
                .build();
    }
}
