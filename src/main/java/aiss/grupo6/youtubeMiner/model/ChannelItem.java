package aiss.grupo6.youtubeMiner.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChannelItem {

    @JsonProperty("items")
    private List<Channel> items;

    @JsonProperty("items")
    public List<Channel> getItems() {
        return items;
    }

    @JsonProperty("items")
    public void setItems(List<Channel> items) {
        this.items = items;
    }
}
