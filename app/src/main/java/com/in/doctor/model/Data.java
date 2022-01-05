package com.in.doctor.model;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("Channel Name")
	private String channelName;

	@SerializedName("user_id")
	private String userId;

	public void setChannelName(String channelName){
		this.channelName = channelName;
	}

	public String getChannelName(){
		return channelName;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"channel Name = '" + channelName + '\'' + 
			",user_id = '" + userId + '\'' + 
			"}";
		}
}