package com.hanssem.remodeling.common.common.util;

import com.hanssem.remodeling.common.api.controller.common.response.UploadResponse;
import com.hanssem.remodeling.common.common.config.WebConfig;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import java.io.IOException;
import java.io.InputStream;
import java.util.StringJoiner;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Component
@RequiredArgsConstructor
public class S3Uploader {

    private final AmazonS3Client amazonS3Client;

    private final WebConfig webConfig;

    public String makeCdnUrl(String path) {
        if (path == null || path.isEmpty()) return null;
        return String.format("%s/%s", webConfig.getCdnUrl(), path);
    }

    public UploadResponse putS3Target(MultipartFile file, final String path) {
        val objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(MediaType.IMAGE_PNG_VALUE.equals(file.getContentType()) ? MediaType.IMAGE_PNG_VALUE : MediaType.IMAGE_JPEG_VALUE);
        log.info("==> bucket:{}, cdnUrl:{}", webConfig.getBucket(), webConfig.getCdnUrl());

        val fileName = new StringJoiner("/").add(path).add(Utility.getJoinString(UUID.randomUUID().toString(), ".", FilenameUtils.getExtension(file.getOriginalFilename()))).toString();
        try {
            amazonS3Client.putObject(new PutObjectRequest(webConfig.getBucket(), fileName, file.getInputStream(), objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return UploadResponse.builder()
            .fileSize(file.getSize())
            .fileName(file.getOriginalFilename())
            .fileExtension(FilenameUtils.getExtension(file.getOriginalFilename()).toLowerCase())
            .fileMimeType(file.getContentType())
            .filePath(fileName)
            .fileUrl(makeCdnUrl(fileName))
            .build();
    }

    private String putS3(MultipartFile multipartFile) {
        val objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(multipartFile.getSize());
        objectMetadata.setContentType(MediaType.IMAGE_PNG_VALUE.equals(multipartFile.getContentType()) ? MediaType.IMAGE_PNG_VALUE : MediaType.IMAGE_JPEG_VALUE);

        val fileName = new StringJoiner("/").add("temp").add(Utility.getJoinString(UUID.randomUUID().toString(), ".", FilenameUtils.getExtension(multipartFile.getOriginalFilename()))).toString();
        try {
            amazonS3Client.putObject(new PutObjectRequest(webConfig.getBucket(), fileName, multipartFile.getInputStream(), objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }

    /**
     * 파일 저장
     */
    private UploadResponse putS3(final String fullPath, final InputStream stream) throws IOException {
        final ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(MediaType.IMAGE_JPEG_VALUE);
        PutObjectResult result = amazonS3Client.putObject(
            new PutObjectRequest(webConfig.getBucket(), fullPath, stream, objectMetadata)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        stream.close();

        return UploadResponse.builder()
            .fileSize(result.getMetadata().getContentLength())
            .fileName(FilenameUtils.getName(fullPath))
            .fileExtension(fullPath.substring(fullPath.lastIndexOf(".") + 1))
            .fileMimeType(objectMetadata.getContentType())
            .filePath(fullPath)
            .fileUrl(makeCdnUrl(fullPath))
            .build();
    }

    /**
     * 파일 삭제
     */
    public void removeS3(final String filePath) {
        if (filePath == null || filePath.isEmpty()) {
            return;
        }
        amazonS3Client.deleteObject(webConfig.getBucket(), filePath);
    }

}
