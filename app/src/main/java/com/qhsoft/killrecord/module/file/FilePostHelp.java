package com.qhsoft.killrecord.module.file;

import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


public class FilePostHelp {
	private static final String TAG = "FilePostHelp";
	public static String getData(String path) {

		FileInputStream fis = null;
		ByteArrayOutputStream baos = null;


		try {
			// 得到文件byte数组
			File file = new File(path);
			fis=new FileInputStream(file);
			baos = new ByteArrayOutputStream(fis.available());
			byte[] buffer = new byte[1024];
			int len = -1;
			while ((len = fis.read(buffer)) != -1) {
				baos.write(buffer, 0, len);
			}
//			byte[] data = baos.toByteArray();
			// GZip压缩
//			out = new ByteArrayOutputStream();
//			gzip = new GZIPOutputStream(out);
//			gzip.write(data);
//			gzip.finish();
			// Base64编码
			byte[] compressData = Base64.encode(baos.toByteArray(),Base64.DEFAULT);
			return new String(compressData);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null) {
					fis.close();
					baos.close();


				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;

	}
	public static void decodeFileData(String str, String filePath) {
		try {
			// 解码
			byte[] compressData = Base64.decode(str
					.getBytes(),Base64.DEFAULT);
			OutputStream fis = new FileOutputStream(filePath);
			fis.write(compressData);
			fis.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}




}
