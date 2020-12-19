/*
 * Copyright 2017 Ivan Velikanova ivan@jphil.xyz.
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
package xyz.jphil.patterns.lambda;

/**
 * Represents a supplier of results, allows Exception.
 *
 * <p>There is no requirement that a new or distinct result be returned each
 * time the supplier is invoked.
 *
 *
 * @param <T> the type of results supplied by this supplier
 *
 * @author Ivan Velikanova ivan@jphil.xyz
 */
@FunctionalInterface
public interface SupplierWithException<T> {
    public T get()throws Exception;    
}
