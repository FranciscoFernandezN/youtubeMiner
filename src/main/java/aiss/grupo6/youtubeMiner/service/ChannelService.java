package aiss.grupo6.youtubeMiner.service;

import aiss.grupo6.youtubeMiner.database.VMChannel;
import aiss.grupo6.youtubeMiner.model.ChannelItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class ChannelService {

    @Value( "${api.token}" )
    private String token;

    @Value( "${api.path}" )
    private String path;

    @Autowired
    RestTemplate restTemplate;

    public VMChannel findChannelById(String idChannel) throws RestClientException {
        VMChannel result = null;

        String uri = path + "/channels?key=" + token + "&id=" + idChannel + "&part=snippet";

        ResponseEntity<ChannelItem> response = restTemplate.exchange(uri, HttpMethod.GET, null, ChannelItem.class);
        result = VMChannel.of(response.getBody().getItems().get(0));

        return result;
    }

}
