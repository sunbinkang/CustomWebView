// ICallbackFromMainProcessToWebViewProcessInterface.aidl
package com.example.webview;

// Declare any non-default types here with import statements

interface ICallbackFromMainProcessToWebViewProcessInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
     void onResult(String callbackname, String response);
}
