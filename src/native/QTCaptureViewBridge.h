//
//  QTCaptureViewBridge.h
//  QTCubed
//
//  Created by Chappell Charles on 10/04/09.
//  Copyright 2010 MC Cubed, Inc. All rights reserved.
//

#import <QTKit/QTKit.h>
#import <JavaNativeFoundation/JavaNativeFoundation.h>
#import "net_mc_cubed_qtcubed_QTKitCaptureView.h"



@interface QTCaptureViewBridge : QTCaptureView 

	-(id)init;
	-(void)awtMessage:(jint)messageID message:(jobject)message env:(JNIEnv *)env;


@end