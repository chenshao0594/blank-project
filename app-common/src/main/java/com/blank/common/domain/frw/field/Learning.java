package com.blank.common.domain.frw.field;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.Test;

public class Learning {
	class TestMap {
		ConcurrentHashMap<String, AtomicLong> map = new ConcurrentHashMap<String, AtomicLong>();

		public long get(String key) {
			map.putIfAbsent(key, new AtomicLong(0));
			return map.get(key).getAndIncrement();
		}

	}

	class LRU extends LinkedHashMap<String, Object> {
		private final int size;

		public LRU(int size) {
			super(size, (float) 0.75, true);
			this.size = size;
		}

		@Override
		protected boolean removeEldestEntry(Map.Entry<String, Object> eldest) {
			return this.size() > size;

		}

	}

	@Test
	public void test() throws InterruptedException {

		LRU lr = new LRU(7);
		for (int i = 0; i < 20; i++) {
			lr.put(Integer.toString(i), i);
			lr.get(Integer.toString(i % 5));
		}
		System.out.println(lr);
		// TestMap map = new TestMap();
		//
		// ExecutorService excutorService = Executors.newFixedThreadPool(50);
		//
		// for (int i = 0; i < 500; i++) {
		// excutorService.submit(new Task(map, "A"));
		// excutorService.submit(new Task(map, "B"));
		// // Thread.sleep(10);
		// }
		// excutorService.shutdown();
		// Thread.sleep(100000);

	}

	class Task implements Runnable {
		private TestMap map;
		private String key;

		public Task(TestMap map, String key) {
			this.map = map;
			this.key = key;
		}

		@Override
		public void run() {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(key + " --- " + map.get(this.key));

		}
	}

}
