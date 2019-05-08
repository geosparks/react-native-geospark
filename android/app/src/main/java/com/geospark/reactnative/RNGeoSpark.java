package com.geospark.reactnative;

import android.app.Activity;
import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableMap;
import com.geospark.lib.GeoSpark;
import com.geospark.lib.callback.GeoSparkCallBack;
import com.geospark.lib.callback.GeoSparkGeofenceCallBack;
import com.geospark.lib.callback.GeoSparkLocationCallback;
import com.geospark.lib.callback.GeoSparkLogoutCallBack;
import com.geospark.lib.callback.GeoSparkTripCallBack;
import com.geospark.lib.callback.GeoSparkTripsCallBack;
import com.geospark.lib.model.GeoSparkActiveTrips;
import com.geospark.lib.model.GeoSparkError;
import com.geospark.lib.model.GeoSparkGeofence;
import com.geospark.lib.model.GeoSparkTrip;
import com.geospark.lib.model.GeoSparkUser;

import java.util.List;

public class RNGeoSpark extends ReactContextBaseJavaModule {
    ReactApplicationContext reactContext;

    public RNGeoSpark(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "RNGeoSpark";
    }

    @ReactMethod
    public void checkLocationPermission(Callback callback) {
        callback.invoke(RNGeoSparkUtils.checkLocationPermission(GeoSpark.checkLocationPermission(reactContext)));
    }

    @ReactMethod
    public void checkLocationServices(Callback callback) {
        callback.invoke(RNGeoSparkUtils.checkEnabled(GeoSpark.checkLocationServices(reactContext)));
    }

    @ReactMethod
    public void disableBatteryOptimization() {
        GeoSpark.disableBatteryOptimization(reactContext);
    }

    @ReactMethod
    public void isBatteryOptimizationEnabled(Callback callback) {
        callback.invoke(RNGeoSparkUtils.checkEnabled(GeoSpark.isBatteryOptimizationEnabled(reactContext)));
    }

    @ReactMethod
    public void isLocationTracking(Callback callback) {
        callback.invoke(RNGeoSparkUtils.checkEnabled(GeoSpark.isLocationTracking(reactContext)));
    }

    @ReactMethod
    public void requestLocationPermission() {
        Activity activity = getCurrentActivity();
        if (activity != null) {
            GeoSpark.requestLocationPermission(activity);
        }
    }

    @ReactMethod
    public void requestLocationServices() {
        Activity activity = getCurrentActivity();
        if (activity != null) {
            GeoSpark.requestLocationServices(getCurrentActivity());
        }
    }

    @ReactMethod
    public void createUser(String description, final Callback successCallback, final Callback errorCallback) {
        GeoSpark.createUser(reactContext, description, new GeoSparkCallBack() {
            @Override
            public void onSuccess(GeoSparkUser geoSparkUser) {
                WritableMap map = Arguments.createMap();
                map.putString("userId", geoSparkUser.getUserId());
                successCallback.invoke(map);
            }

            @Override
            public void onFailure(GeoSparkError geoSparkError) {
                errorCallback.invoke(RNGeoSparkUtils.mapForError(geoSparkError));
            }
        });
    }

    @ReactMethod
    public void getUser(String userId, final Callback successCallback, final Callback errorCallback) {
        GeoSpark.getUser(reactContext, userId, new GeoSparkCallBack() {
            @Override
            public void onSuccess(GeoSparkUser geoSparkUser) {
                WritableMap map = Arguments.createMap();
                map.putString("userId", geoSparkUser.getUserId());
                successCallback.invoke(map);
            }

            @Override
            public void onFailure(GeoSparkError geoSparkError) {
                errorCallback.invoke(RNGeoSparkUtils.mapForError(geoSparkError));
            }
        });
    }

    @ReactMethod
    public void setDescription(String description, final Callback successCallback, final Callback errorCallback) {
        GeoSpark.setDescription(reactContext, description, new GeoSparkCallBack() {
            @Override
            public void onSuccess(GeoSparkUser geoSparkUser) {
                WritableMap map = Arguments.createMap();
                map.putString("userId", geoSparkUser.getUserId());
                successCallback.invoke(map);
            }

            @Override
            public void onFailure(GeoSparkError geoSparkError) {
                errorCallback.invoke(RNGeoSparkUtils.mapForError(geoSparkError));
            }
        });
    }

    @ReactMethod
    public void startTrip(String tripId, String description, final Callback successCallback, final Callback errorCallback) {
        GeoSpark.startTrip(reactContext, tripId, description, new GeoSparkTripCallBack() {
            @Override
            public void onSuccess(GeoSparkTrip geoSparkTrip) {
                WritableMap map = Arguments.createMap();
                map.putString("msg", geoSparkTrip.getMsg());
                successCallback.invoke(map);
            }

            @Override
            public void onFailure(GeoSparkError geoSparkError) {
                errorCallback.invoke(RNGeoSparkUtils.mapForError(geoSparkError));
            }
        });
    }

    @ReactMethod
    public void endTrip(String tripId, final Callback successCallback, final Callback errorCallback) {
        GeoSpark.endTrip(reactContext, tripId, new GeoSparkTripCallBack() {
            @Override
            public void onSuccess(GeoSparkTrip geoSparkTrip) {
                WritableMap map = Arguments.createMap();
                map.putString("message", geoSparkTrip.getMsg());
                successCallback.invoke(map);
            }

            @Override
            public void onFailure(GeoSparkError geoSparkError) {
                errorCallback.invoke(RNGeoSparkUtils.mapForError(geoSparkError));
            }
        });
    }

