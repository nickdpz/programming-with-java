package com.nickdpz.amazonviewer.model;

import java.util.Date;

public interface IDisplayable {
	Date startToSee(Date dateI);
	void stopToSee(Date dateI, Date dateF);
	
}
