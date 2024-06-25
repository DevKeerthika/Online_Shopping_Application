package com.jsp.osa.utility;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MessageData 
{
	private String to;
	private String subject;
	private Date sentDate;
	private String text;
}
