package com.froggengo.cloud.servlet;

import jakarta.servlet.ServletException;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.valves.ValveBase;

import java.io.IOException;
import java.util.logging.Logger;

public class ConnectionLoggingValve extends ValveBase {
    private static final Logger logger = Logger.getLogger(ConnectionLoggingValve.class.getName());

    @Override
    public void invoke(Request request, Response response) throws IOException, ServletException {
        logger.info("request start: " + request.getRemoteAddr());

        try {
            getNext().invoke(request, response);
        } finally {
            logger.info("request end: " + request.getRemoteAddr());
        }
    }
}