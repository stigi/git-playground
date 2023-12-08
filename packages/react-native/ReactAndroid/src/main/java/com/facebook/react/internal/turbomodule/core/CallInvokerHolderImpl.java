/*
 * Copyright (c) Meta Platforms, Inc. and affiliates.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.facebook.react.internal.turbomodule.core;

import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.internal.turbomodule.core.interfaces.CallInvokerHolder;

/**
 * JSCallInvoker is created at a different time/place (i.e: in CatalystInstance) than
 * TurboModuleManager. Therefore, we need to wrap JSCallInvoker within a hybrid class so that we may
 * pass it from CatalystInstance, through Java, to TurboModuleManager::initHybrid.
 */
public class CallInvokerHolderImpl implements CallInvokerHolder {

  @DoNotStrip private final HybridData mHybridData;

  static {
    NativeModuleSoLoader.maybeLoadSoLibrary();
  }

  private CallInvokerHolderImpl(HybridData hd) {
    mHybridData = hd;
  }
}
