package com.geospark.reactnative;

import android.content.Context;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.geospark.lib.models.GeoSparkError;
import com.geospark.lib.models.GeoSparkLocation;
import com.geospark.lib.models.TripStatusListener;
import com.geospark.lib.service.GeoSparkReceiver;


public class RNGeoSparkReceiver extends GeoSparkReceiver {
    private ReactNativeHost mReactNativeHost;

    private void invokeSendEvent(ReactContext reactContext, String eventName, Object data) {
        reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(eventName, data);
    }

    private void sendEvent(final String eventName, final Object data) {
        final ReactInstanceManager reactInstanceManager = mReactNativeHost.getReactInstanceManager();
        ReactContext reactContext = reactInstanceManager.getCurrentReactContext();
        if (reactContext == null) {
            reactInstanceManager.addReactInstanceEventListener(new ReactInstanceManager.ReactInstanceEventListener() {
                @Override
                public void onReactContextInitialized(ReactContext reactContext) {
                    invokeSendEvent(reactContext, eventName, data);
                    reactInstanceManager.removeReactInstanceEventListener(this);
                }
            });
            if (!reactInstanceManager.hasStartedCreatingInitialContext()) {
                reactInstanceManager.createReactContextInBackground();
            }
        } else {
            invokeSendEvent(reactContext, eventName, data);
        }
    }

    @Override
    public void onLocationUpdated(Context context, GeoSparkLocation geoSparkLocation) {
        ReactApplication reactApplication = (ReactApplication) context.getApplicationContext();
        mReactNativeHost = reactApplication.getReactNativeHost();
        WritableMap map = Arguments.createMap();
        map.putString("userId", geoSparkLocation.getUserId());
        map.putMap("location", RNGeoSparkUtils.mapForLocation(geoSparkLocation.getLocation()));
        map.putString("activity", geoSparkLocation.getActivity());
        map.putString("recordedAt", geoSparkLocation.getRecordedAt());
        map.putString("timezone", geoSparkLocation.getTimezoneOffset());
        sendEvent("location", map);
    }

    @Override
    public void onReceiveTripStatus(Context context, TripStatusListener tripStatusListener) {
        ReactApplication reactApplication = (ReactApplication) context.getApplicationContext();
        mReactNativeHost = reactApplication.getReactNativeHost();
        WritableMap map = Arguments.createMap();
        map.putString("tripId", tripStatusListener.getTripId());
        map.putDouble("latitude", tripStatusListener.getLatitude());
        map.putDouble("longitude", tripStatusListener.getLongitue());
        map.putString("startedTime", tripStatusListener.getStartedTime());
        map.putDouble("distance", tripStatusListener.getDistance());
        map.putDouble("duration", tripStatusListener.getDuration());
        map.putDouble("pace", tripStatusListener.getPace());
        map.putDouble("speed", tripStatusListener.getSpeed());
        sendEvent("trip_status", map);
    }

    @Override
    public void onError(Context context, GeoSparkError geoSparkError) {
        ReactApplication reactApplication = (ReactApplication) context.getApplicationContext();
        mReactNativeHost = reactApplication.getReactNativeHost();
        sendEvent("error", RNGeoSparkUtils.mapForError(geoSparkError));
    }
}

