package co.simplon.titrepro.isophoto.service;

import java.io.File;
import java.io.IOException;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

public class ExifInfoImpl {

	public void exifInfo(String text) throws ImageProcessingException, IOException {

		
		File nefFile = new File(text);

		Metadata metadata = ImageMetadataReader.readMetadata(nefFile);

		for (Directory directory : metadata.getDirectories()) {
			for (Tag tag : directory.getTags()) {
				System.out.format("TEST " + "[%s] - %s = %s", directory.getName(), tag.getTagName(),
						tag.getDescription());
			}
			if (directory.hasErrors()) {
				for (String error : directory.getErrors()) {
					System.err.format("ERROR: %s", error);
				}
			}
		}

	}
}

