package aiss.grupo6.youtubeMiner.service;

import aiss.grupo6.youtubeMiner.database.VMCaption;
import aiss.grupo6.youtubeMiner.model.Caption;
import aiss.grupo6.youtubeMiner.model.CaptionItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CaptionService {

    @Value( "${api.token}" )
    private String token;

    @Value( "${api.path}" )
    private String path;

    @Autowired
    RestTemplate restTemplate;

    public List<VMCaption> indexCaptionsByVideoId(String idVideo) throws RestClientException {
        List<VMCaption> result = new ArrayList<>();

        String uri = path + "/captions?key=" + token + "&videoId=" + idVideo + "&part=snippet";

        ResponseEntity<CaptionItem> response = restTemplate.exchange(uri, HttpMethod.GET, null, CaptionItem.class);
        for(Caption c: response.getBody().getItems()){
            result.add(VMCaption.of(c));
        }

        return result;
    }
}
