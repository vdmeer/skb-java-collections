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
import java.util.Comparator;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupString;

import de.vandermeer.skb.base.Skb_Transformer;

/**
 * Transformation methods for collections.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.4-SNAPSHOT build 150618 (18-Jun-15) for Java 1.8
 */
public abstract class CollectionTools {

	/**
	 * Converts a collection into a set and transforms each element of the collection.
	 * @param <T1> type for the left site of the transformation (source)
	 * @param <T2> type for the right site of the transformation (target)
	 * @param <T3> type for the input collection
	 * @param input collection that should be converted
	 * @param transformer object that performs the transformation
	 * @param clazz type of the returned set
	 * @param strategy set strategy
	 * @return an empty set of type clazz or a set of type clazz with transformed objects from the input collection
	 */
	public static final <T1, T2, T3 extends T1> Set<T2> TRANSFORM(final Collection<T3> input, final Skb_Transformer<T1, T2> transformer, Class<T2> clazz, SetStrategy strategy){
		Set<T2> ret = strategy.get(clazz);
		if(input!=null){
			for(T1 t1 : input){
				ret.add(transformer.transform(t1));
			}
		}
		
		return ret;
	}

	/**
	 * Converts a collection into a list and transforms each element of the collection.
	 * @param <T1> type for the left site of the transformation (source)
	 * @param <T2> type for the right site of the transformation (target)
	 * @param <T3> type for the input collection
	 * @param input collection that should be converted
	 * @param transformer object that performs the transformation
	 * @param clazz type of the returned list
	 * @param strategy list strategy
	 * @return an empty list of type clazz or a list of type clazz with transformed objects from the input collection
	 */
	public static final <T1, T2, T3 extends T1> List<T2> TRANSFORM(final Collection<T3> input, final Skb_Transformer<T1, T2> transformer, Class<T2> clazz, ListStrategy strategy){
		List<T2> ret = strategy.get(clazz);
		if(input!=null){
			for(T1 t1 : input){
				ret.add(transformer.transform(t1));
			}
		}
		
		return ret;
	}

	/**
	 * Converts a collection into a deque and transforms each element of the collection.
	 * @param <T1> type for the left site of the transformation (source)
	 * @param <T2> type for the right site of the transformation (target)
	 * @param <T3> type for the input collection
	 * @param input collection that should be converted
	 * @param transformer object that performs the transformation
	 * @param clazz type of the returned deque
	 * @param strategy deque strategy
	 * @return an empty deque of type clazz or a deque of type clazz with transformed objects from the input collection
	 */
	public static final <T1, T2, T3 extends T1> Deque<T2> TRANSFORM(Collection<T3> input, Skb_Transformer<T1, T2> transformer, Class<T2> clazz, DequeStrategy strategy) {
		Deque<T2> ret = strategy.get(clazz);
		if(input!=null){
			for(T1 t1 : input){
				ret.add(transformer.transform(t1));
			}
		}
		
		return ret;
	}

	/**
	 * Converts a collection into a sorted set and transforms each element of the collection.
	 * @param <T1> type for the left site of the transformation (source)
	 * @param <T2> type for the right site of the transformation (target)
	 * @param <T3> type for the input collection
	 * @param input collection that should be converted
	 * @param transformer object that performs the transformation
	 * @param clazz type of the returned sorted set
	 * @param strategy sorted set strategy
	 * @return an empty sorted set of type clazz or a sorted set of type clazz with transformed objects from the input collection
	 */
	public static final <T1, T2 extends Comparable<T2>, T3 extends T1> SortedSet<T2> TRANSFORM(final Collection<T3> input, final Skb_Transformer<T1, T2> transformer, Class<T2> clazz, SortedSetStrategy strategy){
		SortedSet<T2> ret = strategy.get(clazz);
		if(input!=null){
			for(T1 t1 : input){
				ret.add(transformer.transform(t1));
			}
		}
		
		return ret;
	}

	/**
	 * Converts a collection into a sorted set and transforms each element of the collection.
	 * @param <T1> type for the left site of the transformation (source)
	 * @param <T2> type for the right site of the transformation (target)
	 * @param <T3> type for the input collection

	 * @param input collection that should be converted
	 * @param transformer object that performs the transformation
	 * @param clazz type of the returned sorted set
	 * @param strategy sorted set strategy
	 * @param comparator a comparator function to be used by the sorted set
	 * @return an empty collection of type clazz or a collection of type clazz with transformed objects from the input collection
	 */

