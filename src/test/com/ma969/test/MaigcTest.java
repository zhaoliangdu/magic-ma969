package com.ma969.test;

import org.springframework.stereotype.Component;

@Component("taskJob")
public class MaigcTest {
	private static int taskCount = 0;

	public void springTask() {
		System.err.println("定时器任务" + (taskCount++) + " 次");
	}
}
