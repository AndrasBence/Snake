package main;

public enum Speed {

	LOW(350), MID(200), HIGH(100), NULL(0), SUPER(50), AI(20);

	private long timeValue;

	private Speed(long timeValue) {
		this.timeValue = timeValue;
	}

	public long getTimeValue() {
		return this.timeValue;
	}

}
