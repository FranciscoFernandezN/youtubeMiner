package aiss.grupo6.youtubeMiner.controller;

import aiss.grupo6.youtubeMiner.database.VMCaption;
import aiss.grupo6.youtubeMiner.database.VMChannel;
import aiss.grupo6.youtubeMiner.database.VMComment;
import aiss.grupo6.youtubeMiner.database.VMVideo;
import aiss.grupo6.youtubeMiner.repository.ChannelRepository;
import aiss.grupo6.youtubeMiner.service.CaptionService;
import aiss.grupo6.youtubeMiner.service.ChannelService;
import aiss.grupo6.youtubeMiner.service.CommentService;
import aiss.grupo6.youtubeMiner.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;

import java.util.List;

@RestController
@RequestMapping("/youtubeMiner")
public class ChannelController {

    @Autowired
    private ChannelRepository channelRepository;


    @Autowired
    private ChannelService channelService;

    @Autowired
    private VideoService videoService;

    @Autowired
    private CaptionService captionService;

    @Autowired
    private CommentService commentService;

    //TODO: meter los querys

    //GET http://localhost:8082/youtubeMiner/{id}
    @GetMapping("/{id}")
    public VMChannel findChannel(@PathVariable String id, @RequestParam(required = false) Integer maxVideos, @RequestParam(required = false) Integer maxComments) throws RestClientException{
        try {
            VMChannel result = this.channelService.findChannelById(id);
            List<VMVideo> videosCanal = this.videoService.indexVideosById(id, maxVideos);
            for (VMVideo v : videosCanal) {
                List<VMCaption> subtitulosVideo = this.captionService.indexCaptionsByVideoId(v.getId());
                List<VMComment> comentariosVideo = this.commentService.indexCommentsByVideoId(v.getId(), maxComments);

                v.setComments(comentariosVideo);
                v.setCaptions(subtitulosVideo);
            }

            result.setVideos(videosCanal);
            return result;
        }catch(RestClientException e) {
            System.out.println("Fallo cazado: " + e);
            throw e;
        }
    }

    //POST http://localhost:8082/youtubeMiner/{id}
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}")
    public VMChannel createChannel(@PathVariable String id, @RequestParam(required = false) Integer maxVideos, @RequestParam(required = false) Integer maxComments) throws RestClientException{
        VMChannel canal = findChannel(id, maxVideos, maxComments);
        canal = channelRepository.save(canal);
        return canal;
    }

}
