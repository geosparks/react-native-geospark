//
//  RNGeoSpark.m
//  RNGeoSpark
//
//  Copyright © 2018 GeoSpark, Inc. All rights reserved.
//

#import "RNGeoSpark.h"
#import <GeoSpark/GeoSpark.h>

@implementation RNGeoSpark{
    BOOL hasListeners;
}
RCT_EXTERN_METHOD(supportedEvents)
RCT_EXPORT_MODULE();


- (instancetype)init {
  self = [super init];
  if (self) {
    
  }
  return self;
}

- (NSArray<NSString *> *)supportedEvents {
  return @[@"events",@"location", @"error"];
}

- (void)startObserving {
  hasListeners = YES;
}

- (void)stopObserving {
  hasListeners = NO;
}


RCT_EXPORT_METHOD(createUser:(NSString *)userDescription :(RCTResponseSenderBlock)successCallback rejecter:(RCTResponseErrorBlock)errorCallback){
  dispatch_async(dispatch_get_main_queue(), ^{
    [GeoSpark createUser:userDescription :^(GeoSparkUser * user) {
      NSMutableDictionary *dict = [[NSMutableDictionary alloc] init];
      [dict setValue:user.userId  forKey:@"userId"];
      NSMutableArray *array = [[NSMutableArray alloc] initWithObjects:dict, nil];
      successCallback(array);
    } onFailure:^(GeoSparkError * error) {
      errorCallback([self error:error]);
    }];
  });
}

RCT_EXPORT_METHOD(getUser:(NSString *)userId :(RCTResponseSenderBlock)successCallback rejecter:(RCTResponseErrorBlock)errorCallback){
  dispatch_async(dispatch_get_main_queue(), ^{
    [GeoSpark getUser:userId :^(GeoSparkUser * user) {
      NSMutableDictionary *dict = [[NSMutableDictionary alloc] init];
      [dict setValue:user.userId  forKey:@"userId"];
      NSMutableArray *array = [[NSMutableArray alloc] initWithObjects:dict, nil];
      successCallback(array);
    } onFailure:^(GeoSparkError * error) {
      errorCallback([self error:error]);
    }];
  });
}

RCT_EXPORT_METHOD(setDescription:(NSString *)userDescription :(RCTResponseSenderBlock)successCallback rejecter:(RCTResponseErrorBlock)errorCallback){
  dispatch_async(dispatch_get_main_queue(), ^{
    [GeoSpark setDescription:userDescription :^(GeoSparkUser * user) {
      NSMutableDictionary *dict = [[NSMutableDictionary alloc] init];
      [dict setValue:user.userId  forKey:@"userId"];
      NSMutableArray *array = [[NSMutableArray alloc] initWithObjects:dict, nil];
      successCallback(array);
    } onFailure:^(GeoSparkError * error) {
      errorCallback([self error:error]);
    }];
  });
}

RCT_EXPORT_METHOD(requestMotionPermission){
  [GeoSpark requestMotion];
}

RCT_EXPORT_METHOD(requestLocationPermission){
  [GeoSpark requestLocation];
}

RCT_EXPORT_METHOD(isLocationTracking){
  [GeoSpark isLocationTracking];
}

RCT_EXPORT_METHOD(checkLocationPermission :(RCTResponseSenderBlock)callback){
  NSString *str = [self checkPermission:[GeoSpark isLocationEnabled]];
  NSMutableArray *dict = [[NSMutableArray alloc] initWithObjects:str, nil];
  callback(dict);
}

RCT_EXPORT_METHOD(checkMotionPermission :(RCTResponseSenderBlock)callback){
  NSString *str = [self checkPermission:[GeoSpark isMotionEnabled]];
  NSMutableArray *dict = [[NSMutableArray alloc] initWithObjects:str, nil];
  callback(dict);
}


