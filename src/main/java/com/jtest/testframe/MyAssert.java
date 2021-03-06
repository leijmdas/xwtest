package com.jtest.testframe;

class MyComparisonFailure extends MyAssertFailedError {
	private static final int MAX_CONTEXT_LENGTH = 20;
	private static final long serialVersionUID = 1L;

	private final String fExpected;
	private final String fActual;

	/**
	 * Constructs a comparison failure.
	 * 
	 * @param message
	 *            the identifying message or null
	 * @param expected
	 *            the expected string value
	 * @param actual
	 *            the actual string value
	 */
	public MyComparisonFailure(String message, String expected, String actual) {
		super(message);
		fExpected = expected;
		fActual = actual;
	}

	public MyComparisonFailure(String message, int expected, int actual) {
		super(message);
		fExpected = String.valueOf(expected);
		fActual = String.valueOf(actual);
	}

	/**
	 * Returns "..." in place of common prefix and "..." in place of common
	 * suffix between expected and actual.
	 * 
	 * @see Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		// return new ComparisonCompactor(MAX_CONTEXT_LENGTH, fExpected,
		// fActual)
		// .compact(super.getMessage());
		return "";
	}

	/**
	 * Gets the actual string value
	 * 
	 * @return the actual string value
	 */
	public String getActual() {
		return fActual;
	}

	/**
	 * Gets the expected string value
	 * 
	 * @return the expected string value
	 */
	public String getExpected() {
		return fExpected;
	}
}

public class MyAssert {
	protected MyAssert() {

	}

	static public void assertTrue(boolean condition) {
		if (!condition)
			fail(null);
	}

	static public void assertTrue(String message, boolean condition) {
		if (!condition)
			fail(message);
	}

	/**
	 * Asserts that a condition is false. If it isn't it throws an
	 * AssertionFailedError with the given message.
	 */
	static public void assertFalse(String message, boolean condition) {
		assertTrue(message, !condition);
	}

	static public void fail(String message) {
		throw new MyAssertFailedError(message);
	}

	/**
	 * Asserts that two Strings are equal.
	 */
	static public void assertEquals(String message, String expected,
			String actual) {
		if (expected == null && actual == null)
			return;
		if (expected != null && expected.equals(actual))
			return;
		throw new MyComparisonFailure(message, expected, actual);
	}

	static public void assertEquals(int expected, int actual) {
		if (expected == actual)
			return;
		throw new MyComparisonFailure("int not equals", expected, actual);
	}
	// /**
	// * Asserts that a condition is true. If it isn't it throws an
	// * AssertionFailedError with the given message.
	// */
	// static public void assertTrue(String message, boolean condition) {
	// if (!condition)
	// fail(message);
	// }
	// /**
	// * Asserts that a condition is true. If it isn't it throws an
	// * AssertionFailedError.
	// */
	// static public void assertTrue(boolean condition) {
	// assertTrue(null, condition);
	// }

	// /**
	// * Asserts that a condition is false. If it isn't it throws an
	// * AssertionFailedError.
	// */
	// static public void assertFalse(boolean condition) {
	// assertFalse(null, condition);
	// }
	// /**
	// * Fails a groovy with the given message.
	// */
	// static public void fail(String message) {
	// throw new AssertionFailedError(message);
	// }
	// /**
	// * Fails a groovy with no message.
	// */
	// static public void fail() {
	// fail(null);
	// }
	// /**
	// * Asserts that two objects are equal. If they are not an
	// * AssertionFailedError is thrown with the given message.
	// */
	// static public void assertEquals(String message, Object expected,
	// Object actual) {
	// if (expected == null && actual == null)
	// return;
	// if (expected != null && expected.equals(actual))
	// return;
	// failNotEquals(message, expected, actual);
	// }
	// /**
	// * Asserts that two objects are equal. If they are not an
	// * AssertionFailedError is thrown.
	// */
	// static public void assertEquals(Object expected, Object actual) {
	// assertEquals(null, expected, actual);
	// }

