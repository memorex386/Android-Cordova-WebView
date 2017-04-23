/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
*/

package com.mtsdealersolutions.cordova_webview.whitelist;

import android.content.Context;

import com.mtsdealersolutions.cordova_webview.ConfigXmlParser;
import com.mtsdealersolutions.cordova_webview.CordovaPlugin;

import org.xmlpull.v1.XmlPullParser;

public class WhitelistPlugin extends CordovaPlugin {
    private static final String LOG_TAG = "WhitelistPlugin";
    private com.mtsdealersolutions.cordova_webview.Whitelist allowedNavigations;
    private com.mtsdealersolutions.cordova_webview.Whitelist allowedIntents;
    private com.mtsdealersolutions.cordova_webview.Whitelist allowedRequests;

    // Used when instantiated via reflection by PluginManager
    public WhitelistPlugin() {
    }

    // These can be used by embedders to allow Java-configuration of whitelists.
    public WhitelistPlugin(Context context) {
        this(new com.mtsdealersolutions.cordova_webview.Whitelist(), new com.mtsdealersolutions.cordova_webview.Whitelist(), null);
        new CustomConfigXmlParser().parse(context);
    }

    public WhitelistPlugin(XmlPullParser xmlParser) {
        this(new com.mtsdealersolutions.cordova_webview.Whitelist(), new com.mtsdealersolutions.cordova_webview.Whitelist(), null);
        new CustomConfigXmlParser().parse(xmlParser);
    }

    public WhitelistPlugin(com.mtsdealersolutions.cordova_webview.Whitelist allowedNavigations, com.mtsdealersolutions.cordova_webview.Whitelist allowedIntents, com.mtsdealersolutions.cordova_webview.Whitelist allowedRequests) {
        if (allowedRequests == null) {
            allowedRequests = new com.mtsdealersolutions.cordova_webview.Whitelist();
            allowedRequests.addWhiteListEntry("file:///*", false);
            allowedRequests.addWhiteListEntry("data:*", false);
        }
        this.allowedNavigations = allowedNavigations;
        this.allowedIntents = allowedIntents;
        this.allowedRequests = allowedRequests;
    }

    @Override
    public void pluginInitialize() {
        if (allowedNavigations == null) {
            allowedNavigations = new com.mtsdealersolutions.cordova_webview.Whitelist();
            allowedIntents = new com.mtsdealersolutions.cordova_webview.Whitelist();
            allowedRequests = new com.mtsdealersolutions.cordova_webview.Whitelist();
            new CustomConfigXmlParser().parse(webView.getContext());
        }
    }

    private class CustomConfigXmlParser extends ConfigXmlParser {
        @Override
        public void handleStartTag(XmlPullParser xml) {
            String strNode = xml.getName();
            if (strNode.equals("content")) {
                String startPage = xml.getAttributeValue(null, "src");
                allowedNavigations.addWhiteListEntry(startPage, false);
            } else if (strNode.equals("allow-navigation")) {
                String origin = xml.getAttributeValue(null, "href");
                if ("*".equals(origin)) {
                    allowedNavigations.addWhiteListEntry("http://*/*", false);
                    allowedNavigations.addWhiteListEntry("https://*/*", false);
                    allowedNavigations.addWhiteListEntry("data:*", false);
                } else {
                    allowedNavigations.addWhiteListEntry(origin, false);
                }
            } else if (strNode.equals("allow-intent")) {
                String origin = xml.getAttributeValue(null, "href");
                allowedIntents.addWhiteListEntry(origin, false);
            } else if (strNode.equals("access")) {
                String origin = xml.getAttributeValue(null, "origin");
                String subdomains = xml.getAttributeValue(null, "subdomains");
                boolean external = (xml.getAttributeValue(null, "launch-external") != null);
                if (origin != null) {
                    if (external) {
                        com.mtsdealersolutions.cordova_webview.LOG.w(LOG_TAG, "Found <access launch-external> within config.xml. Please use <allow-intent> instead.");
                        allowedIntents.addWhiteListEntry(origin, (subdomains != null) && (subdomains.compareToIgnoreCase("true") == 0));
                    } else {
                        if ("*".equals(origin)) {
                            allowedRequests.addWhiteListEntry("http://*/*", false);
                            allowedRequests.addWhiteListEntry("https://*/*", false);
                        } else {
                            allowedRequests.addWhiteListEntry(origin, (subdomains != null) && (subdomains.compareToIgnoreCase("true") == 0));
                        }
                    }
                }
            }
        }

        @Override
        public void handleEndTag(XmlPullParser xml) {
        }
    }

    @Override
    public Boolean shouldAllowNavigation(String url) {
        if (allowedNavigations.isUrlWhiteListed(url)) {
            return true;
        }
        return null; // Default policy
    }

    @Override
    public Boolean shouldAllowRequest(String url) {
        if (Boolean.TRUE == shouldAllowNavigation(url)) {
            return true;
        }
        if (allowedRequests.isUrlWhiteListed(url)) {
            return true;
        }
        return null; // Default policy
    }

    @Override
    public Boolean shouldOpenExternalUrl(String url) {
        if (allowedIntents.isUrlWhiteListed(url)) {
            return true;
        }
        return null; // Default policy
    }

    public com.mtsdealersolutions.cordova_webview.Whitelist getAllowedNavigations() {
        return allowedNavigations;
    }

    public void setAllowedNavigations(com.mtsdealersolutions.cordova_webview.Whitelist allowedNavigations) {
        this.allowedNavigations = allowedNavigations;
    }

    public com.mtsdealersolutions.cordova_webview.Whitelist getAllowedIntents() {
        return allowedIntents;
    }

    public void setAllowedIntents(com.mtsdealersolutions.cordova_webview.Whitelist allowedIntents) {
        this.allowedIntents = allowedIntents;
    }

    public com.mtsdealersolutions.cordova_webview.Whitelist getAllowedRequests() {
        return allowedRequests;
    }

    public void setAllowedRequests(com.mtsdealersolutions.cordova_webview.Whitelist allowedRequests) {
        this.allowedRequests = allowedRequests;
    }
}
