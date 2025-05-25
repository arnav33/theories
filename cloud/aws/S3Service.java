import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class S3Service {
    
}
public class S3Service {

    private final S3Client s3Client;

    public S3Service(Region region) {
        this.s3Client = S3Client.builder()
                .region(region)
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();
    }

    // Bucket operations
    public void createBucket(String bucketName) {
        s3Client.createBucket(CreateBucketRequest.builder().bucket(bucketName).build());
    }

    public void deleteBucket(String bucketName) {
        s3Client.deleteBucket(DeleteBucketRequest.builder().bucket(bucketName).build());
    }

    public List<String> listBuckets() {
        return s3Client.listBuckets().buckets().stream()
                .map(Bucket::name)
                .collect(Collectors.toList());
    }

    public boolean doesBucketExist(String bucketName) {
        return s3Client.listBuckets().buckets().stream()
                .anyMatch(b -> b.name().equals(bucketName));
    }

    // Object operations
    public void putObject(String bucketName, String key, Path filePath) {
        s3Client.putObject(PutObjectRequest.builder().bucket(bucketName).key(key).build(),
                filePath);
    }

    public void putObject(String bucketName, String key, InputStream inputStream, long contentLength) {
        s3Client.putObject(PutObjectRequest.builder().bucket(bucketName).key(key).build(),
                RequestBody.fromInputStream(inputStream, contentLength));
    }

    public ResponseInputStream<GetObjectResponse> getObject(String bucketName, String key) {
        return s3Client.getObject(GetObjectRequest.builder().bucket(bucketName).key(key).build());
    }

    public void deleteObject(String bucketName, String key) {
        s3Client.deleteObject(DeleteObjectRequest.builder().bucket(bucketName).key(key).build());
    }

    public List<String> listObjects(String bucketName, String prefix) {
        ListObjectsV2Request request = ListObjectsV2Request.builder()
                .bucket(bucketName)
                .prefix(prefix)
                .build();
        return s3Client.listObjectsV2(request).contents().stream()
                .map(S3Object::key)
                .collect(Collectors.toList());
    }

    public void copyObject(String sourceBucket, String sourceKey, String destBucket, String destKey) {
        CopyObjectRequest request = CopyObjectRequest.builder()
                .copySource(sourceBucket + "/" + sourceKey)
                .destinationBucket(destBucket)
                .destinationKey(destKey)
                .build();
        s3Client.copyObject(request);
    }

    public void moveObject(String sourceBucket, String sourceKey, String destBucket, String destKey) {
        copyObject(sourceBucket, sourceKey, destBucket, destKey);
        deleteObject(sourceBucket, sourceKey);
    }

    public void setObjectAcl(String bucketName, String key, ObjectCannedACL acl) {
        s3Client.putObjectAcl(PutObjectAclRequest.builder()
                .bucket(bucketName)
                .key(key)
                .acl(acl)
                .build());
    }

    public ObjectCannedACL getObjectAcl(String bucketName, String key) {
        GetObjectAclResponse response = s3Client.getObjectAcl(GetObjectAclRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build());
        return response.grants().stream()
                .findFirst()
                .map(Grant::permission)
                .map(ObjectCannedACL::fromValue)
                .orElse(ObjectCannedACL.PRIVATE);
    }

    public void close() {
        s3Client.close();
    }
}