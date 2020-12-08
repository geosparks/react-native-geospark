package com.geospark.reactnative;

import android.location.Location;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.geospark.lib.models.ActiveTrips;
import com.geospark.lib.models.GeoSparkError;
import com.geospark.lib.models.GeoSparkUser;
import com.geospark.lib.models.createtrip.Coordinates;
import com.geospark.lib.models.createtrip.Destination;
import com.geospark.lib.models.createtrip.GeoSparkCreateTrip;
import com.geospark.lib.models.createtrip.Origin;

import java.util.List;


class RNGeoSparkUtils {

    static String isGranted(boolean hasGranted) {
        if (hasGranted) {
            return "GRANTED";
        }
        return "DENIED";
    }

    static String checkEnabled(boolean hasEnabled) {
        if (hasEnabled) {
            return "ENABLED";
        }
        return "DISABLED";
    }

    static WritableMap mapForUser(GeoSparkUser geoSparkUser) {
        if (geoSparkUser == null) {
            return null;
        }
        WritableMap map = Arguments.createMap();
        map.putString("userId", geoSparkUser.getUserId());
        map.putString("description", geoSparkUser.getDescription());
        map.putBoolean("geofenceEvents", geoSparkUser.getGeofenceEvents());
        map.putBoolean("locationEvents", geoSparkUser.getLocationEvents());
        map.putBoolean("tripsEvents", geoSparkUser.getTripsEvents());
        map.putBoolean("movingGeofenceEvents", geoSparkUser.getMovingGeofenceEvents());
        map.putBoolean("eventListenerStatus", geoSparkUser.getEventListenerStatus());
        map.putBoolean("locationListenerStatus", geoSparkUser.getLocationListenerStatus());
        return map;
    }

    static WritableMap mapForCreateTrip(GeoSparkCreateTrip geoSparkCreateTrip) {
        if (geoSparkCreateTrip == null) {
            return null;
        }
        WritableMap map = Arguments.createMap();
        if (geoSparkCreateTrip.getId() != null) {
            map.putString("id", geoSparkCreateTrip.getId());
        }
        if (geoSparkCreateTrip.getUser_id() != null) {
            map.putString("userId", geoSparkCreateTrip.getUser_id());
        }
        if (geoSparkCreateTrip.getCreated_at() != null) {
            map.putString("createdAt", geoSparkCreateTrip.getCreated_at());
        }
        if (geoSparkCreateTrip.getUpdated_at() != null) {
            map.putString("updatedAt", geoSparkCreateTrip.getUpdated_at());
        }
        if (geoSparkCreateTrip.getIs_started() != null) {
            map.putBoolean("isStarted", geoSparkCreateTrip.getIs_started());
        }
        if (geoSparkCreateTrip.getIs_paused() != null) {
            map.putBoolean("isPaused", geoSparkCreateTrip.getIs_paused());
        }
        if (geoSparkCreateTrip.getIs_ended() != null) {
            map.putBoolean("isEnded", geoSparkCreateTrip.getIs_ended());
        }
        if (geoSparkCreateTrip.getIs_deleted() != null) {
            map.putBoolean("isDeleted", geoSparkCreateTrip.getIs_deleted());
        }
        if (geoSparkCreateTrip.getTrip_tracking_url() != null) {
            map.putString("tripTrackingUrl", geoSparkCreateTrip.getTrip_tracking_url());
        }
        if (geoSparkCreateTrip.getOrigins() != null && geoSparkCreateTrip.getOrigins().size() > 0) {
            WritableMap originMap = Arguments.createMap();
            for (int i = 0; i < geoSparkCreateTrip.getOrigins().size(); i++) {
                Origin origin = geoSparkCreateTrip.getOrigins().get(i);
                if (origin.getId() != null) {
                    map.putString("id", origin.getId());
                }
                if (origin.getTrip_id() != null) {
                    map.putString("tripId", origin.getTrip_id());
                }
                if (origin.getCreated_at() != null) {
                    map.putString("createdAt", origin.getCreated_at());
                }
                if (origin.getUpdated_at() != null) {
                    map.putString("updatedAt", origin.getUpdated_at());
                }
                if (origin.getLoc_type() != null) {
                    map.putString("getLocType", origin.getLoc_type());
                }
                if (origin.getReached() != null) {
                    map.putBoolean("isPaused", origin.getReached());
                }
                Coordinates coordinates = origin.getCoordinates();
                if (coordinates != null && coordinates.getCoordinates().size() > 0) {
                    map.putDouble("latitude", coordinates.getCoordinates().get(1));
                    map.putDouble("longitude", coordinates.getCoordinates().get(0));
                    if (coordinates.getType() != null) {
                        map.putString("type", coordinates.getType());
                    }
                }
            }
            map.putMap("origin", originMap);
        }
        if (geoSparkCreateTrip.getDestinations() != null && geoSparkCreateTrip.getDestinations().size() > 0) {
            WritableMap destinationMap = Arguments.createMap();
            for (int i = 0; i < geoSparkCreateTrip.getDestinations().size(); i++) {
                Destination destination = geoSparkCreateTrip.getDestinations().get(i);
                if (destination.getId() != null) {
                    map.putString("id", destination.getId());
                }
                if (destination.getTrip_id() != null) {
                    map.putString("tripId", destination.getTrip_id());
                }
                if (destination.getCreated_at() != null) {
                    map.putString("createdAt", destination.getCreated_at());
                }
                if (destination.getUpdated_at() != null) {
                    map.putString("updatedAt", destination.getUpdated_at());
                }
                if (destination.getLoc_type() != null) {
                    map.putString("getLocType", destination.getLoc_type());
                }
                if (destination.getReached() != null) {
                    map.putBoolean("isPaused", destination.getReached());
                }
                Coordinates coordinates = destination.getCoordinates();
                if (coordinates != null && coordinates.getCoordinates().size() > 0) {
                    map.putDouble("latitude", coordinates.getCoordinates().get(1));
                    map.putDouble("longitude", coordinates.getCoordinates().get(0));
                    if (coordinates.getType() != null) {
                        map.putString("type", coordinates.getType());
                    }
                }
            }
            map.putMap("destination", destinationMap);
        }
        return map;
    }

