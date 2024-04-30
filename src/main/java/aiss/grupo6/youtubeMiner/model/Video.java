
package aiss.grupo6.youtubeMiner.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Video {

    @JsonProperty("id")
    private VideoId videoId;
    @JsonProperty("snippet")
    private SnippetMain snippetMain;

    @JsonProperty("id")
    public VideoId getVideoId() {
        return videoId;
    }

    @JsonProperty("id")
    public void setVideoId(VideoId videoId) {
        this.videoId = videoId;
    }

    @JsonProperty("snippet")
    public SnippetMain getSnippetMain() {
        return snippetMain;
    }

    @JsonProperty("snippet")
    public void setSnippetMain(SnippetMain snippetMain) {
        this.snippetMain = snippetMain;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Video.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("videoId");
        sb.append('=');
        sb.append(((this.videoId == null)?"<null>":this.videoId));
        sb.append(',');
        sb.append("snippetMain");
        sb.append('=');
        sb.append(((this.snippetMain == null)?"<null>":this.snippetMain));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
