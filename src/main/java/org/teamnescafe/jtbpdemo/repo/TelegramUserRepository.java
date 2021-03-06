package org.teamnescafe.jtbpdemo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.teamnescafe.jtbpdemo.entity.TelegramUser;

import java.util.List;

@Repository
public interface TelegramUserRepository extends JpaRepository<TelegramUser, String> {
    List<TelegramUser> findAllByActiveTrue();
    List<TelegramUser> findAllByChatIdNotNull();

    @Override
    void delete(TelegramUser telegramUser);
}
