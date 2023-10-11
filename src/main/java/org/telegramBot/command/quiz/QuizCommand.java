package org.telegramBot.command.quiz;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegramBot.button.ButtonExit;
import org.telegramBot.button.ButtonsForQuiz;
import org.telegramBot.command.Command;
import org.telegramBot.command.message.InformationAboutMessage;
import org.telegramBot.service.SendBotMessageService;

import java.util.*;

import static org.telegramBot.command.CommandName.ENDQUIZ;
import static org.telegramBot.command.CommandName.QUIZ;

public class QuizCommand extends InformationAboutMessage implements Command {
    private final SendBotMessageService sendBotMessageService;
    private final EditMessageText editMessageText = new EditMessageText();

    private final ButtonsForQuiz buttonsForQuiz = new ButtonsForQuiz();
    public static final ButtonExit buttonExit = new ButtonExit();

    private static final String LANGUAGE = "org.telegramBot.wordStorage.Resources%s";

    private static Iterator<Integer> iterator;
    private static ResourceBundle wordTo;
    private static ResourceBundle wordFrom;
    private static String wordIndex;
    private static int CORRECT_ANSWERS = 0;

    private static short STAGE = 0;
    private static String LANGUAGE_TO;
    private static String LANGUAGE_FROM;

    public static final String QUIZ_MESSAGE = "Переведите слово: \"%s\"";
    public static final String QUIZ_HELP = String.format("%s - Буду играть с вами в КВИЗ.", QUIZ.getCommandName());
    private static final String QUIZ_WIN = "да, всё верно! Ну ты просто красучик(ца)!";
    private static final String QUIZ_LOSE = "ШТОШ... УВЫ... А вы подавали надежды...\nЭто слово переводится как: \"%s\"";
    private static final String QUIZ_EXIT = "УВЫ... У меня закончились слова.\nНажмите сюда чтобы выйти из квиза и узнать свой результат.";
    private static final String QUIZ_END = String.format("нажмите кнопку выйти или %s", ENDQUIZ.getCommandName());

    public QuizCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }
    // появился вариант не ебаться с отсылкой сообщений, а вызвать команду конецквиза и там уже решать вопрос.
    @Override
    public void execute(Update update) {

        switch (STAGE) {
            case 0 -> {
                STAGE++;

                SendMessage sendMessage = new SendMessage();
                String textMessage = "выберете язык на каком должны быть слова.";

                buttonsForQuiz.attachKeyboard(sendMessage);
                sendBotMessageService.sendMessageWithInlineButton(sendMessage, getStringChatId(update), textMessage);
            }
            case 1 -> {
                LANGUAGE_FROM = getLanguageFrom(update);
                if (LANGUAGE_FROM == null) {
                    STAGE = 0;
                    sendBotMessageService.sendMessage(getStringChatId(update), UNKNOWN_LANGUAGE);
                    return;
                }

                STAGE++;

                String textMessage = "выберете язык, на который хотите переводить слова";

                buttonsForQuiz.attachKeyboard(editMessageText);
                sendBotMessageService.sendEditMessageText(editMessageText, getLongChatId(update), getMessageId(update), textMessage);
            }
            case 2 -> {
                LANGUAGE_TO = getLanguageTo(update);
                if (LANGUAGE_TO == null) {
                    STAGE = 0;
                    sendBotMessageService.sendMessage(getStringChatId(update), UNKNOWN_LANGUAGE);
                    return;
                }
                STAGE++;

                iterator = getSequenceWords().iterator();
                wordIndex = iterator.next().toString();
                wordTo = ResourceBundle.getBundle(String.format(LANGUAGE, LANGUAGE_TO.toUpperCase()));
                wordFrom = ResourceBundle.getBundle(String.format(LANGUAGE, LANGUAGE_FROM.toUpperCase()));

                String textMessage = String.format("Игра началась!\n" + QUIZ_MESSAGE
                        + "\n\nЧтобы завершить игру напишите: %s", getWord(wordIndex), ENDQUIZ.getCommandName());

                buttonsForQuiz.detachKeyboard(editMessageText);
                sendBotMessageService.sendEditMessageText(editMessageText, getLongChatId(update), getMessageId(update), textMessage);
            }
            default -> {
                String chatId = getStringChatId(update);

                if (STAGE == -1) {
                    sendBotMessageService.sendMessage(chatId, QUIZ_END);
                    return;
                }

                String userTranslate = update.getMessage().getText().trim().toLowerCase();
                String botTranslate = wordTo.getString(wordIndex);

                if (botTranslate.equals(userTranslate)) {
                    CORRECT_ANSWERS++;
                    sendBotMessageService.sendMessage(chatId, QUIZ_WIN);
                }
                else {
                    sendBotMessageService.sendMessage(chatId, String.format(QUIZ_LOSE, botTranslate));
                }

                if (!hasNewWord()) {
                    SendMessage sendMessage = new SendMessage();
                    buttonExit.attachKeyboard(sendMessage);

                    sendBotMessageService.sendMessageWithInlineButton(sendMessage, chatId, QUIZ_EXIT);
                }

                wordIndex = iterator.next().toString();
                sendBotMessageService.sendMessage(getStringChatId(update), String.format(QUIZ_MESSAGE, getWord(wordIndex)));
            }
        }
    }

    public static int getCorrectAnswers() {
        return CORRECT_ANSWERS;
    }

    public static void cleanCorrectAnswers() {
        CORRECT_ANSWERS = 0;
    }

    public static void cleanSTAGE() {
        QuizCommand.STAGE = 0;
    }

    private boolean hasNewWord() {
        if (!iterator.hasNext()){
            STAGE = -1;
            return false;
        }
        return true;
    }

    private String getWord(String key) {
        return wordFrom.getString(key);
    }
    // сделать вариант так, чтобы пользователь сам задавал количество слов, которое хочет отгадывать.
    private Set<Integer> getSequenceWords() {
        Random random = new Random();
        Set<Integer> sequenceWords = new LinkedHashSet<>();
        while (sequenceWords.size() < 10) {
            Integer nextWord = random.nextInt(32) + 1;
            sequenceWords.add(nextWord);
        }

        return sequenceWords;
    }
}
