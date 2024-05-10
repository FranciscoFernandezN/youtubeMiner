package aiss.grupo6.youtubeMiner.controller;

import aiss.grupo6.youtubeMiner.database.VMCaption;
import aiss.grupo6.youtubeMiner.database.VMChannel;
import aiss.grupo6.youtubeMiner.database.VMComment;
import aiss.grupo6.youtubeMiner.database.VMVideo;
import aiss.grupo6.youtubeMiner.exception.ChannelNotFoundException;
import aiss.grupo6.youtubeMiner.exception.InternalErrorException;
import aiss.grupo6.youtubeMiner.service.CaptionService;
import aiss.grupo6.youtubeMiner.service.ChannelService;
import aiss.grupo6.youtubeMiner.service.CommentService;
import aiss.grupo6.youtubeMiner.service.VideoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Tag(
        name = "Channel",
        description = "Integration for an standardized model for channels in Youtube using its resource API"
)
@RestController
@RequestMapping("/youtubeminer")
public class ChannelController {

    @Autowired
    RestTemplate restTemplate;

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

    @Value( "${videominer.uri}" )
    private String videominerUri;

    //GET http://localhost:8082/youtubeminer/{id}
    @Operation(
            summary = "Retrieve a Channel by Id",
            description = "Get a Channel object by its id",
            tags = {"channels", "get"}
    )

    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = VMChannel.class), mediaType = "application/json") }, description = "Everything was fine"),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema()), description = "API could not find data for that id, check format of id"),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema()), description = "API may not be accessible at the moment, please try again later or check connections")
    })
    @GetMapping("/{id}")
    public VMChannel findChannel(@Parameter(required = true, description = "Id of the channel to search") @PathVariable String id,
                                 @Parameter(description = "Maximum number of videos to get from the channel") @RequestParam(required = false) Integer maxVideos,
                                 @Parameter(description = "Maximum number of comments to get from the channel") @RequestParam(required = false) Integer maxComments) throws Exception{
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
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND) || e.getStatusCode().equals(HttpStatus.BAD_REQUEST)){
                throw new ChannelNotFoundException(channelError);
            } else{
                throw new InternalErrorException(internalError);
            }

        } catch (NullPointerException e) {
            throw new ChannelNotFoundException(channelError);
        } catch (RuntimeException e) {
            throw new InternalErrorException(internalError);
        }
    }

    //POST http://localhost:8082/youtubeminer/{id}
    @Operation(
            summary = "Upload a Channel by Id",
            description = "Upload a Channel object by its id into the H2 local database",
            tags = {"channels", "post"}
    )

    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = VMChannel.class), mediaType = "application/json") }, description = "Everything was fine"),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema()), description = "API could not find data for that id, check format of id"),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema()), description = "API or database may not be accessible at the moment, please try again later or check connections")
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}")
    public VMChannel createChannel(@Parameter(required = true, description = "Id of the channel to search") @PathVariable String id,
                                   @Parameter(description = "Maximum number of videos to get from the channel") @RequestParam(required = false) Integer maxVideos,
                                   @Parameter(description = "Maximum number of comments to get from the channel") @RequestParam(required = false) Integer maxComments) throws Exception{
        VMChannel canal = findChannel(id, maxVideos, maxComments);
        try {
            HttpEntity<VMChannel> request = new HttpEntity<>(canal);
            ResponseEntity<VMChannel> response = restTemplate.exchange(videominerUri, HttpMethod.POST, request, VMChannel.class);
            canal = response.getBody();
        } catch(HttpClientErrorException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)){
                throw new ChannelNotFoundException(channelError);
            }else{
                throw new InternalErrorException(internalError);
            }

        } catch (RuntimeException e) {
            throw new InternalErrorException(internalError);
        }
        return canal;
    }

}