	public static final <T1, T2, T3 extends T1> SortedSet<T2> TRANSFORM(final Collection<T3> input, final Skb_Transformer<T1, T2> transformer, Class<T2> clazz, SortedSetStrategy strategy, Comparator<T2> comparator){
		SortedSet<T2> ret = strategy.get(clazz, comparator);
		if(input!=null){
			for(T1 t1 : input){
				ret.add(transformer.transform(t1));
			}
		}
		
		return ret;
	}

	/**
	 * Converts a collection into a queue and transforms each element of the collection.
	 * @param <T1> type for the left site of the transformation (source)
	 * @param <T2> type for the right site of the transformation (target)
	 * @param <T3> type for the input collection
	 * @param input collection that should be converted
	 * @param transformer object that performs the transformation
	 * @param clazz type of the returned queue
	 * @param strategy queue strategy
	 * @return an empty queue of type clazz or a queue of type clazz with transformed objects from the input collection
	 */
	public static final <T1, T2, T3 extends T1> Queue<T2> TRANSFORM(Collection<T3> input, Skb_Transformer<T1, T2> transformer, Class<T2> clazz, QueueStrategy strategy) {
		Queue<T2> ret = strategy.get(clazz);
		if(input!=null){
			for(T1 t1 : input){
				ret.add(transformer.transform(t1));
			}
		}
		
		return ret;
	}

	/**
	 * Returns a transformer that takes a collection and transforms it into a textual representation, for instance for debug output.
	 * @return transformer for textual representation of the collection
	 */
	public static final Skb_Transformer<Collection<?>, String> COLLECTION_TO_TEXT(){
		return new Skb_Transformer<Collection<?>, String>(){
			@Override public String transform(Collection<?> coll){
				//String template="    <collection:{n | - <n>}; separator=\"\n\">";		//simple string didn't work in tests??? :(
				//ST ret=new ST(template);

				String collG = "collection(entries) ::= <<\n    <entries:{n | - <n>}; separator=\"\n\">\n>>";
				STGroup stg = new STGroupString(collG);
				ST ret = stg.getInstanceOf("collection");

				if(coll!=null){
					for(Object obj : coll){
						ret.add("entries", obj);
					}
				}
				return ret.render();
			}
		};
	}

	/**
	 * Transforms a collection into a textual representation, for instance for debug output
	 * @param coll input collection
	 * @return textual representation of the collection, empty string as default
	 */
	public final static String COLLECTION_TO_TEXT(Collection<?> coll){
		return CollectionTools.COLLECTION_TO_TEXT().transform(coll);
	}

	/**
	 * Returns a transformer that tranforms a map into a textual representation, for instance for debug output.
	 * @return transformer for a map to text
	 */
	public static final Skb_Transformer<Map<?, ?>, String> MAP_TO_TEXT(){
		return new Skb_Transformer<Map<?, ?>, String>(){
			@Override public String transform(Map<?, ?> map){
				String collG = "map(tree) ::= <<\n    <tree.keys:{k | - <k> ==> [<tree.(k).(\"type\")> <tree.(k).(\"val\")>]}; separator=\"\n\">\n>>";
				STGroup stg = new STGroupString(collG);
				ST ret = stg.getInstanceOf("map");

				LinkedHashMap<String, LinkedHashMap<String, String>> tree = new LinkedHashMap<String, LinkedHashMap<String, String>>();
				@SuppressWarnings("unchecked")
				Set<String> keySet = (Set<String>)map.keySet();
				Iterator<String> keyIt = keySet.iterator();
				while(keyIt.hasNext()){
					LinkedHashMap<String, String> node = new LinkedHashMap<String, String>();
					String key = keyIt.next();

					Object v = map.get(key);
					if(v!=null){
						node.put("type", v.getClass().getSimpleName());//TODO add maybe TypeMap
						String val = v.toString();
						if(val.contains("\n")){
							node.put("val", "\n    "+val);
						}
						else{
							node.put("val", val);
						}
						tree.put(key, node);
					}
				}
				ret.add("tree", tree);
				return ret.render();
			}
		};
	}

	/**
	 * Transforms a map into a textual representation, for instance for debug output
	 * @param map input collection
	 * @return textual representation of the map, empty string as default
	 */
	public final static String MAP_TO_TEXT(Map<?, ?> map){
		return CollectionTools.MAP_TO_TEXT().transform(map);
	}

	/**
	 * Returns the first element of the given collection.
	 * @param coll input collection
	 * @return first element of the collection or null if not applicable
	 */
	public static Object GET_FIRST_ELEMENT(Collection<?> coll){
		Object ret = null;
		if(coll.size()>0){
			Object[] ar = new Object[coll.size()];
			ar = coll.toArray(ar);
			for(int i=0; i<ar.length; i++){
				if((ar[i]!=null)){
					ret = ar[i];
					break;
				}
			}
		}
		return ret;
	}
}
