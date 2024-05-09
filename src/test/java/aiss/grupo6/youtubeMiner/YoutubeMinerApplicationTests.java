package aiss.grupo6.youtubeMiner;

import aiss.grupo6.youtubeMiner.service.CaptionServiceTest;
import aiss.grupo6.youtubeMiner.service.ChannelServiceTest;
import aiss.grupo6.youtubeMiner.service.CommentServiceTest;
import aiss.grupo6.youtubeMiner.service.VideoServiceTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		ChannelServiceTest.class,
		VideoServiceTest.class,
		CommentServiceTest.class,
		CaptionServiceTest.class
})
public class YoutubeMinerApplicationTests {

	@Test
	public void contextLoads() {
	}

}
