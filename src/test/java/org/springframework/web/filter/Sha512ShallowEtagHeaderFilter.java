package org.springframework.web.filter;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import org.springframework.http.HttpHeaders;
import org.springframework.util.Assert;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.util.ContentCachingResponseWrapper;
import java.io.IOException;

public class Sha512ShallowEtagHeaderFilter extends ShallowEtagHeaderFilter {

	@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
        
        filterChain.doFilter(request, responseWrapper);
        
        byte[] responseBody = responseWrapper.getContentAsByteArray();
        if (responseBody.length > 0) {
            String eTag = generateSha512ETag(responseBody);
            response.setHeader(HttpHeaders.ETAG, eTag);
        }
        
        responseWrapper.copyBodyToResponse();
    }

    private String generateSha512ETag(byte[] body) {
        Assert.notNull(body, "Body must not be null");
        final HashCode hash = Hashing.sha512().hashBytes(body);
		return "\"" + hash + "\"";
	}

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getMethod().equals("POST") || request.getMethod().equals("PUT") || request.getMethod().equals("PATCH");
    }
}
