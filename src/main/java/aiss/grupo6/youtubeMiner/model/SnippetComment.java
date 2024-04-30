
package aiss.grupo6.youtubeMiner.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SnippetComment {

    @JsonProperty("textOriginal")
    private String textOriginal;
    @JsonProperty("publishedAt")
    private String publishedAt;
    @JsonProperty("authorDisplayName")
    private String authorDisplayName;
    @JsonProperty("authorChannelUrl")
    private String authorChannelUrl;
    @JsonProperty("authorProfileImageUrl")
    private String authorProfileImageUrl;

    @JsonProperty("textOriginal")
    public String getTextOriginal() {
        return textOriginal;
    }

    @JsonProperty("textOriginal")
    public void setTextOriginal(String textOriginal) {
        this.textOriginal = textOriginal;
    }

    @JsonProperty("publishedAt")
    public String getPublishedAt() {
        return publishedAt;
    }

    @JsonProperty("publishedAt")
    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    @JsonProperty("authorDisplayName")
    public String getAuthorDisplayName() {
        return authorDisplayName;
    }

    @JsonProperty("authorDisplayName")
    public void setAuthorDisplayName(String authorDisplayName) {
        this.authorDisplayName = authorDisplayName;
    }

    @JsonProperty("authorChannelUrl")
    public String getAuthorChannelUrl() {
        return authorChannelUrl;
    }

    @JsonProperty("authorChannelUrl")
    public void setAuthorChannelUrl(String authorChannelUrl) {
        this.authorChannelUrl = authorChannelUrl;
    }

    @JsonProperty("authorProfileImageUrl")
    public String getAuthorProfileImageUrl() {
        return authorProfileImageUrl;
    }

    @JsonProperty("authorProfileImageUrl")
    public void setAuthorProfileImageUrl(String authorProfileImageUrl) {
        this.authorProfileImageUrl = authorProfileImageUrl;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(SnippetComment.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("textOriginal");
        sb.append('=');
        sb.append(((this.textOriginal == null)?"<null>":this.textOriginal));
        sb.append(',');
        sb.append("publishedAt");
        sb.append('=');
        sb.append(((this.publishedAt == null)?"<null>":this.publishedAt));
        sb.append(',');
        sb.append("authorDisplayName");
        sb.append('=');
        sb.append(((this.authorDisplayName == null)?"<null>":this.authorDisplayName));
        sb.append(',');
        sb.append("authorChannelUrl");
        sb.append('=');
        sb.append(((this.authorChannelUrl == null)?"<null>":this.authorChannelUrl));
        sb.append(',');
        sb.append("authorProfileImageUrl");
        sb.append('=');
        sb.append(((this.authorProfileImageUrl == null)?"<null>":this.authorProfileImageUrl));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
