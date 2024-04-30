
package aiss.grupo6.youtubeMiner.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SnippetTopComment {

    @JsonProperty("topLevelComment")
    private TopLevelComment topLevelComment;

    @JsonProperty("topLevelComment")
    public TopLevelComment getTopLevelComment() {
        return topLevelComment;
    }

    @JsonProperty("topLevelComment")
    public void setTopLevelComment(TopLevelComment topLevelComment) {
        this.topLevelComment = topLevelComment;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(SnippetTopComment.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("topLevelComment");
        sb.append('=');
        sb.append(((this.topLevelComment == null)?"<null>":this.topLevelComment));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
