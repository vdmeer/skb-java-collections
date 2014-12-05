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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

/**
 * A filter interface with predefined methods.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.2 build 140626 (26-Jun-14) with Java 1.8
 */
public interface CollectionFilters<T> {

	/**
	 * Filter that uses a predicate as filter method.
	 * @param predicate filter method
	 * @param coll input collection to filter
	 * @return a collection with elements that pass the filter
	 */
	default Collection<T> filter(Predicate<T> predicate, Collection<T> coll){
		Collection<T> ret = new ArrayList<T>();
		if(coll!=null && predicate!=null){
			for(T t : coll){
				if(predicate.test(t)){
					ret.add(t);
				}
			}
		}
		return ret;
	}

	/**
	 * Generic filter that uses a predicate as filter method.
	 * @param predicate filter method
	 * @param array input array to filter
	 * @return a collection with elements that pass the filter
	 */
	default Collection<T> filter(Predicate<T> predicate, T[] array){
		if(array!=null){
			return filter(predicate, Arrays.asList(array));
		}
		else{
			return new ArrayList<T>();
		}
	}

	/**
	 * Generic filter that uses a predicate as filter method.
	 * @param predicate filter method
	 * @param coll array of collections to filter
	 * @return a collection with elements that pass the filter
	 */
	default Collection<T> filter(Predicate<T> predicate, @SuppressWarnings("unchecked") Collection<T> ... coll){
		if(coll==null){
			return new ArrayList<T>(); 
		}
		Collection<T> ret = filter(predicate, coll[0]);
		if(coll.length>1){
			for(int i=1; i<coll.length; i++){
				ret.addAll(filter(predicate, coll[1]));
			}
		}
		return ret;
	}

	/**
	 * Generic filter that uses a predicate as filter method.
	 * @param predicate filter method
	 * @param coll array of arrays to filter
	 * @return a collection with elements that pass the filter
	 */
	default Collection<T> filter(Predicate<T> predicate, @SuppressWarnings("unchecked") T[] ... coll){
		if(coll==null){
			return new ArrayList<T>(); 
		}
		Collection<T> ret = filter(predicate, coll[0]);
		if(coll.length>1){
			for(int i=1; i<coll.length; i++){
				ret.addAll(filter(predicate, coll[1]));
			}
		}
		return ret;
	}

	/**
	 * Generic filter that uses a predicate as filter method returning a specific list.
	 * @param predicate filter method
	 * @param coll a collection with elements that pass the filter
	 * @param strategy list strategy for the returned list
	 * @return a list with elements that pass the filter
	 */
	default List<T> filter(Predicate<T> predicate, Collection<T> coll, ListStrategy strategy){
		return strategy.get(filter(predicate, coll));
	}
}
