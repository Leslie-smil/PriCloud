package eud.scujcc.pircloud;

import java.io.Serializable;

public class File implements Serializable {
    private String bucketName;
    private String id;
    private String key;
    private String eTag;
    private String size;
    private String lastModified;
    private String type;
    private String filepath;
    public static String INTERNALENDPOINT = "INTERNALENDPOINT";
    public static String ACCESSKEYID = "ACCESSKEYID";
    public static String ACCESSKEYIDSECRET = "ACCESSKEYIDSECRET";
    public static String BUKCKETNAME = "BUKCKETNAME";
    public static String TPYEISFILE = "File";
    public static String TPYEISFOLDER = "Folder";

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }


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
