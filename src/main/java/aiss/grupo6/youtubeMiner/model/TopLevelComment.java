package aiss.grupo6.youtubeMiner.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TopLevelComment {

    @JsonProperty("snippet")
    private SnippetComment snippetComment;

    @JsonProperty("snippet")
    public SnippetComment getSnippetComment() {
        return snippetComment;
    }

    @JsonProperty("snippet")
    public void setSnippetComment(SnippetComment snippetComment) {
        this.snippetComment = snippetComment;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(TopLevelComment.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("snippetComment");
        sb.append('=');
        sb.append(((this.snippetComment == null)?"<null>":this.snippetComment));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
