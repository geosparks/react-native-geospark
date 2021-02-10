
import {NativeEventEmitter,NativeModules} from 'react-native';

if (!NativeModules.RNGeoSpark) {
  throw new Error('NativeModules.RNGeoSpark is undefined');
}

const eventEmitter = new NativeEventEmitter(NativeModules.RNGeoSpark);

const PublishOnly = {
  USER_ID : 'USER_ID',
  APP_ID : 'APP_ID',
  RECORDERD_AT :'APP_ID',
  TZ_OFFSET :'TZ_OFFSET',
  GEOFENCE_EVENTS :'GEOFENCE_EVENTS',
  TRIPS_EVENTS :'TRIPS_EVENTS',
  LOCATION_EVENTS :'LOCATION_EVENTS',
  NEARBY_EVENTS :'NEARBY_EVENTS',
  LOCATION_LISTENER :'LOCATION_LISTENER',
  EVENT_LISTENER :'EVENT_LISTENER',
  APP_CONTEXT :'APP_CONTEXT',
  NETWORK_STATUS :'NETWORK_STATUS',
  LOCATIONPERMISSION :'LOCATIONPERMISSION',
  BATTERY_STATUS :'BATTERY_STATUS',
  AIRPLANE_MODE :'AIRPLANE_MODE',
  BATTERY_SAVER :'BATTERY_SAVER',
  BATTERY_REMAINING :'BATTERY_REMAINING',
  DEVICE_MODEL :'DEVICE_MODEL',
  DEVICE_MANUFACTURE :'DEVICE_MANUFACTURE',
  OS_VERSION :'OS_VERSION',
  TRACKING_MODE :'TRACKING_MODE',
  ALTITUDE :'ALTITUDE',
  VERTICAL_ACCURACY :'VERTICAL_ACCURACY',
  HORIZONTAL_ACCURACY :'HORIZONTAL_ACCURACY',
  COURSE :'COURSE',
  ACTIVITY :'ACTIVITY'
 }

const TrackingMode = {
 ACTIVE : 'ACTIVE',
 BALANCED : 'BALANCED',
 PASSIVE :'PASSIVE',
}

const DesiredAccuracy = {
 HIGH : 'HIGH',
 MEDIUM : 'MEDIUM',
 LOW :'LOW',
}

const AppState = {
 ALWAYS_ON : 'ALWAYS_ON',
 FOREGROUND : 'FOREGROUND',
 BACKGROUND :'BACKGROUND',
}

const DesiredAccuracyIOS = {
  BESTFORNAVIGATION:'BESTFORNAVIGATION',
  BEST:'BEST',
  NEAREST_TEN_METERS:'NEAREST_TEN_METERS',
  HUNDRED_METERS:'HUNDRED_METERS',
  KILO_METERS:'KILO_METERS',
  THREE_KILOMETERS:'THREE_KILOMETERS',
}

const ActivityType = {
  OTHER:'OTHER',
  AUTO_NAVIGATION:'AUTO_NAVIGATION',
  OTHER_NAVIGATION:'OTHER_NAVIGATION',
  FITNESS:'FITNESS',
}

const Subscribe = {
 EVENTS : 'EVENTS',
 LOCATION : 'LOCATION',
 BOTH :'BOTH',
}

const createUser = (description,successCallback,errorCallback) => {
  NativeModules.RNGeoSpark.createUser(description,successCallback,errorCallback);
};

const getUser = (userid,successCallback,errorCallback) => {
  NativeModules.RNGeoSpark.getUser(userid,successCallback,errorCallback);
};

const setDescription = (description) => {
  NativeModules.RNGeoSpark.setDescription(description);
};

const toggleEvents = (geofence,trip,location,movingGeofence,successCallback,errorCallback) => {
  NativeModules.RNGeoSpark.toggleEvents(geofence,trip,location,movingGeofence,successCallback,errorCallback);
};

const toggleListener = (location,event,successCallback,errorCallback) => {
  NativeModules.RNGeoSpark.toggleListener(location,event,successCallback,errorCallback);
};

const getEventsStatus = (successCallback,errorCallback) => {
  NativeModules.RNGeoSpark.getEventsStatus(successCallback,errorCallback);
};

