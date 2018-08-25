package com.customers.backend.app.filters;

import com.customers.backend.domain.contracts.services.LoggerService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class LoggerFilter extends OncePerRequestFilter {

    @Autowired
    LoggerService logger;

    private static final List<MediaType> VISIBLE_TYPES = Arrays.asList(
            MediaType.valueOf("text/*"),
            MediaType.APPLICATION_FORM_URLENCODED,
            MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.valueOf("application/*+json"),
            MediaType.valueOf("application/*+xml"),
            MediaType.MULTIPART_FORM_DATA
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        MDC.put("uniqueTrackingNumber", UUID.randomUUID().toString());
        if (isAsyncDispatch(request))
            filterChain.doFilter(request, response);
        else
            doFilterWrapped(wrapRequest(request), wrapResponse(response), filterChain);
    }

    private void doFilterWrapped(ContentCachingRequestWrapper request, ContentCachingResponseWrapper response, FilterChain filterChain) throws ServletException, IOException {
        try {
            beforeRequest(request);
            filterChain.doFilter(request, response);
        } finally {
            afterRequest(request, response);
            response.copyBodyToResponse();
        }
    }

    private void beforeRequest(ContentCachingRequestWrapper request) {
        logRequestHeader(request, String.format("incoming request clientAddress=%s", request.getRemoteAddr()));
    }

    private void afterRequest(ContentCachingRequestWrapper request, ContentCachingResponseWrapper response) {
        logRequestBody(request, String.format("incoming request clientAddress=%s", request.getRemoteAddr()));
        logResponse(response, String.format("outcome response clientAddress=%s", request.getRemoteAddr()));
    }

    private void logRequestHeader(ContentCachingRequestWrapper request, String prefix) {
        val queryString = request.getQueryString();

        Set<String> headers = Collections.list(request.getHeaderNames()).stream().map(headerName -> headerName + ": " + Collections.list(request.getHeaders(headerName)).toString()).collect(Collectors.toSet());

        if (queryString == null)
            logger.Info("Request headers {} method={} headers=\"{}\" uri={}", prefix, request.getMethod(), headers, request.getRequestURI());
        else
            logger.Info("Request headers {} method={} headers=\"{}\" uri={}?{}", prefix, request.getMethod(), headers, request.getRequestURI(), queryString);
    }

    private void logRequestBody(ContentCachingRequestWrapper request, String prefix) {
        val content = request.getContentAsByteArray();

        if (content.length > 0)
            logContent(content, request.getContentType(), request.getCharacterEncoding(), prefix, "Request Payload");
    }

    private void logResponse(ContentCachingResponseWrapper response, String prefix) {
        val status = response.getStatus();

        Set<String> headers = response.getHeaderNames().stream().map(headerName -> headerName + ": " + response.getHeaders(headerName).toString()).collect(Collectors.toSet());

        if (status < 300)
            logger.Info("{} responseStatus={} responseMessage={} headers=\"{}\"", prefix, status, HttpStatus.valueOf(status).getReasonPhrase(), headers);
        else
            logger.Error("{} responseStatus={} responseMessage={} headers=\"{}\"", prefix, status, HttpStatus.valueOf(status).getReasonPhrase(), headers);

        val content = response.getContentAsByteArray();
        if (content.length > 0)
            logContent(content, response.getContentType(), response.getCharacterEncoding(), prefix, "Response Payload");
    }

    private void logContent(byte[] content, String contentType, String contentEncoding, String prefix, String requestMessage) {
        val mediaType = MediaType.valueOf(contentType);
        val visible = VISIBLE_TYPES.stream().anyMatch(visibleType -> visibleType.includes(mediaType));
        if (visible) {
            try {
                val contentString = new String(content, contentEncoding).replaceAll("\n", "").replaceAll("\t", " ");
                logger.Info(requestMessage + " {} payload={}", prefix, contentString);

            } catch (UnsupportedEncodingException e) {
                logger.Info("{} [{} bytes content]", prefix, content.length);
            }
        } else
            logger.Info("{} [{} bytes content]", prefix, content.length);
    }

    private static ContentCachingRequestWrapper wrapRequest(HttpServletRequest request) {
        if (request instanceof ContentCachingRequestWrapper)
            return (ContentCachingRequestWrapper) request;
        else
            return new ContentCachingRequestWrapper(request);
    }

    private static ContentCachingResponseWrapper wrapResponse(HttpServletResponse response) {
        if (response instanceof ContentCachingResponseWrapper)
            return (ContentCachingResponseWrapper) response;
        else
            return new ContentCachingResponseWrapper(response);
    }
}