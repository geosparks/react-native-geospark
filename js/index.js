
import {NativeEventEmitter,NativeModules} from 'react-native';

if (!NativeModules.RNGeoSpark) {
  throw new Error('NativeModules.RNGeoSpark is undefined');
}

const eventEmitter = new NativeEventEmitter(NativeModules.RNGeoSpark);

const GeoSparkTrackingMode = {
 ACTIVE : 'ACTIVE',
 REACTIVE : 'REACTIVE',
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

const IOSType = {
 All: 'GSAll',
 Running: 'GSRunning',
 Walking: 'GSWalking',
 AutoMotive: 'GSAutoMotive',
 Stationary: 'GSStationary',
 Foreground: 'GSForeground',
 Terminated: 'GSTerminated',
 Background: 'GSBackground',
 AlwaysOn: 'GSAlwaysOn'
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

const subscribeEvents = () => {
  NativeModules.RNGeoSpark.subscribeEvents();
};

const unSubscribeEvents = () => {
  NativeModules.RNGeoSpark.unSubscribeEvents();
};

const subscribeLocation = () => {
  NativeModules.RNGeoSpark.subscribeLocation();
};

const unSubscribeLocation = () => {
  NativeModules.RNGeoSpark.unSubscribeLocation();
};

const subscribeUserLocation = (userId) => {
  NativeModules.RNGeoSpark.subscribeUserLocation(userId);
};

const unSubscribeUserLocation = (userId) => {
  NativeModules.RNGeoSpark.unSubscribeUserLocation(userId);
};

const subscribeTripStatus = (userId) => {
  NativeModules.RNGeoSpark.subscribeTripStatus(userId);
};

const unSubscribeTripStatus = (userId) => {
  NativeModules.RNGeoSpark.unSubscribeTripStatus(userId);
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

const startTrackingMode = (trackingMode) => {
  NativeModules.RNGeoSpark.startTracking(trackingMode);
};

const startTrackingTimeInterval = (timeInterval,desiredAccuracy) => {
  NativeModules.RNGeoSpark.startTracking(timeInterval,desiredAccuracy);
};

const startTrackingDistanceInterval = (distance,stationary,desiredAccuracy) => {
  NativeModules.RNGeoSpark.startTracking(distance,stationary,desiredAccuracy);
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

const getCurrentLocationIos = (successCallback) => {
  NativeModules.RNGeoSpark.getCurrentLocation(successCallback);
};

const updateCurrentLocationIos = () => {
  NativeModules.RNGeoSpark.updateCurrentLocation();
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

const locationPublisher = (enabled) => {
  NativeModules.RNGeoSpark.locationPublisher(enabled);
}

const startSelfTrackingMode = (trackingMode) => {
  NativeModules.RNGeoSpark.startSelfTracking(trackingMode);
};

const startSelfTrackingTimeInterval = (timeInterval,desiredAccuracy) => {
  NativeModules.RNGeoSpark.startSelfTracking(timeInterval,desiredAccuracy);
};

const startSelfTrackingDistanceInterval = (distance,stationary,desiredAccuracy) => {
  NativeModules.RNGeoSpark.startSelfTracking(distance,stationary,desiredAccuracy);
};

const stopSelfTracking = () => {
  NativeModules.RNGeoSpark.stopSelfTracking();
};

const startListener = (event, callback) => (
  eventEmitter.addListener(event, callback);
);

const stopListener = (event, callback) => {
  if (callback) {
    eventEmitter.removeListener(event, callback);
  } else {
    eventEmitter.removeAllListeners(event);
  }
};

const GeoSpark = {
GeoSparkTrackingMode,
DesiredAccuracy,
AppState,
createUser,
getUser,
setDescription,
toggleEvents,
toggleListener,
getEventsStatus,
getListenerStatus,
subscribeEvents,
unSubscribeEvents,
subscribeLocation,
unSubscribeLocation,
subscribeUserLocation,
unSubscribeUserLocation,
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
createTrip,
startTrip,
resumeTrip,
pauseTrip,
stopTrip,
forceStopTrip,
deleteTrip,
syncTrip,
activeTrips,
startTrackingMode,
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
locationPublisher,
startSelfTrackingMode,
startSelfTrackingTimeInterval,
startSelfTrackingDistanceInterval,
stopSelfTracking,
startListener,
stopListener,
};

export default GeoSpark;