    static WritableMap mapForTripList(List<ActiveTrips> trips) {
        if (trips == null && trips.size() == 0) {
            return null;
        }
        WritableMap map = Arguments.createMap();
        for (int i = 0; i < trips.size(); i++) {
            WritableMap mapData = Arguments.createMap();
            ActiveTrips geoSparkActiveTrips = trips.get(i);
            mapData.putString("tripId", geoSparkActiveTrips.getTripId());
            mapData.putBoolean("isStarted", geoSparkActiveTrips.isStarted());
            mapData.putBoolean("isPaused", geoSparkActiveTrips.isPaused());
            mapData.putBoolean("isEnded", geoSparkActiveTrips.getEnded());
            mapData.putBoolean("isDeleted", geoSparkActiveTrips.getDeleted());
            mapData.putString("createdAt", geoSparkActiveTrips.getCreatedAt());
            mapData.putString("updatedAt", geoSparkActiveTrips.getUpdatedAt());
            mapData.putString("syncStatus", geoSparkActiveTrips.getSyncStatus());
            map.putMap(String.valueOf(i), mapData);
        }
        return map;
    }

    static WritableMap mapForLocation(Location location) {
        if (location == null) {
            return null;
        }
        WritableMap map = Arguments.createMap();
        map.putDouble("latitude", location.getLatitude());
        map.putDouble("longitude", location.getLongitude());
        map.putDouble("accuracy", location.getAccuracy());
        map.putDouble("altitude", location.getAltitude());
        map.putDouble("speed", location.getSpeed());
        return map;
    }

    static WritableMap mapForError(GeoSparkError geoSparkError) {
        WritableMap map = Arguments.createMap();
        map.putString("errorCode", geoSparkError.getCode());
        map.putString("errorMessage", geoSparkError.getMessage());
        return map;
    }
}

