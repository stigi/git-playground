/**
 * Copyright (c) Meta Platforms, Inc. and affiliates.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 *
 * @flow
 * @format
 */

import {toCppNamespace, toCppType} from './Converters';
import {Property} from './Property';

export class Event {
  domain: string;
  name: string;
  description: ?string;
  experimental: ?boolean;
  parameters: Array<Property>;

  static create(
    domain: string,
    obj: any,
    ignoreExperimental: boolean,
    includeExperimental: Set<string>,
  ): ?Event {
    return ignoreExperimental &&
      obj.experimental &&
      !includeExperimental.has(domain + '.' + obj.name)
      ? null
      : new Event(domain, obj, ignoreExperimental, includeExperimental);
  }

  constructor(
    domain: string,
    obj: any,
    ignoreExperimental: boolean,
    includeExperimental: Set<string>,
  ) {
    this.domain = domain;
    this.name = obj.name;
    this.description = obj.description;
    this.parameters = Property.createArray(
      domain,
      obj.name,
      obj.parameters || [],
      ignoreExperimental,
      includeExperimental,
    );
  }

  getDebuggerName(): string {
    return `${this.domain}.${this.name}`;
  }

  getCppNamespace(): string {
    return toCppNamespace(this.domain);
  }

  getCppType(): string {
    return toCppType(this.name + 'Notification');
  }

  getForwardDecls(): Array<string> {
    return [`struct ${this.getCppType()};`];
  }

  getForwardDeclSortKey(): string {
    return this.getCppType();
  }
}
