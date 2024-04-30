package aiss.grupo6.youtubeMiner.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VideoItem {

    @JsonProperty("items")
    private List<Video> items;

    @JsonProperty("items")
    public List<Video> getItems() {
        return items;
    }

    @JsonProperty("items")
    public void setItems(List<Video> items) {
        this.items = items;
    }

}
