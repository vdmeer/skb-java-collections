/* Copyright 2014 Sven van der Meer <vdmeer.sven@mykolab.com>
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

package de.vandermeer.skb.collections;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Strategies for sets.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.4 build 150619 (19-Jun-15) for Java 1.8
 */
public enum SetStrategy implements IsSetStrategy {
	/** Default set, results in a HashSet */
	DEFAULT,

	/** HashSet from java-util. */
	HASH_SET,

	/** LinkedHashSet from java-util. */
	LINKED_HASH_SET,
	;

	@Override
	public <T> Set<T> get(Collection<T> collection) {
		switch(this){
			case LINKED_HASH_SET:
				if(collection==null){
					return new LinkedHashSet<T>();
				}
				return new LinkedHashSet<T>(collection);
			case HASH_SET:
			case DEFAULT:
			default:
				if(collection==null){
					return new HashSet<T>();
				}
				return new HashSet<T>(collection);
		}
	}

	@Override
	public <T> Set<T> get(Class<T> T){
		Set<T> ret;
		switch(this){
			case LINKED_HASH_SET:
				ret = new LinkedHashSet<T>();
				break;
			case HASH_SET:
			case DEFAULT:
			default:
				ret = new HashSet<T>();
				break;
		}
		return ret;
	}

	@Override
	public String toString() {
		return this.name();
	}

	@Override
	public boolean isList(){
		return false;
	}

	@Override
	public boolean isSet(){
		return true;
	}

	@Override
	public boolean isQueue(){
		return false;
	}
}
