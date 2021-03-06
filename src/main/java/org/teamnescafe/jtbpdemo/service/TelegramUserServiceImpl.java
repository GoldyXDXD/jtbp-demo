package org.teamnescafe.jtbpdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.teamnescafe.jtbpdemo.entity.TelegramUser;
import org.teamnescafe.jtbpdemo.repo.TelegramUserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TelegramUserServiceImpl implements TelegramUserService {

    private final TelegramUserRepository telegramUserRepository;

    @Autowired
    public TelegramUserServiceImpl(TelegramUserRepository telegramUserRepository) {
        this.telegramUserRepository = telegramUserRepository;
    }

    @Override
    public void save(TelegramUser telegramUser) {
        telegramUserRepository.save(telegramUser);
    }

    @Override
    public List<TelegramUser> retrieveAllActiveUsers() {
        return telegramUserRepository.findAllByActiveTrue();
    }

    @Override
    public Optional<TelegramUser> findByChatId(String chatId) {
        return telegramUserRepository.findById(chatId);
    }

    @Override
    public void changeActive(TelegramUser telegramUser) {
        String chatId = telegramUser.getChatId();
        telegramUserRepository.delete(telegramUser);
        TelegramUser TgUser = new TelegramUser();
        TgUser.setChatId(chatId);
        TgUser.setActive(false);
        telegramUserRepository.save(TgUser);
    }

    @Override
    public List<TelegramUser> retrieveAllUsers() {
        return telegramUserRepository.findAllByChatIdNotNull();
    }
}
