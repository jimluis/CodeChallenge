package com.luisfelipejimenez.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class MessageVO implements Serializable
{

	static final AtomicLong NEXT_ID = new AtomicLong(0);
	private static final long serialVersionUID = 1L;
	protected long timestamp; 
	protected boolean eyeId; 
	protected float confidence; 
	protected float normalizedPosX; 
	protected float normalizedPosY; 
	protected float pupilDiameter;
	protected long messageId = NEXT_ID.getAndIncrement();
	
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public boolean isEyeId() {
		return eyeId;
	}
	public void setEyeId(boolean eyeId) {
		this.eyeId = eyeId;
	}
	public float getConfidence() {
		return confidence;
	}
	public void setConfidence(float confidence) {
		this.confidence = confidence;
	}
	public float getNormalizedPosX() {
		return normalizedPosX;
	}
	public void setNormalizedPosX(float normalizedPosX) {
		this.normalizedPosX = normalizedPosX;
	}
	public float getNormalizedPosY() {
		return normalizedPosY;
	}
	public void setNormalizedPosY(float normalizedPosY) {
		this.normalizedPosY = normalizedPosY;
	}
	public float getPupilDiameter() {
		return pupilDiameter;
	}
	public void setPupilDiameter(float pupilDiameter) {
		this.pupilDiameter = pupilDiameter;
	}
	
	public long getIdSeq() {
		return messageId;
	}

	@Override
	public String toString() {
		return "MessageVO [ messageId=" + messageId + ", timestamp=" + timestamp + ", eyeId=" + eyeId + ", confidence=" + confidence
				+ ", normalizedPosX=" + normalizedPosX + ", normalizedPosY=" + normalizedPosY + ", pupilDiameter="
				+ pupilDiameter + "]";
	}
	
}
