package aiss.grupo6.youtubeMiner.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CaptionItem {

    @JsonProperty("items")
    private List<Caption> items;

    @JsonProperty("items")
    public List<Caption> getItems() {
        return items;
    }

    @JsonProperty("items")
    public void setItems(List<Caption> items) {
        this.items = items;
    }
}
