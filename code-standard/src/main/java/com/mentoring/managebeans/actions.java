package com.mentoring.managebeans;

/*
 * PMD checks:
 * UnnecessaryModifier: public is not necessary
 */
public interface actions {
	void add();
	void delete(int object);
	String update();
	String view(int object);
	String turnBack();
}