	// /**
	// * Asserts that two Strings are equal.
	// */
	// static public void assertEquals(String expected, String actual) {
	// assertEquals(null, expected, actual);
	// }
	// /**
	// * Asserts that two doubles are equal concerning a delta. If they are not
	// an
	// * AssertionFailedError is thrown with the given message. If the expected
	// * value is infinity then the delta value is ignored.
	// */
	// static public void assertEquals(String message, double expected,
	// double actual, double delta) {
	// if (Double.compare(expected, actual) == 0)
	// return;
	// if (!(Math.abs(expected - actual) <= delta))
	// failNotEquals(message, new Double(expected), new Double(actual));
	// }
	// /**
	// * Asserts that two doubles are equal concerning a delta. If the expected
	// * value is infinity then the delta value is ignored.
	// */
	// static public void assertEquals(double expected, double actual, double
	// delta) {
	// assertEquals(null, expected, actual, delta);
	// }
	// /**
	// * Asserts that two floats are equal concerning a delta. If they are not
	// an
	// * AssertionFailedError is thrown with the given message. If the expected
	// * value is infinity then the delta value is ignored.
	// */
	// static public void assertEquals(String message, float expected,
	// float actual, float delta) {
	// // handle infinity specially since subtracting to infinite values gives
	// // NaN and the
	// // the following groovy fails
	// if (Float.isInfinite(expected)) {
	// if (!(expected == actual))
	// failNotEquals(message, new Float(expected), new Float(actual));
	// } else if (!(Math.abs(expected - actual) <= delta))
	// failNotEquals(message, new Float(expected), new Float(actual));
	// }
	// /**
	// * Asserts that two floats are equal concerning a delta. If the expected
	// * value is infinity then the delta value is ignored.
	// */
	// static public void assertEquals(float expected, float actual, float
	// delta) {
	// assertEquals(null, expected, actual, delta);
	// }
	// /**
	// * Asserts that two longs are equal. If they are not an
	// AssertionFailedError
	// * is thrown with the given message.
	// */
	// static public void assertEquals(String message, long expected, long
	// actual) {
	// assertEquals(message, new Long(expected), new Long(actual));
	// }
	// /**
	// * Asserts that two longs are equal.
	// */
	// static public void assertEquals(long expected, long actual) {
	// assertEquals(null, expected, actual);
	// }
	// /**
	// * Asserts that two booleans are equal. If they are not an
	// * AssertionFailedError is thrown with the given message.
	// */
	// static public void assertEquals(String message, boolean expected,
	// boolean actual) {
	// assertEquals(message, Boolean.valueOf(expected), Boolean
	// .valueOf(actual));
	// }
	// /**
	// * Asserts that two booleans are equal.
	// */
	// static public void assertEquals(boolean expected, boolean actual) {
	// assertEquals(null, expected, actual);
	// }
	// /**
	// * Asserts that two bytes are equal. If they are not an
	// AssertionFailedError
	// * is thrown with the given message.
	// */
	// static public void assertEquals(String message, byte expected, byte
	// actual) {
	// assertEquals(message, new Byte(expected), new Byte(actual));
	// }
	// /**
	// * Asserts that two bytes are equal.
	// */
	// static public void assertEquals(byte expected, byte actual) {
	// assertEquals(null, expected, actual);
	// }
	// /**
	// * Asserts that two chars are equal. If they are not an
	// AssertionFailedError
	// * is thrown with the given message.
	// */
	// static public void assertEquals(String message, char expected, char
	// actual) {
	// assertEquals(message, new Character(expected), new Character(actual));
	// }
	// /**
	// * Asserts that two chars are equal.
	// */
	// static public void assertEquals(char expected, char actual) {
	// assertEquals(null, expected, actual);
	// }
	// /**
	// * Asserts that two shorts are equal. If they are not an
	// * AssertionFailedError is thrown with the given message.
	// */
	// static public void assertEquals(String message, short expected, short
	// actual) {
	// assertEquals(message, new Short(expected), new Short(actual));
	// }
	// /**
	// * Asserts that two shorts are equal.
	// */
	// static public void assertEquals(short expected, short actual) {
	// assertEquals(null, expected, actual);
	// }
	// /**
	// * Asserts that two ints are equal. If they are not an
	// AssertionFailedError
	// * is thrown with the given message.
	// */
	// static public void assertEquals(String message, int expected, int actual)
	// {
	// assertEquals(message, new Integer(expected), new Integer(actual));
	// }
	// /**
	// * Asserts that two ints are equal.
	// */
	// static public void assertEquals(int expected, int actual) {
	// assertEquals(null, expected, actual);
	// }
	// /**
	// * Asserts that an object isn't null.
	// */
	// static public void assertNotNull(Object object) {
	// assertNotNull(null, object);
	// }
	// /**
	// * Asserts that an object isn't null. If it is an AssertionFailedError is
	// * thrown with the given message.
	// */
	// static public void assertNotNull(String message, Object object) {
	// assertTrue(message, object != null);
	// }
	// /**
	// * Asserts that an object is null.
	// */
	// static public void assertNull(Object object) {
	// assertNull(null, object);
	// }
	// /**
	// * Asserts that an object is null. If it is not an AssertionFailedError is
	// * thrown with the given message.
	// */
	// static public void assertNull(String message, Object object) {
	// assertTrue(message, object == null);
	// }
	// /**
	// * Asserts that two objects refer to the same object. If they are not an
	// * AssertionFailedError is thrown with the given message.
	// */
	// static public void assertSame(String message, Object expected, Object
	// actual) {
	// if (expected == actual)
	// return;
	// failNotSame(message, expected, actual);
	// }
	// /**
	// * Asserts that two objects refer to the same object. If they are not the
	// * same an AssertionFailedError is thrown.
	// */
	// static public void assertSame(Object expected, Object actual) {
	// assertSame(null, expected, actual);
	// }
	// /**
	// * Asserts that two objects do not refer to the same object. If they do
	// * refer to the same object an AssertionFailedError is thrown with the
	// given
	// * message.
	// */
	// static public void assertNotSame(String message, Object expected,
	// Object actual) {
	// if (expected == actual)
	// failSame(message);
	// }
	// /**
	// * Asserts that two objects do not refer to the same object. If they do
	// * refer to the same object an AssertionFailedError is thrown.
	// */
	// static public void assertNotSame(Object expected, Object actual) {
	// assertNotSame(null, expected, actual);
	// }
	//
	// static public void failSame(String message) {
	// String formatted = "";
	// if (message != null)
	// formatted = message + " ";
	// fail(formatted + "expected not same");
	// }
	//
	// static public void failNotSame(String message, Object expected,
	// Object actual) {
	// String formatted = "";
	// if (message != null)
	// formatted = message + " ";
	// fail(formatted + "expected same:<" + expected + "> was not:<" + actual
	// + ">");
	// }
	//
	// static public void failNotEquals(String message, Object expected,
	// Object actual) {
	// fail(format(message, expected, actual));
	// }
	//
	// static String format(String message, Object expected, Object actual) {
	// String formatted = "";
	// if (message != null)
	// formatted = message + " ";
	// return formatted + "expected:<" + expected + "> but was:<" + actual
	// + ">";
	// }

}
