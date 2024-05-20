package com.statistics.component;
import com.statistics.dto.CompleteEnergySystemData;
import com.statistics.services.EnergySensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class MessageSendingComponent {

    @Autowired
    private EnergySensorDataService energySensorDataService;


    @Scheduled(fixedDelay = 5000)
    public void sendMessageOnlyIfSubscribersPresent() {

        if (!WebSocketUsers.getUsers().isEmpty()) {

            WebSocketUsers.sendMessageToUsersByText(energySensorDataService.realTimeData().toString());
        }


    }


}
