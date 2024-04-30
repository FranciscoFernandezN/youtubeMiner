
package aiss.grupo6.youtubeMiner.model;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Caption {

    @JsonProperty("id")
    private String id;
    @JsonProperty("snippet")
    private SnippetCaption snippetCaption;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("snippet")
    public SnippetCaption getSnippetCaption() {
        return snippetCaption;
    }

    @JsonProperty("snippet")
    public void setSnippetCaption(SnippetCaption snippetCaption) {
        this.snippetCaption = snippetCaption;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Caption.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("snippetCaption");
        sb.append('=');
        sb.append(((this.snippetCaption == null)?"<null>":this.snippetCaption));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
