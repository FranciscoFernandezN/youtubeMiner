package aiss.grupo6.youtubeMiner.service;

import aiss.grupo6.youtubeMiner.database.VMChannel;
import jakarta.transaction.Transactional;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class ChannelServiceTest {

    @Autowired
    ChannelService service;

    @Test
    @DisplayName("Find channel by id without videos")
    public void findChannelById() {
        String id = "UC29GGV1kYu1MlpS6VRhIRZw";
        VMChannel channel = service.findChannelById(id);
        assertNotNull(channel, "Channel must not be null");
        assertTrue(channel.getVideos().isEmpty(), "Video list must be empty");
        assertInstanceOf(String.class, channel.getId(), "Id must be string");
        assertInstanceOf(String.class, channel.getName(), "Name must be string");
        assertInstanceOf(String.class, channel.getDescription(), "Description must be string");
        assertInstanceOf(String.class, channel.getCreatedTime(), "Created time must be string");
        assertEquals(channel.getId(), id, "The id of the channel must be " + id);
    }



    @Test
    @DisplayName("Not found exception")
    public void invalidChannelId() {
        try {
            VMChannel canalInvalido = service.findChannelById("12");
        } catch(NullPointerException e){
            assertInstanceOf(NullPointerException.class, e);
        }
    }
}