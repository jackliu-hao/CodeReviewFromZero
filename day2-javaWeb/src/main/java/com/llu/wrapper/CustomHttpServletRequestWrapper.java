package com.llu.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.HashMap;
import java.util.Map;

public class CustomHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private Map<String, String[]> customParams;

    public CustomHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        customParams = new HashMap<>(request.getParameterMap());
    }

    public void addParameter(String name, String value) {
        customParams.put(name, new String[]{value});
    }

    @Override
    public String getParameter(String name) {
        String[] values = customParams.get(name);
        if (values == null || values.length == 0) {
            return null;
        }
        return values[0];
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return customParams;
    }

    @Override
    public String[] getParameterValues(String name) {
        return customParams.get(name);
    }
}
