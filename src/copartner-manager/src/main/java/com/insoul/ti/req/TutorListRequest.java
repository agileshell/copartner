package com.insoul.ti.req;

public class TutorListRequest extends PageRequest {

	@Override
	protected PageRequest Q() {
		StringBuilder sb = new StringBuilder();
		getQuery().setQueryString(sb.toString());
		return this;
	}

}
