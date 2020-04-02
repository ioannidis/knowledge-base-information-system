package eu.ioannidis.vks.commentservice.models.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class CommentRequest {

    @NotNull
    @NotEmpty
    private String content;

//    @NotNull
//    @NotEmpty
    private UUID vulnerabilityId;

    public CommentRequest() {
    }

    public CommentRequest(String content, UUID vulnerabilityId) {
        this.content = content;
        this.vulnerabilityId = vulnerabilityId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UUID getVulnerabilityId() {
        return vulnerabilityId;
    }

    public void setVulnerabilityId(UUID vulnerabilityId) {
        this.vulnerabilityId = vulnerabilityId;
    }

    @Override
    public String toString() {
        return "CommentRequest{" +
                "content='" + content + '\'' +
                ", vulnerabilityId=" + vulnerabilityId +
                '}';
    }
}
