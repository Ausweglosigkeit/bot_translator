package org.telegramBot.translateAPI;

public enum Language {
    ENGLISH("en", "Английский язык",":gb:"),
    ALBANIAN("sq", "Албанский язык",":al:"),
    ARMENIAN("hy", "Армянский язык",":am:"),
    DUTCH("nl", "Нидерландский язык",":nl:"),
    FRENCH("fr", "Французский язык",":fr:"),
    GERMAN("de", "Немецкий язык",":de:"),
    ITALIAN("it", "Итальянский язык",":it:"),
    JAPANESE("ja", "Японский язык",":jp:"),
    KAZAKH("kk", "Казахский язык",":kz:"),
    NORWEGIAN("no", "Норвежский язык",":no:"),
    POLISH("pl", "Польский язык",":pl:"),
    RUSSIAN("ru", "Русский язык",":ru:"),
    SLOVAK("sk", "Словацкий язык",":sl:"),
    SPANISH("es", "Испанский язык",":es:"),
    SWEDISH("sv", "Шведский язык",":se:"),
    TURKISH("tr", "Турецкий язык",":tr:"),
    FINNISH("fi", "Финский язык",":fi:"),
    UKRAINIAN("uk", "Украинский язык",":ua:");

    private final String shortLanguageName;
    private final String nameLanguage;
    private final String flagOfCountry;

    Language(String shortLanguageName, String nameLanguage, String flagOfCountry) {
        this.shortLanguageName = shortLanguageName;
        this.flagOfCountry = flagOfCountry;
        this.nameLanguage = nameLanguage;
    }

    public String getNameLanguage() {
        return nameLanguage;
    }

    public String getShortLanguageName() {
        return shortLanguageName;
    }

    public String getFlagOfCountry() {
        return flagOfCountry;
    }
}
