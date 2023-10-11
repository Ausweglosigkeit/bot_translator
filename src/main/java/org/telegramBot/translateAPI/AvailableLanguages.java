package org.telegramBot.translateAPI;

import com.vdurmont.emoji.EmojiParser;

import static org.telegramBot.translateAPI.Language.*;

public class AvailableLanguages {
    private static final StringBuilder TRANSLATE_AVAILABLE_LANGUAGES = new StringBuilder();

    public static String getLanguages() {
        addAvailableLanguage(ENGLISH.getShortLanguageName(), ENGLISH.getNameLanguage(), ENGLISH.getFlagOfCountry());
        addAvailableLanguage(ALBANIAN.getShortLanguageName(), ALBANIAN.getNameLanguage(), ALBANIAN.getFlagOfCountry());
        addAvailableLanguage(ARMENIAN.getShortLanguageName(), ARMENIAN.getNameLanguage(), ARMENIAN.getFlagOfCountry());
        addAvailableLanguage(DUTCH.getShortLanguageName(), DUTCH.getNameLanguage(), DUTCH.getFlagOfCountry());
        addAvailableLanguage(FRENCH.getShortLanguageName(), FRENCH.getNameLanguage(), FRENCH.getFlagOfCountry());
        addAvailableLanguage(GERMAN.getShortLanguageName(), GERMAN.getNameLanguage(), GERMAN.getFlagOfCountry());
        addAvailableLanguage(ITALIAN.getShortLanguageName(), ITALIAN.getNameLanguage(), ITALIAN.getFlagOfCountry());
        addAvailableLanguage(JAPANESE.getShortLanguageName(), JAPANESE.getNameLanguage(), JAPANESE.getFlagOfCountry());
        addAvailableLanguage(KAZAKH.getShortLanguageName(), KAZAKH.getNameLanguage(), KAZAKH.getFlagOfCountry());
        addAvailableLanguage(NORWEGIAN.getShortLanguageName(), NORWEGIAN.getNameLanguage(), NORWEGIAN.getFlagOfCountry());
        addAvailableLanguage(POLISH.getShortLanguageName(), POLISH.getNameLanguage(), POLISH.getFlagOfCountry());
        addAvailableLanguage(RUSSIAN.getShortLanguageName(), RUSSIAN.getNameLanguage(), RUSSIAN.getFlagOfCountry());
        addAvailableLanguage(SLOVAK.getShortLanguageName(), SLOVAK.getNameLanguage(), SLOVAK.getFlagOfCountry());
        addAvailableLanguage(SPANISH.getShortLanguageName(), SPANISH.getNameLanguage(), SPANISH.getFlagOfCountry());
        addAvailableLanguage(SWEDISH.getShortLanguageName(), SWEDISH.getNameLanguage(), SWEDISH.getFlagOfCountry());
        addAvailableLanguage(TURKISH.getShortLanguageName(), TURKISH.getNameLanguage(), TURKISH.getFlagOfCountry());
        addAvailableLanguage(FINNISH.getShortLanguageName(), FINNISH.getNameLanguage(), FINNISH.getFlagOfCountry());
        addAvailableLanguage(UKRAINIAN.getShortLanguageName(), UKRAINIAN.getNameLanguage(), UKRAINIAN.getFlagOfCountry());

        return TRANSLATE_AVAILABLE_LANGUAGES.toString();
    }

    private static void addAvailableLanguage(String shortName, String fullName, String flagOfCountry) {
        String flag = EmojiParser.parseToUnicode(flagOfCountry);
        TRANSLATE_AVAILABLE_LANGUAGES
                .append(shortName)
                .append(" - ")
                .append(flag)
                .append(" ")
                .append(fullName)
                .append(" ")
                .append(flag)
                .append("\n");
    }
}
