package eud.scujcc.pircloud;

public class File {
    private String bucketName;
    private String id;
    private String key;
    private String eTag;
    private String size;
    private String lastModified;
    private String type;
    public static String INTERNALENDPOINT = "hkoss.fuyu.site";
    public static String ENDPOINT = "oss-cn-hongkong.aliyuncs.com";
    public static String accessKeyId = "LTAI4G14NsAKu8BzBSGCGmHA";
    public static String accessKeySecret = "OG3EXeyA6w7cAlQxTk7A5riJV1xGS9";
    public static String BucketName = "fuyu-hk-test";
    public static String TPYEISFILE = "File";
    public static String TPYEISFOLDER = "Folder";

    @Override
    public String toString() {
        return "File{" +
                "bucketName='" + bucketName + '\'' +
                ", id='" + id + '\'' +
                ", key='" + key + '\'' +
                ", eTag='" + eTag + '\'' +
                ", size='" + size + '\'' +
                ", lastModified='" + lastModified + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String geteTag() {
        return eTag;
    }

    public void seteTag(String eTag) {
        this.eTag = eTag;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
