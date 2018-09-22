package ggboy.study.java.test;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import ggboy.java.common.exception.CommonUtilException;
import ggboy.java.common.utils.io.IoUtil;
import ggboy.study.java.springBean.Remote;

@Service("mustryDoTest")
@Remote
public class Test implements Serializable {
	private static final long serialVersionUID = 112364354L;

	private String name = "asd";

	@ResponseBody
	public String getName() {
		return this.name;
	}
}
