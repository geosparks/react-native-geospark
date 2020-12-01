package com.geospark.reactnative;

import android.app.Activity;
import android.location.Location;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.geospark.lib.GeoSpark;
import com.geospark.lib.GeoSparkTrackingMode;
import com.geospark.lib.callback.GeoSparkActiveTripsCallback;
import com.geospark.lib.callback.GeoSparkCallback;
import com.geospark.lib.callback.GeoSparkCreateTripCallback;
import com.geospark.lib.callback.GeoSparkDeleteTripCallback;
import com.geospark.lib.callback.GeoSparkLocationCallback;
import com.geospark.lib.callback.GeoSparkLogoutCallback;
import com.geospark.lib.callback.GeoSparkSyncTripCallback;
import com.geospark.lib.callback.GeoSparkTripCallback;
import com.geospark.lib.models.GeoSparkError;
import com.geospark.lib.models.GeoSparkTrip;
import com.geospark.lib.models.GeoSparkUser;
import com.geospark.lib.models.createtrip.GeoSparkCreateTrip;

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
    public void createUser(String description, final Callback successCallback, final Callback errorCallback) {
        GeoSpark.createUser(description, new GeoSparkCallback() {
            @Override
            public void onSuccess(GeoSparkUser geoSparkUser) {
                successCallback.invoke(RNGeoSparkUtils.mapForUser(geoSparkUser));
            }

            @Override
            public void onFailure(GeoSparkError geoSparkError) {
                errorCallback.invoke(RNGeoSparkUtils.mapForError(geoSparkError));
            }
        });
    }

    @ReactMethod
    public void getUser(String userId, final Callback successCallback, final Callback errorCallback) {
        GeoSpark.getUser(userId, new GeoSparkCallback() {
            @Override
            public void onSuccess(GeoSparkUser geoSparkUser) {
                successCallback.invoke(RNGeoSparkUtils.mapForUser(geoSparkUser));
            }

            @Override
            public void onFailure(GeoSparkError geoSparkError) {
                errorCallback.invoke(RNGeoSparkUtils.mapForError(geoSparkError));
            }
        });
    }

    @ReactMethod
    public static void setDescription(String description) {
        GeoSpark.setDescription(description);
    }

    @ReactMethod
    public void toggleEvents(boolean geofence, boolean trip, boolean location, boolean movingGeofence, final Callback successCallback, final Callback errorCallback) {
        GeoSpark.toggleEvents(geofence, trip, location, movingGeofence, new GeoSparkCallback() {
            @Override
            public void onSuccess(GeoSparkUser geoSparkUser) {
                successCallback.invoke(RNGeoSparkUtils.mapForUser(geoSparkUser));
            }

            @Override
            public void onFailure(GeoSparkError geoSparkError) {
                errorCallback.invoke(RNGeoSparkUtils.mapForError(geoSparkError));
            }
        });
    }

    @ReactMethod
    public void toggleListener(boolean location, boolean event, final Callback successCallback, final Callback errorCallback) {
        GeoSpark.toggleListener(location, event, new GeoSparkCallback() {
            @Override
            public void onSuccess(GeoSparkUser geoSparkUser) {
                successCallback.invoke(RNGeoSparkUtils.mapForUser(geoSparkUser));
            }

            @Override
            public void onFailure(GeoSparkError geoSparkError) {
                errorCallback.invoke(RNGeoSparkUtils.mapForError(geoSparkError));
            }
        });
    }

    @ReactMethod
    public void getEventsStatus(final Callback successCallback, final Callback errorCallback) {
        GeoSpark.getEventsStatus(new GeoSparkCallback() {
            @Override
            public void onSuccess(GeoSparkUser geoSparkUser) {
                successCallback.invoke(RNGeoSparkUtils.mapForUser(geoSparkUser));
            }

            @Override
            public void onFailure(GeoSparkError geoSparkError) {
                errorCallback.invoke(RNGeoSparkUtils.mapForError(geoSparkError));
            }
        });
    }

    @ReactMethod
    public void getListenerStatus(final Callback successCallback, final Callback errorCallback) {
        GeoSpark.getListenerStatus(new GeoSparkCallback() {
            @Override
            public void onSuccess(GeoSparkUser geoSparkUser) {
                successCallback.invoke(RNGeoSparkUtils.mapForUser(geoSparkUser));
            }

            @Override
            public void onFailure(GeoSparkError geoSparkError) {
                errorCallback.invoke(RNGeoSparkUtils.mapForError(geoSparkError));
            }
        });
    }

    @ReactMethod
    public static void subscribeEvents() {
        GeoSpark.subscribeEvents();
    }

    @ReactMethod
    public static void unSubscribeEvents() {
        GeoSpark.unSubscribeEvents();
    }

    @ReactMethod
    public static void subscribeLocation() {
        GeoSpark.subscribeLocation();
    }

    @ReactMethod
    public static void unSubscribeLocation() {
        GeoSpark.unSubscribeLocation();
    }

    @ReactMethod
    public static void subscribeUserLocation(String userId) {
        GeoSpark.subscribeUserLocation(userId);
    }

    @ReactMethod
    public static void unSubscribeUserLocation(String userId) {
        GeoSpark.unSubscribeUserLocation(userId);
    }

    @ReactMethod
    public static void subscribeTripStatus(String tripId) {
        GeoSpark.subscribeTripStatus(tripId);
    }

    @ReactMethod
    public static void unSubscribeTripStatus(String tripId) {
        GeoSpark.unSubscribeTripStatus(tripId);
    }

    @ReactMethod
    public void disableBatteryOptimization() {
        GeoSpark.disableBatteryOptimization();
    }

    @ReactMethod
    public void isBatteryOptimizationEnabled(Callback callback) {
        callback.invoke(RNGeoSparkUtils.checkEnabled(GeoSpark.isBatteryOptimizationEnabled()));
    }

    @ReactMethod
    public void checkLocationPermission(Callback callback) {
        callback.invoke(RNGeoSparkUtils.isGranted(GeoSpark.checkLocationPermission()));
    }

    @ReactMethod
    public void checkLocationServices(Callback callback) {
        callback.invoke(RNGeoSparkUtils.checkEnabled(GeoSpark.checkLocationServices()));
    }

    @ReactMethod
    public void checkBackgroundLocationPermission(Callback callback) {
        callback.invoke(RNGeoSparkUtils.isGranted(GeoSpark.checkBackgroundLocationPermission()));
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
            GeoSpark.requestLocationServices(activity);
        }
    }

    @ReactMethod
    public void requestBackgroundLocationPermission() {
        Activity activity = getCurrentActivity();
        if (activity != null) {
            GeoSpark.requestBackgroundLocationPermission(activity);
        }
    }

    @ReactMethod
    public void startTracking(String trackingMode) {
        switch (trackingMode) {
            case "ACTIVE":
                GeoSpark.startTracking(GeoSparkTrackingMode.ACTIVE);
                break;
            case "REACTIVE":
                GeoSpark.startTracking(GeoSparkTrackingMode.REACTIVE);
                break;
            case "PASSIVE":
                GeoSpark.startTracking(GeoSparkTrackingMode.PASSIVE);
                break;
        }
    }

    @ReactMethod
    public void startTrackingTimeInterval(int timeInterval, String desiredAccuracy) {
        GeoSparkTrackingMode.Builder builder = new GeoSparkTrackingMode.Builder(timeInterval);
        switch (desiredAccuracy) {
            case "MEDIUM":
                builder.setDesiredAccuracy(GeoSparkTrackingMode.DesiredAccuracy.MEDIUM);
                break;
            case "LOW":
                builder.setDesiredAccuracy(GeoSparkTrackingMode.DesiredAccuracy.LOW);
                break;
            default:
                builder.setDesiredAccuracy(GeoSparkTrackingMode.DesiredAccuracy.HIGH);
                break;
        }
        GeoSpark.startTracking(builder.build());
    }

    @ReactMethod
    public void startTrackingDistanceInterval(int distance, int stationary, String desiredAccuracy) {
        GeoSparkTrackingMode.Builder builder = new GeoSparkTrackingMode.Builder(distance, stationary);
        switch (desiredAccuracy) {
            case "MEDIUM":
                builder.setDesiredAccuracy(GeoSparkTrackingMode.DesiredAccuracy.MEDIUM);
                break;
            case "LOW":
                builder.setDesiredAccuracy(GeoSparkTrackingMode.DesiredAccuracy.LOW);
                break;
            default:
                builder.setDesiredAccuracy(GeoSparkTrackingMode.DesiredAccuracy.HIGH);
                break;
        }
        GeoSpark.startTracking(builder.build());
    }

    @ReactMethod
    public void stopTracking() {
        GeoSpark.stopTracking();
    }

    @ReactMethod
    public void isLocationTracking(Callback callback) {
        callback.invoke(RNGeoSparkUtils.checkEnabled(GeoSpark.isLocationTracking()));
    }

    @ReactMethod
    public static void allowMockLocation(boolean value) {
        GeoSpark.allowMockLocation(value);
    }

    @ReactMethod
    public void getCurrentLocation(String desired_Accuracy, int accuracy, final Callback successCallback, final Callback errorCallback) {
        GeoSparkTrackingMode.DesiredAccuracy desiredAccuracy = null;
        switch (desired_Accuracy) {
            case "MEDIUM":
                desiredAccuracy = GeoSparkTrackingMode.DesiredAccuracy.MEDIUM;
                break;
            case "LOW":
                desiredAccuracy = GeoSparkTrackingMode.DesiredAccuracy.LOW;
                break;
            default:
                desiredAccuracy = GeoSparkTrackingMode.DesiredAccuracy.HIGH;
                break;
        }
        GeoSpark.getCurrentLocation(desiredAccuracy, accuracy, new GeoSparkLocationCallback() {
            @Override
            public void location(Location location) {
                WritableMap map = Arguments.createMap();
                map.putDouble("latitude", location.getLatitude());
                map.putDouble("longitude", location.getLongitude());
                map.putDouble("accuracy", location.getAccuracy());
                successCallback.invoke(map);
            }

            @Override
            public void onFailure(GeoSparkError geoSparkError) {
                errorCallback.invoke(RNGeoSparkUtils.mapForError(geoSparkError));
            }
        });
    }

    @ReactMethod
    public void updateCurrentLocation(String desiredAccuracy, int accuracy) {
        switch (desiredAccuracy) {
            case "MEDIUM":
                GeoSpark.updateCurrentLocation(GeoSparkTrackingMode.DesiredAccuracy.MEDIUM, accuracy);
                break;
            case "LOW":
                GeoSpark.updateCurrentLocation(GeoSparkTrackingMode.DesiredAccuracy.LOW, accuracy);
                break;
            default:
                GeoSpark.updateCurrentLocation(GeoSparkTrackingMode.DesiredAccuracy.HIGH, accuracy);
                break;
        }

    }

    @ReactMethod
    public void startTrip(String tripId, String description, final Callback successCallback, final Callback errorCallback) {
        GeoSpark.startTrip(tripId, description, new GeoSparkTripCallback() {
            @Override
            public void onSuccess(String msg) {
                WritableMap map = Arguments.createMap();
                map.putString("message", msg);
                successCallback.invoke(map);
            }

            @Override
            public void onFailure(GeoSparkError geoSparkError) {
                errorCallback.invoke(RNGeoSparkUtils.mapForError(geoSparkError));
            }
        });
    }

    @ReactMethod
    public void resumeTrip(String tripId, final Callback successCallback, final Callback errorCallback) {
        GeoSpark.resumeTrip(tripId, new GeoSparkTripCallback() {
            @Override
            public void onSuccess(String msg) {
                WritableMap map = Arguments.createMap();
                map.putString("message", msg);
                successCallback.invoke(map);
            }

            @Override
            public void onFailure(GeoSparkError geoSparkError) {
                errorCallback.invoke(RNGeoSparkUtils.mapForError(geoSparkError));
            }
        });
    }

    @ReactMethod
    public void pauseTrip(String tripId, final Callback successCallback, final Callback errorCallback) {
        GeoSpark.pauseTrip(tripId, new GeoSparkTripCallback() {
            @Override
            public void onSuccess(String msg) {
                WritableMap map = Arguments.createMap();
                map.putString("message", msg);
                successCallback.invoke(map);
            }

            @Override
            public void onFailure(GeoSparkError geoSparkError) {
                errorCallback.invoke(RNGeoSparkUtils.mapForError(geoSparkError));
            }
        });
    }

    @ReactMethod
    public void stopTrip(String tripId, final Callback successCallback, final Callback errorCallback) {
        GeoSpark.stopTrip(tripId, new GeoSparkTripCallback() {
            @Override
            public void onSuccess(String msg) {
                WritableMap map = Arguments.createMap();
                map.putString("message", msg);
                successCallback.invoke(map);
            }

            @Override
            public void onFailure(GeoSparkError geoSparkError) {
                errorCallback.invoke(RNGeoSparkUtils.mapForError(geoSparkError));
            }
        });
    }

    @ReactMethod
    public void forceStopTrip(String tripId, final Callback successCallback, final Callback errorCallback) {
        GeoSpark.forceStopTrip(tripId, new GeoSparkTripCallback() {
            @Override
            public void onSuccess(String msg) {
                WritableMap map = Arguments.createMap();
                map.putString("message", msg);
                successCallback.invoke(map);
            }

            @Override
            public void onFailure(GeoSparkError geoSparkError) {
                errorCallback.invoke(RNGeoSparkUtils.mapForError(geoSparkError));
            }
        });
    }

    @ReactMethod
    public static void createTrip(boolean offline, final Callback successCallback, final Callback errorCallback) {
        GeoSpark.createTrip(null, null, offline, new GeoSparkCreateTripCallback() {
            @Override
            public void onSuccess(GeoSparkCreateTrip geoSparkCreateTrip) {
                successCallback.invoke(RNGeoSparkUtils.mapForCreateTrip(geoSparkCreateTrip));
            }

            @Override
            public void onFailure(GeoSparkError geoSparkError) {
                errorCallback.invoke(RNGeoSparkUtils.mapForError(geoSparkError));
            }
        });
    }

    @ReactMethod
    public static void deleteTrip(String tripId, final Callback successCallback, final Callback errorCallback) {
        GeoSpark.deleteTrip(tripId, new GeoSparkDeleteTripCallback() {
            @Override
            public void onSuccess(String msg) {
                WritableMap map = Arguments.createMap();
                map.putString("message", msg);
                successCallback.invoke(map);
            }

            @Override
            public void onFailure(GeoSparkError geoSparkError) {
                errorCallback.invoke(RNGeoSparkUtils.mapForError(geoSparkError));
            }
        });
    }

    @ReactMethod
    public static void syncTrip(String tripId, final Callback successCallback, final Callback errorCallback) {
        GeoSpark.syncTrip(tripId, new GeoSparkSyncTripCallback() {
            @Override
            public void onSuccess(String msg) {
                WritableMap map = Arguments.createMap();
                map.putString("message", msg);
                successCallback.invoke(map);
            }

            @Override
            public void onFailure(GeoSparkError geoSparkError) {
                errorCallback.invoke(RNGeoSparkUtils.mapForError(geoSparkError));
            }
        });
    }

    @ReactMethod
    public void activeTrips(boolean value, final Callback successCallback, final Callback errorCallback) {
        GeoSpark.activeTrips(value, new GeoSparkActiveTripsCallback() {
            @Override
            public void onSuccess(GeoSparkTrip geoSparkTrip) {
                WritableMap map = Arguments.createMap();
                map.putMap("activeTrips", RNGeoSparkUtils.mapForTripList(geoSparkTrip.getActiveTrips()));
                successCallback.invoke(map);
            }

            @Override
            public void onFailure(GeoSparkError geoSparkError) {
                errorCallback.invoke(RNGeoSparkUtils.mapForError(geoSparkError));
            }
        });
    }

    @ReactMethod
    public void logout(final Callback successCallback, final Callback errorCallback) {
        GeoSpark.logout(new GeoSparkLogoutCallback() {
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
    public void setTrackingInAppState(String appState) {
        try {
            if (appState != null) {
                switch (appState) {
                    case "ALWAYS_ON":
                        GeoSpark.setTrackingInAppState(GeoSparkTrackingMode.AppState.ALWAYS_ON);
                        break;
                    case "FOREGROUND":
                        GeoSpark.setTrackingInAppState(GeoSparkTrackingMode.AppState.FOREGROUND);
                        break;
                    case "BACKGROUND":
                        GeoSpark.setTrackingInAppState(GeoSparkTrackingMode.AppState.BACKGROUND);
                        break;
                }
            }
        } catch (Exception e) {
        }
    }

    @ReactMethod
    public static void offlineLocationTracking(boolean value) {
        GeoSpark.offlineLocationTracking(value);
    }

    @ReactMethod
    public static void locationPublisher(boolean value) {
        GeoSpark.locationPublisher(value);
    }

    @ReactMethod
    public void startSelfTracking(String trackingMode) {
        switch (trackingMode) {
            case "ACTIVE":
                GeoSpark.startSelfTracking(GeoSparkTrackingMode.ACTIVE);
                break;
            case "REACTIVE":
                GeoSpark.startSelfTracking(GeoSparkTrackingMode.REACTIVE);
                break;
            case "PASSIVE":
                GeoSpark.startSelfTracking(GeoSparkTrackingMode.PASSIVE);
                break;
        }
    }

    @ReactMethod
    public void startSelfTrackingTimeInterval(int timeInterval, String desiredAccuracy) {
        GeoSparkTrackingMode.Builder builder = new GeoSparkTrackingMode.Builder(timeInterval);
        switch (desiredAccuracy) {
            case "MEDIUM":
                builder.setDesiredAccuracy(GeoSparkTrackingMode.DesiredAccuracy.MEDIUM);
                break;
            case "LOW":
                builder.setDesiredAccuracy(GeoSparkTrackingMode.DesiredAccuracy.LOW);
                break;
            default:
                builder.setDesiredAccuracy(GeoSparkTrackingMode.DesiredAccuracy.HIGH);
                break;
        }
        GeoSpark.startSelfTracking(builder.build());
    }

    @ReactMethod
    public void startSelfTrackingDistanceInterval(int distance, int stationary, String desiredAccuracy) {
        GeoSparkTrackingMode.Builder builder = new GeoSparkTrackingMode.Builder(distance, stationary);
        switch (desiredAccuracy) {
            case "MEDIUM":
                builder.setDesiredAccuracy(GeoSparkTrackingMode.DesiredAccuracy.MEDIUM);
                break;
            case "LOW":
                builder.setDesiredAccuracy(GeoSparkTrackingMode.DesiredAccuracy.LOW);
                break;
            default:
                builder.setDesiredAccuracy(GeoSparkTrackingMode.DesiredAccuracy.HIGH);
                break;
        }
        GeoSpark.startSelfTracking(builder.build());
    }

    @ReactMethod
    public static void stopSelfTracking() {
        GeoSpark.stopSelfTracking();
    }
}

