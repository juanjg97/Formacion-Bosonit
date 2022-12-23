package com.bosonit.block11uploaddownloadfiles;

import com.bosonit.block11uploaddownloadfiles.application.services.StorageProperties;
import com.bosonit.block11uploaddownloadfiles.application.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class Block11UploadDownloadFilesApplication implements CommandLineRunner{
@Autowired
StorageService storageService;
	public static void main(String[] args) {

		SpringApplication.run(Block11UploadDownloadFilesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		storageService.init();
	}
}
