package com.jyujyu.springtest.service;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

@Service
@RequiredArgsConstructor
public class S3Service {

	private final S3Client s3Client;

	public void putFile(String bucket, String key, File file) {
		s3Client.putObject((req) -> {
			req.bucket(bucket);
			req.key(key);
		}, RequestBody.fromFile(file));
	}

	public File getFile(String bucket, String key) {
		File file = new File("build/output/getFile.txt");

		ResponseInputStream<GetObjectResponse> object = s3Client.getObject((req) -> {
			req.bucket(bucket);
			req.key(key);
		});

		try {
			FileUtils.writeByteArrayToFile(file, object.readAllBytes());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return file;
	}
}
