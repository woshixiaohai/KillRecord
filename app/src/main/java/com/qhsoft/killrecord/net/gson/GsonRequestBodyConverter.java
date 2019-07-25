package com.qhsoft.killrecord.net.gson;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Converter;

/**
 * Description:
 * Author: hong
 * Date: 2017-04-12 18:11
 */
final class GsonRequestBodyConverter<T> implements Converter<T, RequestBody> {
	private static final MediaType MEDIA_TYPE = MediaType.parse("text/plain; charset=UTF-8");
	private static final Charset UTF_8 = Charset.forName("UTF-8");

	GsonRequestBodyConverter() {
	}

	@Override
	public RequestBody convert(T value) throws IOException {
		Buffer buffer = new Buffer();
		Writer writer = new OutputStreamWriter(buffer.outputStream(), UTF_8);
		writer.write(value.toString());
		writer.close();

		return RequestBody.create(MEDIA_TYPE, buffer.readByteString());
	}
}
