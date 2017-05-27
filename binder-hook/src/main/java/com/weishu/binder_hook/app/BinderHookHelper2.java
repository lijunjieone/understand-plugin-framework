package com.weishu.binder_hook.app;

import android.os.IBinder;

import com.mx.common.reflect.Reflect;

import java.lang.reflect.Proxy;
import java.sql.Ref;
import java.util.Map;

/**
 * Created by lijunjie on 27/05/2017.
 */

public class BinderHookHelper2 {

    public static void hookClipboardService() throws Exception {
        final String CLIPBOARD_SERVICE = "clipboard";



        Object serviceManager = Reflect.on("android.os.ServiceManager").create();

        IBinder rawBinder = (IBinder) Reflect.on(serviceManager).call("getService",CLIPBOARD_SERVICE);

        IBinder hookedBinder = (IBinder) Proxy.newProxyInstance(serviceManager.getClass().getClassLoader(),new Class[]{IBinder.class},new BinderProxyHookHandler((rawBinder)));


        Map<String,IBinder> cache = (Map)Reflect.on(serviceManager).field("sCache");

        cache.put(CLIPBOARD_SERVICE,hookedBinder);

    }
}
