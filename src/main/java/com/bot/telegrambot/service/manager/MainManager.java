package com.bot.telegrambot.service.manager;

import com.bot.telegrambot.service.factory.KeyboardFactory;
import com.bot.telegrambot.telegram.Bot;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

import static com.bot.telegrambot.data.QueryData.fc;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MainManager {
    KeyboardFactory keyboardFactory;

    public BotApiMethod<?> answerCommand(Message message, Bot bot) {
        return SendMessage.builder()
                .text(
                        """
                                🖖 Приветствую в боте прогноза погоды!
                                
                                ✅ Бот был написан в демонстрационных целях
                                ✅ Использовалось API WeatherBit
                                ✅ Использованные технологии: Java & Spring Boot
                                
                                Нажав на кнопку снизу вы переместитесь на страницу прогноза, приятного пользования!
                                """
                )
                .chatId(message.getChatId())
                .replyMarkup(
                        keyboardFactory.getInlineKeyboardMarkup(
                                List.of("⛈ Прогноз ⛈"),
                                List.of(1),
                                List.of(fc.name())
                        )
                )
                .build();
    }


    public BotApiMethod<?> answerQuery(CallbackQuery query, String[] data, Bot bot) {
        return EditMessageText.builder()
                .text(
                        """
                                🖖 Приветствую в боте прогноза погоды!
                                
                                ✅ Бот был написан в демонстрационных целях
                                ✅ Использовалось API WeatherBit
                                ✅ Использованные технологии: Java & Spring Boot
                                
                                Нажав на кнопку снизу вы переместитесь на страницу прогноза, приятного пользования!
                                """
                )
                .chatId(query.getMessage().getChatId())
                .messageId(query.getMessage().getMessageId())
                .replyMarkup(
                        keyboardFactory.getInlineKeyboardMarkup(
                                List.of("⛈ Прогноз ⛈"),
                                List.of(1),
                                List.of(fc.name())
                        )
                )
                .build();
    }
}
