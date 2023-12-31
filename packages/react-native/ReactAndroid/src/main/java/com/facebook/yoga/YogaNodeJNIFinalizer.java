/*
 * Copyright (c) Meta Platforms, Inc. and affiliates.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.facebook.yoga;

public class YogaNodeJNIFinalizer extends YogaNodeJNIBase {
  public YogaNodeJNIFinalizer() {
    super();
  }

  public YogaNodeJNIFinalizer(YogaConfig config) {
    super(config);
  }

  @Override
  protected void finalize() throws Throwable {
    try {
      freeNatives();
    } finally {
      super.finalize();
    }
   }

  public void freeNatives() {
    if (mNativePointer != 0) {
      long nativePointer = mNativePointer;
      mNativePointer = 0;
      YogaNative.jni_YGNodeFinalizeJNI(nativePointer);
    }
  }
}
