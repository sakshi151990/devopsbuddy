package com.devopsbuddy.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;

@Configuration
@EnableJpaRepositories(basePackages = "com.devopsbuddy.persistence")
@EntityScan(basePackages = "com.devopsbuddy.persistence")
@EnableTransactionManagement
public class ApplicationConfig {

	@Value("${aws.s3.profile}")
	private String awsProfileName;

	@Bean
	public AmazonS3Client S3Client() {
		AWSCredentials credentials = new ProfileCredentialsProvider(awsProfileName).getCredentials();
		AmazonS3Client s3client = new AmazonS3Client(credentials);
		Region region = Region.getRegion(Regions.AP_SOUTHEAST_2);
		s3client.setRegion(region);

		return s3client;
	}

}