    @ReactMethod
    public void activeTrips(final Callback successCallback, final Callback errorCallback) {
        GeoSpark.activeTrips(reactContext, new GeoSparkTripsCallBack() {
            @Override
            public void onSuccess(List<GeoSparkActiveTrips> geoSparkActiveTrips) {
                WritableMap map = Arguments.createMap();
                map.putMap("activeTrips", RNGeoSparkUtils.mapForTripList(geoSparkActiveTrips));
                successCallback.invoke(map);
            }

            @Override
            public void onFailure(GeoSparkError geoSparkError) {
                errorCallback.invoke(RNGeoSparkUtils.mapForError(geoSparkError));
            }
        });
    }

    @ReactMethod
    public void createGeofence(double latitude, double longitude, int radius, int expireInSeconds, final Callback successCallback, final Callback errorCallback) {
        GeoSpark.createGeofence(reactContext, latitude, longitude, radius, expireInSeconds, new GeoSparkGeofenceCallBack() {
            @Override
            public void onSuccess(GeoSparkGeofence geoSparkGeofence) {
                WritableMap map = Arguments.createMap();
                map.putString("geofenceId", geoSparkGeofence.getId());
                map.putString("createdAt", geoSparkGeofence.getCreatedAt());
                map.putString("expireAt", geoSparkGeofence.getExpiresAt());
                map.putDouble("latitude", geoSparkGeofence.getCoordinates().get(0));
                map.putDouble("longitude", geoSparkGeofence.getCoordinates().get(1));
                successCallback.invoke(map);
            }

            @Override
            public void onFailure(GeoSparkError geoSparkError) {
                errorCallback.invoke(RNGeoSparkUtils.mapForError(geoSparkError));
            }
        });
    }

    @ReactMethod
    public void deleteGeofence(String geofenceId, final Callback successCallback, final Callback errorCallback) {
        GeoSpark.deleteGeofence(reactContext, geofenceId, new GeoSparkGeofenceCallBack() {
            @Override
            public void onSuccess(GeoSparkGeofence geoSparkGeofence) {
                WritableMap map = Arguments.createMap();
                map.putString("message", geoSparkGeofence.getMessage());
                successCallback.invoke(map);
            }

            @Override
            public void onFailure(GeoSparkError geoSparkError) {
                errorCallback.invoke(RNGeoSparkUtils.mapForError(geoSparkError));
            }
        });
    }

    @ReactMethod
    public void geofenceList(final Callback successCallback, final Callback errorCallback) {
        GeoSpark.geofenceList(reactContext, new GeoSparkGeofenceCallBack() {
            @Override
            public void onSuccess(GeoSparkGeofence geoSparkGeofence) {
                WritableMap map = Arguments.createMap();
                map.putMap("geofenceList", RNGeoSparkUtils.mapForGeofenceList(geoSparkGeofence.getGeofenceList()));
                successCallback.invoke(map);
            }

            @Override
            public void onFailure(GeoSparkError geoSparkError) {
                errorCallback.invoke(RNGeoSparkUtils.mapForError(geoSparkError));
            }
        });
    }

    @ReactMethod
    public void startTracking() {
        GeoSpark.startTracking(reactContext);
    }

    @ReactMethod
    public void stopTracking() {
        GeoSpark.stopTracking(reactContext);
    }

    @ReactMethod
    public void getCurrentLocation(String type, int accuracy, final Callback successCallback, final Callback errorCallback) {
        GeoSpark.getCurrentLocation(reactContext, RNGeoSparkUtils.stringToEnum(type), accuracy, new GeoSparkLocationCallback() {
            @Override
            public void location(double latitude, double longitude, double accuracy) {
                WritableMap map = Arguments.createMap();
                map.putDouble("latitude", latitude);
                map.putDouble("longitude", longitude);
                map.putDouble("accuracy", accuracy);
                successCallback.invoke(map);
            }

            @Override
            public void onFailure(GeoSparkError geoSparkError) {
                errorCallback.invoke(RNGeoSparkUtils.mapForError(geoSparkError));
            }
        });
    }

    @ReactMethod
    public void updateCurrentLocation(int accuracy) {
        GeoSpark.updateCurrentLocation(reactContext, accuracy);
    }

    @ReactMethod
    public void logout(final Callback successCallback, final Callback errorCallback) {
        GeoSpark.logout(reactContext, new GeoSparkLogoutCallBack() {
            @Override
            public void onSuccess(String message) {
                WritableMap map = Arguments.createMap();
                map.putString("message", message);
                successCallback.invoke(map);
            }

            @Override
            public void onFailure(GeoSparkError geoSparkError) {
                errorCallback.invoke(RNGeoSparkUtils.mapForError(geoSparkError));
            }
        });
    }

    @ReactMethod
    public void setTrackingInAppState(ReadableArray readableArray) {
        try {
            if (RNGeoSparkUtils.isArrayNotNull(readableArray)) {
                GeoSpark.setTrackingInAppState(reactContext, RNGeoSparkUtils.arrayToEnum(readableArray));
            } else {
                Log.e(RNGeoSparkConstants.TAG, RNGeoSparkConstants.APPSTATE_SETTINGS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ReactMethod
    public void setTrackingInMotion(ReadableArray readableArray) {
        try {
            if (RNGeoSparkUtils.isArrayNotNull(readableArray)) {
                GeoSpark.setTrackingInMotion(reactContext, RNGeoSparkUtils.arrayToEnum(readableArray));
            } else {
                Log.e(RNGeoSparkConstants.TAG, RNGeoSparkConstants.MOTION_SETTINGS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
