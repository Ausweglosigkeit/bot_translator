package org.telegramBot.command.notification;

import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegramBot.bot.DarkZeit;
import org.telegramBot.command.message.InformationAboutMessage;
import org.telegramBot.service.SendBotMessageService;

import java.io.File;
import java.util.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class NotificationSchedule extends InformationAboutMessage {

    private final SendBotMessageService sendBotMessageService;
    private static ScheduledExecutorService executorServiceCat;
    private static ScheduledExecutorService executorServiceWord;
    private final Update update;

    private static final String IMAGE_CAPTION_CAT = EmojiParser.parseToUnicode("Цалую тебя в щёчки. :kissing_heart:");
    private static final long INITIAL_DELAY_CAT = 10;
    private static final long DELAY_CAT = 40;

    private static final String IMAGE_CAPTION_WORD = "%s - Слово дня!\n\nПеревод: %s";
    private static final long INITIAL_DELAY_WORD = 5;
    private static final long DELAY_WORD = 5;

    private static final ResourceBundle LANGUAGE_RU = ResourceBundle.getBundle("org.telegramBot.wordStorage.ResourcesRU");
    private static final ResourceBundle LANGUAGE_EN = ResourceBundle.getBundle("org.telegramBot.wordStorage.ResourcesEN");

    public NotificationSchedule(SendBotMessageService sendBotMessageService, Update update, Notification notification) {
        this.sendBotMessageService = sendBotMessageService;
        this.update = update;

        if (notification.equals(Notification.CAT)) {
            startSendCatsNotification();
        } else {
            startSendWordsNotification();
        }
    }

    private void startSendCatsNotification() {
        executorServiceCat = new ScheduledThreadPoolExecutor(1);

        executorServiceCat.scheduleWithFixedDelay(new CatsNotification(), INITIAL_DELAY_CAT, DELAY_CAT, TimeUnit.SECONDS);
    }

    private void startSendWordsNotification() {
        executorServiceWord = new ScheduledThreadPoolExecutor(1);

        executorServiceWord.scheduleWithFixedDelay(new WordsNotification(), INITIAL_DELAY_WORD, DELAY_WORD, TimeUnit.SECONDS);
    }

    public static void stopSendWordsNotification() {
        executorServiceWord.shutdown();
    }

    public static void stopSendCatsNotification() {
        executorServiceCat.shutdown();
    }

    class CatsNotification implements Runnable {
        @Override
        public void run() {
            try {
                String imagePath = String.format(DarkZeit.BOT_DATA.getString("cat.path.photo"), new Random().nextInt(16) + 1);
                sendBotMessageService.sendPhoto(getStringChatId(update), imagePath, IMAGE_CAPTION_CAT);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class WordsNotification implements Runnable {
        private static final Set<Integer> sequenceImages = getSequenceImages();
        private Iterator<Integer> iterator;

        public WordsNotification() {
            iterator = sequenceImages.iterator();
        }

        @Override
        public void run() {
            try {
                if (!iterator.hasNext()){
                    iterator = sequenceImages.iterator();
                }
                Integer indexImage = iterator.next();
                String absolutPath = String.format(DarkZeit.BOT_DATA.getString("word.path.image"), indexImage);

                String word = getWord(indexImage);
                String translateWord = getTranslate(indexImage);

                File image = new File(absolutPath);
                if (image.exists() && image.isFile()) {
                    sendBotMessageService.sendPhoto(getStringChatId(update), absolutPath, String.format(IMAGE_CAPTION_WORD, word, translateWord));
                }
                else {
                    ImageGeneration.generate(word, translateWord, indexImage);
                    sendBotMessageService.sendPhoto(getStringChatId(update), absolutPath, String.format(IMAGE_CAPTION_WORD, word, translateWord));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private String getTranslate(Integer key) {
            return LANGUAGE_RU.getString(String.valueOf(key));
        }

        private String getWord(Integer key) {
            return LANGUAGE_EN.getString(String.valueOf(key));
        }

        private static Set<Integer> getSequenceImages() {
            Random random = new Random();
            Set<Integer> sequenceWords = new LinkedHashSet<>();
            while (sequenceWords.size() < 32) {
                Integer nextWord = random.nextInt(32) + 1;
                sequenceWords.add(nextWord);
            }

            return sequenceWords;
        }
    }
}
