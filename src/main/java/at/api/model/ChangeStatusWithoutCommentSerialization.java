package at.api.model;

public class ChangeStatusWithoutCommentSerialization {
    private Integer id;
    private String deviceId;
    private String terminalType;
    private String comment;

    public Integer getId() {
        return id;
    }

    public ChangeStatusWithoutCommentSerialization setId(String s, Integer id) {
        this.id = id;
        return this;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public ChangeStatusWithoutCommentSerialization setDeviceId(String id, String deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    public String getTerminalType() {
        return terminalType;
    }

    public ChangeStatusWithoutCommentSerialization setTerminalType(String type, String terminalType) {
        this.terminalType = terminalType;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public ChangeStatusWithoutCommentSerialization setComment(String s, String comment) {
        this.comment = comment;
        return this;
    }
}
