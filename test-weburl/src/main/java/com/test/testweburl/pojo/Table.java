package com.test.testweburl.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * TODO
 *
 * @author: Junjie Zhang
 * @date: 2021/10/9
 */
@Data
public class Table {
	@JsonProperty("TableID")
	private String TableID;

}
