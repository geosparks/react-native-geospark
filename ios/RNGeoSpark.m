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
  [GeoSpark createUser:userDescription :^(GeoSparkUser * user) {
    NSMutableArray *dict = [[NSMutableArray alloc] initWithObjects:user.userId, nil];
    successCallback(dict);
  } onFailure:^(GeoSparkError * error) {
    errorCallback([self error:error]);
  }];
}

RCT_EXPORT_METHOD(getUser:(NSString *)userId :(RCTResponseSenderBlock)successCallback rejecter:(RCTResponseErrorBlock)errorCallback){
  [GeoSpark getUser:userId :^(GeoSparkUser * user) {
    NSMutableArray *dict = [[NSMutableArray alloc] initWithObjects:user.userId, nil];
    successCallback(dict);
  } onFailure:^(GeoSparkError * error) {
    errorCallback([self error:error]);
  }];
}

RCT_EXPORT_METHOD(setDescription:(NSString *)userDescription :(RCTResponseSenderBlock)successCallback rejecter:(RCTResponseErrorBlock)errorCallback){
  [GeoSpark setDescription:userDescription :^(GeoSparkUser * user) {
    NSMutableArray *dict = [[NSMutableArray alloc] initWithObjects:user.userId, nil];
    successCallback(dict);
  } onFailure:^(GeoSparkError * error) {
    errorCallback([self error:error]);
  }];
}

RCT_EXPORT_METHOD(requestMotionPermission){
  [GeoSpark requestMotion];
}

RCT_EXPORT_METHOD(requestLocationPermission){
  [GeoSpark requestLocation];
}

RCT_EXPORT_METHOD(checkLocationPermission :(RCTPromiseResolveBlock)callback){
  NSString *str = [self checkPermission:[GeoSpark isLocationEnabled]];
  callback(str);
}

RCT_EXPORT_METHOD(checkMotionPermission :(RCTPromiseResolveBlock)callback){
  NSString *str = [self checkPermission:[GeoSpark isMotionEnabled]];
  callback(str);
}


RCT_EXPORT_METHOD(startTrip:(NSString *)tripDescription :(RCTResponseSenderBlock)successCallback rejecter:(RCTResponseErrorBlock)errorCallback){
    dispatch_async(dispatch_get_main_queue(), ^{
        [GeoSpark startTrip:tripDescription :^(GeoSparkTrip * trip) {
            NSMutableArray *dict = [[NSMutableArray alloc] initWithObjects:trip.tripId, nil];
            successCallback(dict);
        } onFailure:^(GeoSparkError * error) {
            errorCallback([self error:error]);
        }];
    });
}
RCT_EXPORT_METHOD(endTrip:(NSString *)tripId :(RCTResponseSenderBlock)successCallback rejecter:(RCTResponseErrorBlock)errorCallback){
  [GeoSpark endTrip:tripId :^(GeoSparkTrip * trip) {
    NSMutableArray *dict = [[NSMutableArray alloc] initWithObjects:trip.tripId, nil];
    successCallback(dict);
  } onFailure:^(GeoSparkError * error) {
    errorCallback([self error:error]);
  }];
}

RCT_EXPORT_METHOD(activeTrips :(RCTResponseSenderBlock)successCallback rejecter:(RCTResponseErrorBlock)errorCallback){
  [GeoSpark activeTrips:^(GeoSparkTrips * trips) {
      NSMutableArray *dict = [[NSMutableArray alloc] init];
      for (int i = 0; i < [trips.data count]; i++)
      {
          [dict addObject:[trips.data objectAtIndex:i].tripId];
      }
    successCallback(dict);
  } onFailure:^(GeoSparkError * error) {
    errorCallback([self error:error]);
  }];
}

RCT_EXPORT_METHOD(createGeofence:(double)latitude :(double)longitude :(NSInteger)radius :(NSInteger)expiryTime :(RCTResponseSenderBlock)successCallback rejecter:(RCTResponseErrorBlock)errorCallback){
  [GeoSpark createGeofenceWithLatitude:latitude longitude:longitude expiry:expiryTime radius:radius :^(GeoSparkGeofence * geofence) {
    NSMutableArray *dict = [[NSMutableArray alloc] initWithObjects:geofence.geofence_id, nil];
    successCallback(dict);
  } onFailure:^(GeoSparkError * error) {
    errorCallback([self error:error]);
  }];
}

RCT_EXPORT_METHOD(deleteGeofence:(NSString *)geofenceId :(RCTResponseSenderBlock)successCallback rejecter:(RCTResponseErrorBlock)errorCallback){
  [GeoSpark deleteGeofence:geofenceId :^(NSString * geofence) {
    NSMutableArray *dict = [[NSMutableArray alloc] initWithObjects:geofence, nil];
    successCallback(dict);
  } onFailure:^(GeoSparkError * error) {
    errorCallback([self error:error]);
  }];
}

RCT_EXPORT_METHOD(geofenceList:(RCTResponseSenderBlock)successCallback rejecter:(RCTResponseErrorBlock)errorCallback){
  [GeoSpark geofenceList:^(GeoSparkGeofenceList * list) {
      NSMutableArray *dict = [[NSMutableArray alloc] init];
      for (int i = 0; i < [list.data count]; i++)
      {
          [dict addObject:[list.data objectAtIndex:i].geofenceId];
      }
    successCallback(dict);
  } onFailure:^(GeoSparkError * error) {
    errorCallback([self error:error]);
  }];
}

RCT_EXPORT_METHOD(startTracking){
  [GeoSpark startTracking];
}

RCT_EXPORT_METHOD(stopTracking){
  [GeoSpark stopTracking];
}

RCT_EXPORT_METHOD(logout:(RCTResponseSenderBlock)successCallback rejecter:(RCTResponseErrorBlock)errorCallback){
  [GeoSpark logout:^(NSString * userId) {
    NSMutableArray *dict = [[NSMutableArray alloc] initWithObjects:userId, nil];
    successCallback(dict);
  } onFailure:^(GeoSparkError * error) {
    errorCallback([self error:error]);
  }];
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

@end
