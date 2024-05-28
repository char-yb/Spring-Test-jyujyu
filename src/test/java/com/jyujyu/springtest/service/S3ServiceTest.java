package com.jyujyu.springtest.service;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import com.jyujyu.springtest.IntegrationTest;

public class S3ServiceTest extends IntegrationTest {

	@Autowired
	private S3Service s3Service;

	@Test
	public void s3PutAndGetTest() throws Exception {
		// given
		String bucket = "test-bucket";
		String key = "sampleObject.txt";
		File sampleFile = new ClassPathResource("static/sample.txt").getFile();

		// when
		s3Service.putFile(bucket, key, sampleFile);

		// then
		File resultFile = s3Service.getFile(bucket, key);
		// 파일을 가져왔을 때 내용이 같은 지 확인
		List<String> sampleFileLines = FileUtils.readLines(sampleFile);
		List<String> resultFileLines = FileUtils.readLines(resultFile);

		System.out.println("======================================");
		System.out.println(Arrays.toString(sampleFileLines.toArray()));
		System.out.println("======================================");
		System.out.println(Arrays.toString(resultFileLines.toArray()));
		System.out.println("======================================");
		Assertions.assertIterableEquals(sampleFileLines, resultFileLines);
	}
}