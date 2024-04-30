package aiss.grupo6.youtubeMiner.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentItem {
    @JsonProperty("items")
    private List<Comment> items;

    @JsonProperty("items")
    public List<Comment> getItems() {
        return items;
    }

    @JsonProperty("items")
    public void setItems(List<Comment> items) {
        this.items = items;
    }
}
