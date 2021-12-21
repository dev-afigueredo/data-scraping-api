package com.afigueredo.datascraping.utils;

import com.afigueredo.datascraping.domain.File;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReadFileUtil {

	public static List<File> readZipStream(URL url) throws IOException {

		List<File> listDetailsFile = new ArrayList<>();

		InputStream inputStream = url.openStream();

		try (ZipInputStream zipInput = new ZipInputStream(inputStream)) {

			ZipEntry entry;

			while ((entry = zipInput.getNextEntry()) != null) {
				File currentFile = new File();

				if (entry.getSize() > 0) {
					String[] path = entry.getName().split("/");
					String[] file = path[path.length - 1].split("[.]");

					currentFile.setExtensionFile(file[file.length - 1]);
					currentFile.setCountFile(1L);
					currentFile.setSizeFile(entry.getSize());
					long numberLines = readContents(new FilterInputStream(zipInput) {
						@Override
						public void close() throws IOException {
							zipInput.closeEntry();
						}
					});
					currentFile.setNumberLinesFile(numberLines);

					listDetailsFile.add(currentFile);
				}
			}
		}
		return listDetailsFile;
	}

	private static long readContents(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		return reader.lines().count();
	}
}
