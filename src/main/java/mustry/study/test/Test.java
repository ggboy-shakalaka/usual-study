package mustry.study.test;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import mustry.common.exception.CommonUtilException;
import mustry.common.utils.io.IoUtil;
import mustry.study.springBean.Remote;

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
