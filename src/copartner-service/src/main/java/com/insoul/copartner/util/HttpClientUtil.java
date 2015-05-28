package com.insoul.copartner.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import com.insoul.copartner.constant.CommonConstant;

/**
 * The Class HttpClientUtil.
 */
public final class HttpClientUtil {

    /**
     * Instantiates a new http client util.
     */
    private HttpClientUtil() {
        // empty
    }

    /**
     * Do get request.
     * 
     * @param url
     *            the url
     * @return the map
     */
    public static Map<String, Object> doGetRequest(final String url) {
        return doGetRequest(url, null);
    }

    /**
     * Do get request.
     * 
     * @param url
     *            the url
     * @param postData
     *            the postData
     * @return the map
     */
    public static Map<String, Object> doGetRequest(final String url, final Map<String, Object> postData) {
        return doGetRequest(url, postData, null);
    }

    /**
     * Do get request.
     * 
     * @param url
     *            the url
     * @param postData
     *            the postData
     * @param headers
     *            the headers
     * @return the map
     */
    public static Map<String, Object> doGetRequest(final String url, final Map<String, Object> postData,
            final Map<String, Object> headers) {
        String response = "";
        int statusCode = HttpStatus.SC_BAD_REQUEST;

        StringBuilder param = new StringBuilder();
        if ((null != postData) && !postData.isEmpty()) {
            Set<Entry<String, Object>> postDataEntrySets = postData.entrySet();
            for (Map.Entry<String, Object> m : postDataEntrySets) {
                param.append(m.getKey()).append("=").append(m.getValue()).append("&");
            }
            if (!postDataEntrySets.isEmpty()) {
                param.insert(0, "?");
                param.delete(param.length() - 1, param.length());
            }
        }

        HttpClient httpClient = new HttpClient();
        GetMethod getMethod = new GetMethod(url + param);
        // default retry handler
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        // set header
        if ((null != headers) && !headers.isEmpty()) {
            Set<Entry<String, Object>> headerEntrySets = headers.entrySet();
            for (Map.Entry<String, Object> m : headerEntrySets) {
                getMethod.setRequestHeader(new Header(m.getKey(), m.getValue().toString()));
            }
        }
        try {
            statusCode = httpClient.executeMethod(getMethod);

            StringBuffer responseBuffer = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(getMethod.getResponseBodyAsStream(),
                    getMethod.getResponseCharSet()));
            try {
                int l;
                while ((l = reader.read()) != -1) {
                    responseBuffer.append((char) l);
                }
            } finally {
                reader.close();
            }
            response = responseBuffer.toString();
        } catch (HttpException e) {
            response = "http protocol exception.";
        } catch (IOException e) {
            response = "transport exception";
        } finally {
            getMethod.releaseConnection();
        }

        Map<String, Object> result = new HashMap<String, Object>();
        result.put(CommonConstant.STATUS, statusCode);
        result.put(CommonConstant.BODY, response);
        return result;
    }

    /**
     * Do post request.
     * 
     * @param url
     *            the url
     * @param postData
     *            the postData
     * @return the map
     */
    public static Map<String, Object> doPostRequest(final String url, final Map<String, Object> postData) {
        return doPostRequest(url, null, null);
    }

    /**
     * Do post request.
     * 
     * @param url
     *            the url
     * @param postData
     *            the postData
     * @param headers
     *            the headers
     * @return the map
     */
    public static Map<String, Object> doPostRequest(final String url, final Map<String, Object> postData,
            final Map<String, Object> headers) {
        String response = "";
        int statusCode = HttpStatus.SC_BAD_REQUEST;

        HttpClient client = new HttpClient();
        client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        PostMethod postMethod = new PostMethod(url);
        // set header
        if ((null != headers) && !headers.isEmpty()) {
            Set<Entry<String, Object>> headerEntrySets = headers.entrySet();
            for (Map.Entry<String, Object> m : headerEntrySets) {
                postMethod.setRequestHeader(new Header(m.getKey(), m.getValue().toString()));
            }
        }
        // set parameter
        if ((null != postData) && !postData.isEmpty()) {
            Set<Entry<String, Object>> postDataEntrySets = postData.entrySet();
            for (Map.Entry<String, Object> m : postDataEntrySets) {
                postMethod.setParameter(m.getKey(), m.getValue().toString());
            }
        }
        // custom retry handler
        postMethod.getParams()
                .setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));

        try {
            statusCode = client.executeMethod(postMethod);

            StringBuffer responseBuffer = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(postMethod.getResponseBodyAsStream(),
                    postMethod.getResponseCharSet()));
            try {
                int l;
                while ((l = reader.read()) != -1) {
                    responseBuffer.append((char) l);
                }
            } finally {
                reader.close();
            }
            response = responseBuffer.toString();
        } catch (HttpException e) {
            response = "http protocol exception.";
        } catch (IOException e) {
            response = "transport exception";
        } finally {
            postMethod.releaseConnection();
        }

        Map<String, Object> result = new HashMap<String, Object>();
        result.put(CommonConstant.STATUS, statusCode);
        result.put(CommonConstant.BODY, response);
        return result;
    }
}
