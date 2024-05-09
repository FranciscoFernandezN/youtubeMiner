package aiss.grupo6.youtubeMiner.service;

import aiss.grupo6.youtubeMiner.database.VMVideo;
import aiss.grupo6.youtubeMiner.model.Video;
import aiss.grupo6.youtubeMiner.model.VideoItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class VideoService {

    @Value( "${api.token}" )
    private String token;

    @Value( "${api.path}" )
    private String path;

    @Autowired
    RestTemplate restTemplate;

    public List<VMVideo> indexVideosById(String idChannel, Integer maxVideos) throws RestClientException {
        List<VMVideo> result = new ArrayList<>();
        maxVideos = maxVideos == null? 10: maxVideos;

        String uri = path + "/search?key=" + token + "&channelId=" + idChannel + "&part=snippet&type=video&maxResults=" + maxVideos;

        ResponseEntity<VideoItem> response = restTemplate.exchange(uri, HttpMethod.GET, null, VideoItem.class);

        for(Video v: response.getBody().getItems()) {
            result.add(VMVideo.of(v));
        }



        return result;
    }
}