RCT_EXPORT_METHOD(tripId:(NSString *)tripId startTrip:(NSString *)tripDescription :(RCTResponseSenderBlock)successCallback rejecter:(RCTResponseErrorBlock)errorCallback){
    dispatch_async(dispatch_get_main_queue(), ^{
        [GeoSpark startTrip: tripId :tripDescription  :^(GeoSparkTrip * trip) {
          NSMutableDictionary *dict = [[NSMutableDictionary alloc] init];
          [dict setValue:trip.msg  forKey:@"msg"];
          NSMutableArray *array = [[NSMutableArray alloc] initWithObjects:dict, nil];
          successCallback(array);
        } onFailure:^(GeoSparkError * error) {
            errorCallback([self error:error]);
        }];
    });
}
RCT_EXPORT_METHOD(endTrip:(NSString *)tripId :(RCTResponseSenderBlock)successCallback rejecter:(RCTResponseErrorBlock)errorCallback){
  dispatch_async(dispatch_get_main_queue(), ^{
    [GeoSpark endTrip:tripId :^(GeoSparkTrip * trip) {
      NSMutableDictionary *dict = [[NSMutableDictionary alloc] init];
      [dict setValue:trip.msg  forKey:@"msg"];
      NSMutableArray *array = [[NSMutableArray alloc] initWithObjects:dict, nil];
      successCallback(array);
    } onFailure:^(GeoSparkError * error) {
      errorCallback([self error:error]);
    }];
  });
}

RCT_EXPORT_METHOD(activeTrips :(RCTResponseSenderBlock)successCallback rejecter:(RCTResponseErrorBlock)errorCallback){
  [GeoSpark activeTrip:^(ActiveTripsV2Response * response) {
    NSMutableDictionary *tripsData = [[NSMutableDictionary alloc] init];
    NSMutableDictionary *trip = [[NSMutableDictionary alloc] init];

    for (int i = 0; i < [response.trips count]; i++) {
      ActiveTripsV2ResponseData  *tripValue = [response.trips objectAtIndex:i];
      [trip setValue:tripValue.trip_id forKey:@"tripId"];
      [trip setValue:[NSString stringWithFormat:@"%c", tripValue.isStarted] forKey:@"isStarted"];
      [trip setValue:[NSString stringWithFormat:@"%c", tripValue.isEnded] forKey:@"isEnded"];
      [trip setValue:[NSString stringWithFormat:@"%c", tripValue.isDeleted] forKey:@"isDeleted"];
      [trip setValue:tripValue.createdAt forKey:@"createdAt"];
      [trip setValue:tripValue.updatedAt forKey:@"updatedAt"];
    }
    [tripsData setValue:trip forKey:@"activeTrips"];
    NSArray *outArray = [[NSArray alloc] initWithObjects:tripsData, nil];
    successCallback(outArray);
  } onFailure:^(GeoSparkError * error) {
       errorCallback([self error:error]);
  }];
}

RCT_EXPORT_METHOD(createGeofence:(double)latitude :(double)longitude :(NSInteger)radius :(NSInteger)expiryTime :(RCTResponseSenderBlock)successCallback rejecter:(RCTResponseErrorBlock)errorCallback){
  dispatch_async(dispatch_get_main_queue(), ^{
    [GeoSpark createGeofenceWithLatitude:latitude longitude:longitude expiry:expiryTime radius:radius :^(GeoSparkGeofence * geofence) {
      NSMutableDictionary *dict = [[NSMutableDictionary alloc] init];
      [dict setValue:geofence.geofence_id  forKey:@"geofenceId"];
      [dict setValue:geofence.create_at  forKey:@"createdAt"];
      [dict setValue:geofence.expire_at  forKey:@"expireAt"];
      [dict setValue:geofence.coordinate.firstObject  forKey:@"latitude"];
      [dict setValue:geofence.coordinate.lastObject  forKey:@"longitude"];
      NSMutableArray *array = [[NSMutableArray alloc] initWithObjects:dict, nil];
      successCallback(array);
    } onFailure:^(GeoSparkError * error) {
      errorCallback([self error:error]);
    }];
  });
}

RCT_EXPORT_METHOD(deleteGeofence:(NSString *)geofenceId :(RCTResponseSenderBlock)successCallback rejecter:(RCTResponseErrorBlock)errorCallback){
  dispatch_async(dispatch_get_main_queue(), ^{
    [GeoSpark deleteGeofence:geofenceId :^(NSString * geofence) {
      NSMutableDictionary *dict = [[NSMutableDictionary alloc] init];
      [dict setValue:geofence  forKey:@"message"];
      NSMutableArray *array = [[NSMutableArray alloc] initWithObjects:dict, nil];
      successCallback(array);
    } onFailure:^(GeoSparkError * error) {
      errorCallback([self error:error]);
    }];
  });
}

