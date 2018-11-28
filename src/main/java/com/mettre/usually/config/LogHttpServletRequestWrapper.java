package com.mettre.usually.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 创建者：梁建军
 * 创建日期： 2018/1/31
 * 创建时间： 15:38
 * BodyReaderHttpServletRequestWrapper
 * 版本：1.0
 * 说明：
 */
public class LogHttpServletRequestWrapper extends HttpServletRequestWrapper {

	public static final String TAG = "--" + LogHttpServletRequestWrapper.class.getSimpleName();

	private static final Logger logger = LoggerFactory.getLogger(LogHttpServletRequestWrapper.class);


	private ServletInputStream servletInputStream;

	/**
	 * Constructs a request object wrapping the given request.
	 *
	 * @param request The request to wrap
	 * @throws IllegalArgumentException if the request is null
	 */
	public LogHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public BufferedReader getReader() throws IOException {
		return new BufferedReader(new InputStreamReader(getInputStream(),getContentType()));
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		if (servletInputStream == null) {
			servletInputStream = new RetainServletInputStream(super.getInputStream(), this);
		}
		return servletInputStream;
	}


	/**
	 * 保留
	 */
	private static class RetainServletInputStream extends ServletInputStream {

		private final ServletInputStream servletInputStream;

		private LogHttpServletRequestWrapper logHttpServletRequestWrapper;
		/**
		 * 用于记录请求体，打印完日志就释放
		 */
		private ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024 * 10);

		public RetainServletInputStream(ServletInputStream servletInputStream, LogHttpServletRequestWrapper logHttpServletRequestWrapper) {

			this.servletInputStream = servletInputStream;
			this.logHttpServletRequestWrapper = logHttpServletRequestWrapper;
		}

		@Override
		public boolean isFinished() {
			return servletInputStream.isFinished();
		}

		@Override
		public boolean isReady() {
			return servletInputStream.isReady();
		}

		@Override
		public void setReadListener(ReadListener listener) {
			servletInputStream.setReadListener(listener);
		}

		@Override
		public int read() throws IOException {
			int b = servletInputStream.read();
			if (b != -1) {
				byteArrayOutputStream.write(b);
			} else if (byteArrayOutputStream != null) {

				logger.info("业务号={} 请求：request={}", JSONLogFilter.getBehaviorId(), new String(byteArrayOutputStream.toByteArray(), logHttpServletRequestWrapper.getCharacterEncoding())); //args[]
				byteArrayOutputStream = null;
			}
			return b;
		}
	}
}
