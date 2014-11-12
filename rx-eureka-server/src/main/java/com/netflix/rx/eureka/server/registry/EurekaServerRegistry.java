/*
 * Copyright 2014 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netflix.rx.eureka.server.registry;

import java.util.Set;

import com.netflix.rx.eureka.interests.ChangeNotification;
import com.netflix.rx.eureka.interests.Interest;
import com.netflix.rx.eureka.registry.Delta;
import com.netflix.rx.eureka.registry.EurekaRegistry;
import com.netflix.rx.eureka.server.registry.EurekaServerRegistry.Status;
import rx.Observable;

/**
 * @author Tomasz Bak
 */
public interface EurekaServerRegistry<T> extends EurekaRegistry<T, Status> {

    enum Status {
        AddedFirst,
        AddedChange,
        AddExpired,
        RemovedFragment,
        RemovedLast,
        RemoveExpired
    }

    Observable<Status> register(T instanceInfo, Source source);

    Observable<Status> unregister(T instanceInfo, Source source);

    Observable<Status> update(T updatedInfo, Set<Delta<?>> deltas, Source source);

    Observable<ChangeNotification<T>> forInterest(Interest<T> interest, Source source);

    int size();
}
