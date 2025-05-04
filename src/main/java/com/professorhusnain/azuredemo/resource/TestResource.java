package com.professorhusnain.azuredemo.resource;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestResource {

    private static final Logger logger = LoggerFactory.getLogger(TestResource.class);

    /**
     * This is a test endpoint to check if the application is running.
     * @param request HttpServletRequest
     * @return String
     */
    @RequestMapping
    public String test(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        String ipAddress = getClientIp(request);

        logger.info("Incoming request - IP: {}, User-Agent: {}", ipAddress, userAgent);

        return "Hello World from Azure!";
    }

    private String getClientIp(HttpServletRequest request) {
        String forwarded = request.getHeader("X-Forwarded-For");
        if (forwarded != null && !forwarded.isEmpty()) {
            return forwarded.split(",")[0];
        }
        return request.getRemoteAddr();
    }
}
