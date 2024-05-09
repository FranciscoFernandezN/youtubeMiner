package aiss.grupo6.youtubeMiner.service;

import aiss.grupo6.youtubeMiner.database.VMComment;
import jakarta.transaction.Transactional;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class CommentServiceTest {

    @Autowired
    CommentService service;

    @Test
    @DisplayName("Find comments by video id with default maxComments parameter and users id is null")
    public void indexCommentsByVideoIdAndNull() {
        List<VMComment> comments = service.indexCommentsByVideoId("UC29GGV1kYu1MlpS6VRhIRZw",null);
        assertTrue(comments.size() <= 10, "Default size of the comments list must be 10 or lower");
        assertNotNull(comments, "Comments list must not be null");
        if(!comments.isEmpty()) {
            for (VMComment c : comments) {
                assertInstanceOf(String.class, c.getId(), "Id must be a string");
                assertInstanceOf(String.class, c.getText(), "Text must be a string");
                assertInstanceOf(String.class, c.getCreatedOn(), "The created time must be a string");
                assertNull(c.getAuthor().getId(), "The user id of the comment's author must be null initially");
                assertInstanceOf(String.class, c.getAuthor().getName(), "The name of the comment's author must be a string");
                assertInstanceOf(String.class, c.getAuthor().getUser_link(), "The user link of the comment's author must be a string");
                assertInstanceOf(String.class, c.getAuthor().getPicture_link(), "The picture link of the comment's author must be a string");
            }
        }
    }

    @Test
    @DisplayName("Find comments by video id and users id is null")
    public void indexCommentsByVideoId() {
        int maxC = 5;
        List<VMComment> comments = service.indexCommentsByVideoId("UC29GGV1kYu1MlpS6VRhIRZw", maxC);
        assertTrue(comments.size() <= maxC, "The size of the comments list must be lower or equal to " + maxC);
        assertNotNull(comments, "Comments list must not be null");
        if(!comments.isEmpty()) {
            for (VMComment c : comments) {
                assertInstanceOf(String.class, c.getId(), "Id must be a string");
                assertInstanceOf(String.class, c.getText(), "Text must be a string");
                assertInstanceOf(String.class, c.getCreatedOn(), "The created time must be a string");
                assertNull(c.getAuthor().getId(), "The user id of the comment's author must be null initially");
                assertInstanceOf(String.class, c.getAuthor().getName(), "The name of the comment's author must be a string");
                assertInstanceOf(String.class, c.getAuthor().getUser_link(), "The user link of the comment's author must be a string");
                assertInstanceOf(String.class, c.getAuthor().getPicture_link(), "The picture link of the comment's author must be a string");
            }
        }
    }

    @Test
    @DisplayName("Find video with disabled comments")
    public void disabledCommentsByVideoId() {
        List<VMComment> comments = service.indexCommentsByVideoId("pOUH78w3rGE", null);
        assertNotNull(comments, "Comments list must not be null");
        assertTrue(comments.isEmpty(), "Comments disabled must return an empty list");
    }


    @Test
    @DisplayName("Video not found exception")
    public void invalidVideoId() {
        try {
            List<VMComment> videoInvalido = service.indexCommentsByVideoId("123",null);
        } catch(HttpClientErrorException e){
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}