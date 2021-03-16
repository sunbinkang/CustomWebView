// IWebViewProcessToMainProcessInterface.aidl
package com.example.webview;

// Declare any non-default types here with import statements
import com.example.webview.ICallbackFromMainProcessToWebViewProcessInterface;

interface IWebViewProcessToMainProcessInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */

    void handleWebCommand(String commandName,String jsonParams,in ICallbackFromMainProcessToWebViewProcessInterface call);
}
