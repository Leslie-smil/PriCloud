package eud.scujcc.pircloud;

public class Configure {
    public String token;
    public String internalendpoint;
    public String bucketName;
    public String accessKeyId;
    public String accessKeySecret;
    public User user;

    @Override
    public String toString() {
        return "Configure{" +
                "token='" + token + '\'' +
                ", internalendpoint='" + internalendpoint + '\'' +
                ", bucketName='" + bucketName + '\'' +
                ", accessKeyId='" + accessKeyId + '\'' +
                ", accessKeySecret='" + accessKeySecret + '\'' +
                ", user=" + user +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getInternalendpoint() {
        return internalendpoint;
    }

    public void setInternalendpoint(String internalendpoint) {
        this.internalendpoint = internalendpoint;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }
}