const getListenerStatus = (successCallback,errorCallback) => {
  NativeModules.RNGeoSpark.getListenerStatus(successCallback,errorCallback);
};

const subscribe = (type,userid) => {
  NativeModules.RNGeoSpark.subscribe(type,userid);
};

const unSubscribe = (type,userid) => {
  NativeModules.RNGeoSpark.unSubscribe(type,userid);
};

const subscribeTripStatus = (tripId) => {
  NativeModules.RNGeoSpark.subscribeTripStatus(tripId);
};

const unSubscribeTripStatus = (tripId) => {
  NativeModules.RNGeoSpark.unSubscribeTripStatus(tripId);
};

const disableBatteryOptimization = () => {
  NativeModules.RNGeoSpark.disableBatteryOptimization();
};

const isBatteryOptimizationEnabled = (callback) => {
  NativeModules.RNGeoSpark.isBatteryOptimizationEnabled(callback);
};

const checkLocationPermission = (callback) => {
  NativeModules.RNGeoSpark.checkLocationPermission(callback);
};

const checkLocationServices = (callback) => {
  NativeModules.RNGeoSpark.checkLocationServices(callback);
};

const checkBackgroundLocationPermission = (callback) => {
  NativeModules.RNGeoSpark.checkBackgroundLocationPermission(callback);
};

const locationPermissionStatus = (callback) => {
  NativeModules.RNGeoSpark.locationPermissionStatus(callback);
};

const requestLocationPermission = () => {
  NativeModules.RNGeoSpark.requestLocationPermission();
};

const requestLocationServices = () => {
  NativeModules.RNGeoSpark.requestLocationServices();
};

const requestBackgroundLocationPermission = () => {
  NativeModules.RNGeoSpark.requestBackgroundLocationPermission();
};

const createTrip = (offline,successCallback,errorCallback) => {
  NativeModules.RNGeoSpark.createTrip(offline,successCallback,errorCallback);
};

const startTrip = (tripId,description,successCallback,errorCallback) => {
  NativeModules.RNGeoSpark.startTrip(tripId,description,successCallback,errorCallback);
};

const resumeTrip = (tripId,successCallback,errorCallback) => {
  NativeModules.RNGeoSpark.resumeTrip(tripId,successCallback,errorCallback);
};

const pauseTrip = (tripId,successCallback,errorCallback) => {
  NativeModules.RNGeoSpark.pauseTrip(tripId,successCallback,errorCallback);
};

const stopTrip = (tripId,successCallback,errorCallback) => {
  NativeModules.RNGeoSpark.stopTrip(tripId,successCallback,errorCallback);
};

const forceStopTrip = (tripId,successCallback,errorCallback) => {
  NativeModules.RNGeoSpark.forceStopTrip(tripId,successCallback,errorCallback);
};

const deleteTrip = (tripId,successCallback,errorCallback) => {
  NativeModules.RNGeoSpark.deleteTrip(tripId,successCallback,errorCallback);
};

const syncTrip = (tripId,successCallback,errorCallback) => {
  NativeModules.RNGeoSpark.syncTrip(tripId,successCallback,errorCallback);
};

const activeTrips = (offline,successCallback,errorCallback) => {
  NativeModules.RNGeoSpark.activeTrips(offline,successCallback,errorCallback);
};

const startTracking = (trackingMode) => {
  NativeModules.RNGeoSpark.startTracking(trackingMode);
};

const startTrackingCustom = (allowBackground,pauseAutomatic,activityType,desiredAccuracy,showBackIndicator,distanceFilter,accuracyFilter) => {
  NativeModules.RNGeoSpark.startTrackingCustom(allowBackground,pauseAutomatic,activityType,desiredAccuracy,showBackIndicator,distanceFilter,accuracyFilter);
};

const startSelfTrackingCustom = (allowBackground,pauseAutomatic,activityType,desiredAccuracy,showBackIndicator,distanceFilter,accuracyFilter) => {
  NativeModules.RNGeoSpark.startSelfTrackingCustom(allowBackground,pauseAutomatic,activityType,desiredAccuracy,showBackIndicator,distanceFilter,accuracyFilter);
};

const startTrackingTimeInterval = (timeInterval,desiredAccuracy) => {
  NativeModules.RNGeoSpark.startTrackingTimeInterval(timeInterval,desiredAccuracy);
};

