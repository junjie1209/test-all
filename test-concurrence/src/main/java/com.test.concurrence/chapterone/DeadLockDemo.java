package com.test.concurrence.chapterone;

/**
 * 死锁
 *
 * @author: Junjie Zhang
 * @date: 2022/1/11
 */
public class DeadLockDemo {

	private static String A = "A";
	private static String B = "B";

	public static void main(String[] args) {
		new DeadLockDemo().deadLock();
	}

	private void deadLock() {
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				synchronized (A) {
					try {
						Thread.currentThread().sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}

				synchronized (B) {

					System.out.println("1");

				}
			}

		});
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				synchronized (B) {

				}
			}

		});
	}

}