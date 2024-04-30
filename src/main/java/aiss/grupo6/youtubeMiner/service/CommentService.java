package aiss.grupo6.youtubeMiner.service;

import aiss.grupo6.youtubeMiner.database.VMComment;
import aiss.grupo6.youtubeMiner.model.Comment;
import aiss.grupo6.youtubeMiner.model.CommentItem;
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
public class CommentService {

    @Value( "${api.token}" )
    private String token;

    @Value( "${api.path}" )
    private String path;

    @Autowired
    RestTemplate restTemplate;

    public List<VMComment> indexCommentsByVideoId(String idVideo, Integer maxComments) throws RestClientException {
        List<VMComment> result = new ArrayList<>();
        maxComments = maxComments == null? 10: maxComments;

        String uri = path + "/commentThreads?key=" + token + "&videoId=" + idVideo + "&part=snippet&maxResults=" + maxComments;


        ResponseEntity<CommentItem> response = restTemplate.exchange(uri, HttpMethod.GET, null, CommentItem.class);

        for(Comment c: response.getBody().getItems()) {
            result.add(VMComment.of(c));
        }

        return result;
    }
}