const startTrackingDistanceInterval = (distance,stationary,desiredAccuracy) => {
  NativeModules.RNGeoSpark.startTrackingDistanceInterval(distance,stationary,desiredAccuracy);
};

const stopTracking = () => {
  NativeModules.RNGeoSpark.stopTracking();
};

const isLocationTracking = (callback) => {
  NativeModules.RNGeoSpark.isLocationTracking(callback);
};

const allowMockLocation = (enabled) => {
  NativeModules.RNGeoSpark.allowMockLocation(enabled);
};

const getCurrentLocation = (desiredAccuracy,accuracy,successCallback,errorCallback) => {
  NativeModules.RNGeoSpark.getCurrentLocation(desiredAccuracy,accuracy,successCallback,errorCallback);
};

const updateCurrentLocation = (desiredAccuracy,accuracy) => {
  NativeModules.RNGeoSpark.updateCurrentLocation(desiredAccuracy,accuracy);
};

const getCurrentLocationIos = (accuracy,successCallback,errorCallback) => {
  NativeModules.RNGeoSpark.getCurrentLocationIos(accuracy,successCallback,errorCallback);
};

const updateCurrentLocationIos = (accuracy) => {
  NativeModules.RNGeoSpark.updateCurrentLocationIos(accuracy);
};

const logout = (successCallback,errorCallback) => {
  NativeModules.RNGeoSpark.logout(successCallback,errorCallback);
};

const setTrackingInAppState = (appState) =>{
  NativeModules.RNGeoSpark.setTrackingInAppState(appState);
}

const offlineLocationTracking = (enabled) => {
  NativeModules.RNGeoSpark.offlineLocationTracking(enabled);
}

const startSelfTracking = (trackingMode) => {
  NativeModules.RNGeoSpark.startSelfTracking(trackingMode);
};

const startSelfTrackingTimeInterval = (timeInterval,desiredAccuracy) => {
  NativeModules.RNGeoSpark.startSelfTrackingTimeInterval(timeInterval,desiredAccuracy);
};

const startSelfTrackingDistanceInterval = (distance,stationary,desiredAccuracy) => {
  NativeModules.RNGeoSpark.startSelfTrackingDistanceInterval(distance,stationary,desiredAccuracy);
};

const stopSelfTracking = () => {
  NativeModules.RNGeoSpark.stopSelfTracking();
};

const enableAccuracyEngine = () => {
  NativeModules.RNGeoSpark.enableAccuracyEngine();
};

const disableAccuracyEngine = () => {
  NativeModules.RNGeoSpark.disableAccuracyEngine();
};

const startListener = (event, callback) => (
  eventEmitter.addListener(event, callback)
);

const stopListener = (event, callback) => {
  if (callback) {
    eventEmitter.removeListener(event, callback);
  } else {
    eventEmitter.removeAllListeners(event);
  }
};

const GeoSpark = {
TrackingMode,
DesiredAccuracy,
AppState,
DesiredAccuracyIOS,
ActivityType,
createUser,
getUser,
setDescription,
toggleEvents,
toggleListener,
getEventsStatus,
getListenerStatus,
subscribe,
unSubscribe,
subscribeTripStatus,
unSubscribeTripStatus,
disableBatteryOptimization,
isBatteryOptimizationEnabled,
checkLocationPermission,
checkLocationServices,
checkBackgroundLocationPermission,
requestLocationPermission,
requestLocationServices,
requestBackgroundLocationPermission,
locationPermissionStatus,
createTrip,
startTrip,
resumeTrip,
pauseTrip,
stopTrip,
forceStopTrip,
deleteTrip,
syncTrip,
activeTrips,
startTracking,
startTrackingCustom,
startSelfTrackingCustom,
startTrackingTimeInterval,
startTrackingDistanceInterval,
stopTracking,
isLocationTracking,
allowMockLocation,
getCurrentLocation,
updateCurrentLocation,
getCurrentLocationIos,
updateCurrentLocationIos,
logout,
setTrackingInAppState,
offlineLocationTracking,
startSelfTracking,
startSelfTrackingTimeInterval,
startSelfTrackingDistanceInterval,
stopSelfTracking,
enableAccuracyEngine,
disableAccuracyEngine,
startListener,
stopListener,
};

export default GeoSpark;