package org.throwable.ch2;

/**
 * @author zjc
 * @version 2017/1/12 0:00
 * @description
 */
public class MMSCInfo {

	private final String deviceID;
	private final String url;
	private final int maxAttachmentSizeInBytes;

	public MMSCInfo(String deviceID, String url, int maxAttachmentSizeInBytes) {
		this.deviceID = deviceID;
		this.url = url;
		this.maxAttachmentSizeInBytes = maxAttachmentSizeInBytes;
	}

	public String getDeviceID() {
		return deviceID;
	}

	public String getUrl() {
		return url;
	}

	public int getMaxAttachmentSizeInBytes() {
		return maxAttachmentSizeInBytes;
	}
}
