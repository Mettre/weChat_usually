package com.mettre.usually.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * 创建者：梁建军
 * 创建日期： 2018/1/31
 * 创建时间： 15:33
 * JSONLogFilter
 * 版本：1.0
 * 说明：
 */
@Component
@WebFilter(urlPatterns = "/*", filterName = "JSONLogFilter")
public class JSONLogFilter implements Filter {

    public static final String TAG = "--" + JSONLogFilter.class.getSimpleName();

    private static final Logger logger = LoggerFactory.getLogger(JSONLogFilter.class);

    /**
     * @param BEHAVIOR_ID 行为的ID
     */
    public static final String BEHAVIOR_ID = "BEHAVIOR_ID";

    /**
     * @param REQUEST_START_TIME 请求开始时间
     */
    public static final String REQUEST_START_TIME = "REQUEST_START_TIME";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        request.setAttribute(REQUEST_START_TIME, System.currentTimeMillis());
        request.setAttribute(BEHAVIOR_ID, UUID.randomUUID().toString().replace("-", ""));
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        Map<String, String> header = new HashMap<>();
        Enumeration<String> stringEnumeration = httpRequest.getHeaderNames();
        while (stringEnumeration.hasMoreElements()) {
            String name = stringEnumeration.nextElement();
            header.put(name, httpRequest.getHeader(name));
        }
		logger.info("业务号={} 请求：url={} method={},header={}", request.getAttribute(BEHAVIOR_ID), httpRequest.getRequestURL(),
				httpRequest.getMethod(),
                new ObjectMapper().writeValueAsString(header));

        HttpMethod httpMethod = HttpMethod.resolve(httpRequest.getMethod());
        if (HttpMethod.POST == httpMethod && request.getContentType() != null && MediaType.parseMediaType(request.getContentType()).includes(MediaType.APPLICATION_JSON)) {
            //json
            LogHttpServletRequestWrapper logHttpServletRequestWrapper = new LogHttpServletRequestWrapper((HttpServletRequest) request);
            LogHttpServletResponseWrapper logHttpServletResponseWrapper = new LogHttpServletResponseWrapper((HttpServletResponse) response);
            chain.doFilter(logHttpServletRequestWrapper, logHttpServletResponseWrapper);
            long end = System.currentTimeMillis();
			logger.info("业务号={} 返回：header={}", request.getAttribute(BEHAVIOR_ID), new ObjectMapper().writeValueAsString((getResponseHeader((HttpServletResponse) response))));
            logger.info("业务号={} 返回：code={} 耗时={}ms response={}", request.getAttribute(BEHAVIOR_ID), logHttpServletResponseWrapper.getStatus(), (end - (long) request.getAttribute(REQUEST_START_TIME)), new String(logHttpServletResponseWrapper.getBody(), logHttpServletResponseWrapper.getCharacterEncoding())); //args[]

        } else if (HttpMethod.GET == httpMethod) {
//            logger.info("业务号={} 请求：request={}", request.getAttribute(BEHAVIOR_ID), request.getParameterMap()); //args[]
            chain.doFilter(request, response);
            long end = System.currentTimeMillis();
			logger.info("业务号={} 返回：header={}", request.getAttribute(BEHAVIOR_ID), new ObjectMapper().writeValueAsString(getResponseHeader((HttpServletResponse) response)));
            logger.info("业务号={} 返回：code={} 耗时={}ms", request.getAttribute(BEHAVIOR_ID), ((HttpServletResponse) response).getStatus(), (end - (long) request.getAttribute(REQUEST_START_TIME))); //args[]

        } else {
            chain.doFilter(request, response);
            long end = System.currentTimeMillis();
//            logger.info("业务号={} 请求：request={}", request.getAttribute(BEHAVIOR_ID), request.getParameterMap()); //args[]
			logger.info("业务号={} 返回：header={}", request.getAttribute(BEHAVIOR_ID), new ObjectMapper().writeValueAsString(getResponseHeader((HttpServletResponse) response)));
            logger.info("业务号={} 返回：code={} 耗时={}ms", request.getAttribute(BEHAVIOR_ID), ((HttpServletResponse) response).getStatus(), (end - (long) request.getAttribute(REQUEST_START_TIME))); //args[]
        }
    }

    private Map<String, String> getResponseHeader(HttpServletResponse response) {
        Map<String, String> header = new HashMap<>();
        Collection<String> stringEnumeration = response.getHeaderNames();
        for (String name : stringEnumeration) {
            header.put(name, response.getHeader(name));
        }
        return header;
    }

    @Override
    public void destroy() {

    }


    /**
     * 获得行为的ID
     */
    public static String getBehaviorId() {
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            return (String) request.getAttribute(BEHAVIOR_ID);
        } catch (Exception ignored) {
        }
        return "获取失败";
    }

}
