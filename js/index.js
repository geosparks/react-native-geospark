
import {NativeEventEmitter,NativeModules} from 'react-native';

if (!NativeModules.RNGeoSpark) {
  throw new Error('NativeModules.RNGeoSpark is undefined');
}

const eventEmitter = new NativeEventEmitter(NativeModules.RNGeoSpark);

const Type = {
 ALL : 'ALL',
 STOP : 'STOP',
 WALK :'WALK',
 DRIVE : 'DRIVE',
 BICYCLE : 'BICYCLE',
 FOREGROUND : 'FOREGROUND',
 BACKGROUND : 'BACKGROUND',
 ALWAYS_ON : 'ALWAYS_ON'
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

const checkLocationPermission = (callback) => {
  NativeModules.RNGeoSpark.checkLocationPermission(callback);
};

const checkLocationServices = (callback) => {
  NativeModules.RNGeoSpark.checkLocationServices(callback);
};

const checkMotionServices = (callback) => {
  NativeModules.RNGeoSpark.checkMotionServices(callback);
};

const requestMotionPermission = () => {
  NativeModules.RNGeoSpark.requestMotionPermission();
};

const requestLocationPermission = () => {
  NativeModules.RNGeoSpark.requestLocationPermission();
};

const requestLocationServices = () => {
  NativeModules.RNGeoSpark.requestLocationServices();
};

const createUser = (description,successCallback,errorCallback) => {
  NativeModules.RNGeoSpark.createUser(description,successCallback,errorCallback);
};

const getUser = (userid,successCallback,errorCallback) => {
  NativeModules.RNGeoSpark.getUser(userid,successCallback,errorCallback);
};

const setDescription = (description,successCallback,errorCallback) => {
  NativeModules.RNGeoSpark.setDescription(description,successCallback,errorCallback);
};

const startTrip = (description,successCallback,errorCallback) => {
  NativeModules.RNGeoSpark.startTrip(description,successCallback,errorCallback);
};

const endTrip = (tripId,successCallback,errorCallback) => {
  NativeModules.RNGeoSpark.endTrip(tripId,successCallback,errorCallback);
};

const activeTrips = (successCallback,errorCallback) => {
  NativeModules.RNGeoSpark.activeTrips(successCallback,errorCallback);
};

const createGeofence = (latitude,longitude,radius,expireInSeconds,successCallback,errorCallback) => {
  NativeModules.RNGeoSpark.createGeofence(latitude,longitude,radius,expireInSeconds,successCallback,errorCallback);
};

const deleteGeofence = (geofenceId,successCallback,errorCallback) => {
  NativeModules.RNGeoSpark.deleteGeofence(geofenceId,successCallback,errorCallback);
};
 
const geofenceList = (successCallback,errorCallback) => {
  NativeModules.RNGeoSpark.geofenceList(successCallback,errorCallback);
};
   
const startTracking = () => {
  NativeModules.RNGeoSpark.startTracking();
};

const stopTracking = () => {
  NativeModules.RNGeoSpark.stopTracking();
};

const logout = (successCallback,errorCallback) => {
  NativeModules.RNGeoSpark.logout(successCallback,errorCallback);
};

const setTrackingInAppState = (types) =>{
  NativeModules.RNGeoSpark.setTrackingInAppState(types);
}

const setTrackingInMotion = (types) => {
  NativeModules.RNGeoSpark.setTrackingInMotion(types);
}

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
 Type,
 IOSType,
 checkMotionPermission,
 checkLocationPermission,
 checkLocationServices,
 requestMotionPermission
 requestLocationPermission,
 requestLocationServices,
 createUser,
 getUser,
 setDescription,
 startTrip,
 endTrip ,
 activeTrips,
 createGeofence,
 deleteGeofence,
 geofenceList,
 startTracking,
 stopTracking,
 logout,
 setTrackingInAppState,
 setTrackingInMotion,
 startListener,
 stopListener
};

export default GeoSpark;