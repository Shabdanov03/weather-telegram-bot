package com.bot.telegrambot.service.handler;

import com.bot.telegrambot.data.QueryData;
import com.bot.telegrambot.service.manager.ForecastManager;
import com.bot.telegrambot.service.manager.MainManager;
import com.bot.telegrambot.telegram.Bot;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CallbackQueryHandler {
    MainManager mainManager;
    ForecastManager forecastManager;

    public BotApiMethod<?> answer(CallbackQuery query, Bot bot) {
        String[] data = query.getData().split("_");
        QueryData queryData;
        try {
            queryData = QueryData.valueOf(data[0]);
        }catch (Exception e){
            log.error("Unsupported query data received: {}", query.getData());
            return null;
        }


        switch (queryData){
            case main -> {
                return mainManager.answerQuery(query,data,bot);
            }
            case fc -> {
                return forecastManager.answerQuery(query,data,bot);
            }
            case empty -> {
                return AnswerCallbackQuery.builder()
                        .callbackQueryId(query.getId())
                        .text("Это кнопка нечего не делает")
                        .build();
            }
        }

        throw new UnsupportedOperationException();
    }
}
