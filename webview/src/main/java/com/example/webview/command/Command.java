package com.example.webview.command;

import com.example.webview.ICallbackFromMainProcessToWebViewProcessInterface;

import java.util.Map;

/**
 * Created by BinKang on 2021/3/15.
 * Des :
 */
public interface Command {

    String name();

    void execute(Map parameters, ICallbackFromMainProcessToWebViewProcessInterface callback);

}
