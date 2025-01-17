/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.car.app.hardware.common;

import static com.google.common.truth.Truth.assertThat;

import com.google.common.collect.ImmutableList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.internal.DoNotInstrument;

import java.util.List;

@RunWith(RobolectricTestRunner.class)
@DoNotInstrument
public class PropertyResponseCacheTest {
    @Mock
    private OnCarPropertyResponseListener mOnCarPropertyResponseListener;

    @Test
    public void getResponsesByListener_returnsUnknownResponseIfNotInCache() {
        Integer testPropertyId = 1;
        PropertyResponseCache propertyResponseCache = new PropertyResponseCache();
        propertyResponseCache.putListenerAndPropertyIds(mOnCarPropertyResponseListener,
                ImmutableList.of(testPropertyId));
        List<CarPropertyResponse<?>> carPropertyResponses =
                propertyResponseCache.getResponsesByListener(mOnCarPropertyResponseListener);
        assertThat(carPropertyResponses).containsExactly(
                CarPropertyResponse.builder().setPropertyId(testPropertyId).setStatus(
                        CarValue.STATUS_UNKNOWN).build());
    }
}