RCT_EXPORT_METHOD(geofenceList:(RCTResponseSenderBlock)successCallback rejecter:(RCTResponseErrorBlock)errorCallback){
  dispatch_async(dispatch_get_main_queue(), ^{
    [GeoSpark geofenceList:^(GeoSparkGeofenceList * list) {
      NSMutableDictionary *dict = [[NSMutableDictionary alloc] init];
      NSMutableDictionary *dict1 = [[NSMutableDictionary alloc] init];
      for (int i = 0; i < [list.data count]; i++)
      {
        GeoSparkGeofenceListData *listData = [list.data objectAtIndex:i];
        NSMutableDictionary *dict2 = [[NSMutableDictionary alloc] init];
        [dict2 setValue:listData.geofenceId forKey:@"geofenceId"];
        [dict2 setValue:listData.expiresAt forKey:@"expiresAt"];
        [dict2 setValue:listData.createdAt forKey:@"createdAt"];
        [dict2 setValue:[NSNumber numberWithInteger:listData.geometryRadius] forKey:@"geometryRadius"];
        [dict2 setValue:listData.coordinates.firstObject forKey:@"latitude"];
        [dict2 setValue:listData.coordinates.lastObject forKey:@"longitude"];
        [dict setValue:dict2 forKey:[@(i) stringValue]];
      }
      [dict1 setValue:dict forKey:@"activeTrips"];
      NSArray *outArray = [[NSArray alloc] initWithObjects:dict1, nil];
      successCallback(outArray);
    } onFailure:^(GeoSparkError * error) {
      errorCallback([self error:error]);
    }];
  });
}

RCT_EXPORT_METHOD(startTracking){
  [GeoSpark startTracking];
}

RCT_EXPORT_METHOD(stopTracking){
  [GeoSpark stopTracking];
}

RCT_EXPORT_METHOD(logout:(RCTResponseSenderBlock)successCallback rejecter:(RCTResponseErrorBlock)errorCallback){
  dispatch_async(dispatch_get_main_queue(), ^{
    [GeoSpark logout:^(NSString * userId) {
      NSMutableDictionary *dict = [[NSMutableDictionary alloc] init];
      [dict setValue:userId forKey:@"message"];
      NSMutableArray *array = [[NSMutableArray alloc] initWithObjects:dict, nil];
      successCallback(array);
    } onFailure:^(GeoSparkError * error) {
      errorCallback([self error:error]);
    }];
  });
}

RCT_EXPORT_METHOD(getCurrentLocation:(RCTResponseSenderBlock)successCallback){
  dispatch_async(dispatch_get_main_queue(), ^{
    [GeoSpark getCurrentLocationWithLocation:^(GSLocation * location) {
      NSMutableDictionary *locationDict = [[NSMutableDictionary alloc] init];
      [locationDict setValue:[NSNumber numberWithDouble:location.latitude]  forKey:@"latitude"];
      [locationDict setValue:[NSNumber numberWithDouble:location.longitude]  forKey:@"longitude"];
      [locationDict setValue:[NSNumber numberWithDouble:location.accuracy]  forKey:@"accuracy"];
      NSMutableArray *array = [[NSMutableArray alloc] initWithObjects:locationDict, nil];
      successCallback(array);
    }];
  });
}

RCT_EXPORT_METHOD(accuracy:(NSInteger *)accuracy updateCurrentLocation:(RCTResponseSenderBlock)successCallback){
  dispatch_async(dispatch_get_main_queue(), ^{
    [GeoSpark updateCurrentLocation:accuracy];
  });
}

RCT_EXPORT_METHOD(setTrackingInAppState:(NSArray *)states){
  [GeoSpark trackLocationInAppState:states];
}

RCT_EXPORT_METHOD(setTrackingInMotion:(NSArray *)motions){
  [GeoSpark trackLocationInMotion:motions];
}

-(NSError *)error:(GeoSparkError *)error{
  return [NSError errorWithDomain:error.errorMessage code:[self removeString:error.errorCode] userInfo:nil];
}

-(NSString *)checkPermission:(BOOL)isEnabled{
  if (isEnabled){
    return @"GRANTED";
  } else {
    return @"DENIED";
  }
}

-(NSInteger)removeString:(NSString *)stringValue{
  NSMutableString *strippedString = [NSMutableString stringWithCapacity:stringValue.length];
  NSScanner *scanner = [NSScanner scannerWithString:stringValue];
  NSCharacterSet *numbers = [NSCharacterSet characterSetWithCharactersInString:@"0123456789"];
  while ([scanner isAtEnd] == NO) {
    NSString *buffer;
    if ([scanner scanCharactersFromSet:numbers intoString:&buffer]) {
      [strippedString appendString:buffer];
    } else {
      [scanner setScanLocation:([scanner scanLocation] + 1)];
    }
  }
  return @([strippedString integerValue]);
}

@endx
