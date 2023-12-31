/*
 * Copyright (c) Meta Platforms, Inc. and affiliates.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

#pragma once

#include <ReactCommon/CallInvoker.h>
#include <fbjni/fbjni.h>
#include <memory>

namespace facebook::react {

class CallInvokerHolder : public jni::HybridClass<CallInvokerHolder> {
 public:
  static auto constexpr kJavaDescriptor =
      "Lcom/facebook/react/internal/turbomodule/core/CallInvokerHolderImpl;";

  static void registerNatives();
  std::shared_ptr<CallInvoker> getCallInvoker();

 private:
  friend HybridBase;
  CallInvokerHolder(std::shared_ptr<CallInvoker> callInvoker);
  std::shared_ptr<CallInvoker> _callInvoker;
};

} // namespace facebook::react
