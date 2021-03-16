package com.example.webview.mainprocess;

import android.os.RemoteException;

import com.example.webview.ICallbackFromMainProcessToWebViewProcessInterface;
import com.example.webview.IWebViewProcessToMainProcessInterface;
import com.example.webview.command.Command;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * Created by BinKang on 2021/3/15.
 * Des :
 */
public class MainProcessCommandsManager extends IWebViewProcessToMainProcessInterface.Stub {

    private static MainProcessCommandsManager sInstance;
    private HashMap<String, Command> mCommandHashMap = new HashMap<>();

    private MainProcessCommandsManager() {
        ServiceLoader<Command> serviceLoader = ServiceLoader.load(Command.class);
        for (Command command : serviceLoader) {
            if (!mCommandHashMap.containsKey(command.name())) {
                mCommandHashMap.put(command.name(), command);
            }
        }
    }

    public static MainProcessCommandsManager getInstance() {
        if (sInstance == null) {
            synchronized (MainProcessCommandsManager.class) {
                sInstance = new MainProcessCommandsManager();
            }
        }
        return sInstance;
    }

//    使用了autoService就不需要这个
//    public void registerCommand(String commandName, Command command) {
//        mCommandHashMap.put(commandName, command);
//    }

    public void executeCommand(String commandName, Map params,ICallbackFromMainProcessToWebViewProcessInterface callbackFromMainProcessToWebViewProcessInterface) {
        mCommandHashMap.get(commandName).execute(params,callbackFromMainProcessToWebViewProcessInterface);
    }

    @Override
    public void handleWebCommand(String commandName, String jsonParams, ICallbackFromMainProcessToWebViewProcessInterface callback) throws RemoteException {
        MainProcessCommandsManager.getInstance().executeCommand(commandName, new Gson().fromJson(jsonParams, Map.class),callback);
    }
}
