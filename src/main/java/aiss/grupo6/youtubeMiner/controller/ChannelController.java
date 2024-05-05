package aiss.grupo6.youtubeMiner.controller;

import aiss.grupo6.youtubeMiner.database.VMCaption;
import aiss.grupo6.youtubeMiner.database.VMChannel;
import aiss.grupo6.youtubeMiner.database.VMComment;
import aiss.grupo6.youtubeMiner.database.VMVideo;
import aiss.grupo6.youtubeMiner.exception.ChannelNotFoundException;
import aiss.grupo6.youtubeMiner.exception.InternalErrorException;
import aiss.grupo6.youtubeMiner.repository.ChannelRepository;
import aiss.grupo6.youtubeMiner.service.CaptionService;
import aiss.grupo6.youtubeMiner.service.ChannelService;
import aiss.grupo6.youtubeMiner.service.CommentService;
import aiss.grupo6.youtubeMiner.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

import java.util.List;

@RestController
@RequestMapping("/youtubeminer")
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

    @Value( "${message.channelNotFound}" )
    private String channelError;

    @Value( "${message.internalError}" )
    private String internalError;

    //GET http://localhost:8082/youtubeminer/{id}
    @GetMapping("/{id}")
    public VMChannel findChannel(@PathVariable String id, @RequestParam(required = false) Integer maxVideos, @RequestParam(required = false) Integer maxComments) throws Exception{
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
        }   catch(HttpClientErrorException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)){
                throw new ChannelNotFoundException(channelError);
            }else{
                throw new InternalErrorException(internalError);
            }

        } catch (NullPointerException e) {
            throw new ChannelNotFoundException(channelError);
        } catch (RuntimeException e) {
            throw new InternalErrorException(internalError);
        }
    }

    //POST http://localhost:8082/youtubeminer/{id}
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}")
    public VMChannel createChannel(@PathVariable String id, @RequestParam(required = false) Integer maxVideos, @RequestParam(required = false) Integer maxComments) throws Exception{
        VMChannel canal = findChannel(id, maxVideos, maxComments);
        canal = channelRepository.save(canal);
        return canal;
    }

}
