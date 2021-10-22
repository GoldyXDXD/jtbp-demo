package org.teamnescafe.jtbpdemo.service;

import org.springframework.stereotype.Service;
import org.teamnescafe.jtbpdemo.dto.GroupStatDTO;
import org.teamnescafe.jtbpdemo.dto.StatisticDTO;
import org.teamnescafe.jtbpdemo.entity.TelegramUser;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    private final GroupSubService groupSubService;
    private final TelegramUserService telegramUserService;

    public StatisticsServiceImpl(GroupSubService groupSubService, TelegramUserService telegramUserService) {
        this.groupSubService = groupSubService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public StatisticDTO countBotStatistic() {
        List<GroupStatDTO> groupStatDTOS = groupSubService.findAll().stream()
                .filter(it -> !isEmpty(it.getUsers()))
                .map(groupSub -> new GroupStatDTO(groupSub.getId(), groupSub.getTitle(), groupSub.getUsers().size()))
                .collect(Collectors.toList());
        List<TelegramUser> allInActiveUsers = telegramUserService.retrieveAllInActiveUsers();
        List<TelegramUser> allActiveUsers = telegramUserService.retrieveAllActiveUsers();

        double groupsPerUser = getGroupsPerUser(allActiveUsers);
        return new StatisticDTO(allActiveUsers.size(), allInActiveUsers.size(), groupStatDTOS, groupsPerUser);
    }

    private double getGroupsPerUser(List<TelegramUser> allActiveUsers) {
        return (double) allActiveUsers.stream().mapToInt(it -> it.getGroupSubs().size()).sum() / allActiveUsers.size();
    }
}