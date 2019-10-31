/**
 * 
 */
package test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.qigan.qiganshop.service.UserCouponService;
import com.qigan.qiganshop.utils.json.JsonUtils;

/**
 * @author wyy
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/*.xml"})
@WebAppConfiguration
public class T2 {
	
	@Autowired
	UserCouponService service;
	
	@org.junit.Test
	public void testIsShowCoupon(){
		System.err.println(JsonUtils.writeValueAsString(service.isShowReceive("to_2326c238354d4d208187dac1cf0dd789")));
	}
}
