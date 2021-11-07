package org.teamnescafe.jtbpdemo.service;

import org.teamnescafe.jtbpdemo.entity.TelegramUser;

import java.util.List;
import java.util.Optional;

public interface TelegramUserService {

    void save(TelegramUser telegramUser);

    List<TelegramUser> retrieveAllActiveUsers();

    Optional<TelegramUser> findByChatId(String chatId);

    List<TelegramUser> retrieveAllInActiveUsers();

    List<TelegramUser> retrieveAllUsers();
}

